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

    /**
     * Tests that read access can be set with a list of recipes.
     */
    @Test
    void setReadAccess() {
        Recipe recipe = new Recipe("Soup");
        Recipe recipe2 = new Recipe("Trifle");
        List<Recipe> recipes = new ArrayList<>(Arrays.asList(recipe, recipe2));
        jorge.setReadAccess(recipes);
        assertEquals(jorge.getReadAccess().size(), 2);
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

    /**
     * Tests that write access can be set with a list of recipes.
     */
    @Test
    void setWriteAccess() {
        Recipe recipe = new Recipe("Soup");
        Recipe recipe2 = new Recipe("Trifle");
        List<Recipe> recipes = new ArrayList<>(Arrays.asList(recipe, recipe2));
        jorge.setWriteAccess(recipes);
        assertEquals(jorge.getWriteAccess().size(), 2);
    }

    /**
     * Test that email is correctly retrieved.
     */
    @Test
    void getEmail() {
        assertEquals(jorge.getEmail(), "jj@jmail.com");
    }

    /**
     * Test email can be set.
     */
    @Test
    void setEmail() {
        jorge.setEmail("test");
        assertEquals(jorge.getEmail(), "test");
    }

    /**
     * Test name can be retrieved.
     */
    @Test
    void getName() {
        assertEquals(jorge.getName(), "Jorge Jones");
    }

    /**
     * Test name can be set.
     */
    @Test
    void setName() {
        jeff.setName("jeff");
        assertEquals(jeff.getName(), "jeff");
    }

    /**
     * Tests that an individual recipe can be added to liked list.
     */
    @Test
    void addLikedRecipe() {
        Recipe recipe = new Recipe("Soup");
        jorge.addLikedRecipe(recipe);
        assertTrue(jorge.getLikedRecipes().contains(recipe));
    }

    /**
     * Tests that an individual recipe can be removed from liked list.
     */
    @Test
    void removeLikedRecipe() {
        Recipe recipe = new Recipe("Soup");
        jorge.addLikedRecipe(recipe);
        assertTrue(jorge.getLikedRecipes().contains(recipe));
        jorge.removeLikedRecipe(recipe);
        assertFalse(jorge.getLikedRecipes().contains(recipe));
    }

    /**
     * Tests all liked recipes can be retrieved.
     */
    @Test
    void getLikedRecipes() {
        assertTrue(jorge.getLikedRecipes().isEmpty());
        Recipe recipe = new Recipe("Soup");
        jorge.addLikedRecipe(recipe);
        assertTrue(jorge.getLikedRecipes().contains(recipe));
    }

    /**
     * Tests that liked recipes can be set with a list.
     */
    @Test
    void setLikedRecipes() {
        Recipe recipe = new Recipe("Soup");
        Recipe recipe2 = new Recipe("Trifle");
        List<Recipe> recipes = new ArrayList<>(Arrays.asList(recipe, recipe2));
        jorge.setLikedRecipes(recipes);
        assertEquals(jorge.getLikedRecipes().size(), 2);
    }

    /**
     * Tests that an individual recipe can be added to a user's history.
     */
    @Test
    void addToHistory() {
        Recipe recipe = new Recipe("Soup");
        jorge.addToHistory(recipe);
        assertTrue(jorge.getHistory().contains(recipe));
    }

    /**
     * Tests that a user's full history can be returned.
     */
    @Test
    void getHistory() {
        assertTrue(jorge.getHistory().isEmpty());
        Recipe recipe = new Recipe("Soup");
        jorge.addToHistory(recipe);
        assertTrue(jorge.getHistory().contains(recipe));
    }
}