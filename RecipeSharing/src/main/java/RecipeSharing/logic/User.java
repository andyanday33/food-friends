package RecipeSharing.logic;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("Person")
public class User extends Person{
    /**
     * Constructor for class.
     *
     * @param name  person's name.
     * @param email person's email address.
     */
    public User(String name, String email) {
        super(name, email);
    }

}
