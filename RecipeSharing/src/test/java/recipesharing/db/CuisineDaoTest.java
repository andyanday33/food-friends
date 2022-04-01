package recipesharing.db;

import recipesharing.logic.Cuisine;
import com.mongodb.client.MongoClient;
import com.mongodb.client.internal.MongoClientImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
@SpringBootTest
class CuisineDaoTest {

    @Autowired
    MongoTemplate mongoTemplate;
    @Test
    void addOneCuisine() {

        CuisineDao cuisineDao = new CuisineDao();
//        cuisineDao.addOneCuisine(new Cuisine("Chinese"));
        mongoTemplate.save(new Cuisine("Chinese"), "Cuisine");

    }
}