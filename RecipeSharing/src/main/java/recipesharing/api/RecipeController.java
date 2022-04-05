package recipesharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.logic.Recipe;
import recipesharing.service.CuisineService;
import recipesharing.service.RecipeService;
import recipesharing.vo.Result;

import java.util.List;

/**
 * Contains recipe related endpoints for the API.
 */
@CrossOrigin(origins = "*")
@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;
    CuisineService cuisineService;

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
     * Returns a description of how the API works.
     * @return a String representing the description of the api.
     */
    @GetMapping("/api")
    public String apiDescription() {
        return "description of api";
    }

    // *** Recipe related API endpoints *** //

    @GetMapping("/createNewRecipe")
    public Result createNewRecipe(
            // !! All request param names must be specified but the values can be null. !!
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String ownerId,
            @RequestParam String[] instructions,
            @RequestParam String[] ingredientNames,
            @RequestParam String[] ingredientQuantities,
            @RequestParam String mealType,
            @RequestParam String cuisineTitle
    ) {
        // TODO we need to fix the recipe constructor
        //Recipe recipe = new Recipe();
        //return Result.success(recipeService.addRecipe();)
        return null;
    }

    /**
     * Returns a list of recipes which have the same title as the one specified by the user.
     * @param title
     * @return
     */
    @GetMapping("/getRecipeByTitle")
    public Result getRecipeByTitle(@RequestParam String title) {
        try {
            return Result.success(recipeService.findRecipeByRecipeName(title));
        } catch (NotFoundDBException e) {
            return Result.fail(404, e.getMessage());
        }
    }

    /**
     * Retrieves the recipe when given a recipe ID if the recipe exists.
     * @param recipeId - the unique recipe ID String for the recipe.
     * @return The result of the query (status code, the recipe).
     */
    @GetMapping("/getRecipeById")
    public Result getRecipeById(@RequestParam String recipeId) {
        try {
            return Result.success(recipeService.findRecipeById(recipeId));
        } catch (NotFoundDBException e) {
            return Result.fail(404, e.getMessage());
        }

    }

    /**
     * Returns a list of recipes which an author owns when given an author Id.
     * @param authorId
     * @return
     */
    @GetMapping("/getRecipesByAuthorId")
    public Result getRecipesByAuthorId(@RequestParam String authorId) {
        try {
            return Result.success(recipeService.findRecipeByAuthorId(authorId));
        } catch (NotFoundDBException e) {
            return Result.fail(404, e.getMessage());
        }
    }

    /**
     * Checks if a recipe has the specified access type.
     * @param accessType - todo not sure what this is (public, private, read, write?)
     * @param recipeId - the unique recipe id as a String.
     * @return true if has specified access type.
     */
    @GetMapping("/getRecipeAccessById")
    public Result getRecipeAccessById(@RequestParam String accessType, @RequestParam String recipeId) {
        boolean hasAccess = recipeService.findRecipeAccessById(accessType, recipeId);
        return Result.success(hasAccess);
    }



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
     * Deletes a recipe from the database given a unique recipe ID.
     * @param recipeID - the String representation of the recipe ID.
     */
    @DeleteMapping("/deleteRecipeByID")
    public Result deleteRecipeByID(@RequestParam String recipeID) {
        recipeService.deleteRecipeById(recipeID);
        return Result.success(null);
    }

    // *** Cuisine related API endpoints *** //

    /**
     * Find all cuisines in the database.
     * @return A list of all the cuisines in the database.
     */
    @GetMapping("/getAllCuisines")
    public Result getCuisines() {
        try {
            return Result.success(cuisineService.getAllCuisines());
        } catch (NotFoundDBException e) {
            return Result.fail(404, e.getMessage());
        }
    }

    /**
     * Add a new cuisine to the database.
     * TODO Doesn't work.
     */
    @PostMapping("/addOneCuisine")
    public Result addOneCuisine(@RequestParam String cuisineTitle) {
        // TODO Check if cuisine already exists in DB first!!
        //recipeService.addRecipe(recipe);
        //return Result.success(recipe);
        return null;
    }

    /**
     * Delete cuisine by id if the cuisine exists.
     * @param id recipe id
     */
    @DeleteMapping("/deleteCuisineByID")
    public Result deleteCuisineByID(@RequestParam String id) {
        recipeService.deleteRecipeById(id);
        return Result.success(null);
    }


}
