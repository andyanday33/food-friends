package recipesharing.api;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.test.web.reactive.server.WebTestClient;

public class UserControllerTest {
    WebTestClient client;

    @BeforeEach
    void setup() {
        client = WebTestClient.bindToController(new RecipeController()).build();
    }


}
