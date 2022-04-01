package RecipeSharing.logic;

import RecipeSharing.DB.MealDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for meals. Contains list of associated recipes. This should probably be a table in the database.
 */
@Data
@Document("Meal")
@Component
public class Meal {

    @Autowired
    MealDao mealDao;
    @Id
    private String id;
    private String title;
    private List<Recipe> recipes = new ArrayList<>();

    public Meal(String title, List<Recipe> recipes) {
        this.title = title;
        this.recipes = recipes;
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    public void removeRecipe(Recipe recipe) {
        this.recipes.remove(recipe);
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }
}
