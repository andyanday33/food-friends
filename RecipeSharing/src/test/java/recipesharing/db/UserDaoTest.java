package recipesharing.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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
        //Create test user.
        List<RecipeItem> recipeItems = new ArrayList<>();
        User user = new User("FIND ME BY ID", "test4@st-andrews.ac.uk", "test password", recipeItems);
        userDao.addUser(user);

        //get ID
        List<User> users = userDao.findUserByUserName("FIND ME BY ID");
        String id = users.get(0).getUserId();

        //find user by ID and check equal
        User userById = userDao.findUserById(id);
        assertEquals(user, userById);

        //remove test user
        removeUserFromDB(userById);
    }

    /**
     * Tests that users can be found based on their username.
     */
    @Test
    void findUserByUserName() {
        //Create test user.
        List<RecipeItem> recipeItems = new ArrayList<>();
        User user = new User("FIND ME BY USERNAME", "test4@st-andrews.ac.uk", "test password", recipeItems);
        userDao.addUser(user);

        //check retrieval and equality.
        List<User> users = userDao.findUserByUserName("FIND ME BY ID");
        assertEquals(users.get(0), user);

        //remove test user
        removeUserFromDB(users.get(0));
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