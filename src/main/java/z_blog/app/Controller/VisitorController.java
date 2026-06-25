package z_blog.app.Controller;

import z_blog.app.Entity.Blog;
import z_blog.app.Entity.Visitor;
import z_blog.app.Repository.BlogRepo;
import z_blog.app.Service.VisitorService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping
public class VisitorController {

    @Autowired
    private VisitorService visitorService;
    @Autowired
    private BlogRepo blogRepo;

    @PostMapping("/visitor")//admin,user,author
    public ResponseEntity<?> createVisitor(@RequestBody Visitor visitor){
        visitorService.saveVisitor(visitor);
        return new ResponseEntity<>(visitor,HttpStatus.OK);
    }

    @GetMapping("/getallvisitor")//Admin ke liye
    public ResponseEntity<?> getAllVisitor(){
        List<Visitor> all=visitorService.getAll();
        if(!all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getvisitor/{myid}")
    public ResponseEntity<?> getVisitor(@PathVariable ObjectId myid) {
        Optional<Visitor> visitor = visitorService.getVisitorById(myid);
        if (visitor.isPresent()) {
            List<Blog> blogs = blogRepo.findByVisitorId(myid);
            Map<String, Object> response = new HashMap<>();
            response.put("visitor", visitor.get());
            response.put("blogs", blogs);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/visitor/{myid}")//Admin ke liye
    public ResponseEntity<?> deleteVisitor(@PathVariable ObjectId myid){
        Optional<Visitor> visitor=visitorService.getVisitorById(myid);
        if(!visitor.isEmpty()){
            visitorService.deleteVisitorById(myid);
            return new ResponseEntity<>(visitor, HttpStatus.OK);
        }
        else return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }}
