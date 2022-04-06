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
    }

    /**
     * Tests that users can log out.
     */
    @Test
    void userLogout() {
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
