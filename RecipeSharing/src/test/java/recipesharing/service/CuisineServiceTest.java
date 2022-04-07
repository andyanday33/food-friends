package recipesharing.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.db.CuisineDao;
import recipesharing.logic.Cuisine;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */

@SpringBootTest
class CuisineServiceTest {

    @Autowired
    CuisineService cuisineService;

    /**
     * Tests that if there are cuisines in the database, that they can be returned a list.
     */
    @Test
    void getAllCuisines() {
        try {
            List<Cuisine> cuisines = cuisineService.getAllCuisines();
            assertTrue(cuisines.size() > 0);
        } catch (NotFoundDBException e) {
            e.printStackTrace();
        }

    }

    /**
     * Tests that a cuisine can be added to the database as expected.
     */
    @Test
    void addOneCuisine() {
        Cuisine cuisine = new Cuisine("Japanese");
        cuisineService.addOneCuisine(cuisine);

        //Tests that the test cuisine has been added to the database.
        try {
            List<Cuisine> returned = cuisineService.getAllCuisines();
            assertTrue(returned.contains(cuisine));

            //delete test cuisine.
            cuisineService.delOneCuisine(cuisine);
        } catch (NotFoundDBException e) {
            e.printStackTrace();
        }

    }

    /**
     * Tests that a cuisine can be deleted from the database as expected.
     */
    @Test
    void delOneCuisine() {
        // create a new cuisine and add to the database
        Cuisine cuisine = new Cuisine("Japanese");
        cuisineService.addOneCuisine(cuisine);

        //Tests that the test cuisine has been added to the database.
        try {
            List<Cuisine> returned = cuisineService.getAllCuisines();
            //delete test cuisine.
            cuisineService.delOneCuisine(cuisine);
            List<Cuisine> del = cuisineService.getAllCuisines();
            //check deleted
            assertFalse(del.contains(cuisine));
        } catch (NotFoundDBException e) {
            e.printStackTrace();
        }

    }
}


