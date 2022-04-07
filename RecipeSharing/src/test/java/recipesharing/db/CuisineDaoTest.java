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

    /**
     * Checks that the database updates the list of cuisines when they are added or deleted.
     */
    @Test
    void getAllCuisines() {
        // create new cuisine and add it to the database
        Cuisine cuisine = new Cuisine("Caribbean");
        cuisineDao.addOneCuisine(cuisine);
        List<Cuisine> cuisines = cuisineDao.getAllCuisines();
        // check that the cuisine is in the list of cuisines
        assertTrue(cuisines.contains(cuisine));
        // delete the cuisine
        cuisineDao.delOneCuisine(cuisine);
        cuisines = cuisineDao.getAllCuisines();
        // Check that the cuisine is no longer in the database
        assertFalse(cuisines.contains(cuisine));
    }

    @Test
    void addOneCuisine() {

        Cuisine cuisine = new Cuisine("Icelandic");
        cuisineDao.addOneCuisine(cuisine);

        //Tests that the test cuisine has been added to the database.
        List<Cuisine> returned = cuisineDao.getAllCuisines();
        // check that the cuisine is in the list of cuisines
        assertTrue(returned.contains(cuisine));

        //delete test cuisine.
        cuisineDao.delOneCuisine(cuisine);
        returned = cuisineDao.getAllCuisines();
        // Check that the cuisine is no longer in the database
        assertFalse(returned.contains(cuisine));
    }






}

