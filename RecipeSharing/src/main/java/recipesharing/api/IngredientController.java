package recipesharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import recipesharing.logic.Ingredient;
import recipesharing.service.IngredientService;
import recipesharing.vo.Result;

/**
 *
 */
@RestController
public class IngredientController {

    @Autowired
    IngredientService ingredientService;

    // *** Ingredient  related API endpoints *** //
    /**
     * Returns a list containing all the ingredients stored in the database.
     * @return A List of ingredients.
     * TODO Does not work! Breaks at .findAllIngredients!
     */
    @GetMapping("/findAllIngredients")
    public Result getAllIngredients() {
        return Result.success(ingredientService.findAllIngredients());
    }

    /**
     * Returns an ingredient object based on the ingredient name given as a String.
     * @return ingredient object.
     */
    @GetMapping("/findOneIngredient")
    public Result getOneIngredient(@RequestParam String ingredientName) {
        return Result.success(ingredientService.findOneIngredient(ingredientName));
    }


}
