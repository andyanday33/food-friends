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

    @Test
    public void testIndex() {
        String apiDescription = client.get().uri("/").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
        assertEquals(apiDescription, "Use GET /api for information on how to use the API.");
    }

    @Test
    public void testApiDescription() {
        String apiDescription = client.get().uri("/api").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectBody(String.class)
                .returnResult()
                .getResponseBody();
        assertEquals(apiDescription, "description of api");
    }

    @Test
    public void testCreateNewRecipe() {

    }

    @Test
    public void getRecipeByTitle() {

    }

    @Test
    public void getRecipeById() {

    }

    @Test
    public void getRecipeByAuthorId() {

    }

    @Test
    public void getRecipeAccessById() {

    }

    @Test
    public void changeUserPermissionsOnRecipe() {

    }
    @Test
    public void testDeleteRecipeById() {

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
        String userId = "authId37";
        String recipeId = "624ed6060f30844c15ae1036";

        RecipeController recipeController = new RecipeController();
        recipeController.getUserWritableAccess(userId, recipeId);
    }
}
