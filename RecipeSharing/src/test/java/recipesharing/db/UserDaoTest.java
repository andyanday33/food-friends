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

    /**
     * Check that there are users in the database.
     */
    @Test
    void findAllUsers() {
        List<User> allUsers = userDao.findAllUsers();
        assertTrue(allUsers.size() > 0);
    }

    /**
     * Creates a user that is added to the db. Checks that that user can then be found in the db.
     * Finally this test user is removed.
     */
    @Test
    void addUser() {
        //add user
        List<RecipeItem> recipeItems = new ArrayList<>();
        User user = new User("TEST PERSON", "test4@st-andrews.ac.uk", "test password", recipeItems);
        userDao.addUser(user);

        //check user exists in db.
        List<User> getUser = userDao.findUserByUserName("TEST PERSON");
        assertTrue(getUser.contains(user));

        //remove added user from db
        removeUserFromDB(getUser);
    }

    /**
     * Check that individual users can be found via their ID.
     */
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
    void deleteUserById() {
        User userByEmail = userDao.findUserByEmail("test4@st-andrews.ac.uk");
        if (userByEmail != null) {
            userDao.deleteUserById(userByEmail.getUserId());
        }
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

    /**
     * Helper method for cleaning up after test. Removes user from database.
     * @param user to be removed.
     */
    private void removeUserFromDB(User user) {
        String id = user.getUserId();
        userDao.deleteUserById(id);
    }

    /**
     * Helper method for cleaning up after test. Removes user from database.
     * @param users list of users, the first of which is removed.
     */
    private void removeUserFromDB(List<User> users) {
        User user = users.get(0);
        String id = user.getUserId();
        userDao.deleteUserById(id);
    }
}