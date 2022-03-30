package RecipeSharing.logic;

import RecipeSharing.DB.IngredientDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("Ingredient")
public class Ingredient {

    @Autowired
    IngredientDao ingredientDao;
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
