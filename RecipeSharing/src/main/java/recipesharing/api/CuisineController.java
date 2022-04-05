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
public class CuisineController {

    @Autowired
    CuisineService cuisineService;


    // *** Cuisine related API endpoints *** //

    /**
     * Find all cuisines in the database.
     * TODO not sure this returns cuisines, seems to return recipes
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




//---------------------
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
