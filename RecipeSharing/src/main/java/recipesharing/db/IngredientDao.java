package recipesharing.db;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import recipesharing.logic.Ingredient;

import java.util.List;

/**
 * Data Access Object for Ingredient
 */

@Repository
public class IngredientDao {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<Ingredient> findAllIngredients() {
        return mongoTemplate.findAll(Ingredient.class, "Ingredient");
    }

    public Ingredient findOneIngredient(String title) {
        Query query = Query.query(Criteria.where("title").is(title));
        return mongoTemplate.findOne(query, Ingredient.class);
    }

    public void addOneIngredient(Ingredient ingredient) {
        mongoTemplate.save(ingredient, "Ingredient");
    }

    public void deleteIngredientByTitle(String title) {
        Query query = Query.query(Criteria.where("title").is(title));

        mongoTemplate.remove(query, Ingredient.class);
    }

    public void updateIngredientByTitle(Ingredient ingredient, String newTitle, Double newQuantity) {
        Query query = Query.query(Criteria.where("title").is(ingredient.getTitle()));
        Update update = new Update();
        update.set("title", newTitle);
        update.set("quantity", newQuantity);

        UpdateResult upsert = mongoTemplate.upsert(query, update, Ingredient.class);
    }
}
