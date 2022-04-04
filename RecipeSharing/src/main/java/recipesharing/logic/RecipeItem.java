package recipesharing.logic;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 *  used for database table association
 *  this is for [user] - [recipe]
 */
@Data
public class RecipeItem {

    private String recipeItemId;

    private String recipeId;

    private String userId;

    public RecipeItem(String recipeId, String userId){
        this.recipeItemId = String.valueOf(recipeId.hashCode());
        this.userId = userId;
        this.recipeId = recipeId;
    }
}
