package RecipeSharing.DB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

/**
 * Data Access Object for Admin
 */
@Repository
public class AdminDao {
    @Autowired
    MongoTemplate mongoTemplate;
}
