package RecipeSharing.logic;

import RecipeSharing.DB.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("User")
public class User extends Person{

    @Autowired
    UserDao userDao;

    /**
     * Constructor for class.
     *
     * @param name  person's name.
     * @param email person's email address.
     */
    public User(String name, String email) {
        super(name, email);
    }

    public User(){
    }
}
