package recipesharing.db;

import recipesharing.logic.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserDaoTest {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    UserDao userDao;
    @Test
    void findAllUsersTest(){
        System.out.println(mongoTemplate.findAll(User.class));
    }

    @Test
    void addUserTest(){
        User user = new User("test add1111","test1111@gamil.com");
        userDao.addOneUser(user);
    }

    @Test
    void findAllUserTest(){
        userDao.findAllUsers();
    }
}