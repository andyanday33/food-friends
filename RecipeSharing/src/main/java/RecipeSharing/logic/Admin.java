package RecipeSharing.logic;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("Admin")
public class Admin extends Person{
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
