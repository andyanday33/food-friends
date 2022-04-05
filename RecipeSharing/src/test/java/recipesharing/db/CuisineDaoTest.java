package recipesharing.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import recipesharing.logic.Admin;
import recipesharing.logic.Cuisine;
import recipesharing.logic.Cuisine;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Testing of Ingredient data access object and its methods.
 */
@SpringBootTest
class CuisineDaoTest {

    @Autowired
    CuisineDao cuisineDao;

    @Autowired
    MongoTemplate mongoTemplate;
    @Test
    void getAllCuisines() {
        List<Cuisine> cuisines = cuisineDao.getAllCuisines();
        cuisines.forEach(System.out::println);
        assertTrue(cuisines.size() > 0);
    }

    @Test
    void addOneCuisine() {
        Cuisine cuisine = new Cuisine("Japanese");
        cuisineDao.addOneCuisine(cuisine);

        //Tests that the test cuisine has been added to the database.
        List<Cuisine> returned = cuisineDao.getAllCuisines();
        assertTrue(returned.contains(cuisine));

        //delete test cuisine.
        Cuisine testCuisine = returned.get(0);
        cuisineDao.delOneCuisine(testCuisine);
    }

    @Test
    void delOneCuisine() {

        Cuisine cuisine = new Cuisine("Japanese");
        cuisineDao.addOneCuisine(cuisine);

        //Tests that the test cuisine has been added to the database.
        List<Cuisine> returned = cuisineDao.getAllCuisines();
        //delete test cuisine.
        Cuisine testCuisine = returned.get(0);
        cuisineDao.delOneCuisine(testCuisine);

        List<Cuisine> del = cuisineDao.getAllCuisines();
        //check deleted
        assertFalse(del.contains(testCuisine));
    }

    @Test
    void del(){

        Query query = Query.query(Criteria.where("_id").is("sdsdsds"));
        mongoTemplate.remove(query, Cuisine.class);

    }
}