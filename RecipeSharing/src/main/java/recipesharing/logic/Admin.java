package recipesharing.logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("t_admin")
public class Admin {
    @Id
    private String id;
    @Indexed
    private String subId; // for the front end auth0
    @Indexed
    private String name;
    @Indexed
    private String email;
    private String password;

    @Transient
    private String token;

    public Admin(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
