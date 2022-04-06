package recipesharing.logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("t_user")
public class User {
    @Id
    private String userId;
    @Indexed
    private String subId; // for the front end auth0
    @Indexed
    private String userName;
    @Indexed
    private String email;

    private String password;

    @Transient
    private String token;

    @Field("history")
    private List<RecipeItem> history = new ArrayList<>();


    public User(String userName, String email, String password, List<RecipeItem> history) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.history = history;
    }
}
