package recipesharing.api;

//import org.junit.Test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import recipesharing.api.RecipeController;
import static org.junit.Assert.assertEquals;

@SpringBootTest
public class RecipeControllerTest {
    WebTestClient client;

    @BeforeEach
    void setup() {
        client = WebTestClient.bindToController(new RecipeController()).build(); }

    @Test
    public void invalidGetRequest() {
        client.get().uri("/invalidReq").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }



}
