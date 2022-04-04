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
 * Testing of Ingredient data access object and its methods.
 */
@SpringBootTest
public class IngredientDaoTest {

    @Autowired
    IngredientDao ingredientDao;

    @Test
    public void testFindAllIngredients() {

    }

    @Test
    public void testFindOneIngredient() {

    }

    @Test
    public void testDeleteIngredientByTitle() {

    }

    @Test
    public void testUpdateIngredientByTitle() {

    }

}
