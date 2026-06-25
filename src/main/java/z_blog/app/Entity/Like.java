package z_blog.app.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Like")
@Data
public class Like {

    @Id
    private ObjectId id;
    private ObjectId blogId;

    private ObjectId visitorId;

   // private String visitorType; // "USER" or "author"
  //  private LocalDateTime createdAt;

}
