package z_blog.app.Service;
import z_blog.app.Entity.Blog;
import z_blog.app.Entity.Comment;
import z_blog.app.Repository.BlogRepo;
import z_blog.app.Repository.CommentRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentService {

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private BlogRepo blogRepo;

    public Comment addComment(Comment comment) {
        Comment savedComment = commentRepo.save(comment);
        Optional<Blog> blogOptional = blogRepo.findById(comment.getBlogId());
        if (blogOptional.isPresent()) {
            Blog blog = blogOptional.get();
            if (blog.getList() == null) {
                blog.setList(new ArrayList<>());
            }
            blog.getList().add(savedComment);
            blogRepo.save(blog);
        }
        return savedComment;
    }

    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }

    public List<Comment> getCommentsByBlogId(ObjectId blogId) {
        return commentRepo.findByBlogId(blogId);
    }

    public String deleteComment(ObjectId id) {

        Optional<Comment> commentOptional = commentRepo.findById(id);

        if (commentOptional.isEmpty()) {
            return "Comment Not Found";
        }

        Comment comment = commentOptional.get();

        blogRepo.findById(comment.getBlogId()).ifPresent(blog -> {
            blog.getList().removeIf(c -> c.getId().equals(id));
            blogRepo.save(blog);
        });

        commentRepo.deleteById(id);

        return "Comment Deleted Successfully";
    }}