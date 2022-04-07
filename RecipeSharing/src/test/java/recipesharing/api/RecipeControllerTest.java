package recipesharing.api;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import recipesharing.api.RecipeController;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests the recipe API and its methods.
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecipeControllerTest {

    @Autowired
    WebTestClient client;

    /**
     * Tests that a new recipe can be created.
     */
    @Test
    public void testCreateNewRecipe() {
        client.post().uri("/createNewRecipe?title=stinky tofu&description=tofu that really stinks&ownerId=auth78&instructions=let it ferment for 50 years&ingredientNames=Tofu&ingredientQuantities=1.0&cuisineName=Spannish")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }

    /**
     * Tests that recipes can be retrieved by their title.
     */
    @Test
    public void getRecipeByTitle() {
        client.get().uri("getRecipeByTitle?title=stinky tofu")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo("true")
                .jsonPath("$.data[0].recipeName").isEqualTo("stinky tofu");
    }

    /**
     * Tests that recipes can be retrieved by their ID.
     */
    @Test
    public void getRecipeById() {
        client.get().uri("/getRecipeById?recipeId=624ed6763570fc64f5db9e70")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo("true")
                .jsonPath("$.data.recipeId").isEqualTo("624ed6763570fc64f5db9e70");
    }

    /**
     * Tests that recipes can be retrieved by their author's ID.
     */
    @Test
    public void getRecipesByAuthorId() {
        client.get().uri("/getRecipesByAuthorId?authorId=auth78")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo("true")
                .jsonPath("$.data[0].authorId").isEqualTo("auth78");
    }

    /**
     * Used to test retrieving the read and write access on a recipe using the ID.
     */
    @Test
    public void getRecipeAccessById() {
        client.get().uri("/getRecipeAccessById?accessType=read&recipeId=624ed6763570fc64f5db9e70")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo("true")
                .jsonPath("$.data").isEqualTo("true");

        client.get().uri("/getRecipeAccessById?accessType=write&recipeId=624ed6763570fc64f5db9e70")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("$.success").isEqualTo("true")
                .jsonPath("$.data").isEqualTo("false");
    }

    /**
     * Used to test that recipe permissions can be changed.
     */
    @Test
    public void changeUserPermissionsOnRecipe() {
        //TODO add test when how method is to be implemented is decided. Or delete this test and the method.
    }

    /**
     * Tests that recipes can be deleted using their ID.
     */
    @Test
    public void testDeleteRecipeById() throws JSONException {
        String response = client.post().uri("/createNewRecipe?title=spanneros&description=extra jumpy&ownerId=kermit&instructions=boil them&ingredientNames=frogLegs&ingredientQuantities=2.0&cuisineName=Spannish")
                .accept(MediaType.APPLICATION_JSON)
                .exchange().expectBody(String.class)
                .returnResult()
                .getResponseBody();

        //get id from created recipe
        JSONObject json = new JSONObject(response);
        JSONObject nestedJson = json.getJSONObject("data");
        String id = (String) nestedJson.get("recipeId");

        //delete created recipe
        client.delete().uri("deleteRecipeByID?recipeID=" + id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk();

        //check deleted recipe no longer exists.
        client.get().uri("/getRecipeById?recipeId=" + id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody()
                .jsonPath("code").isEqualTo("404");
    }

    /**
     * Test that a 404 status code is returned when an invalid endpoint is requested.
     */
    @Test
    public void invalidGetRequest() {
        client.get().uri("/invalidReq").accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isNotFound();
    }

    /**
     * Test that all recipes with a given title can be retrieved.
     */
    @Test
    public void findListOfRecipesByTitle() {
        client.get().uri("/getRecipeByTitle?title=pancake")
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk()
                .expectBody();
    }
}
