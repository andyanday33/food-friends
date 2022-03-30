package RecipeSharing.logic;

import RecipeSharing.DB.CuisineDao;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Cuisine class.
 */
@Document("Cuisine")
@Data
public class Cuisine {

    @Autowired
    CuisineDao cuisineDao;
    private final String title;
    private List<Recipe> recipes = new ArrayList<>();

    /**
     * Constructor for class.
     */
    public Cuisine(String title) {
        this.title = title;
    }

    /**
     * Add a recipe to this cuisine.
     * @param recipe recipe to be added.
     */
    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    /**
     * Remove a recipe from this cuisine.
     * @param recipe recipe to be removed.
     */
    public void removeRecipe(Recipe recipe) {
        this.recipes.remove(recipe);
    }

    /**
     * Get the recipes associated with this Cuisine.
     * @return list of recipes.
     */
    public List<Recipe> getRecipes() {
        return recipes;
    }

    /**
     * Get the recipes associated with this Cuisine.
     */
    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    /**
     * Get the title associated with this Cuisine.
     * @return title String.
     */
    public String getTitle() {
        return title;
    }
}
