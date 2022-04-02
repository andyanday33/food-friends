package recipesharing.api;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipesharing.db.*;
import recipesharing.logic.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class RecipeSharingApi {

    @Autowired
    IngredientDao ingredientDao;
    RecipeDao recipeDao;
    CuisineDao cuisineDao;
    UserDao userDao;
    AdminDao adminDao;
    MealDao mealdao;

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
    public Recipe getRecipe(@RequestParam String title) {
        Recipe recipe = recipeDao.findOneRecipeByTitle(title);
        return recipe;
    }


    /**
     * Create a new recipe.
     * @param title - title of recipe
     * @param description - description of recipe
     * @param ownerName - the recipe owner's name
     * @param ownerEmail - the owner's email
     * @param instructions - a list of instructions
     * @param ingredientNames - a list of ingredients
     * @param ingredientQuantities - a list of quantities for each ingredient
     * @param mealType - type of meal (lunch, dinner, etc)
     * @param cuisineTitle - type of cuisine
     * @return
     */
    @PostMapping("/createRecipe")
    public String createRecipe(
            // Expects the following params! They can be null though.
            // title of the recipe
            @RequestParam String title,
            // description of the recipe
            @RequestParam String description,
            // name and email of the owner of the recipe
            @RequestParam String ownerName,
            @RequestParam String ownerEmail,
            // list of instructions for the recipe
            @RequestParam String[] instructions,
            // list of ingredient names and corresponding list of ingredient quantities
            @RequestParam String[] ingredientNames,
            @RequestParam String[] ingredientQuantities,
            // type of meal (lunch, dinner, etc)
            @RequestParam String mealType,
            // type of cuisine (italian, chinese, etc)
            @RequestParam String cuisineTitle
    ) {
        // Converting all passed in strings to their appropriate type (Owner, Cuisine, etc)
        // No need to convert title, description as they are already of type String.
        // TODO validate the input! i.e. check owner info is correct

        //create a new owner
        User newOwner = new User(ownerName, ownerEmail);

        // Create a new list of ingredients and their corresponding quantities
        // This assumes that ingredientNames and quantities have the same length have been added correctly
        List<Ingredient> ingredients = new ArrayList<>();
        // TODO create a better solution.
        for (int i = 0; i < ingredientNames.length; i++) {
            // todo currently a double but will all incoming ingredient quantities be a double?
            double quantity = Double.parseDouble(ingredientQuantities[i]);
            ingredients.add(new Ingredient(ingredientNames[i], quantity));
        }

        // Create a new meal
        Meal meal = new Meal(mealType);

        // Create a new Cuisine
        Cuisine cuisine = new Cuisine(cuisineTitle);

        // create new recipe.
        Recipe recipe = new Recipe(title, description, newOwner, instructions, ingredients, meal, cuisine);
        // Can't currently add recipe to db.
        //recipeDao.addOneRecipe(recipe);
        // Does this return an ID from the DB?
        return recipe.getId();
    }


    @GetMapping("/getRecipe/{user}")
    public void getRecipesByUser(@PathVariable String user) {
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
        Cuisine cuisine = new Cuisine(cuisineType);
        //todo needs to be implemented in db
    }

    /**
     * Delete a recipe
     * todo what should we pass in here to delete a recipe?
     * @param recipeID
     */
    @DeleteMapping("/deleteRecipe")
    public void deleteRecipe(@RequestParam String recipeID) {
        // First find the recipe associated with the recipeID
        // todo think we need a method in RecipeDao for finding the recipe by ID
        // then delete it
        //recipeDao.deleteOneRecipeById(recipe);
    }

    // *** Cuisine related API endpoints *** //

    /**
     * Find all cuisines in the database.
     * TODO Does not currently work.
     * @return
     */
    @GetMapping("/getAllCuisines")
    public List<Cuisine> getCuisines() {
        // attempting to find all cuisines. currently doesn't work
        List<Cuisine> allCuisines = cuisineDao.findAllCuisines();
        return allCuisines;
    }

    /**
     * Add a new cuisine to the database.
     * TODO Doesn't work. Throws server error because it can't access addOneCuisine.
     * @param cuisineTitle - the title of the cuisine.
     */
    @PostMapping("/addOneCuisine")
    public void addOneCuisine(@RequestParam String cuisineTitle) {
        // Create a new cuisine. TODO add validation checks.
        Cuisine newCuisine = new Cuisine(cuisineTitle);
        // Add to DB.
        cuisineDao.addOneCuisine(newCuisine);
    }

    /**
     * Delete cuisine by title.
     * TODO Does this delete all cuisines that share the same title?
     * @param title
     */
    @DeleteMapping("/deleteCuisineByTitle")
    public void deleteCuisineByTitle(@RequestParam String title) {
        //TODO check if exists first
        cuisineDao.deleteCuisineByTitle(title);
    }

    // *** Ingredient  related API endpoints *** //
    /**
     * Returns a list containing all the ingredients stored in the database.
     * @return A List of ingredients.
     * TODO Does not work! Breaks at .findAllIngredients!
     */
    @GetMapping("/findAllIngredients")
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> allIngredients = ingredientDao.findAllIngredients();
        return allIngredients;
    }

    /**
     * Returns an ingredient object based on the ingredient name given as a String.
     * @return ingredient object.
     */
    @GetMapping("/findOneIngredient")
    public Ingredient getOneIngredient(@RequestParam String ingredientName) {
        Ingredient ingredient = ingredientDao.findOneIngredient(ingredientName);
        return ingredient;
    }



    // *** Admin related API endpoints *** //

    /**
     * Find all admins in the database and return them as a list.
     * @return a list of admins.
     */
    @GetMapping("/getAllAdmins")
    public List<Admin> getAllAdmins() {
        // TODO This should call the function in adminDao which prints out a system println statement but it doesn't reach that point
        // Breaks before it can be called.
        return adminDao.findAllAdmins();
    }


}
