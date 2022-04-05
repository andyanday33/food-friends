package recipesharing.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;
import recipesharing.logic.Cuisine;

import java.util.List;

/**
 *
 */
@Repository
public class CuisineDao {

    @Autowired
    MongoTemplate mongoTemplate;

    public List<Cuisine> getAllCuisines(){
        return mongoTemplate.findAll(Cuisine.class, "t_cuisine");
    }

    public void addOneCuisine(Cuisine cuisine){
        mongoTemplate.save(cuisine, "t_cuisine");
    }

    public void delOneCuisine(Cuisine cuisine){
        mongoTemplate.remove(cuisine, "t_cuisine");
    }

    public void delOneCuisine(String cuisineId){
        mongoTemplate.remove(cuisineId, "t_cuisine");
    }
}
