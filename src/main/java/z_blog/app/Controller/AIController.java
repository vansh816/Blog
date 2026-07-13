package z_blog.app.Controller;

import org.bson.types.ObjectId;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import z_blog.app.Entity.Blog;
import z_blog.app.Service.AIService;
import z_blog.app.Service.BlogService;


@RestController
@RequestMapping
public class AIController {
    @Autowired
    private AIService aiService;

    @Autowired
    private BlogService blogService;


    @PostMapping("/blogs/{blogId}/summary")
    public String summarizeBlog(@PathVariable ObjectId blogId) {
        Blog blog = blogService.getBlogById(blogId).orElse(null);
        if(blog!=null){
            System.out.println("Blog Found");
        return aiService.summarize(blog.getContent());
    }
        else {
            return "No Blog Found";
        }}
    @PostMapping("/blogs/{blogId}/title")
    public String MakeTitle(@PathVariable ObjectId blogId) {
        Blog blog = blogService.getBlogById(blogId).orElse(null);
        if(blog!=null){
            System.out.println("Blog Found");
            return aiService.Title(blog.getContent());
        }
        else {
            return "No Blog Found";
        }}
    @PostMapping("/blogs/{blogId}/grammar")
    public String grammar(@PathVariable ObjectId blogId) {
        Blog blog = blogService.getBlogById(blogId).orElse(null);
        if(blog!=null){
            System.out.println("Blog Found");
            return aiService.niceGrammar(blog.getContent());
        }
        else {
            return "No Blog Found";
        }}
    @PostMapping("/blogs/{blogId}/translate")
    public String language(@PathVariable ObjectId blogId,@RequestBody String translate){
        Blog blog = blogService.getBlogById(blogId).orElse(null);
        if(blog!=null){
            System.out.println("Blog Found");
            return aiService.translate(blog.getContent(),translate);
        }
        else {
            return "No Blog Found";
        }}
    @PostMapping("/blogs/{blogId}/score")
    public String score(@PathVariable ObjectId blogId){
        Blog blog = blogService.getBlogById(blogId).orElse(null);
        if(blog!=null){
            System.out.println("Blog Found");
            return aiService.scoreGenerate(blog.getContent());
        }
        else {
            return "No Blog Found";
        }}
    @GetMapping("/kkk")
    public String kkk(){
        return "hello";
    }
}