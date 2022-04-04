package recipesharing.logic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

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

    @DBRef
    private User author;

    private String description;

    private boolean writeAccess;
    private boolean readAccess;
    private int thumpsUp;

    private ArrayList<String> MealType; //e.g., {breakfast:String, lunch:String}
    private String Cuisine; //e.g., French

    private ArrayList<IngredientItem> ingredients;

    public Recipe(String recipeName, String authorId, User author, boolean writeAccess, boolean readAccess, int thumpsUp) {
        this.recipeName = recipeName;
        this.authorId = authorId;
        this.author = author;
        this.writeAccess = writeAccess;
        this.readAccess = readAccess;
        this.thumpsUp = thumpsUp;
    }

    public Recipe(String recipeName, String authorId, User author, String description, boolean writeAccess, boolean readAccess, int thumpsUp, ArrayList<String> mealType, String cuisine) {
        this.recipeName = recipeName;
        this.authorId = authorId;
        this.author = author;
        this.description = description;
        this.writeAccess = writeAccess;
        this.readAccess = readAccess;
        this.thumpsUp = thumpsUp;
        MealType = mealType;
        Cuisine = cuisine;
    }

    public Recipe(String recipeName, String authorId, User author, boolean writeAccess, boolean readAccess, int thumpsUp, ArrayList<String> mealType, String cuisine) {
        this.recipeName = recipeName;
        this.authorId = authorId;
        this.author = author;
        this.writeAccess = writeAccess;
        this.readAccess = readAccess;
        this.thumpsUp = thumpsUp;
        MealType = mealType;
        Cuisine = cuisine;
    }

    public Recipe(String recipeName, String authorId, User author, String description, boolean writeAccess, boolean readAccess, int thumpsUp, ArrayList<String> mealType, String cuisine, ArrayList<IngredientItem> ingredients) {
        this.recipeName = recipeName;
        this.authorId = authorId;
        this.author = author;
        this.description = description;
        this.writeAccess = writeAccess;
        this.readAccess = readAccess;
        this.thumpsUp = thumpsUp;
        MealType = mealType;
        Cuisine = cuisine;
        this.ingredients = ingredients;
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
