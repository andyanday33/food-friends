package RecipeSharing.logic;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Ingredient")
public class Ingredient {

    private String title;
    private double quantity;

    //ml
    //litres
    //fluid ounce
    //pint
    //quart

    //grams
    //kilograms
    //milligrams
    //ounce
    //pound
    //stone
}
