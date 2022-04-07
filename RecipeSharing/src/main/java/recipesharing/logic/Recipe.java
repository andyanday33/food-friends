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
    private User author;

    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;

    private String description;
    private ArrayList<String> instructions;
    private boolean writeAccess;
    private boolean readAccess;
    private int thumpsUp;


    //    @DBRef
    private Cuisine cuisine; //e.g., French
    @Indexed
    private String cuisineId;
    private String cuisineTitle;

    private List<IngredientItem> ingredients;

    private List<MealItem> mealItems;

    private List<User> groupUsers;

    /**
     *
     * @param recipeName
     * @param recipeDescription
     * @param ownerId
     * @param instructions
     * @param ingredients
     * @param cuisine
     */
    public Recipe(String recipeName, String recipeDescription, String ownerId, ArrayList<String> instructions, ArrayList<IngredientItem> ingredients, Cuisine cuisine) {
        this.recipeName = recipeName;
        this.description = recipeDescription;
        this.authorId = ownerId;
        this.instructions = instructions;
        this.ingredients = ingredients;
        this.cuisine = cuisine;
        this.readAccess=true;
    }



    public Recipe(String recipeName, String authorId, LocalDateTime createdTime, LocalDateTime updatedTime, String description, boolean writeAccess, boolean readAccess, int thumpsUp, ArrayList<String> mealType, String cuisineId, ArrayList<IngredientItem> ingredients, ArrayList<MealItem> mealItems) {
        this.recipeName = recipeName;
        this.authorId = authorId;
        this.createdTime = createdTime;
        this.updatedTime = updatedTime;
        this.description = description;
        this.writeAccess = writeAccess;
        this.readAccess = readAccess;
        this.thumpsUp = thumpsUp;
        this.cuisineId = cuisineId;
        this.ingredients = ingredients;
        this.mealItems = mealItems;
    }



    public Recipe(String delete_me, String authorId1, User user, boolean b, boolean b1, int i) {
    }

    public Recipe(String recipeId, String recipeName) {
        this.recipeId = recipeId;
        this.recipeName = recipeName;
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
