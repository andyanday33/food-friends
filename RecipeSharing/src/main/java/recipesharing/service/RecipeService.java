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
import recipesharing.db.AdminDao;
import recipesharing.db.RecipeDao;
import recipesharing.logic.Recipe;
import recipesharing.logic.Cuisine;
import recipesharing.logic.Recipe;
import recipesharing.logic.User;
import recipesharing.vo.RecipesCuisineVo;

import java.util.ArrayList;
import java.util.List;


@Service
public class RecipeService {

    @Autowired
    RecipeDao recipeDao;

    @Autowired
    AdminDao adminDao;

    @Autowired
    MongoTemplate mongoTemplate;
    /**
     *  find all recipes, 
     * @return recipe list
     */
    public ArrayList<Recipe> findAllRecipe() throws NotFoundDBException {
        ArrayList<Recipe> recipeList = (ArrayList<Recipe>) recipeDao.findAllRecipe();
        if (recipeList.isEmpty()) {
            throw new NotFoundDBException("There are currently no recipes in the database.");
        }
        return recipeList;
    }

    /**
     * find all recipe with page limit
     * @param page page number
     * @param size the number of records
     * @return
     */
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

    /**
     *  check if the recipe is accessible
     * @param accessType write/read
     * @param recipeId _id for recipe
     * @return true/false
     */
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


    /**
     * check if the person has writing access to the recipe now: only author & invited users & admins have the write
     * access
     *
     * @param userId   userId from "t_recipe"/ _id from "t_admin"
     *                 /group user id from "t_recipe"'s list
     * @param recipeId recipe's id
     *
     * @return
     */
    public Boolean isWritable(String userId, String recipeId) {
        Recipe recipeById = recipeDao.findRecipeById(recipeId);

        System.out.println(recipeById.toString());
        Query query = Query.query(Criteria.where("_id").is(userId));
        //Author check from "t_recipe"
        if (recipeById.getAuthorId().equals(userId)) {
            System.out.println("author!");
            return true;
            // admin check from "t_admin"
        } else if (mongoTemplate.exists(query, "t_admin")) {
            System.out.println("admin!");
            return true;
        } else {
            // group user check from "t_recipe"'s group user list
            List<String> groupUsers = recipeById.getGroupUserIds();

            return !groupUsers.isEmpty() && groupUsers.contains(userId);
        }
    }

    /**
     *  change the read access
     * @param opt private/public
     * @param recipeId _id for recipe
     * @param userId _id for user
     * @return true/false
     */
    public boolean changeReadAccess(String opt, String recipeId, String userId){
        Boolean writable = this.isWritable(userId, recipeId);
        if(writable){
            if (opt.equals("private")){
                Recipe recipeById = recipeDao.findRecipeById(recipeId);
                recipeById.setReadAccess(false);
                recipeDao.updateRecipeById(recipeById);
                return true;
            }
            else if (opt.equals("public")){
                Recipe recipeById = recipeDao.findRecipeById(recipeId);
                recipeById.setReadAccess(true);
                recipeDao.updateRecipeById(recipeById);
                return true;
            }
        }
        return false;
    }

}
