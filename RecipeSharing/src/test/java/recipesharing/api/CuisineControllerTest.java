package recipesharing.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import recipesharing.api.RecipeController;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests API methods in CuisineController class.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CuisineControllerTest {

    @Autowired
    WebTestClient client;

    /**
     * Tests all cuisines can be retrieved.
     */
    @Test
    public void testGetAllCuisines() {
        client.get().uri("/getAllCuisines")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    /**
     * Tests that a cuisine can be added.
     */
    @Test
    public void testAddCuisineFail() {
        client.post().uri("/addOneCuisine?name=Spannish")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("success").isEqualTo("false")
                .jsonPath("code").isEqualTo("400");
    }

    /**
     * Test that a cuisine can be deleted.
     */
    @Test
    public void deleteCuisineById() {

    }

    /**
     * Tests that recipes associated with that cuisine can be retrieved.
     */
    @Test
    public void getRecipesByCuisineId() {

    }
}