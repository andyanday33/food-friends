package recipesharing.logic;

import recipesharing.db.AdminDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Admin")
public class Admin extends Person {

    @Autowired
    AdminDao adminDao;
    @Id
    private String id;

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