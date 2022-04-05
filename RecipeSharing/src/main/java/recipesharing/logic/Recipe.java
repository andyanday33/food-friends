package recipesharing.logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@Document("t_recipe")
public class Recipe {
    @Id
    private String recipeId;
    @Indexed
    private String recipeName;
    private String authorId;

//    @DBRef
//    private User author;

    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    private String description;

    private boolean writeAccess;
    private boolean readAccess;
    private int thumpsUp;

    private ArrayList<String> MealType; //e.g., {breakfast:String, lunch:String}

    //    @DBRef
//    private Cuisine Cuisine; //e.g., French
    @Indexed
    private String cuisineId;

    private ArrayList<IngredientItem> ingredients;

    private ArrayList<MealItem> mealItems;


    // TODO LJ create new recipe constructor to match api but not sure if this is correct
    // The user's read and write access should be
    /*
    public Recipe(String recipeName, String recipeDescription, String ownerId, List<String> instructions, List<Ingredient> ingredients, Meal meal, Cuisine cusine) {
        this.recipeName = recipeName;
        this.description = recipeDescription;
        // todo this.ownerID doesn't exist
        //this.ownerId = ownerId;
        // todo instructions list doesn't exist
        //this.instructions = instructions;
        this.ingredients = ingredients;
        // meal doesnt exist
        // cuisine doesnt exist
    }

     */

    public Recipe(String recipeName, String authorId, LocalDateTime createdTime, LocalDateTime updatedTime, String description, boolean writeAccess, boolean readAccess, int thumpsUp, ArrayList<String> mealType, String cuisineId, ArrayList<IngredientItem> ingredients, ArrayList<MealItem> mealItems) {
        this.recipeName = recipeName;
        this.authorId = authorId;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.description = description;
        this.writeAccess = writeAccess;
        this.readAccess = readAccess;
        this.thumpsUp = thumpsUp;
        MealType = mealType;
        this.cuisineId = cuisineId;
        this.ingredients = ingredients;
        this.mealItems = mealItems;
    }

    public boolean isWriteAccess() {
        return writeAccess;
    }

    public void setWriteAccess(boolean writeAccess) {
        this.writeAccess = writeAccess;
    }

    public boolean isReadAccess() {
        return readAccess;
    }

    public void setReadAccess(boolean readAccess) {
        this.readAccess = readAccess;
    }
}
