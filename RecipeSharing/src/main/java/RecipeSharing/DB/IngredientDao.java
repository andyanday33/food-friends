package RecipeSharing.DB;

import RecipeSharing.logic.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object for Ingredient
 */

@Repository
public class IngredientDao {
    @Autowired
    MongoTemplate mongoTemplate;

    String findIngredient() {
        Query query = Query.query(Criteria.where("title").is("salt"));
        mongoTemplate.find(query, Ingredient.class);
        System.out.println(mongoTemplate.find(query, Ingredient.class));
        return "success";
    }

}
