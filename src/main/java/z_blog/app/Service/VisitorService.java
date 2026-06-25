package z_blog.app.Service;

import z_blog.app.Entity.Visitor;
import z_blog.app.Repository.VisitorRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitorService {

    @Autowired
    private VisitorRepo visitorRepo;

    public Visitor saveVisitor(Visitor visitor){
        return visitorRepo.save(visitor);
    }
    public List<Visitor> getAll(){
        return visitorRepo.findAll();
    }
    public Optional<Visitor> getVisitorById(ObjectId myid){
        return visitorRepo.findById(myid);
    }
    public void deleteVisitorById(ObjectId myid){
        visitorRepo.deleteById(myid);
    }



}
