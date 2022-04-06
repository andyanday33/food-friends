package recipesharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.db.RecipeDao;
import recipesharing.logic.Recipe;
import recipesharing.logic.Cuisine;
import recipesharing.logic.Recipe;
import recipesharing.vo.RecipesCuisineVo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class RecipeService {

    @Autowired
    RecipeDao recipeDao;

    @Autowired
    MongoTemplate mongoTemplate;
    /**
     *  find all recipes, 
     * @return recipe list
     */
    public List<Recipe> findAllRecipe() throws NotFoundDBException {
        List<Recipe> recipeList = recipeDao.findAllRecipe();
        if (recipeList.isEmpty()) {
            throw new NotFoundDBException("There are currently no recipes in the database.");
        }
        return recipeList;
    }

    public List<Recipe> findAllRecipesWithPageLimit(int page, int size) {


        Query query = new Query();
        long count = mongoTemplate.count(query, Recipe.class);

        Pageable pageable = PageRequest.of(page-1,size);

        return mongoTemplate.find(query.with(pageable).with(Sort.by(Sort.Direction.DESC, "recipeName")), Recipe.class);
    }

    /**
     * find admins with page limits and query
     * the query can specify the name, id etc
     * @param query query condition
     * @param page current
     * @param size the number of records shows up
     * @return admin list 
     */
    public List<Recipe> findAllRecipesByQueryWithPageLimit(Query query, int page, int size) {

        long count = mongoTemplate.count(query, Recipe.class);

        Pageable pageable = PageRequest.of(page-1,size);

        return mongoTemplate.find(query.with(pageable).with(Sort.by(Sort.Direction.DESC,"recipeName")), Recipe.class);
    }
    /**
     *  find a recipe by its _id
     * @param id recipe _id
     * @return one recipe
     */
    public Recipe findRecipeById(String id) throws NotFoundDBException {
        Recipe recipe = recipeDao.findRecipeById(id);
        if (recipe == null) {
            throw new NotFoundDBException("The recipe given by id " + id + "does not exist.");
        }
        return recipe;
    }

    /**
     *  find recipes by its name, can not be unique
     * @param name recipe's name/title
     * @return a list of recipes that have the same name
     */
    public List<Recipe> findRecipeByRecipeName(String name) throws NotFoundDBException {
        List<Recipe> recipeList = recipeDao.findRecipeByRecipeName(name);
        if (recipeList.isEmpty()) {
            throw new NotFoundDBException("There are no recipes with the recipe name " + name + ".");
        }
        return recipeList;
    }

    /**
     *  find recipe list that created by a same author
     * @param authorId the author's id
     * @return a list of recipes that created by the same user
     */
    public List<Recipe> findRecipeByAuthorId(String authorId) throws NotFoundDBException {
        List<Recipe> recipeList = recipeDao.findRecipeByAuthorId(authorId);
        if (recipeList.isEmpty()) {
            throw new NotFoundDBException("There are no recipes created by author with id " + authorId + ".");
        }
        return recipeList;
    }

    // TODO need to throw exception here but not sure how to because it returns a boolean which is never null I think?
    public boolean findRecipeAccessById(String accessType, String recipeId) {
        return recipeDao.findRecipeAccessById(accessType, recipeId);
    }

    /**
     * add one recipe
     * @param recipe new recipe
     */
    public void addRecipe(Recipe recipe){
        recipeDao.addRecipe(recipe);
    }

    /**
     * delete the recipe by the recipe _id
     * @param id recipe id
     */
    public void deleteRecipeById(String id){
        recipeDao.deleteRecipeById(id);
    }

    /**
     * update the recipe by the recipe
     * @param newRecipe the updated recipe
     */
    public void updateRecipeById(Recipe newRecipe){
        recipeDao.updateRecipeById(newRecipe);
    }

//    public void getIngredientListByRecipeId(String recipeId){
//        Query query = Query.query(Criteria.where("").is(cuisineId));
//        mongoTemplate.remove(query, Recipe.class);
//    }
}
