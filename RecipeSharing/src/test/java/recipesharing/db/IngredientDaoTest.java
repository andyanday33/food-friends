package recipesharing.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import recipesharing.logic.Ingredient;
import recipesharing.logic.Recipe;
import recipesharing.logic.RecipeItem;
import recipesharing.logic.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//TODO add assertions to each test?
/**
 * Testing of Ingredient data access object and its methods.
 */
@SpringBootTest
public class IngredientDaoTest {

    @Autowired
    IngredientDao ingredientDao;

    @Test
    public void testFindAllIngredients() {
        List<Ingredient> allIngredients = ingredientDao.findAllIngredients();
        allIngredients.forEach(System.out::println);
    }

    @Test
    public void testFindOneIngredient() {
        Ingredient salt = ingredientDao.findOneIngredient("salt");
        assertEquals(salt.getTitle(), "salt");
    }

    @Test
    public void testAddOneIngredient() {
        Ingredient pepper = new Ingredient("poivre", 37.0);
        ingredientDao.addOneIngredient(pepper);
        Ingredient pep = ingredientDao.findOneIngredient("poivre");
        assertEquals(pep.getTitle(), "poivre");
        assertEquals(pep.getQuantity(), 37.0);
    }

    @Test
    public void testDeleteIngredientByTitle() {
        ingredientDao.deleteIngredientByTitle("poivre");
        Ingredient pep = ingredientDao.findOneIngredient("poivre");
        assertNull(pep);
    }

    @Test
    public void testUpdateIngredientByTitle() {
        Ingredient pepper = new Ingredient("poivre", 37.0);
        ingredientDao.addOneIngredient(pepper);
        ingredientDao.updateIngredientByTitle(pepper, "poivron", 2.0);
        Ingredient pep = ingredientDao.findOneIngredient("poivron");
        assertEquals(pep.getTitle(), "poivron");
        assertEquals(pep.getQuantity(), 2.0);
        ingredientDao.deleteIngredientByTitle("poivron");
    }
}
