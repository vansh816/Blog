package z_blog.app.Entity;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Blog")
@Data
public class Blog {
    @Id
    private ObjectId id;

    public int like=0;

    List<Comment> list=new ArrayList<>();

    private String Content;

    private ObjectId visitorId;//only author

}
