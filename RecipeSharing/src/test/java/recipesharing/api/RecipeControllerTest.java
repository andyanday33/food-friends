package recipesharing.api;

//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import recipesharing.api.RecipeController;

import javax.print.attribute.standard.Media;

import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecipeControllerTest {
    @Autowired
    WebTestClient client;

    @BeforeEach
    void setup() {

    }

    /**
     * Test that a 404 status code is returned when an invalid endpoint is requested.
     */
    @Test
    public void invalidGetRequest() {
        client.get().uri("/invalidReq").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    /*
    @Test
    public void createRecipe() {
        client.post().uri("/createRecipe?title=exampleTitle&description=exampleDescription&ownerId=test1234&instructions=Step 1 - example instruction, Step 2 - example instruction&ingredientNames=Milk, Eggs, Flour&ingredientQuantities=325.2, 2.0, 500.0&mealType=Lunch&cuisineTitle=Italian")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();
    }
     */

    @Test
    public void findListOfRecipesByTitle() {
        client.get().uri("/getRecipeByTitle?title=pancake")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody();

    }

    @Test void findRecipeById() {

    }

    @Test void findRecipesByAuthorId() {

    }


    @Test
    void getUserWritableAccess() {
        String userId = "6249cadaa1f0c07dba837007";
        String recipeId = "6249e0a345eefd38211decab";

        RecipeController recipeController = new RecipeController();
        recipeController.getUserWritableAccess(userId, recipeId);
    }
}
