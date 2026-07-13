package z_blog.app.Repository;

import z_blog.app.Entity.Blog;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BlogRepo extends MongoRepository<Blog, ObjectId> {
    List<Blog> findByVisitorId(ObjectId authorId);
    Blog deleteBlogByVisitorId(ObjectId authorId);
}