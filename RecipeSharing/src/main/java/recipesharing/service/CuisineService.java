package recipesharing.service;

import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import org.springframework.stereotype.Service;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.db.CuisineDao;
import recipesharing.logic.Cuisine;
import recipesharing.logic.Ingredient;
import recipesharing.logic.Recipe;
import recipesharing.vo.RecipesCuisineVo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
@Service
public class CuisineService {

    @Autowired
    CuisineDao cuisineDao;

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Cuisine> getAllCuisines() throws NotFoundDBException {
        List<Cuisine> cuisineList = cuisineDao.getAllCuisines();
        if (cuisineList.isEmpty()) {
            throw new NotFoundDBException("There are no cuisines.");
        }
        return cuisineList;
    }

    /**
     * Checks if a cuisine given by the passed id already exists in the database.
     * @param id - the unique cuisine id.
     * @return true if it does, false if not.
     */
    public boolean containsCuisineWithId(String id) {
        try {
            // Get the list of all cuisines (contains cuisine id and name)
            List<Cuisine> cuisineList = getAllCuisines();
            // for each cuisine check if the passed cusine id matches one in the list
            for (Cuisine cuisine : cuisineList) {
                // if it matches then the cuisine is already in the list
                if (cuisine.getId().equals(id)) {
                    return true;
                }
            }
            // cuisine is not in the list
            return false;
        } catch (NotFoundDBException e) {
            System.out.println("Cuisine list is empty");
            return false;
        }
    }

    /**
     * Checks if a cuisine given by the passed name already exists in the database.
     * @param name - the name of the cuisine.
     * @return true if it does, false if not.
     */
    public boolean containsCuisineWithName(String name) {
        try {
            // Get the list of all cuisines (contains cuisine id and name)
            List<Cuisine> cuisineList = getAllCuisines();
            // for each cuisine check if the passed cusine name matches one in the list
            for (Cuisine cuisine : cuisineList) {
                // if it matches then the cuisine is already in the list
                if (cuisine.getName().equals(name)) {
                    return true;
                }
            }
            // cuisine is not in the list
            return false;
        } catch (NotFoundDBException e) {
            System.out.println("Cuisine list is empty");
            return false;
        }
    }

    public void addOneCuisine(Cuisine cuisine) {
        cuisineDao.addOneCuisine(cuisine);
    }

    public void delOneCuisine(Cuisine cuisine){
        cuisineDao.delOneCuisine(cuisine);
    }

    public void delOneCuisine(String cuisineId){
        cuisineDao.delOneCuisine(cuisineId);
    }


    public List<RecipesCuisineVo> findRecipesBycuisineId(String cuisineId){
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("t_cuisine")//Associated From the foreign Table
                .localField(cuisineId)//Related fields in the main table (t_recipe)
                .foreignField("_id")// Fields associated from the main table (t_recipe)
                .as("cuisineRecipes");//Search result name
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation);

        AggregationResults<RecipesCuisineVo> results1 = mongoTemplate.aggregate(aggregation,
                "t_recipe", RecipesCuisineVo.class);

        ArrayList<RecipesCuisineVo> recipesCuisineVos = new ArrayList<>();
        for (RecipesCuisineVo result:results1
        ) {
            if(result.getCuisineId()!=null && result.get_id()!=null)
            {
                recipesCuisineVos.add(result);
            }
        }
        return recipesCuisineVos;
    }
}
