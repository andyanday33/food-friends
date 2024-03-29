package recipesharing.db;

import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;
import recipesharing.logic.Recipe;
import recipesharing.logic.User;

import java.util.List;

/**
 * data access object for recipe
 */
@Repository
public class RecipeDao {
    @Autowired
    MongoTemplate mongoTemplate;

    public List<Recipe> findAllRecipe() {
        return mongoTemplate.findAll(Recipe.class);
    }

    public Recipe findRecipeById(String recipeId) {
        return mongoTemplate.findById(recipeId, Recipe.class);
    }

    public List<Recipe> findRecipeByRecipeName(String recipeName) {
        Query query = Query.query(Criteria.where("recipeName").is(recipeName));
        return mongoTemplate.find(query, Recipe.class, "t_recipe");
    }

    public List<Recipe> findRecipeByAuthorId(String authorId) {
        Query query = Query.query(Criteria.where("authorId").is(authorId));
        return mongoTemplate.find(query, Recipe.class, "t_recipe");
    }

    public boolean findRecipeAccessById(String accessType, String recipeId) {
        Recipe byId = mongoTemplate.findById(recipeId, Recipe.class);
        assert byId != null;
        if (accessType.equals("read")) {
            return byId.isReadAccess();
        } else {
            return byId.isWriteAccess();
        }
    }

    public void addRecipe(Recipe recipe) {
        mongoTemplate.save(recipe, "t_recipe");
    }

    public void deleteRecipeById(String recipeId) {
        Query query = Query.query(Criteria.where("_id").is(recipeId));
        mongoTemplate.remove(query, Recipe.class);
    }

    //TODO decide how to implement update given the number of variables.
    public void updateRecipeById(Recipe recipe) {
        Query query = Query.query(Criteria.where("_id").is(recipe.getRecipeId()));
        Update update = new Update();
        //recipe.set <- set whatever wanna change
        UpdateResult upsert = mongoTemplate.upsert(query, update, Recipe.class);
    }
}
