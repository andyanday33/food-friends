package recipesharing.db;

import recipesharing.logic.Ingredient;
import recipesharing.logic.User;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data Access Object for User
 */
@Repository
public class UserDao {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> findAllUsers(){
        System.out.println(mongoTemplate.findAll(User.class));
        return mongoTemplate.findAll(User.class);
    }
    public User findOneUserById(String id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, User.class);
    }

    public User findOneUserByEmail(String email) {
        Query query = Query.query(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, User.class);
    }
    public void addOneUser(User user){
        mongoTemplate.save(user, "user");
    }

    public void deleteOneUserById(String id) {
        Query query = Query.query(Criteria.where("_id").is(id));
        mongoTemplate.remove(query, User.class);
    }
    public void deleteOneUserByEmail(String email) {
        Query query = Query.query(Criteria.where("email").is(email));
        mongoTemplate.remove(query, User.class);
    }
    public void updateOneUser(User user){
        Query query = Query.query(Criteria.where("_id").is(user.getEmail()));
        Update update = new Update();
        UpdateResult upsert = mongoTemplate.upsert(query, update, User.class);
    }
}
