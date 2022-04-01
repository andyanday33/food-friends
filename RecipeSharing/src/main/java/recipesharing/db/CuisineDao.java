package recipesharing.db;

import recipesharing.logic.Admin;
import recipesharing.logic.Cuisine;
import recipesharing.logic.Cuisine;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Data Access Object for Cuisine
 */
@Repository
public class CuisineDao {


    @Autowired
    MongoTemplate mongoTemplate;


    public List<Cuisine> findAllCuisines() {
        return mongoTemplate.findAll(Cuisine.class);
    }

    public Cuisine findOneCuisine(String title) {
        Query query = Query.query(Criteria.where("title").is(title));
        return mongoTemplate.findOne(query, Cuisine.class);
    }

    public void addOneCuisine(Cuisine cuisine) {
        mongoTemplate.save(cuisine, "Cuisine");
    }

    public void deleteCuisineByTitle(String title) {
        Query query = Query.query(Criteria.where("title").is(title));

        mongoTemplate.remove(query, Cuisine.class);

    }

    public void updateCuisineByTitle(Cuisine cuisine) {
        Query query = Query.query(Criteria.where("title").is(cuisine.getTitle()));
        Update update = new Update();

        UpdateResult upsert = mongoTemplate.upsert(query, update, Cuisine.class);

    }

}
