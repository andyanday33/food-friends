package recipesharing.logic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test methods in User class and its superclass Person.
 */
class UserTest {

    Person jorge;
    Person jeff;

    /**
     * Set up two users before each test.
     */
    @BeforeEach
    void setUp() {
        jorge = new User("Jorge Jones", "jj@jmail.com");
        jeff = new User();
    }

    /**
     * Tests that a recipe can be added to a user's authored list.
     */
    @Test
    void addRecipe() {
        Recipe recipe = new Recipe("Soup");
        jorge.addRecipe(recipe);
        assertTrue(jorge.getAuthoredRecipes().contains(recipe));
    }

    /**
     * Tests that a recipe can be removed from a user's authored list.
     */
    @Test
    void removeRecipe() {
        Recipe recipe = new Recipe("Soup");
        jorge.addRecipe(recipe);
        assertTrue(jorge.getAuthoredRecipes().contains(recipe));
        jorge.removeRecipe(recipe);
        assertFalse(jorge.getAuthoredRecipes().contains(recipe));
    }

    /**
     * Tests that a user's authored recipe's can be retrieved correctly.
     */
    @Test
    void getAuthoredRecipes() {
        assertTrue(jorge.getAuthoredRecipes().isEmpty());
        Recipe recipe = new Recipe("Soup");
        jorge.addRecipe(recipe);
        assertTrue(jorge.getAuthoredRecipes().contains(recipe));
        assertEquals(jorge.getAuthoredRecipes().size(), 1);
    }

    /**
     * Checks that a user's authored recipes can be set with a list of recipes.
     */
    @Test
    void setAuthoredRecipes() {
        Recipe recipe = new Recipe("Soup");
        Recipe recipe2 = new Recipe("Trifle");
        List<Recipe> recipes = new ArrayList<>(Arrays.asList(recipe, recipe2));
        jorge.setAuthoredRecipes(recipes);
        assertEquals(jorge.getAuthoredRecipes().size(), 2);
    }

    /**
     * Tests that the get list of recipes the user has read access to, works.
     */
    @Test
    void getReadAccess() {
        assertTrue(jorge.getReadAccess().isEmpty());
    }

    /**
     * Tests that an individual recipe can be added to the user's read access list.
     */
    @Test
    void addReadAccess() {
        Recipe recipe = new Recipe("Soup");
        jorge.addReadAccess(recipe);
        assertTrue(jorge.getReadAccess().contains(recipe));
    }

    /**
     * Tests that an individual recipe can be removed from the user's read access list.
     */
    @Test
    void removeReadAccess() {
        Recipe recipe = new Recipe("Soup");
        jorge.addReadAccess(recipe);
        assertTrue(jorge.getReadAccess().contains(recipe));
        jorge.removeReadAccess(recipe);
        assertFalse(jorge.getReadAccess().contains(recipe));
    }

    @Test
    void setReadAccess() {
    }

    /**
     * Tests that an individual recipe can be added to the user's write access list.
     */
    @Test
    void addWriteAccess() {
        Recipe recipe = new Recipe("Soup");
        jorge.addWriteAccess(recipe);
        assertTrue(jorge.getWriteAccess().contains(recipe));
    }

    /**
     * Tests that an individual recipe can be removed from the user's write access list.
     */
    @Test
    void removeWriteAccess() {
        Recipe recipe = new Recipe("Soup");
        jorge.addWriteAccess(recipe);
        assertTrue(jorge.getWriteAccess().contains(recipe));
        jorge.removeWriteAccess(recipe);
        assertFalse(jorge.getWriteAccess().contains(recipe));
    }

    /**
     * Tests that the get list of recipes the user has write access to, works.
     */
    @Test
    void getWriteAccess() {
        assertTrue(jorge.getWriteAccess().isEmpty());
    }

    @Test
    void setWriteAccess() {
    }

    @Test
    void getEmail() {
    }

    @Test
    void setEmail() {
    }

    @Test
    void getName() {
    }

    @Test
    void setName() {
    }

    @Test
    void addLikedRecipe() {
    }

    @Test
    void removeLikedRecipe() {
    }

    @Test
    void getLikedRecipes() {
    }

    @Test
    void setLikedRecipes() {
    }

    @Test
    void addToHistory() {
    }

    @Test
    void getHistory() {
    }
}