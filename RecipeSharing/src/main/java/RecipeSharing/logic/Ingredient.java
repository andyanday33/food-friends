package RecipeSharing.logic;

import RecipeSharing.DB.IngredientDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Ingredient")
public class Ingredient {

    @Autowired
    IngredientDao ingredientDao;
    @Id
    private String id;
    private String title;
    private Double quantity;

    public Ingredient(String title, Double quantity) {
        this.title = title;
        this.quantity = quantity;
    }

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
