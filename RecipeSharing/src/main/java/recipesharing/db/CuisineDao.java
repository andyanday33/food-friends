package recipesharing.db;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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

    public List<Cuisine> getAllCuisines() {
        return mongoTemplate.findAll(Cuisine.class, "t_cuisine");
    }

    public void addOneCuisine(Cuisine cuisine) {
        if (!mongoTemplate.exists(new Query(Criteria.where("name").is(cuisine.getName()))
                , Cuisine.class
                , "t_cuisine")) {
            mongoTemplate.save(cuisine, "t_cuisine");
        }
    }

    public void delOneCuisine(Cuisine cuisine) {
        mongoTemplate.remove(cuisine, "t_cuisine");
    }

    public void delOneCuisine(String cuisineId){
        Query query = Query.query(Criteria.where("_id").is(cuisineId));
        mongoTemplate.remove(query, Cuisine.class);
    }
}
