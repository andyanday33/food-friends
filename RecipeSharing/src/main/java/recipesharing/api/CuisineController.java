package recipesharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.logic.Cuisine;
import recipesharing.logic.Recipe;
import recipesharing.service.CuisineService;
import recipesharing.service.RecipeService;
import recipesharing.vo.Result;

import java.util.List;

/**
 * Contains cuisine related endpoints for the API.
 */
@CrossOrigin(origins = "*")
@RestController
public class CuisineController {

    @Autowired
    CuisineService cuisineService;

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
     * Error message will be returned if the cuisine already exists in the database.
     */
    @PostMapping("/addOneCuisine")
    public Result addOneCuisine(@RequestParam String name) {
        // If the database does not contain the cuisine then add it
        if (!cuisineService.containsCuisineWithName(name)) {
            Cuisine cuisine = new Cuisine(name);
            cuisineService.addOneCuisine(cuisine);
            return Result.success(null);
        }
        // Otherwise return an error message as the cuisine already exists.
        else {
            return Result.fail(400, "Cannot add cuisine " + name + " as it already exists.");
        }

    }

    /**
     * Delete cuisine by id if the cuisine exists.
     * Error message will be returned if the cuisine does not exist in the DB.
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

}
