package recipesharing.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Tests the ingredient API and its methods.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IngredientControllerTest {

    @Autowired
    WebTestClient client;

    /**
     * Tests that all ingredients can be retrieved.
     */
    @Test
    public void testFindAllIngredients() {
        client.get().uri("/findAllIngredients")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo("true");
    }

    /**
     * Tests that ingredients can be added.
     */
    @Test
    public void testAddIngredient() {
        client.post().uri("/addIngredient?ingredientName=snails tails&quantityToDouble=4.0&unitName=grams")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo("true")
                .jsonPath("$.data.title").isEqualTo("snails tails");
    }

    /**
     * Tests that ingredients can be deleted.
     */
    @Test
    public void testDeleteIngredient() {
        client.post().uri("/addIngredient?ingredientName=pigeon thigh&quantityToDouble=4.0&unitName=grams")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo("true")
                .jsonPath("$.data.title").isEqualTo("pigeon thigh");

        client.delete().uri("/deleteIngredientByTitle?title=pigeon thigh")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo("true");
    }
}
