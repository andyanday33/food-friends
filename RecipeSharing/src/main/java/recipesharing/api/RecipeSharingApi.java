package recipesharing.api;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import recipesharing.db.CuisineDao;
import recipesharing.db.IngredientDao;
import recipesharing.logic.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class RecipeSharingApi {

    @Autowired
    IngredientDao ingredientDao;
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

    /**
     * Returns a recipe given a unique recipe ID
     */
    @GetMapping("/getRecipe/{recipeID}")
    public void getRecipe() {

    }

    /**
     * For testing purposes to see if requestbody works as expected.
     * Ignore this method.
     * @param title
     * @param ingredients
     * @return
     */
    @PostMapping("/createIngredientsList")
    public Ingredient[] createIngredientsList (
            @RequestParam String title,
            @RequestBody Ingredient[] ingredients
    ) {
        ArrayList<Ingredient> ingredientList = new ArrayList<>();
        for (Ingredient i : ingredients) {
            System.out.println(i.getTitle() + "," + i.getQuantity());
            ingredientList.add(new Ingredient(i.getTitle(), i.getQuantity()));
        }
        for (Ingredient ingr : ingredientList) {
            System.out.println(ingr);
        }
        return ingredients;
    }

    /**
     * Create a new recipe.
     * @param title
     * @param description
     * @param ownerName
     * @param ownerEmail
     * @param instructions
     * @param ingredientNames
     * @param ingredientQuantities
     * @param mealType
     * @param cuisineTitle
     * @return
     */
    @PostMapping("/createRecipe")
    public Recipe createRecipe(
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
        return recipe;
        //new Recipe
        // recipe.set,,,,
        //recipeDao.save....()
    }


    @GetMapping("/getRecipe/{user}")
    public void getRecipesByUser(@PathVariable String user) {
        //change from String to User
        // return list of recipes

    }

    @GetMapping("/getRecipesByCuisine/{cuisine}")
    public void getRecipesByCuisine() {

    }

    @GetMapping("/getCuisines")
    public List<Cuisine> getCuisines() {
        // attempting to find all cuisines. currently doesn't work
        CuisineDao cuisineDao = new CuisineDao();
        List<Cuisine> allCuisines = cuisineDao.findAllCuisines();
        return allCuisines;
    }

    @GetMapping("/findAllIngredients")
    public List<Ingredient> getAllIngredients() {
        System.out.println("test 2");
        List<Ingredient> allIngredients = ingredientDao.findAllIngredients();
        System.out.println("test 3");
        return allIngredients;
    }

    /**
     *
     * @return
     */
    @GetMapping("/findOneIngredient")
    public Ingredient getOneIngredient() {
        System.out.println("test 2");
        Ingredient ingredient = ingredientDao.findOneIngredient("salt");
        System.out.println("test 3");
        return ingredient;
    }

    @PostMapping("/changePermissionsOnRecipe/{user}/{recipeID}")
    public void changeUserPermissionsOnRecipe() {

    }

    @GetMapping("/getRecipesWithIngredient/{ingredient}")
    public void getRecipesWithIngredient() {

    }



}
