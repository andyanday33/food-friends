package recipesharing.logic;

import lombok.Data;

/**
 *  used for database table association
 *  this is for [ingredient] n - 1 [recipe]
 */
@Data
public class IngredientItem {

    private String ingredientItemId;

    private String recipeId;

    private String ingredientId;

    public IngredientItem(String recipeId, String ingredientId){
        this.ingredientItemId = String.valueOf(ingredientId.hashCode());
        this.ingredientId = ingredientId;
        this.recipeId = recipeId;
    }
}
