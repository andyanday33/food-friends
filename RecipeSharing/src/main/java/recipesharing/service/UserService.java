package recipesharing.service;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.db.RecipeDao;
import recipesharing.db.UserDao;
import recipesharing.logic.Recipe;
import recipesharing.logic.User;
import recipesharing.logic.User;

import java.util.List;

/**
 *
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    RecipeDao recipeDao;

    @Autowired
    MongoTemplate mongoTemplate;

    /**
     * find all users
     *
     * @return list of all users
     */
    public List<User> findAllUsers() throws NotFoundDBException {
        List<User> userList = userDao.findAllUsers();
        if (userList.isEmpty()) {
            throw new NotFoundDBException("There are no users in the database.");
        }
        return userList;
    }

    public List<User> findAllUsersWithPageLimit(int page, int size) {


        Query query = new Query();
        long count = mongoTemplate.count(query, User.class);

        Pageable pageable = PageRequest.of(page - 1, size);

        return mongoTemplate.find(query.with(pageable).with(Sort.by(Sort.Direction.DESC, "userName")), User.class);
    }

    /**
     * find users with page limits and query the query can specify the name, id etc
     *
     * @param query query condition
     * @param page  current
     * @param size  the number of records shows up
     *
     * @return user list
     */
    public List<User> findAllUsersByQueryWithPageLimit(Query query, int page, int size) {

        long count = mongoTemplate.count(query, User.class);

        Pageable pageable = PageRequest.of(page - 1, size);

        return mongoTemplate.find(query.with(pageable).with(Sort.by(Sort.Direction.DESC, "userName")), User.class);
    }

    /**
     * find user by _id
     *
     * @param id _id of the user
     *
     * @return user class
     */
    public User findUserById(String id) throws NotFoundDBException {
        User user = userDao.findUserById(id);
        if (user == null) {
            throw new NotFoundDBException("The user with id " + id + "does not exist.");
        }
        return user;
    }

    /**
     * find users by the user name, can be not unique
     *
     * @param name user name
     *
     * @return user list
     */
    public List<User> findUserByUserName(String name) throws NotFoundDBException {
        List<User> userList = userDao.findUserByUserName(name);
        if (userList.isEmpty()) {
            throw new NotFoundDBException("There are no users with name " + name + ".");
        }
        return userList;
    }

    /**
     * find user by its email address
     *
     * @param email user email
     *
     * @return user class
     */
    public User findUserByEmail(String email) {
        User user = userDao.findUserByEmail(email);
        // TODO had to comment out exception handling as already seems to be done in LoginService class?
        //if (user == null) {
        // throw new NotFoundDBException("There is no user with the email " + email + ".");
        // }
        return user;
    }

    /**
     * add one user
     *
     * @param user user class obj that needs to be added into the collection
     */
    public void addUser(User user) {
        userDao.addUser(user);
    }

    /**
     * delete the user by the user id
     *
     * @param id user _id
     */
    public void deleteUserById(String id) {
        userDao.deleteUserById(id);
    }

    /**
     * update the user by its id
     *
     * @param newUser the user obj that has already changed the attributes
     */
    public void updateUserById(User newUser) {
        userDao.updateUserById(newUser);
    }

    /**
     *  invite a user to edit one recipe (should be used by the author)
     * @param recipeId id of the recipe
     * @param invitedId id of the user being invited to edit the recipe
     */
    public void inviteUserById(String recipeId, String invitedId){
        Recipe joined = recipeDao.findRecipeById(recipeId);

        List<String> groupUsers = joined.getGroupUserIds();
        groupUsers.add(invitedId);

        joined.setGroupUserIds(groupUsers);

    }

}
