package RecipeSharing.logic;

import RecipeSharing.DB.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Admin")
public class Admin extends Person {

    @Autowired
    AdminDao adminDao;

    /**
     * Constructor for class.
     *
     * @param name  person's name.
     * @param email person's email address.
     */
    public Admin(String name, String email) {
        super(name, email);
    }
}
