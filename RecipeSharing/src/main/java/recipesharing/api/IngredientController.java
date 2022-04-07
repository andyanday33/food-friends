package recipesharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.logic.Ingredient;
import recipesharing.logic.Unit;
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

    /**
     * Searches for all ingredients and returns them as a list.
     * If the ingredients cannot be found, a fail result will be returned.
     * @return The result of the request (200 OK or 404 NOT FOUND) and the list of ingredients if found.
     */
    @GetMapping("/findAllIngredients")
    public Result getAllIngredients() {
        try {
            return Result.success(ingredientService.findAllIngredients());
        } catch (NotFoundDBException e) {
            return Result.fail(404, e.getMessage());
        }
    }

    /**
     * Add an ingredient to the database.
     * @param ingredientName the name of ingredient
     * @param quantityToDouble the string quantity of ingredient
     * @param unitName the unit name
     * @return
     */
    @PostMapping("/addIngredient")
    public Result addIngredient(@RequestParam String ingredientName,
                                @RequestParam String quantityToDouble,
                                @RequestParam String unitName) {

        //parse the json string
        Double quantity = Double.parseDouble(quantityToDouble);
        //TODO consider how to include Unit enum
        Ingredient ingredient = new Ingredient(ingredientName, quantity);
        ingredientService.addOneIngredient(ingredient);
        return Result.success(ingredient);
    }

    /**
     * Deletes an ingredient from the database if exists.
     * @param title - the title/name of the ingredient.
     * @return The result of the request.
     */
    @DeleteMapping("/deleteIngredientByTitle")
    public Result deleteIngredientByTitle(@RequestParam String title) {
        ingredientService.deleteIngredientByTitle(title);
        return Result.success(null);
    }
}
