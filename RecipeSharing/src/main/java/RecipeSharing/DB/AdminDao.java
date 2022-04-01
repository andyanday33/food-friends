package RecipeSharing.DB;

import RecipeSharing.logic.Admin;
import RecipeSharing.logic.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data Access Object for Admin
 */
@Repository
public class AdminDao {
/*    @Autowired
    MongoTemplate mongoTemplate;*/
    private MongoTemplate mongoTemplate;

    @Autowired
    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }
    public List<Admin> findAllAdmin(){
        System.out.println(mongoTemplate.findAll(Admin.class));
        return mongoTemplate.findAll(Admin.class);
    }

    public void addOneAdmin(Admin admin){
        mongoTemplate.save(admin, "Admin");
    }


}
