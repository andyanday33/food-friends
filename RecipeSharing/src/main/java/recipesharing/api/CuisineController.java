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
            cuisineService.containsCuisine();
            return Result.success(cuisineService.getAllCuisines());
        } catch (NotFoundDBException e) {
            return Result.fail(404, e.getMessage());
        }
    }

    /**
     * Add a new cuisine to the database.
     */
    @PostMapping("/addOneCuisine")
    public Result addOneCuisine(@RequestParam String name) {
        // TODO Check if cuisine already exists in DB first!!
        Cuisine cuisine = new Cuisine(name);
        cuisineService.addOneCuisine(cuisine);
        return Result.success(null);
    }

    /**
     * Delete cuisine by id if the cuisine exists.
     *
     * @param id recipe id
     */
    @DeleteMapping("/deleteCuisineById")
    public Result deleteCuisineByID(@RequestParam String id) {
        cuisineService.delOneCuisine(id);
        return Result.success(null);
    }

    /**
     * get a list of recipes that has the same cuisine id
     * @param cuisineId cuisine id, from the 'recipe table' as a foreign key
     * @return a list of recipes
     */
    @PostMapping("/getRecipesByCuisine")
    public Result getRecipesByCuisineId(@RequestParam String cuisineId) {
        return Result.success(cuisineService.findRecipesBycuisineId(cuisineId));
    }
}


