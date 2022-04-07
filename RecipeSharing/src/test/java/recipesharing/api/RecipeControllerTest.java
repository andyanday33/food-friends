package recipesharing.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import recipesharing.api.RecipeController;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        client.post().uri("/createNewRecipe?title=stinky tofu&description=tofu that really stinks&ownerId=auth78&instructions=let it ferment for 50 years&ingredientNames=Tofu&ingredientQuantities=1.0&cuisineName=Japanese")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
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

    @Test
    public void findListOfRecipesByTitle() {
        client.get().uri("/getRecipeByTitle?title=pancake")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody();

    }
}
