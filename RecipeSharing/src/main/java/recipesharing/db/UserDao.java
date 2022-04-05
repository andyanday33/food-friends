package recipesharing.db;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import recipesharing.logic.User;

import java.util.List;

/**
 * Data access object for user.
 */
@Repository
public class UserDao {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<User> findAllUsers(){
        return mongoTemplate.findAll(User.class);
    }

    public User findUserById(String userId) {
        return mongoTemplate.findById(userId, User.class);
    }

    public List<User> findUserByUserName(String userName) {
        Query query = Query.query(Criteria.where("userName").is(userName));
        return mongoTemplate.find(query, User.class, "t_user");
    }

    public User findUserByEmail(String email) {
        Query query = Query.query(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, User.class);
    }

    public void addUser(User user) {
        mongoTemplate.save(user, "t_user");
    }

    public void deleteUserById(String userId) {
        Query query = Query.query(Criteria.where("_id").is(userId));
        mongoTemplate.remove(query, User.class);
    }

    public void updateUserById(User user) {
        Query query = Query.query(Criteria.where("_id").is(user.getEmail()));
        Update update = new Update();
        UpdateResult upsert = mongoTemplate.upsert(query, update, User.class);
    }
}
