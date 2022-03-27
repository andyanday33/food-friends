package RecipeSharing.API;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RestController
public class RecipeSharingApi {

    /**
     * If user is unsure of how to use the API then may access the base root.
     * @return Returns instructions on how to access /api for more information.
     */
    @GetMapping("/")
    public String index() {
        return "Use GET /api for information on how to use the API.";
    }

    /**
     * Returns a description of how the API works
     * @return
     */
    @GetMapping("/api")
    public String apiDescription() {
        return "description of api";
    }

    @GetMapping("/getRecipe/{recipeID}")
    public void getRecipe() {

    }

    @PostMapping("/createRecipe")
    public void createRecipe(
            // Change from String to Cuisine
            @RequestParam(name = "cuisine") String cuisineType,
            // change to Ingredient[]
            @RequestParam(name = "ingredients") String[] ingredients,
            @RequestParam(name = "title") String title,
            @RequestParam(name = "description") String description
    ) {
        // create new recipe. Store in DB?
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
    public void getCuisines() {

    }

    @PostMapping("/changePermissionsOnRecipe/{user}/{recipeID}")
    public void changeUserPermissionsOnRecipe() {

    }

    @GetMapping("/getRecipesWithIngredient/{ingredient}")
    public void getRecipesWithIngredient() {

    }



}
