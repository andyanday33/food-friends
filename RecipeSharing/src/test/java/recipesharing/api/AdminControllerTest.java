package recipesharing.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

/**
 * Tests the admin related API methods.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AdminControllerTest {

    @Autowired
    WebTestClient client;

    /**
     * Tests that an admin can log in.
     */
    @Test
    public void testLogin() {

    }

    /**
     * Tests that the logout method in the API is called successfully.
     */
    @Test
    public void testLogout() {
        client.post().uri("/admin/logout").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    /**
     * Gets all admins from the API and check it was successful.
     */
    //TODO potentially add some layer of authentication here to prevent a random user getting all admin details.
    @Test
    public void testGetAllAdmins() {
        client.get().uri("/admin/getAllAdmins").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("success", "true");
    }
}
