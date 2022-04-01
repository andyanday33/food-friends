package RecipeSharing.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests for the logic in the Recipe class.
 */
public class RecipeTest {

    Recipe soup;
    Recipe simpleSoup;
    Person user;
    String[] instructions;
    Ingredient tomatoes;
    Ingredient lentils;
    Ingredient secretSauce;
    Ingredient[] ingredients;
    Meal meal;
    Cuisine cuisine;

    @BeforeEach
    void setUp() {
        user = new User("John Caboodle", "jc@toucanmail.com");
        instructions = new String[]{"1. Chop tomatoes", "2. Add lentils", "3. Add the secret ingredient"};
        ingredients = null;
        meal = new Meal("Lunch");
        cuisine = new Cuisine("Generico");

        soup = new Recipe("Grandma's lentil soup",
                "The old family recipe",
                user,
                instructions,
                ingredients,
                meal,
                cuisine);
        simpleSoup = new Recipe("Lentil Soup");
    }

    /**
     * Checks that Meal, Cuisine, and User objects have been updated to include this recipe.
     */
    @Test
    void checkObjectsUpdated() {

    }

    @Test
    void updateRating() {
    }

    @Test
    void getTitle() {
    }

    @Test
    void setTitle() {
    }

    @Test
    void getDescription() {
    }

    @Test
    void setDescription() {
    }

    @Test
    void getOwner() {
    }

    @Test
    void isShareable() {
    }

    @Test
    void setShareable() {
    }

    @Test
    void getReadAccess() {
    }

    @Test
    void setReadAccess() {
    }

    @Test
    void addReadAccess() {
    }

    @Test
    void removeReadAccess() {
    }

    @Test
    void getWriteAccess() {
    }

    @Test
    void setWriteAccess() {
    }

    @Test
    void addWriteAccess() {
    }

    @Test
    void removeWriteAccess() {
    }

    @Test
    void getCuisines() {
    }

    @Test
    void setCuisines() {
    }

    @Test
    void getIngredients() {
    }

    @Test
    void setIngredients() {
    }

    @Test
    void setOwner() {
    }

    @Test
    void getPhotos() {
    }

    @Test
    void setPhotos() {
    }

    @Test
    void addPhoto() {
    }

    @Test
    void removePhoto() {
    }

    @Test
    void getRecipeDao() {
    }

    @Test
    void getId() {
    }

    @Test
    void getInstructions() {
    }

    @Test
    void getMeal() {
    }

    @Test
    void getRating() {
    }

    @Test
    void getNumRatings() {
    }

    @Test
    void setRecipeDao() {
    }

    @Test
    void setId() {
    }

    @Test
    void setInstructions() {
    }

    @Test
    void setMeal() {
    }

    @Test
    void setRating() {
    }

    @Test
    void setNumRatings() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}
