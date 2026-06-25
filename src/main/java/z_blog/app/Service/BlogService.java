package z_blog.app.Service;
import z_blog.app.Entity.Blog;
import z_blog.app.Repository.BlogRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {

    @Autowired
    private BlogRepo blogRepo;

    public List<Blog> getAll(){
        return blogRepo.findAll();
    }
    public Blog saveBlog(Blog blog){
        return blogRepo.save(blog);
    }
    public Optional<Blog> getBlogById(ObjectId myid){
        return blogRepo.findById(myid);
    }
    public void deleteBlogById(ObjectId myid){
         blogRepo.deleteById(myid);
    }

}
