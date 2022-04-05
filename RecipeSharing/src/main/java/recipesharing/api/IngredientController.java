package recipesharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.logic.Ingredient;
import recipesharing.service.IngredientService;
import recipesharing.vo.Result;
import recipesharing.vo.TransStatusCode;

import java.util.List;

/**
 * Contains ingredient related endpoints for the API.
 */
@RestController
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    // *** Ingredient  related API endpoints *** //

    /**
     * Searches for all ingredients and returns them as a list.
     * If the ingredients cannot be found, a fail result will be returned.
     * @return The result of the request (200 OK or 404 NOT FOUND) and the list of ingredients if found.
     */
    @GetMapping("/findAllIngredients")
    public Result getAllIngredients()  {
        try {
            return Result.success(ingredientService.findAllIngredients());
        } catch (NotFoundDBException e) {
            return Result.fail(404, e.getMessage());
        }
    }

    /**
     * Returns an ingredient object based on the ingredient name given as a String.
     * If the ingredient does not exist then the fail result is returned.
     * @return the result of the request (200 OK or 404 NOT FOUND) and the ingredient if found.
     */
    @GetMapping("/findOneIngredient")
    public Result getOneIngredient(@RequestParam String ingredientName) {
        try {
            return Result.success(ingredientService.findOneIngredient(ingredientName));
        } catch (NotFoundDBException e) {
            return Result.fail(404, e.getMessage());
        }
    }


}
