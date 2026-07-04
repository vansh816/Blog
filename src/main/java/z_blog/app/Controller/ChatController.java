package z_blog.app.Controller;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/chat")
public class ChatController {

    private final ChatClient chatClient;
    private final VectorStore vectorStore;

    // single chat memory for all requests
    private final List<String> chatMemory = new ArrayList<>();

    public ChatController(ChatClient.Builder chatClientBuilder, VectorStore vectorStore) {
        this.chatClient = chatClientBuilder.build();
        this.vectorStore = vectorStore;
    }

    @PostMapping
    public String ragChat(@RequestBody Map<String, String> request) {

        String message = request.get("message");

        if (message == null || message.isBlank()) {
            return "Message cannot be empty.";
        }

        // 1) RAG retrieval
        List<Document> documents = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(message)
                        .topK(4)
                        .build()
        );

        String context = "";
        if (documents != null && !documents.isEmpty()) {
            context = documents.stream()
                    .map(Document::getText)
                    .collect(Collectors.joining("\n\n"));
        }

        if (context.isBlank()) {
            return "No relevant blog content found.";
        }

        // 2) Previous chat history
        String historyText = String.join("\n", chatMemory);

        // 3) Prompt
        String prompt = """
                You are an AI assistant for a blog application.

                Rules:
                1. Answer using the retrieved blog context.
                2. Use conversation history only to understand follow-up references like "it", "that", "same topic", etc.
                3. If the answer is not present in the retrieved context, say it was not found in the retrieved blog content.
                4. Do not mention technical details like vector store, embeddings, metadata, or retrieval unless asked.

                Conversation History:
                %s

                Retrieved Blog Context:
                %s

                User Question:
                %s
                """.formatted(historyText, context, message);

        // 4) LLM response
        String response = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        // 5) Save memory
        chatMemory.add("User: " + message);
        chatMemory.add("Assistant: " + response);

        // keep only last 20 lines
        int maxLines = 20;
        if (chatMemory.size() > maxLines) {
            chatMemory.subList(0, chatMemory.size() - maxLines).clear();
        }

        return response;
    }
}