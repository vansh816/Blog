package z_blog.app.Entity;

import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "Like")

public class Like {

    @Id
    private ObjectId id;
    private ObjectId blogId;

    private ObjectId visitorId;

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public ObjectId getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(ObjectId visitorId) {
        this.visitorId = visitorId;
    }

    public ObjectId getBlogId() {
        return blogId;
    }

    public void setBlogId(ObjectId blogId) {
        this.blogId = blogId;
    }
}
