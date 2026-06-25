package z_blog.app.Controller;
import z_blog.app.Service.LikeService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class LikeController {

    @Autowired
    private LikeService likeService;

    @PostMapping("/{blogId}/like/{visitorId}")// Like Blog
    public String likeBlog(@PathVariable ObjectId blogId, @PathVariable ObjectId visitorId) {
        return likeService.addLike(blogId, visitorId);
    }

    @GetMapping("/{blogId}/likes/count")// Get Like Count
    public long getLikeCount(@PathVariable ObjectId blogId) {
        return likeService.getLikeCount(blogId);
    }
}