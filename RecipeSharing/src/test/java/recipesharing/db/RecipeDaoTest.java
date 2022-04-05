package recipesharing.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import recipesharing.logic.Recipe;
import recipesharing.logic.RecipeItem;
import recipesharing.logic.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
     * Deletes the recipe created in the previous test and checks that this has been completed.
     */
    @Test
    void testDeleteRecipeById() {
        recipeDao.deleteRecipeById("624c031ea9531c27eb1c0138");
        Recipe recipe = recipeDao.findRecipeById("624c031ea9531c27eb1c0138");
        assertNull(recipe);
    }

    @Test
    void testUpdateRecipeById() {

    }

    @Test
    void findAllRecipe() {
        List<Recipe> allRecipe = recipeDao.findAllRecipe();
        allRecipe.forEach(System.out::println);
    }

    @Test
    void findRecipeById() {
        System.out.println(recipeDao.findRecipeById("6249daf38c96d557eb053ced"));
    }

    @Test
    void findRecipeByRecipeName() {
        List<Recipe> recipeName = recipeDao.findRecipeByRecipeName("recipeName");
        recipeName.forEach(System.out::println);
    }

    @Test
    void findRecipeByAuthorId() {
        List<Recipe> recipeName = recipeDao.findRecipeByAuthorId("6249cadaa1f0c07dba837007");
        recipeName.forEach(System.out::println);
    }

    @Test
    void testFindRecipeAccessById() {

    }
}
