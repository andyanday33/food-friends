package recipesharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipesharing.db.RecipeDao;
import recipesharing.logic.Recipe;

import java.util.List;

/**
 *
 */
@Service
public class RecipeService {

    @Autowired
    RecipeDao recipeDao;

    /**
     *  find all recipes, todo: page limits
     * @return recipe list
     */
    public List<Recipe> findAllRecipe(){
        return recipeDao.findAllRecipe();
    }

    /**
     *  find a recipe by its _id
     * @param id recipe _id
     * @return one recipe
     */
    public Recipe findRecipeById(String id){
        return recipeDao.findRecipeById(id);
    }

    /**
     *  find recipes by its name, can not be unique
     * @param name recipe's name/title
     * @return a list of recipes that have the same name
     */
    public List<Recipe> findRecipeByRecipeName(String name){
        return recipeDao.findRecipeByRecipeName(name);
    }

    /**
     *  find recipe list that created by a same author
     * @param authorId the author's id
     * @return a list of recipes that created by the same user
     */
    public List<Recipe> findRecipeByAuthorId(String authorId){
        return recipeDao.findRecipeByAuthorId(authorId);
    }

    public boolean findRecipeAccessById(String accessType, String recipeId){
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
}
