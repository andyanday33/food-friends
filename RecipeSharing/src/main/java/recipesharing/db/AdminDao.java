package recipesharing.db;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import recipesharing.logic.Admin;

import java.util.List;

/**
 *
 */
@Repository
public class AdminDao {

    @Autowired
    MongoTemplate mongoTemplate;
    public List<Admin> findAllAdmins(){
        return mongoTemplate.findAll(Admin.class);
    }
    public Admin findAdminById(String adminId) {
        return mongoTemplate.findById(adminId, Admin.class);
    }

    public List<Admin> findAdminByAdminName(String adminName) {
        Query query = Query.query(Criteria.where("adminName").is(adminName));
        return mongoTemplate.find(query, Admin.class, "t_admin");

    }

    public Admin findAdminByEmail(String email) {
        Query query = Query.query(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, Admin.class);
    }

    public void addAdmin(Admin admin) {
        mongoTemplate.save(admin, "t_admin");
    }

    public void deleteAdminById(String adminId) {
        Query query = Query.query(Criteria.where("_id").is(adminId));
        mongoTemplate.remove(query, Admin.class);
    }

    public void updateAdminById(Admin admin) {
        Query query = Query.query(Criteria.where("_id").is(admin.getEmail()));
        Update update = new Update();
        UpdateResult upsert = mongoTemplate.upsert(query, update, Admin.class);
    }

}
