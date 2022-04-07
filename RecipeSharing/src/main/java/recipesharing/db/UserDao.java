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

    /**
     * find all the users
     * @return a list of users
     */
    public List<User> findAllUsers(){
        return mongoTemplate.findAll(User.class);
    }

    /**
     * find a user by its _id
     * @param userId user _id
     * @return user obj
     */
    public User findUserById(String userId) {
        return mongoTemplate.findById(userId, User.class);
    }

    /**
     * find a user by its name
     * @param userName username
     * @return a list of users, since ppl can have the same name
     */
    public List<User> findUserByUserName(String userName) {
        Query query = Query.query(Criteria.where("userName").is(userName));
        return mongoTemplate.find(query, User.class, "t_user");
    }

    /**
     * find a user by its email
     * @param email email
     * @return user obj
     */
    public User findUserByEmail(String email) {
        Query query = Query.query(Criteria.where("email").is(email));
        return mongoTemplate.findOne(query, User.class);
    }

    /**
     * add one user
     * @param user user obj
     */
    public void addUser(User user) {
        mongoTemplate.save(user, "t_user");
    }

    /**
     * delete one user by its _id
     * @param userId _id for user
     */
    public void deleteUserById(String userId) {
        Query query = Query.query(Criteria.where("_id").is(userId));
        mongoTemplate.remove(query, User.class);
    }

    /**
     * update one user
     * @param user user obj
     */
    public void updateUserById(User user) {
        Query query = Query.query(Criteria.where("_id").is(user.getEmail()));
        Update update = new Update();
        UpdateResult upsert = mongoTemplate.upsert(query, update, User.class);
    }
}
