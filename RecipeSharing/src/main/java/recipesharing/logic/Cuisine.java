package recipesharing.logic;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 *
 */
@Data
@Document("t_cuisine")
public class Cuisine {

    @Id
    private String id;
    @Indexed
    private String name;


    public Cuisine(String name) {
        this.name = name;
    }
}
