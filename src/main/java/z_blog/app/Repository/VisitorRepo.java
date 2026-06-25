package z_blog.app.Repository;

import z_blog.app.Entity.Visitor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitorRepo extends MongoRepository<Visitor, ObjectId> {
}
