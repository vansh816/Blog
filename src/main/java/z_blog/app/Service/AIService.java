package z_blog.app.Service;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Service
public class AIService{


    private final ChatClient chatClient;

    public AIService(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    public String summarize(String content) {
        return chatClient.prompt()
                .system("You are an expert blog summarizer. Return only 6 concise bullet points.")
                .user(content)
                .call()
                .content();
    }
    public String Title(String content) {
        return chatClient.prompt()
                .system("You are an expert blog title generator. " +
                        "Generate a single, catchy, SEO-friendly blog title based on the user's topic." +
                        " Rules:- " +
                        "-Return only the title. " +
                        "- Clearly reflect the topic."+
                        "- Keep it under 3-4 words. " +
                        "- Make it clear and engaging. " +
                        "- Do not use quotes, numbering, or extra explanations.")
                .user(content)
                .call()
                .content();
    }
    public String niceGrammar(String content) {
        return chatClient.prompt()
                .system("Correct any grammatical, spelling, and punctuation errors while preserving " +
                        "the original meaning and tone of the text.")
                .user(content)
                .call()
                .content();
    }
    public String translate(String content,String targetLanguage) {
        return chatClient.prompt()
                .system("""
                    Translate the following text.
                    Requirements:
                    - Preserve the original meaning, tone, and intent.
                    - Use natural and fluent language.
                    - Do not add, remove, or interpret information.
                    - Return only the translated text.
                    """)
                .user("Target Language: " + targetLanguage +
                        "\n\nText:\n" + content)
                .call()
                .content();
    }
    public String scoreGenerate(String content) {
        return chatClient.prompt()
                .system("""
                        You are an expert blog reviewer.
                        Analyze the provided blog content and evaluate it on:
                       - Content Quality
                       - Clarity and Readability
                       - Grammar and Language
                       - Structure and Organization
                       - Engagement
                       - Relevance to Topic
                       Provide:
                       1. Overall Score (1-10)
                       2. Strengths (3-5 bullet points)
                       3. Weaknesses (3-5 bullet points)
                       4. Suggestions for Improvement (3-5 bullet points)
                      """)
                .user(content)
                .call()
                .content();
    }

}