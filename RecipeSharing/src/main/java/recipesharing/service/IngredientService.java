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

    public Ingredient findOneIngredient(String name){
        return ingredientDao.findOneIngredient(name);
    }

    public void addOneIngredient(Ingredient ingredient){
        ingredientDao.addOneIngredient(ingredient);
    }

    public void deleteIngredientByTitle(String title){
        ingredientDao.deleteIngredientByTitle(title);
    }

    public void updateIngredientByTitle(Ingredient ingredient, String newTitle, Double newQuantity){
        ingredientDao.updateIngredientByTitle(ingredient, newTitle, newQuantity);
    }

}
