package RecipeSharing.DB;

import RecipeSharing.logic.Ingredient;
import RecipeSharing.logic.Meal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
@SpringBootTest
class IngredientDaoTest {

    @Autowired
    IngredientDao ingredientDao;

    @Autowired
    MongoTemplate mongoTemplate;

    Ingredient ingredient;

    @BeforeEach
    void setUp() {
        ingredient = new Ingredient("pepper", 20.00);
    }

    @Test
    void findOneTest() {
        Query query = Query.query(Criteria.where("title").is("salt"));
        mongoTemplate.find(query, Ingredient.class);
        System.out.println(mongoTemplate.find(query, Ingredient.class));
    }

    @Test
    void findAllTest(){
        ingredientDao.findAllIngredients();
    }

    @Test
    void FindIngredientById() {
        Query query = Query.query(Criteria.where("_id").is("62436ea6afdcb6df20cd8373"));
        mongoTemplate.find(query, Ingredient.class);
        System.out.println(mongoTemplate.find(query, Ingredient.class));
    }

    @Test
    void findOIngredient() {
        ingredientDao.findOneIngredient("salt");
    }

    @Test
    void addOneIngredient() {
        ingredientDao.addOneIngredient(ingredient);
    }

    @Test
    void deleteIngredientByTitle() {
        ingredientDao.deleteIngredientByTitle(ingredient.getTitle());
    }


    @Test
    void updateIngredientByTitle() {
        ingredient.setQuantity(30330);
        ingredientDao.updateIngredientByTitle(ingredient);
    }
}