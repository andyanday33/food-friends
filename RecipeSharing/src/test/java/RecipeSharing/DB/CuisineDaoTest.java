package RecipeSharing.DB;

import RecipeSharing.logic.Cuisine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class CuisineDaoTest {

    @Test
    void addOneCuisine() {
        CuisineDao cuisineDao = new CuisineDao();
        cuisineDao.addOneCuisine(new Cuisine("Chinese"));
    }
}