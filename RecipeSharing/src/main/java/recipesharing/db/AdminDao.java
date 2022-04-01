package recipesharing.db;

import recipesharing.logic.Admin;
import recipesharing.logic.Admin;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data Access Object for Admin
 */
@Repository
public class AdminDao {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<Admin> findAllAdmins() {
        System.out.println(mongoTemplate.findAll(Admin.class));
        return mongoTemplate.findAll(Admin.class);
    }

    public Admin findOneAdminById(String id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Admin.class);
    }

    public Admin findOneAdminByEmail(String email) {
        Query query = Query.query(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, Admin.class);
    }

    public void addOneAdmin(Admin admin) {
        mongoTemplate.save(admin, "Admin");
    }

    public void deleteOneAdminById(String id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, Admin.class);
    }

    public void deleteOneAdminByEmail(String email) {
        Query query = Query.query(Criteria.where("email").is(email));
        mongoTemplate.remove(query, Admin.class);
    }

    public void updateOneAdmin(Admin Admin) {
        Query query = Query.query(Criteria.where("_id").is(Admin.getEmail()));
        Update update = new Update();
        UpdateResult upsert = mongoTemplate.upsert(query, update, Admin.class);

    }

}
