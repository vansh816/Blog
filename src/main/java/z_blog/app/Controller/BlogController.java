package z_blog.app.Controller;
import z_blog.app.Entity.Blog;
import z_blog.app.Entity.Visitor;
import z_blog.app.Repository.BlogRepo;
import z_blog.app.Service.BlogService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import z_blog.app.Service.VisitorService;
import java.util.List;
import java.util.Optional;

    @RestController
    @RequestMapping
    public class BlogController {
        @Autowired
        private BlogService blogService;
        @Autowired
        private VisitorService visitorService;
        @Autowired
        private BlogRepo blogRepo;

    @PostMapping("/create/{visitorId}") // Author ke liye
    public ResponseEntity<?> createBlog(@RequestBody Blog blog, @PathVariable ObjectId visitorId) {
        Optional<Visitor> old = visitorService.getVisitorById(visitorId);
        if (old.isPresent()) {
            Visitor visitor = old.get();
            if (visitor.getRoles().equalsIgnoreCase("author")) {
                blog.setVisitorId(visitorId);
                blogService.saveBlog(blog);
                return new ResponseEntity<>(blog, HttpStatus.OK);
            }
            return new ResponseEntity<>("Only AUTHOR can create blog", HttpStatus.FORBIDDEN);
        }
        return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
    }
        @GetMapping("/getall")//Admin ke liye
        public ResponseEntity<?> getAllBlogs(){
            List<Blog> all=blogService.getAll();
            if(!all.isEmpty()){
                return new ResponseEntity<>(all, HttpStatus.OK);
            }
            else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        @GetMapping("/author/{authorId}")//Specific Author ke blog
        public ResponseEntity<?> getBlog(@PathVariable ObjectId authorId){
            Optional<Visitor> visitor=visitorService.getVisitorById(authorId);
            if(!visitor.isEmpty()){
                List<Blog> blogs = blogRepo.findByVisitorId(authorId);
                return new ResponseEntity<>(blogs, HttpStatus.OK);
            }
            else return new ResponseEntity<>("No author found",HttpStatus.NO_CONTENT);
        }
        @DeleteMapping("/blog/{myid}")//Specific Author ke blog by blog id
        public ResponseEntity<?> deleteBlogByBlogId(@PathVariable ObjectId myid){
            Optional<Blog> blog=blogService.getBlogById(myid);
            if(!blog.isEmpty()){
                blogService.deleteBlogById(myid);
                return new ResponseEntity<>(blog, HttpStatus.OK);
            }
            else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        @DeleteMapping("/delete/{authorId}")//Specific Author ke blog by authoridid
        public ResponseEntity<?> deleteBlogByAuthorId(@PathVariable ObjectId authorId){
            Optional<Visitor> visitor=visitorService.getVisitorById(authorId);
            if(!visitor.isEmpty()){
                blogRepo.deleteBlogByVisitorId(authorId);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }


}
