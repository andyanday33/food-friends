package recipesharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.db.IngredientDao;
import recipesharing.logic.Ingredient;

import java.util.List;

/**
 *
 */
@Service
public class IngredientService {

    @Autowired
    IngredientDao ingredientDao;


    public List<Ingredient> findAllIngredients() throws NotFoundDBException {
        List<Ingredient> ingredientList = ingredientDao.findAllIngredients();
        if (ingredientList.isEmpty()) {
            throw new NotFoundDBException("There are no ingredients.");
        }
        return ingredientList;
    }

    public Ingredient findOneIngredient(String name) throws NotFoundDBException {
        Ingredient ingredient = ingredientDao.findOneIngredient(name);
        if (ingredient == null) {
            throw new NotFoundDBException("The ingredient " + name + "cannot be found in the database.");
        }
        return ingredient;
    }

    public void addOneIngredient(Ingredient ingredient)  {
        ingredientDao.addOneIngredient(ingredient);
    }

    public void deleteIngredientByTitle(String title){
        ingredientDao.deleteIngredientByTitle(title);
    }

    public void updateIngredientByTitle(Ingredient ingredient, String newTitle, Double newQuantity){
        ingredientDao.updateIngredientByTitle(ingredient, newTitle, newQuantity);
    }

}
