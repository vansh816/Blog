package z_blog.app.Repository;

import z_blog.app.Entity.Comment;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends MongoRepository<Comment, ObjectId> {
    List<Comment> findByBlogId(ObjectId blogId);
}
