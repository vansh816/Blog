package z_blog.app.Entity;
import lombok.Data;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "Blog")
public class Blog {
    @Id
    private ObjectId id;

    public int like=0;

    List<Comment> list=new ArrayList<>();

    private String content;

    private ObjectId visitorId;//only author

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public ObjectId getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(ObjectId visitorId) {
        this.visitorId = visitorId;
    }

    public List<Comment> getList() {
        return list;
    }

    public void setList(List<Comment> list) {
        this.list = list;
    }
}
