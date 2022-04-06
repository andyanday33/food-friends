package recipesharing.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import recipesharing.logic.Ingredient;
import recipesharing.logic.Unit;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing of Ingredient data access object and its methods.
 */
@SpringBootTest
public class IngredientDaoTest {

    @Autowired
    IngredientDao ingredientDao;

    /**
     * Tests that all ingredients can be retrieved and printed.
     */
    @Test
    public void testFindAllIngredients() {
        List<Ingredient> allIngredients = ingredientDao.findAllIngredients();
        allIngredients.forEach(System.out::println);
    }

    /**
     * Tests that an ingredient can be found by name.
     */
    @Test
    public void testFindOneIngredient() {
        Ingredient addSalt = new Ingredient("salt", 5.0, Unit.GRAM);
        ingredientDao.addOneIngredient(addSalt);
        Ingredient salt = ingredientDao.findOneIngredient("salt");
        assertEquals(salt.getTitle(), "salt");
    }

    /**
     * Tests that one ingredient can be added and retrieved appropriately.
     */
    @Test
    public void testAddOneIngredient() {
        Ingredient pepper = new Ingredient("poivre", 37.0);
        ingredientDao.addOneIngredient(pepper);
        Ingredient pep = ingredientDao.findOneIngredient("poivre");
        assertEquals(pep.getTitle(), "poivre");
        assertEquals(pep.getQuantity(), 37.0);
    }

    /**
     * Tests that an ingredient can be deleted from the DB.
     */
    @Test
    public void testDeleteIngredientByTitle() {
        ingredientDao.deleteIngredientByTitle("poivre");
        Ingredient pep = ingredientDao.findOneIngredient("poivre");
        assertNull(pep);
    }

    /**
     * Tests that ingredient details in the database can be updated.
     */
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
