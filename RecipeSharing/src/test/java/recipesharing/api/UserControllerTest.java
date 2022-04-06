package recipesharing.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

/**
 * Tests the admin related API methods.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    WebTestClient client;

    /**
     * Tests that users can log in.
     */
    @Test
    void userLogin() {
        URI uri = UriComponentsBuilder.fromPath("/user/login")
                .queryParam("email", "test2@st-andrews.ac.uk")
                .queryParam("password", "test password")
                .build()
                .toUri();

        client.post().uri(uri).accept(MediaType.APPLICATION_JSON)
                .exchange();
    }

    /**
     * Tests that users can log out.
     */
    @Test
    void userLogout() {
        client.post().uri("/user/logout").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    /**
     * Tests that users can register.
     */
    @Test
    void register() {
    }

    /**
     * Tests JWT.
     */
    //TODO potentially remove if not necessary.
    @Test
    void test1() {
    }

    /**
     * Tests that users can log in with JWT.
     */
    @Test
    void userLoginWithJWT() {
    }
}
