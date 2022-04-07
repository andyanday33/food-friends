package recipesharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.logic.*;
import recipesharing.service.CuisineService;
import recipesharing.service.RecipeService;
import recipesharing.vo.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains recipe related endpoints for the API.
 */
@CrossOrigin(origins = "*")
@RestController
public class RecipeController {

    @Autowired
    RecipeService recipeService;
    @Autowired
    CuisineService cuisineService;



    // *** Recipe related API endpoints *** //

    /**
     * Create a new recipe which is stored in the db.
     * @param title - the title of the recipe.
     * @param description - the description of the recipe.
     * @param ownerId - the id of the owner of the recipe.
     * @param instructions - A list of instructions.
     * @param ingredientNames - A list of names of ingredients.
     * @param ingredientQuantities - A list of quantities corresponding to each ingredient name.
     * @param cuisineName - the name of the cuisine.
     * @return
     */
    @PostMapping("/createNewRecipe")
    public Result createNewRecipe(
            // !! All request param names must be specified but the values can be null. !!
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String ownerId,
            @RequestParam ArrayList<String> instructions,
            @RequestParam String[] ingredientNames,
            @RequestParam String[] ingredientQuantities,
            @RequestParam String cuisineName
    ) {

        // Create a new list to store each ingredient item.
        ArrayList<IngredientItem> ingredients = new ArrayList<>();
        // Loop through all ingredient names and add each ingredient and corresponding quantity to the list.
        for (int i = 0; i < ingredientNames.length; i++) {
            ingredients.add(new IngredientItem(title, new Ingredient(ingredientNames[i], Double.parseDouble(ingredientQuantities[i]))));
        }
        // if the database contains a cuisine with the given name then they may add the recipe
        if (cuisineService.containsCuisineWithName(cuisineName)) {
            // find the cuisine object associated with the cuisine title
            Cuisine cuisine = cuisineService.findCuisineWithName(cuisineName);
            // Create a new recipe
            Recipe recipe = new Recipe(title, description, ownerId, instructions, ingredients, cuisine);
            // add the recipe
            recipeService.addRecipe(recipe);
            return Result.success(recipe);
        } else {
            return Result.fail(400, "the cuisine you are trying to add does not exist. Please choose a valid cuisine.");
        }
    }


    /**
     * Return all recipes if they exist. Otherwise returns a 404 and error message.
     * @return
     */
    @GetMapping("/getAllRecipes")
    public Result getAllRecipes() {
        try {
            return Result.success(recipeService.findAllRecipe());
        } catch (NotFoundDBException e) {
            return Result.fail(404, e.getMessage());
        }
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


    @PutMapping("/updateRecipeById")
    public Result updateRecipe(
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String ownerId,
            @RequestParam ArrayList<String> instructions,
            @RequestParam String[] ingredientNames,
            @RequestParam String[] ingredientQuantities,
            @RequestParam String cuisineName
    ) {
        // Create a new list to store each ingredient item.
        ArrayList<IngredientItem> ingredients = new ArrayList<>();
        // Loop through all ingredient names and add each ingredient and corresponding quantity to the list.
        for (int i = 0; i < ingredientNames.length; i++) {
            ingredients.add(new IngredientItem(title, new Ingredient(ingredientNames[i], Double.parseDouble(ingredientQuantities[i]))));
        }
        // if the database contains a cuisine with the given name then they may add the recipe
        if (cuisineService.containsCuisineWithName(cuisineName)) {
            // find the cuisine object associated with the cuisine title
            Cuisine cuisine = cuisineService.findCuisineWithName(cuisineName);
            // Create a new recipe
            Recipe recipe = new Recipe(title, description, ownerId, instructions, ingredients, cuisine);
            // update the recipe
            recipeService.updateRecipeById(recipe);
            return Result.success(recipe);
        } else {
            return Result.fail(400, "the cuisine you are trying to add does not exist. Please choose a valid cuisine.");
        }

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
     * Deletes a recipe from the database given a unique recipe ID.
     * @param recipeID - the String representation of the recipe ID.
     */
    @DeleteMapping("/deleteRecipeByID")
    public Result deleteRecipeByID(@RequestParam String recipeID) {
        recipeService.deleteRecipeById(recipeID);
        return Result.success(null);
    }


    /**
     *  find a meal tag list from one recipe
     * @param recipeId id of the recipe
     * @return meal item list that contains the tags
     * @throws NotFoundDBException
     */
    @GetMapping("/getmealtypebyrecipename")
    public Result getMealTypesByRecipeName(@RequestParam String recipeId) throws NotFoundDBException {
        Recipe recipe = recipeService.findRecipeById(recipeId);

        List<MealItem> mealItems = recipe.getMealItems();
        return Result.success(mealItems);
    }
    /**
     *  find a ingredient list from one recipe
     * @param recipeId id of the recipe
     * @return ingredient item list that contains the ingredient
     * @throws NotFoundDBException
     */
    @PostMapping("/getingredientlistbyrecipe")
    public Result getIngredientListByRecipe(@RequestParam String recipeId) throws NotFoundDBException {
        Recipe recipe = recipeService.findRecipeById(recipeId);

        List<IngredientItem> ingredients = recipe.getIngredients();
        return Result.success(ingredients);
    }

    /**
     * check if the person has writing access to the recipe now: [only author & invited users & admins] have the write
     * access
     *
     * @param userId   userId from "t_recipe"/ _id from "t_admin" /group user id from "t_recipe"'s list
     * @param recipeId recipe id
     *
     * @return boolean variable that shows if it is writable
     */
    @GetMapping("/getUserWriteAccessById")
    public Result getUserWritableAccess(@RequestParam String userId, @RequestParam String recipeId) {
        return Result.success(recipeService.isWritable(userId, recipeId));
    }

    /**
     * change the read permission on one recipe
     * @param opt "private'/'public'
     * @param recipeId _id of the recipe
     * @param userId _id of the user/admin
     * @return boolean
     */
    @PostMapping("/changePermissionsOnRecipe")
    public Result changeReadPermissionOnRecipe(@RequestParam String opt, @RequestParam String recipeId, @RequestParam String userId) {
        return Result.success(recipeService.changeReadAccess(opt, recipeId, userId));
    }

}
