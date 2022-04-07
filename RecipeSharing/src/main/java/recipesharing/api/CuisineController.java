package recipesharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.web.bind.annotation.*;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.logic.Cuisine;
import recipesharing.logic.Recipe;
import recipesharing.service.CuisineService;
import recipesharing.service.RecipeService;
import recipesharing.vo.RecipesCuisineVo;
import recipesharing.vo.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * Contains cuisine related endpoints for the API.
 */
@CrossOrigin(origins = "*")
@RestController
public class CuisineController {

    @Autowired
    CuisineService cuisineService;
    @Autowired
    RecipeService recipeService;
    @Autowired
    MongoTemplate mongoTemplate;

    // *** Cuisine related API endpoints *** //

    /**
     * Find all cuisines in the database.
     *
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
     * Add a new cuisine to the database. Error message will be returned if the cuisine already exists in the database.
     */
    @PostMapping("/addOneCuisine")
    public Result addOneCuisine(@RequestParam String name) {
        // If the database does not contain the cuisine then add it
        if (!cuisineService.containsCuisineWithName(name)) {
            Cuisine cuisine = new Cuisine(name);
            cuisineService.addOneCuisine(cuisine);
            return Result.success(null);
        }
        // Otherwise, return an error message as the cuisine already exists.
        else {
            return Result.fail(400, "Cannot add cuisine " + name + " as it already exists.");
        }

    }

    /**
     * Delete cuisine by id if the cuisine exists. Error message will be returned if the cuisine does not exist in the
     * DB.
     *
     * @param id recipe id
     */
    @DeleteMapping("/deleteCuisineById")
    public Result deleteCuisineByID(@RequestParam String id) {
        // Check if the database contains the cuisine
        if (cuisineService.containsCuisineWithId(id)) {
            cuisineService.delOneCuisine(id);
            return Result.success(null);
        } else {
            return Result.fail(400, "The cuisine given by id " + id + "does not exist and cannot be deleted.");
        }


    }

    /**
     * get a list of recipes that has the same cuisine id
     *
     * @param recipesCuisineId cuisine id, from the 'recipe table' as a foreign key
     *
     * @return a list of recipes
     */
    @GetMapping("/getRecipesByCuisineId")
    public Result getRecipesByCuisineId(@RequestParam String recipesCuisineId) throws NotFoundDBException {

        List<RecipesCuisineVo> recipesBycuisineId = cuisineService.findRecipesBycuisineId(recipesCuisineId);

        System.out.println(recipesBycuisineId);
        List<Recipe> recipes = new ArrayList<>();
        for (RecipesCuisineVo r : recipesBycuisineId
        ) {
            Recipe recipeById = recipeService.findRecipeById(r.get_id());

            System.out.println(recipeById);

            recipes.add(recipeById);
        }

        return Result.success(recipes);
    }


}


