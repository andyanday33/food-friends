package recipesharing.db;

import recipesharing.logic.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

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
    MongoTemplate mongoTemplate;
    @Test
    void addRecipeTest() {
        Recipe recipe = new Recipe("title");
        recipeDao.addOneRecipe(recipe);
    }
/*    @Test
    void findRecipeTest() {
//        recipeDao.findAllRecipes();
        mongoTemplate.findAll(Recipe.class);
    }*/

    @Test
    void findOneRecipeByTitle() {
    }

    @Test
    void findAllRecipes() {
    }

    @Test
    void updateOneRecipe() {
    }

    @Test
    void deleteOneRecipeById() {
    }
}