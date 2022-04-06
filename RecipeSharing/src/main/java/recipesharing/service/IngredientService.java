package recipesharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.db.IngredientDao;
import recipesharing.logic.Ingredient;
import recipesharing.logic.Ingredient;

import java.util.List;

/**
 *
 */
@Service
public class IngredientService {

    @Autowired
    IngredientDao ingredientDao;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Ingredient> findAllIngredients() throws NotFoundDBException {
        List<Ingredient> ingredientList = ingredientDao.findAllIngredients();
        if (ingredientList.isEmpty()) {
            throw new NotFoundDBException("There are no ingredients.");
        }
        return ingredientList;
    }

    public List<Ingredient> findAllIngredientsWithPageLimit(int page, int size) {


        Query query = new Query();
        long count = mongoTemplate.count(query, Ingredient.class);

        Pageable pageable = PageRequest.of(page-1,size);

        return mongoTemplate.find(query.with(pageable).with(Sort.by(Sort.Direction.DESC, "title")), Ingredient.class);
    }

    /**
     * find ingredients with page limits and query
     * the query can specify the name, id etc
     * @param query query condition
     * @param page current
     * @param size the number of records shows up
     * @return ingredient list 
     */
    public List<Ingredient> findAllIngredientsByQueryWithPageLimit(Query query, int page, int size) {

        long count = mongoTemplate.count(query, Ingredient.class);

        Pageable pageable = PageRequest.of(page-1,size);

        return mongoTemplate.find(query.with(pageable).with(Sort.by(Sort.Direction.DESC,"title")), Ingredient.class);
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
