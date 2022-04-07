package recipesharing.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

    }

    /**
     * Tests that ingredients can be added.
     */
    @Test
    public void testAddIngredient() {

    }

    /**
     * Tests that ingredients can be deleted.
     */
    @Test
    public void testDeleteIngredient() {

    }
}
