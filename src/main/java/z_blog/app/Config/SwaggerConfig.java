package z_blog.app.Config;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI myCustomConfig() {
        return new OpenAPI().info(
                new Info().title("Blog Backened")
                        .description("Built a secure AI-Powered Blog Backend System" +
                                "using Spring Boot with JWT authentication, where Authors ans Users can create " +
                                "blogs with proper authorization and MongoDB integration.  " +
                                "by VANSH SHARMA")
        );
    }
}