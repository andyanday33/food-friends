package RecipeSharing.DB;

import RecipeSharing.logic.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data Access Object for Ingredient
 */

@Repository
public class IngredientDao {
    @Autowired
    MongoTemplate mongoTemplate;

    Ingredient findOIngredient(String title) {
        Query query = Query.query(Criteria.where("title").is(title));
        return mongoTemplate.findOne(query, Ingredient.class);
    }

    void addOneIngredient(Ingredient ingredient) {
        mongoTemplate.save(ingredient, "Ingredient");
    }

    void deleteIngredientByTitle(String title) {
        Query query = Query.query(Criteria.where("title").is(title));
        Ingredient ingredient = mongoTemplate.findOne(query, Ingredient.class);
        if (ingredient != null) {
            mongoTemplate.remove(ingredient);
            System.out.println("successfully delete: " + ingredient.getTitle());
        }
    }

}
