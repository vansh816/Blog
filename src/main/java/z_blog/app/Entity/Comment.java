package z_blog.app.Entity;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Comment")
@Data
public class Comment {
    @Id
    private ObjectId id;

    private ObjectId blogId;

    private ObjectId visitorId;//user,author


    private String content;
}
