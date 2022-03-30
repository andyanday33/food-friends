package RecipeSharing.DB;

import RecipeSharing.logic.Ingredient;
import RecipeSharing.logic.Meal;
import RecipeSharing.logic.Recipe;

import RecipeSharing.logic.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MealDaoTest {

    @Autowired
    MealDao mealDao;

    @Autowired
    MongoTemplate mongoTemplate;

/*    @Test
    void findAllMeal() {
        mealDao.findAllMeal();

        mongoTemplate.findAll(Ingredient.class);
    }

    @Test
    void findOneMealByTitle() {
    }

    @Test
    void addOneMeal() {

        Recipe recipe1 = new Recipe("recipe1");
        Recipe recipe2 = new Recipe("recipe2");
        List<Recipe> recipes = new ArrayList<>();
        recipes.add(recipe1);
        recipes.add(recipe2);

        Meal meal = new Meal("pizza", recipes);
        mealDao.addOneMeal(meal);
    }

    @Test
    void updateOneMeal() {
    }

    @Test
    void deleteOneMealById() {
    }*/
}