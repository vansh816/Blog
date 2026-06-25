package z_blog.app.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import z_blog.app.Entity.Comment;
import z_blog.app.Service.CommentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

    @RestController
    @RequestMapping
    public class CommentController {

        @Autowired
        private CommentService commentService;

        @PostMapping("/{visitorId}/{blogId}")
        public ResponseEntity<?> addComment(@PathVariable ObjectId visitorId, @PathVariable ObjectId blogId,
                @RequestBody Comment comment) {
            comment.setVisitorId(visitorId);
            comment.setBlogId(blogId);
            return new ResponseEntity<>(commentService.addComment(comment), HttpStatus.CREATED);
        }

        @GetMapping
        public ResponseEntity<?> getAllComments() {
            List<Comment> comments = commentService.getAllComments();
            if (comments.isEmpty()) {
                return new ResponseEntity<>("No Comments Found", HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }

        @GetMapping("/blog/{blogId}")
        public ResponseEntity<?> getCommentsByBlogId(@PathVariable ObjectId blogId) {
            List<Comment> comments = commentService.getCommentsByBlogId(blogId);
            if (comments.isEmpty()) {
                return new ResponseEntity<>("No Comments Found",
                        HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(comments, HttpStatus.OK);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<?> deleteComment(@PathVariable ObjectId id) {
            return new ResponseEntity<>(
                    commentService.deleteComment(id),
                    HttpStatus.OK);
        }
    }