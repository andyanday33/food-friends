package recipesharing.api;

<<<<<<< HEAD
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import recipesharing.logic.Cuisine;
import recipesharing.service.CuisineService;
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.logic.Cuisine;
import recipesharing.logic.Recipe;
import recipesharing.service.CuisineService;
import recipesharing.service.RecipeService;
>>>>>>> origin/main
import recipesharing.vo.Result;

import java.util.List;

/**
<<<<<<< HEAD
 *
 */
=======
 * Contains cuisine related endpoints for the API.
 */
@CrossOrigin(origins = "*")
>>>>>>> origin/main
@RestController
public class CuisineController {

    @Autowired
    CuisineService cuisineService;

<<<<<<< HEAD
    @GetMapping("/findAllCuisines")
    public Result getAllCuisines(){
        List<Cuisine> allCuisines = cuisineService.getAllCuisines();
        return Result.success(allCuisines);
    }

    @PostMapping("/findRecipesByCuisine")
    public Result getRecipesByCuisineName(@RequestParam String cuisineName){

        return null;
    }


    @PostMapping("/addOneMoreCuisine")
    public Result addOneCuisine(@RequestParam String cuisineName){
        cuisineService.addOneCuisine(new Cuisine(cuisineName));
=======
    // *** Cuisine related API endpoints *** //

    /**
     * Find all cuisines in the database.
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
     * @param id recipe id
     */
    @DeleteMapping("/deleteCuisineById")
    public Result deleteCuisineByID(@RequestParam String id) {
        cuisineService.delOneCuisine(id);
>>>>>>> origin/main
        return Result.success(null);
    }

}
