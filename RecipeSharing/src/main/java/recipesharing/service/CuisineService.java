package recipesharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipesharing.db.CuisineDao;
import recipesharing.logic.Cuisine;

import java.util.List;

/**
 *
 */
@Service
public class CuisineService {

    @Autowired
    CuisineDao cuisineDao;

    public List<Cuisine> getAllCuisines(){
        return cuisineDao.getAllCuisines();
    }

    public void addOneCuisine(Cuisine cuisine){
        cuisineDao.addOneCuisine(cuisine);
    }

    public void delOneCuisine(Cuisine cuisine){
        cuisineDao.delOneCuisine(cuisine);
    }


}
