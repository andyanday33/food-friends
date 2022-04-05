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
 *
 */
@SpringBootTest
class RecipeDaoTest {

    @Autowired
    RecipeDao recipeDao;

    @Autowired
    UserDao userDao;

    @Test
    void addRecipe() {
        User user = userDao.findUserById("6249cadaa1f0c07dba837007");
        Recipe recipe = new Recipe("pancake", "authorId1", user, true, true, 0);
        recipeDao.addRecipe(recipe);
    }

    @Test
    void testDeleteRecipeById() {

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
