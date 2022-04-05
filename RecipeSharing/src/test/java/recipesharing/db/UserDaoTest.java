package recipesharing.db;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import recipesharing.logic.RecipeItem;
import recipesharing.logic.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing for the UserDao class and its methods.
 */
@SpringBootTest
class UserDaoTest {

    @Autowired
    UserDao userDao;

    @Test
    void findAllUsers() {
        List<User> allUsers = userDao.findAllUsers();
        allUsers.forEach(System.out::println);
    }
    @Test
    void updateUserById() {
        User userById = userDao.findUserById("6249cadaa1f0c07dba837007");
        List<RecipeItem> recipeItems = new ArrayList<>();
        RecipeItem recipeItem = new RecipeItem("recipeid", "authorid");
        recipeItems.add(recipeItem);
        userById.setHistory(recipeItems);
        userDao.updateUserById(userById);
    }

    @Test
    void testFindUserById() {
        User userById = userDao.findUserById("6249c95cf85a657f0d39954f");
        System.out.println(userById);
    }

    @Test
    void findUserByUserName() {
        List<User> test_user = userDao.findUserByUserName("test user");
        test_user.forEach(System.out::println);
    }

    @Test
    void findUserByEmail() {
        User userByEmail = userDao.findUserByEmail("test@st-andrews.ac.uk");
        System.out.println(userByEmail);
    }

    @Test
    void addUser() {
        List<RecipeItem> recipeItems = new ArrayList<>();
        RecipeItem recipeItem = new RecipeItem("recipeid", "authorid");
        recipeItems.add(recipeItem);
        recipeItem.setRecipeId("ididididi");
        recipeItem.setUserId("dsdsadasds");
        recipeItems.add(recipeItem);
        User user = new User("test user4", "test4@st-andrews.ac.uk", "test password", recipeItems);
        userDao.addUser(user);

    }

    @Test
    void deleteUserById() {
        User userByEmail = userDao.findUserByEmail("test4@st-andrews.ac.uk");
        if (userByEmail != null) {
            userDao.deleteUserById(userByEmail.getUserId());
        }
    }

}