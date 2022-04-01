package RecipeSharing.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        tomatoes = new Ingredient("Tomato", 5.0);
        lentils = new Ingredient("Lentil", 500.0);
        secretSauce = new Ingredient("Secret Sauce", 50.0);
        ingredients = new Ingredient[]{tomatoes, lentils, secretSauce};
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
        List<Recipe> userAuthoredRecipes = user.getAuthoredRecipes();
        List<Recipe> cuisineRecipes = cuisine.getRecipes();
        List<Recipe> mealRecipes = meal.getRecipes();

        assertTrue(userAuthoredRecipes.contains(soup));
        assertTrue(cuisineRecipes.contains(soup));
        assertTrue(mealRecipes.contains(soup));
    }

    /**
     * Checks rating update works.
     */
    @Test
    void updateRating() {
        soup.updateRating(5);
        assertEquals(soup.getRating(), 5.0);
    }

    /**
     * Check correct title returned.
     */
    @Test
    void getTitle() {
        assertEquals(soup.getTitle(), "Grandma's lentil soup");
    }

    /**
     * Checks that title setting works.
     */
    @Test
    void setTitle() {
        soup.setTitle("Eye of newt");
        assertEquals(soup.getTitle(), "Eye of newt");
    }

    /**
     * Check description returned.
     */
    @Test
    void getDescription() {
        assertEquals(soup.getDescription(), "The old family recipe");
    }

    /**
     * Check description can be set.
     */
    @Test
    void setDescription() {
        soup.setTitle("Double, double, toil, and trouble");
        assertEquals(soup.getTitle(), "Double, double, toil, and trouble");
    }

    /**
     * Check owner returned.
     */
    @Test
    void getOwner() {
        assertEquals(soup.getOwner(), user);
    }

    /**
     * Check shareable is false.
     */
    @Test
    void isShareable() {
        assertFalse(soup.isShareable());
    }

    /**
     * Check shareable can be set.
     */
    @Test
    void setShareable() {
        soup.setShareable(true);
        assertTrue(soup.isShareable());
    }

    /**
     * Check users with read access.
     */
    @Test
    void getReadAccess() {
        assertEquals(soup.getReadAccess().size(), 0);
    }

    @Test
    void setReadAccess() {
        User jim = new User("Jim", "jim@jimmail.com");
        soup.setReadAccess(new ArrayList<>(List.of(jim)));
        assertTrue(soup.getReadAccess().contains(jim));
    }

    @Test
    void addReadAccess() {
        User jim = new User("Jim", "jim@jimmail.com");
        soup.addReadAccess(jim);
        assertTrue(soup.getReadAccess().contains(jim));
    }

    @Test
    void removeReadAccess() {
    }

    /**
     * Check users with write access.
     */
    @Test
    void getWriteAccess() {
        assertEquals(soup.getWriteAccess().size(), 0);
    }

    @Test
    void setWriteAccess() {
        User jim = new User("Jim", "jim@jimmail.com");
        soup.setWriteAccess(new ArrayList<>(List.of(jim)));
        assertTrue(soup.getWriteAccess().contains(jim));
    }

    @Test
    void addWriteAccess() {
        User jim = new User("Jim", "jim@jimmail.com");
        soup.addWriteAccess(jim);
        assertTrue(soup.getWriteAccess().contains(jim));
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
