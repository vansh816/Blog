package z_blog.app.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Visitor")
@Data
public class Visitor {
    @Id
    private ObjectId id;
    private String email;
    private String password;
    private String roles;

}
