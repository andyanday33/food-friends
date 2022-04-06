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
    @Test
    void getAllCuisines() {
        try {
            List<Cuisine> cuisines = cuisineService.getAllCuisines();
            cuisines.forEach(System.out::println);
            assertTrue(cuisines.size() > 0);
        } catch (NotFoundDBException e) {
            e.printStackTrace();
        }

    }

    @Test
    void addOneCuisine() {
        Cuisine cuisine = new Cuisine("Japanese");
        cuisineService.addOneCuisine(cuisine);

        //Tests that the test cuisine has been added to the database.
        try {
            List<Cuisine> returned = cuisineService.getAllCuisines();
            assertTrue(returned.contains(cuisine));

            //delete test cuisine.
            Cuisine testCuisine = returned.get(0);
            cuisineService.delOneCuisine(testCuisine);
        } catch (NotFoundDBException e) {
            e.printStackTrace();
        }

    }

    @Test
    void delOneCuisine() {

        Cuisine cuisine = new Cuisine("Japanese");
        cuisineService.addOneCuisine(cuisine);

        //Tests that the test cuisine has been added to the database.
        try {
            List<Cuisine> returned = cuisineService.getAllCuisines();
            //delete test cuisine.
            Cuisine testCuisine = returned.get(0);
            cuisineService.delOneCuisine(testCuisine);
            List<Cuisine> del = cuisineService.getAllCuisines();
            //check deleted
            assertFalse(del.contains(testCuisine));
        } catch (NotFoundDBException e) {
            e.printStackTrace();
        }

    }
}