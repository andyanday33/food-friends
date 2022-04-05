package recipesharing.logic;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

/**
 *   used for recipe collection
 *   can be many e.g., breakfast, vegan, drinks, etc.?
 */
public class MealItem {

    @Id
    private String MealItemId;

    @Indexed
    private String recipeId;

    private String mealName;

}
