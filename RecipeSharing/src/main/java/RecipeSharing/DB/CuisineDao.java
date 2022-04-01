package RecipeSharing.DB;

import RecipeSharing.logic.Admin;
import RecipeSharing.logic.Cuisine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object for Cuisine
 */
@Repository
public class CuisineDao {
    @Autowired
    MongoTemplate mongoTemplate;

    public void addOneCuisine(Cuisine cuisine){
        mongoTemplate.save(cuisine, "Cuisine");
    }
}
