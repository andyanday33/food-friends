package recipesharing.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import recipesharing.logic.Recipe;
import recipesharing.logic.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing of recipe data access object and its methods.
 */
@SpringBootTest
class RecipeDaoTest {

    @Autowired
    RecipeDao recipeDao;

    @Autowired
    UserDao userDao;

    /**
     * Creates and adds a recipe to the database and checks that it can retrieved and has the correct details.
     */
    @Test
    void addRecipe() {
        User user = userDao.findUserById("6249cadaa1f0c07dba837007");
        Recipe recipe = new Recipe("jaffa cake", "authorId1", user, true, true, 0);
        recipeDao.addRecipe(recipe);
        List<Recipe> recipes = recipeDao.findRecipeByRecipeName("jaffa cake");
        Recipe retrievedRecipe = recipes.get(0);
        assertEquals(retrievedRecipe.getRecipeName(), "jaffa cake");
        assertEquals(retrievedRecipe.getAuthorId(), "authorId1");
    }

    /**
     * Checks that a recipe can be found using all retrieval methods, and they all return the same thing.
     */
    @Test
    void findRecipeById() {
        User user = userDao.findUserById("6249cadaa1f0c07dba837007");
        Recipe recipe = new Recipe("retrieveID test", "authorId1", user, true, true, 0);
        recipeDao.addRecipe(recipe);
        List<Recipe> returnedRecipes = recipeDao.findRecipeByRecipeName("retrieveID test");
        Recipe intermediate = returnedRecipes.get(0);
        String id = intermediate.getRecipeId();

        Recipe idRetrieve = recipeDao.findRecipeById(id);
        assertEquals(idRetrieve, intermediate);
        assertEquals(idRetrieve, recipe);
    }

    /**
     * Deletes the recipe created in the previous test and checks that this has been completed.
     */
    @Test
    void testDeleteRecipeById() {
        User user = userDao.findUserById("6249cadaa1f0c07dba837007");
        Recipe recipe = new Recipe("DELETE ME", "authorId1", user, true, true, 0);

        recipeDao.addRecipe(recipe);
        List<Recipe> returnedRecipes = recipeDao.findRecipeByRecipeName("DELETE ME");
        Recipe intermediate = returnedRecipes.get(0);
        String id = intermediate.getRecipeId();

        recipeDao.deleteRecipeById(id);
        assertNull(recipeDao.findRecipeById(id));
    }

    //TODO Consider how this should be implemented given the number of variables
    @Test
    void testUpdateRecipeById() {

    }

    /**
     * Adds a recipe and then returns all recipes from the DB. Checks size and one recipe it contains.
     */
    @Test
    void findAllRecipe() {
        User user = userDao.findUserById("6249cadaa1f0c07dba837007");
        Recipe recipe = new Recipe("jaffa cake", "authorId1", user, true, true, 0);
        recipeDao.addRecipe(recipe);
        List<Recipe> allRecipe = recipeDao.findAllRecipe();
        assertTrue(allRecipe.size() > 0);
        assertTrue(allRecipe.contains(recipe));
    }

    /**
     * Tests that a recipe can be retrieved using its name.
     */
    @Test
    void findRecipeByRecipeName() {
        User user = userDao.findUserById("6249cadaa1f0c07dba837007");
        Recipe recipe = new Recipe("jaffa cake unique", "authorId1", user, true, true, 0);
        recipeDao.addRecipe(recipe);
        List<Recipe> recipes = recipeDao.findRecipeByRecipeName("jaffa cake unique");
        assertEquals(recipe, recipes.get(0));
    }

    /**
     * Creates and adds a recipe to the DB. Then finds it using its author. Then deletes the recipe to finish.
     */
    @Test
    void findRecipeByAuthorId() {
        User user = userDao.findUserById("6249cadaa1f0c07dba837007");
        Recipe recipe = new Recipe("DELETE ME", "authorId1", user, true, true, 0);

        recipeDao.addRecipe(recipe);
        List<Recipe> recipeName = recipeDao.findRecipeByAuthorId("authorId1");
        assertTrue(recipeName.contains(recipe));

        List<Recipe> returnedRecipes = recipeDao.findRecipeByRecipeName("DELETE ME");
        Recipe intermediate = returnedRecipes.get(0);
        String id = intermediate.getRecipeId();

        recipeDao.deleteRecipeById(id);
        assertNull(recipeDao.findRecipeById(id));
    }

    /**
     * Creates recipe and adds to DB. Then checks that the correct access booleans can be retrieved.
     * Deletes test recipe to finish.
     */
    @Test
    void testFindRecipeAccessById() {
        User user = userDao.findUserById("6249cadaa1f0c07dba837007");
        Recipe recipe = new Recipe("DELETE ME", "authorId1", user, true, true, 0);
        recipeDao.addRecipe(recipe);

        List<Recipe> returnedRecipes = recipeDao.findRecipeByRecipeName("DELETE ME");
        Recipe intermediate = returnedRecipes.get(0);
        String id = intermediate.getRecipeId();

        assertTrue(recipeDao.findRecipeAccessById("read", id));
        assertTrue(recipeDao.findRecipeAccessById("write", id));

        recipeDao.deleteRecipeById(id);
        assertNull(recipeDao.findRecipeById(id));
    }
}
