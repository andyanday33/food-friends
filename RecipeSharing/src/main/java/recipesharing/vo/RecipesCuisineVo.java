package recipesharing.vo;

import lombok.Data;
import recipesharing.logic.Recipe;

import java.util.List;

/**
 * view obj for recipe table and cuisine table
 */
@Data
public class RecipesCuisineVo {
    private String _id; //recipe id
    private String recipeName; //recipe name
    private String cuisineId; //cuisine id, foreign key
//    private List<Recipe> recipes;
}
