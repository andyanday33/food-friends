package recipesharing.logic;

import lombok.Data;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * store images for every recipe
 */
@Document("t_image")
@Data
public class Image {

    @Id
    private String id;
    @Indexed
    private String recipeId;

    private String name;

    private LocalDateTime createdTime;

    private String contentType;

    private Binary content;

//    private Long size;


    public Image(String name) {
        this.name = name;
    }
}
