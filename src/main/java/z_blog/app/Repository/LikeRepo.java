package z_blog.app.Repository;
import z_blog.app.Entity.Like;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface LikeRepo extends MongoRepository<Like, ObjectId> {

    long countByBlogId(ObjectId blogId);

    boolean existsByBlogIdAndVisitorId(ObjectId blogId, ObjectId visitorId);
    
}