package recipesharing.logic;

import lombok.Data;
import org.springframework.data.annotation.PersistenceConstructor;

/**
 * used for database table association this is for [ingredient] n - 1 [recipe]
 */
@Data
public class IngredientItem {

    private String ingredientItemId;

    private String recipeId;
    private String recipeName;

    private Ingredient ingredient;

    @PersistenceConstructor
    public IngredientItem(String recipeName, Ingredient ingredient) {
        this.recipeName = recipeName;
        this.ingredient = ingredient;
    }

    public IngredientItem(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
