package RecipeSharing.DB;

import RecipeSharing.logic.Ingredient;
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
    @Test
    void findOneTest(){;
        Query query = Query.query(Criteria.where("title").is("salt"));
        mongoTemplate.find(query, Ingredient.class);
        System.out.println(mongoTemplate.find(query, Ingredient.class));
    }

    @Test
    void findOIngredient() {
        ingredientDao.findOIngredient("salt");
    }

    @Test
    void addOneIngredient() {
    }

    @Test
    void deleteIngredientByTitle() {
    }
}