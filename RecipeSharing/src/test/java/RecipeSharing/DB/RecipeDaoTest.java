package RecipeSharing.DB;

import RecipeSharing.logic.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
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
    @Test
    void findRecipeTest() {
//        recipeDao.findAllRecipes();
        mongoTemplate.findAll(Recipe.class);
    }
}