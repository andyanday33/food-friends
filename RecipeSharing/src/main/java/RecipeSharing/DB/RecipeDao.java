package RecipeSharing.DB;

import RecipeSharing.logic.Ingredient;
import RecipeSharing.logic.Recipe;
import RecipeSharing.logic.User;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Queue;

/**
 * Data Access Object for Recipe
 */
@Repository
public class RecipeDao {
    @Autowired
    MongoTemplate mongoTemplate;

    public void addOneRecipe(Recipe recipe) {
        mongoTemplate.save(recipe, "Recipe");
    }

    public Recipe findOneRecipeByTitle(String title) {
        Query query = Query.query(Criteria.where("title").is(title));
        return mongoTemplate.findOne(query, Recipe.class, "Recipe");
    }

    public List<Recipe> findAllRecipes() {
        return mongoTemplate.findAll(Recipe.class);
    }

    public void updateOneRecipe(Recipe recipe) {
        Query query = Query.query(Criteria.where("_id").is(recipe.getId()));
        Update update = new Update();
        UpdateResult upsert = mongoTemplate.upsert(query, update, Recipe.class);
    }

    public void deleteOneRecipeById(Recipe recipe) {
        Query query = Query.query(Criteria.where("_id").is(recipe.getId()));
        mongoTemplate.remove(query, User.class);
    }
}
