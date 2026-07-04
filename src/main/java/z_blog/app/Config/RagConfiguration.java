package z_blog.app.Config;

import z_blog.app.Entity.Blog;
import z_blog.app.Entity.Visitor;
import org.springframework.ai.document.Document;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import z_blog.app.Repository.BlogRepo;
import z_blog.app.Repository.VisitorRepo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
public class RagConfiguration {

    @Bean
    CommandLineRunner loadBlogDataToVectorStore(VectorStore vectorStore,
                                                BlogRepo blogRepository,
                                                VisitorRepo visitorRepository) {
        return args -> {

            List<Blog> blogs = blogRepository.findAll();
            List<Visitor> visitors = visitorRepository.findAll();

            Map<String, Visitor> visitorMap = visitors.stream()
                    .filter(v -> v.getId() != null)
                    .collect(Collectors.toMap(v -> v.getId().toHexString(), v -> v));

            if (blogs.isEmpty()) {
                System.out.println("No blogs found. Nothing to load into vector store.");
                return;
            }

            List<Document> documents = blogs.stream()
                    .map(blog -> {
                        String blogId = blog.getId() != null ? blog.getId().toHexString() : "null";
                        String authorId = blog.getVisitorId() != null ? blog.getVisitorId().toHexString() : null;
                        String authorEmail = "unknown";

                        if (authorId != null && visitorMap.containsKey(authorId)) {
                            authorEmail = visitorMap.get(authorId).getEmail();
                        }

                        String content = blog.getContent() != null ? blog.getContent() : "";

                        String text = """
                                Blog ID: %s
                                Author Email: %s
                                Blog Content: %s
                                Like Count Field: %d
                                """.formatted(
                                blogId,
                                authorEmail,
                                content,
                                blog.getLike()
                        );

                        Document doc = new Document(content);

                        doc.getMetadata().put("blogId", blogId);
                        doc.getMetadata().put("type", "blog");
                        return doc;
                    })
                    .toList();

            TokenTextSplitter splitter = new TokenTextSplitter();
            List<Document> splitDocuments = splitter.apply(documents);

            vectorStore.add(splitDocuments);

            System.out.println("Loaded " + splitDocuments.size() + " blog chunks into vector store.");
        };
    }
}