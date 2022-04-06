package recipesharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.db.CuisineDao;
import recipesharing.logic.Cuisine;
import recipesharing.logic.Ingredient;

import java.util.List;

/**
 *
 */
@Service
public class CuisineService {

    @Autowired
    CuisineDao cuisineDao;

    public List<Cuisine> getAllCuisines() throws NotFoundDBException {
        List<Cuisine> cuisineList = cuisineDao.getAllCuisines();
        if (cuisineList.isEmpty()) {
            throw new NotFoundDBException("There are no cuisines.");
        }
        return cuisineList;
    }

    public boolean containsCuisine() {
        try {
            List<Cuisine> cuisineList = getAllCuisines();
            for (Cuisine cuisine : cuisineList) {
                System.out.println(cuisine.getId());
                System.out.println(cuisine.getName());
            }
            System.out.println(cuisineList);
            return true;
        } catch (NotFoundDBException e) {
            System.out.println("cusine list is empty");
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


}
