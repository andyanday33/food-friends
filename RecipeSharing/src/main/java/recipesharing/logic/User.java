package recipesharing.logic;

import recipesharing.db.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("user")
public class User extends Person{

    @Autowired
    UserDao userDao;
    @Id
    private String id;

    /**
     * Constructor for class.
     *
     * @param name  person's name.
     * @param email person's email address.
     */
    public User(String name, String email) {
        super(name, email);
    }

    public User() {
    }
}