package recipesharing.api;

import org.apache.coyote.Response;
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

    @GetMapping("/getRecipe/{recipeID}")
    public void getRecipe() {

    }

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

    @PostMapping("/createRecipe")
    public Recipe createRecipe(
            // Expects the following params! They can be null though.
            @RequestParam String title,
            @RequestParam String description,
            @RequestParam String owner,
            @RequestParam String[] instructions,
            @RequestBody Ingredient[] ingredients,
            @RequestParam String mealType,
            @RequestParam String cuisineTitle
    ) {

        //create a new owner
        //User owner = new User(owner);
        User newOwner = new User();
        // Create a new list of ingredients
        Ingredient ingr1 = new Ingredient("eggs", 2.0);
        Ingredient[] ingrList = new Ingredient[] {ingr1};
        // Create a new meal
        Meal meal = new Meal (mealType);
        // Create a new Cuisine
        Cuisine cuisine = new Cuisine(cuisineTitle);
        // create new recipe.
        Recipe recipe = new Recipe(title, description, newOwner, instructions, ingrList, meal, cuisine);
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
        IngredientDao ingredientDao = new IngredientDao();
        System.out.println("test 2");
        List<Ingredient> allIngredients = ingredientDao.findAllIngredients();
        System.out.println("test 3");
        return allIngredients;
    }

    @PostMapping("/changePermissionsOnRecipe/{user}/{recipeID}")
    public void changeUserPermissionsOnRecipe() {

    }

    @GetMapping("/getRecipesWithIngredient/{ingredient}")
    public void getRecipesWithIngredient() {

    }



}
