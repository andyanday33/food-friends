package recipesharing.db;

import recipesharing.logic.Ingredient;
import recipesharing.logic.Meal;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data Access Object for Meal
 */
@Repository
public class MealDao {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<Meal> findAllMeal() {
        return mongoTemplate.findAll(Meal.class);
    }

    public Meal findOneMealByTitle(String title) {
        Query query = Query.query(Criteria.where("title").is(title));
        return mongoTemplate.findOne(query, Meal.class);
    }

    public void addOneMeal(Meal meal) {
        mongoTemplate.save(meal, "Meal");
    }

    public void updateOneMeal(Meal meal) {
        Query query = Query.query(Criteria.where("title").is(meal.getTitle()));
        Update update = new Update();
        update.set("title", meal.getTitle());
        update.set("recipes", meal.getRecipes());
        UpdateResult upsert = mongoTemplate.upsert(query, update, Meal.class);
    }

    public void deleteOneMealById(String Id) {
        Query query = Query.query(Criteria.where("_id").is(Id));
        mongoTemplate.remove(query, Ingredient.class);
    }


}
