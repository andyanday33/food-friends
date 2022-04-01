package RecipeSharing.DB;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class MealDaoTest {

    @Autowired
    MealDao mealDao;
    @Test
    void findAllMeal() {
        mealDao.findAllMeal();
    }
}