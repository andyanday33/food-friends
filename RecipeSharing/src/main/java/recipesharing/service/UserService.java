package recipesharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }
    /**
     *  find user by _id
     * @param id _id of the user
     * @return user class
     */
    public User findUserById(String id) {
        return userDao.findUserById(id);
    }
    /**
     *  find users by the user name, can be not unique
     * @param name user name
     * @return user list
     */
    public List<User> findUserByUserName(String name) {
        return userDao.findUserByUserName(name);
    }
    /**
     *  find user by its email address
     * @param email user email
     * @return user class
     */
    public User findUserByEmail(String email) {
        return userDao.findUserByEmail(email);
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
