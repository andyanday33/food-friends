package recipesharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipesharing.logic.Recipe;
import recipesharing.service.RecipeService;
import recipesharing.vo.Result;

import java.util.List;

/**
 *
 */
@CrossOrigin(origins = "*")
@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;

    /**
     * If user is unsure of how to use the API then may access the base root.
     *
     * @return Returns instructions on how to access /api for more information.
     */
    @GetMapping("/")
    public String index() {
        return "Use GET /api for information on how to use the API.";
    }

    /**
     * Returns a description of how the API works
     *
     * @return
     */
    @GetMapping("/api")
    public String apiDescription() {
        return "description of api";
    }

    // *** Recipe related API endpoints *** //

    /**
     * Returns a recipe given a title.
     * todo What if there are more than one with the same title?
     * todo Doesn't currently work when testing title = title
     */
    @GetMapping("/getRecipe")
    public Result getRecipe(@RequestParam String title) {
        List<Recipe> recipeByRecipeName = recipeService.findRecipeByRecipeName(title);
        return Result.success(recipeByRecipeName);
    }
    // *** Cuisine related API endpoints *** //

    /**
     * Find all cuisines in the database.
     * TODO Does not currently work.
     *
     * @return
     */
    @GetMapping("/getAllCuisines")
    public Result getCuisines() {
        return Result.success(recipeService.findAllRecipe());
    }


//-----------------------------------
// TODO  public String createRecipe
    @GetMapping("/getRecipe/{user}")
    public void getRecipesByUser(@PathVariable String userId) {
        //change from String to User
        // return list of recipes

    }

    /**
     * Change permissions for a recipe
     * @param name
     * @param email
     * @param recipeID
     */
    @PostMapping("/changePermissionsOnRecipe")
    public void changeUserPermissionsOnRecipe(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String recipeID
    ) {
        // need to implement
    }

    /**
     * Searches for all recipes with the given ingredient in them and returns a list of recipes.
     * @param ingredientName
     */
    @GetMapping("/getRecipesWithIngredient")
    public void getRecipesWithIngredient(@RequestParam String ingredientName) {
        //TODO missing DB functionality
    }

    /**
     * todo still to be implemented.
     * @param cuisineType
     */
    @GetMapping("/getRecipesByCuisine")
    public void getRecipesByCuisine(@RequestParam String cuisineType) {
        // Create new cuisine. TODO validate input.
//        Cuisine cuisine = new Cuisine(cuisineType);
        //todo needs to be implemented in db
    }

    /**
     * Delete a recipe
     * todo what should we pass in here to delete a recipe?
     * @param recipeID
     */
    @DeleteMapping("/deleteRecipe")
    public Result deleteRecipe(@RequestParam String recipeID) {
        // First find the recipe associated with the recipeID
        // todo think we need a method in RecipeDao for finding the recipe by ID
        // then delete it
        //recipeDao.deleteOneRecipeById(recipe);
        recipeService.deleteRecipeById(recipeID);
        return Result.success(null);
    }
//---------------------
    /**
     * Add a new cuisine to the database.
     * TODO Doesn't work. Throws server error because it can't access addOneCuisine.
     */
    @PostMapping("/addOneCuisine")
    public Result addOneCuisine(@RequestBody Recipe recipe) {
        recipeService.addRecipe(recipe);
        return Result.success(recipe);
    }

    /**
     * Delete cuisine by id
     *
     * @param id recipe id
     */
    @DeleteMapping("/deleteCuisineById")
    public Result deleteCuisineById(@RequestParam String id) {
        //TODO check if exists first
        recipeService.deleteRecipeById(id);
        return Result.success(null);
    }


}
