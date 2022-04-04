package recipesharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.db.UserDao;
import recipesharing.logic.User;

import java.util.List;

/**
 *
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;
    /**
     *  find all users 
     * @return list of all users
     */
    public List<User> findAllUsers() throws NotFoundDBException {
        List<User> userList = userDao.findAllUsers();
        if (userList.isEmpty()) {
            throw new NotFoundDBException("There are no users in the database.");
        }
        return userList;
    }
    /**
     *  find user by _id
     * @param id _id of the user
     * @return user class
     */
    public User findUserById(String id) throws NotFoundDBException {
        User user =  userDao.findUserById(id);
        if (user == null) {
            throw new NotFoundDBException("The user with id " + id + "does not exist.");
        }
        return user;
    }
    /**
     *  find users by the user name, can be not unique
     * @param name user name
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
     *  find user by its email address
     * @param email user email
     * @return user class
     */
    public User findUserByEmail(String email)  {
        User user = userDao.findUserByEmail(email);
        // TODO had to comment out exception handling as already seems to be done in LoginService class?
        //if (user == null) {
           // throw new NotFoundDBException("There is no user with the email " + email + ".");
       // }
        return user;
    }
    /**
     *  add one user 
     * @param user user class obj that needs to be added into the collection
     */
    public void addUser(User user) {
        userDao.addUser(user);
    }
    /**
     * delete the user by the user id
     * @param id user _id
     */
    public void deleteUserById(String id) {
        userDao.deleteUserById(id);
    }
    /**
     *  update the user by its id
     * @param newUser the user obj that has already changed the attributes
     */
    public void updateUserById(User newUser) {
        userDao.updateUserById(newUser);
    }

}
