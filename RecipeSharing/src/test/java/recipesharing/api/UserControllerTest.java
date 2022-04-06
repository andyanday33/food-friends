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
public class UserControllerTest {

    @Autowired
    WebTestClient client;

    /**
     * Tests that users can be retrieved via their id.
     */
    @Test
    void testGetUserByIdSuccess() {
        client.get().uri("/user/getUserById?id=624d72f04923ce4b64c5e1b5").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    /**
     * Tests that retrieving a non-existent user fails.
     */
    @Test
    void testGetUserByIdFail() {
        client.get().uri("/user/getUserById?id=ojbvijdfbvoj").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("success").isEqualTo("false")
                .jsonPath("code").isEqualTo("404");
    }


    /**
     * Tests that users can be retrieved via their name.
     */
    @Test
    void testGetUserByNameSuccess() {
        client.get().uri("/user/getUserByName?name=JeremyDingDong").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    /**
     * Tests that retrieving a non-existent user fails.
     */
    @Test
    void testGetUserByNameFailure() {
        client.get().uri("/user/getUserByName?name=ojbvijdfbvoj").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("success").isEqualTo("false")
                .jsonPath("code").isEqualTo("404");
    }

    /**
     * Tests that users can be retrieved via their email.
     */
    @Test
    void testGetUserByEmailSuccess() {
        client.get().uri("/user/getUserByEmail?email=JeremyDingDong@st-andrews.ac.uk").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().is2xxSuccessful();
    }

    /**
     * Tests that retrieving a non-existent user fails.
     */
    @Test
    void testGetUserByEmailFailure() {
        client.get().uri("/user/getUserByEmail?email=ojbvijdfbvoj").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("success").isEqualTo("false")
                .jsonPath("code").isEqualTo("404");
    }

    /**
     * Tests that an existing user in the database can log in.
     */
    @Test
    void userSuccessfulLogin() {
        client.post().uri("user/login?email=test2@st-andrews.ac.uk&password=test password").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("success", "true");
    }
    */
    /**
     * Tests that users can log out.
     */
    @Test
    void userUnsuccessfulLogin() {
        client.post().uri("user/login?email=garbage&password=garbage").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("success", "false");
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
     * Tests that an existing user cannot register again.
     */
    @Test
    void register() {
        client.post().uri("user/register?userName=JeremyDingDong&email=JeremyDingDong@st-andrews.ac.uk&password=password").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("success", "false");
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
    //TODO potentially remove if not necessary.
    @Test
    void userLoginWithJWT() {
    }
}
