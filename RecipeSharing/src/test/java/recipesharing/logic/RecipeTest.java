package recipesharing.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
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

    /**
     * Set up necessary objects for testing.
     */
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

    /**
     * Tests that read access can be set with a list of users.
     */
    @Test
    void setReadAccess() {
        User jim = new User("Jim", "jim@jimmail.com");
        User josephine = new User("Josephine", "epicsniper94@gmail.com");
        soup.setReadAccess(new ArrayList<>(List.of(jim, josephine)));
        assertTrue(soup.getReadAccess().contains(jim));
        assertTrue(soup.getReadAccess().contains(josephine));
    }

    /**
     * Tests an individual user can be given read access.
     */
    @Test
    void addReadAccess() {
        User jim = new User("Jim", "jim@jimmail.com");
        soup.addReadAccess(jim);
        assertTrue(soup.getReadAccess().contains(jim));
    }

    /**
     * Tests individual users can have read access removed.
     */
    @Test
    void removeReadAccess() {
        User jim = new User("Jim", "jim@jimmail.com");
        soup.addReadAccess(jim);
        assertTrue(soup.getReadAccess().contains(jim));
        soup.removeReadAccess(jim);
        assertFalse(soup.getReadAccess().contains(jim));
    }

    /**
     * Check users with write access.
     */
    @Test
    void getWriteAccess() {
        assertEquals(soup.getWriteAccess().size(), 0);
    }

    /**
     * Tests that write access can be set with a list of users.
     */
    @Test
    void setWriteAccess() {
        User jim = new User("Jim", "jim@jimmail.com");
        User josephine = new User("Josephine", "epicsniper94@gmail.com");
        soup.setWriteAccess(new ArrayList<>(List.of(jim, josephine)));
        assertTrue(soup.getWriteAccess().contains(jim));
        assertTrue(soup.getWriteAccess().contains(josephine));
    }

    /**
     * Tests an individual user can be given write access.
     */
    @Test
    void addWriteAccess() {
        User jim = new User("Jim", "jim@jimmail.com");
        soup.addWriteAccess(jim);
        assertTrue(soup.getWriteAccess().contains(jim));
    }

    /**
     * Tests individual users can have read access removed.
     */
    @Test
    void removeWriteAccess() {
        User jim = new User("Jim", "jim@jimmail.com");
        soup.addWriteAccess(jim);
        assertTrue(soup.getWriteAccess().contains(jim));
        soup.removeWriteAccess(jim);
        assertFalse(soup.getWriteAccess().contains(jim));
    }

    /**
     * Checks that the cuisine is added to the Recipe correctly.
     */
    @Test
    void getCuisines() {
        List<Cuisine> cuisines = soup.getCuisines();
        assertTrue(cuisines.contains(cuisine));
    }

    /**
     * Set the cuisines associated with this recipe with a list.
     */
    @Test
    void setCuisines() {
        Cuisine antrim = new Cuisine("Antrim");
        Cuisine chilean = new Cuisine("Chilean");
        soup.setCuisines(Arrays.asList(antrim, chilean));
        assertTrue(soup.getCuisines().contains(antrim));
        assertTrue(soup.getCuisines().contains(chilean));
    }

    /**
     * Check that all ingredients are returned correctly.
     */
    @Test
    void getIngredients() {
        List<Ingredient> ingreds = soup.getIngredients();
        assertTrue(ingreds.contains(tomatoes));
        assertTrue(ingreds.contains(lentils));
        assertTrue(ingreds.contains(secretSauce));
    }

    /**
     * Checks that ingredients can be set with a list.
     */
    @Test
    void setIngredients() {
        Ingredient tails = new Ingredient("Snails tails", 5.0);
        Ingredient eyebrows = new Ingredient("Pigeons eyebrows", 10.0);
        soup.setIngredients(Arrays.asList(tails, eyebrows));
        assertTrue(soup.getIngredients().contains(tails));
        assertTrue(soup.getIngredients().contains(eyebrows));
    }

    /**
     * Checks that the owner can be updated if necessary.
     */
    @Test
    void setOwner() {
        User sneakySamantha = new User("Sam", "irrational_orangutan423@hotmail.com");
        soup.setOwner(sneakySamantha);
        assertEquals(soup.getOwner(), sneakySamantha);
    }

    /**
     * Check initial photos field is null.
     */
    //TODO consider how to upload and implement photos
    @Test
    void getPhotos() {
        assertNull(soup.getPhotos());
    }

    /**
     * Checks that a list of photos can be set correctly.
     */
    //TODO consider how to upload and implement photos
    @Test
    void setPhotos() {
    }

    /**
     * Checks that an individual photo can be added as a Byte[].
     */
    //TODO consider how to upload and implement photos
    @Test
    void addPhoto() {
    }

    /**
     * Checks that an individual photo can be removed.
     */
    //TODO consider how to upload and implement photos
    @Test
    void removePhoto() {
    }

    /**
     * Checks that the instructions for the recipe can be returned correctly.
     */
    @Test
    void getInstructions() {
        assertEquals(soup.getInstructions(), instructions);
    }

    /**
     * Checks that the correct meal associated with a recipe is returned.
     */
    @Test
    void getMeal() {
        assertEquals(soup.getMeal(), meal);
    }

    /**
     * Checks correct rating of recipe is returned.
     */
    @Test
    void getRating() {
        assertEquals(soup.getRating(), 0.0);
        soup.updateRating(3);
        soup.updateRating(5);
        assertEquals(soup.getRating(), 4.0);
    }

    /**
     * Checks that the number of rating associated with a recipe is updating correctly.
     */
    @Test
    void getNumRatings() {
        assertEquals(soup.getNumRatings(), 0);
        soup.updateRating(3);
        soup.updateRating(5);
        soup.updateRating(5);
        assertEquals(soup.getNumRatings(), 3);
    }
}
