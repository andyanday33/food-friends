package recipesharing.api;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import recipesharing.api.RecipeController;
import static org.junit.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminControllerTest {

    @Autowired
    WebTestClient client;

    @Test
    public void testLogin() {

    }

    @Test
    public void testLogout() {

    }

    /**
     * Gets all admins from the API and check it was successful.
     */
    //TODO add or test some layer of authentication here to prevent a random user getting all admin details.
    @Test
    public void testGetAllAdmins() {
        client.get().uri("/admin/getAllAdmins").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("success", "true");
    }
}
