package RecipeSharing.DB;

import RecipeSharing.logic.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data Access Object for User
 */
@Repository
public class UserDao {
    @Autowired
    MongoTemplate mongoTemplate;

    List<User> findAllUsers(){
        System.out.println(mongoTemplate.findAll(User.class));
        return mongoTemplate.findAll(User.class);
    }
}
