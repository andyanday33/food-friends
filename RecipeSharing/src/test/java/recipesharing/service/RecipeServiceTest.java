package recipesharing.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.logic.Recipe;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
@SpringBootTest
class RecipeServiceTest {

    @Autowired
    RecipeService recipeService;
    @Test
    void authorIsWritable() {

        String authorId = "authorId";
        String recipeId = "6249dbf1136cfb0263539a50";

        Boolean writable = recipeService.isWritable(authorId, recipeId);
        System.out.println(writable);
    }

    @Test
    void adminIsWritable() {

        String adminId = "624a8505db4d1e7eefe7bc8f";
        String recipeId = "6249dbf1136cfb0263539a50";

        Boolean writable = recipeService.isWritable(adminId, recipeId);
        System.out.println(writable);
    }

    @Test
    void groupUserIsWritable() {

        String groupUserId = "groupuser1";
        String recipeId = "6249dbf1136cfb0263539a50";

        Boolean writable = recipeService.isWritable(groupUserId, recipeId);
        System.out.println(writable);
    }

    @Test
    void changePermissionsOnRecipe() {
        String recipeId = "6249dbf1136cfb0263539a50";
        String adminId = "624a8505db4d1e7eefe7bc8f";
        String opt ="private";

        assertTrue(recipeService.changeReadAccess(opt, recipeId, adminId));
    }

//    @Test
//    void test() throws NotFoundDBException {
//        Recipe recipeById = recipeService.findRecipeById("624c16225f659e0f5bd81f92");
//        System.out.println(recipeById);
//    }
}