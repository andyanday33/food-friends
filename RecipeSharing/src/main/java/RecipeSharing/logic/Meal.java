package RecipeSharing.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for meals. Contains list of associated recipes. This should probably be a table in the database.
 */
public class Meal {

    private String title;
    private List<Recipe> recipes = new ArrayList<>();

    public Meal(String title) {
        this.title = title;
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
