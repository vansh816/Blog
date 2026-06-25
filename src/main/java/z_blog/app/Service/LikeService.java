package z_blog.app.Service;

import z_blog.app.Entity.Blog;
import z_blog.app.Entity.Like;
import z_blog.app.Repository.BlogRepo;
import z_blog.app.Repository.LikeRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    @Autowired
    private LikeRepo likeRepo;

        @Autowired
        private BlogRepo blogRepo;

        public String addLike(ObjectId blogId, ObjectId visitorId) {
            boolean alreadyLiked = likeRepo.existsByBlogIdAndVisitorId(blogId, visitorId);
            if (alreadyLiked) {
                return "Already Liked";
            }
            // Save like entry
            Like like = new Like();
            like.setBlogId(blogId);
            like.setVisitorId(visitorId);
           // like.setCreatedAt(LocalDateTime.now());
            likeRepo.save(like);
            // Increase blog like count
            Blog blog = blogRepo.findById(blogId).orElseThrow(() -> new RuntimeException("Blog Not Found"));
            blog.setLike(blog.getLike() + 1);
            blogRepo.save(blog);
            return "Blog Liked Successfully";
        }

    // Get Like Count
    public long getLikeCount(ObjectId blogId) {
        return likeRepo.countByBlogId(blogId);
    }
}