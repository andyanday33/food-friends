package RecipeSharing.DB;

import RecipeSharing.logic.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDaoTest {

    @Autowired
    MongoTemplate mongoTemplate;
    @Test
    void findAllUsersTest(){
        System.out.println(mongoTemplate.findAll(User.class));
    }

    @Test
    void addUserTest(){
        User user = new User("test add","test@gamil.com");
        mongoTemplate.save(user, "user");
    }
}