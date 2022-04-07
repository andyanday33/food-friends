package recipesharing.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.aggregation.LookupOperation;
import recipesharing.logic.Ingredient;
import recipesharing.logic.IngredientItem;
import recipesharing.logic.Recipe;
import recipesharing.logic.User;
import recipesharing.vo.RecipesCuisineVo;
import recipesharing.vo.Result;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Testing of recipe data access object and its methods.
 */
@SpringBootTest
class RecipeDaoTest {

    @Autowired
    RecipeDao recipeDao;

    @Autowired
    UserDao userDao;

    @Autowired
    MongoTemplate mongotemplate;

    /**
     * Creates and adds a recipe to the database and checks that it can retrieved and has the correct details.
     */
    @Test
    void addRecipe() {
        User user = userDao.findUserById("6249cadaa1f0c07dba837007");

        List<String> groupUserIds = new ArrayList<>();
        groupUserIds.add("groupuser1");
        Recipe recipe = new Recipe("jaffa cake", "authId37", null, null, "half moon", true, true, 0, null, "cuis1", null, null);
        recipe.setGroupUserIds(groupUserIds);
        recipeDao.addRecipe(recipe);
        List<Recipe> recipes = recipeDao.findRecipeByRecipeName("jaffa cake");
        Recipe retrievedRecipe = recipes.get(0);
        assertEquals(retrievedRecipe.getRecipeName(), "jaffa cake");
        assertEquals(retrievedRecipe.getAuthorId(), "authorId1");
    }

    /**
     * Checks that a recipe can be found using all retrieval methods, and they all return the same thing.
     */
    @Test
    void findRecipeById() {
        User user = userDao.findUserById("6249cadaa1f0c07dba837007");
        Recipe recipe = new Recipe("findRecipeByIDTest", "authId37", null, null, "half moon", true, true, 0, null, "cuis1", null, null);
        recipeDao.addRecipe(recipe);
        List<Recipe> returnedRecipes = recipeDao.findRecipeByRecipeName("findRecipeByIDTest");
        Recipe intermediate = returnedRecipes.get(0);
        String id = intermediate.getRecipeId();

        Recipe idRetrieve = recipeDao.findRecipeById(id);
        assertEquals(idRetrieve, intermediate);
        assertEquals(idRetrieve, recipe);

        recipeDao.deleteRecipeById(id);
        assertNull(recipeDao.findRecipeById(id));
    }

    /**
     * Deletes the recipe created in the previous test and checks that this has been completed.
     */
    @Test
    void testDeleteRecipeById() {
        User user = userDao.findUserById("6249cadaa1f0c07dba837007");
        Recipe recipe = new Recipe("DELETE ME", "authId37", null, null, "half moon", true, true, 0, null, "cuis1", null, null);

        recipeDao.addRecipe(recipe);
        List<Recipe> returnedRecipes = recipeDao.findRecipeByRecipeName("DELETE ME");
        Recipe intermediate = returnedRecipes.get(0);
        String id = intermediate.getRecipeId();

        recipeDao.deleteRecipeById(id);
        assertNull(recipeDao.findRecipeById(id));
    }

    //TODO Consider how this should be implemented given the number of variables
    @Test
    void testUpdateRecipeById() {

    }

    /**
     * Adds a recipe and then returns all recipes from the DB. Checks size and one recipe it contains.
     */
    @Test
    void findAllRecipe() {
        User user = userDao.findUserById("6249cadaa1f0c07dba837007");
        Recipe recipe = new Recipe("CONTAINS ME", "authId37", null, null, "half moon", true, true, 0, null, "cuis1", null, null);
        recipeDao.addRecipe(recipe);
        List<Recipe> allRecipe = recipeDao.findAllRecipe();
        assertTrue(allRecipe.size() > 0);
        assertTrue(allRecipe.contains(recipe));
    }

    /**
     * Tests that a recipe can be retrieved using its name.
     */
    @Test
    void findRecipeByRecipeName() {
        User user = userDao.findUserById("6249cadaa1f0c07dba837007");
        Recipe recipe = new Recipe("findThisName", "authId37", null, null, "half moon", true, true, 0, null, "cuis1", null, null);
        recipeDao.addRecipe(recipe);
        List<Recipe> recipes = recipeDao.findRecipeByRecipeName("findThisName");
        assertEquals(recipe, recipes.get(0));

        String id = recipes.get(0).getRecipeId();

        recipeDao.deleteRecipeById(id);
        assertNull(recipeDao.findRecipeById(id));
    }

    /**
     * Creates and adds a recipe to the DB. Then finds it using its author. Then deletes the recipe to finish.
     */
    @Test
    void findRecipeByAuthorId() {
        User user = userDao.findUserById("6249cadaa1f0c07dba837007");
        Recipe recipe = new Recipe("DELETE ME", "authId49", null, null, "half moon", true, true, 0, null, "cuis1", null, null);

        recipeDao.addRecipe(recipe);
        List<Recipe> recipeName = recipeDao.findRecipeByAuthorId("authId49");
        assertTrue(recipeName.contains(recipe));

        List<Recipe> returnedRecipes = recipeDao.findRecipeByRecipeName("DELETE ME");
        Recipe intermediate = returnedRecipes.get(0);
        String id = intermediate.getRecipeId();

        recipeDao.deleteRecipeById(id);
        assertNull(recipeDao.findRecipeById(id));
    }

    /**
     * Creates recipe and adds to DB. Then checks that the correct access booleans can be retrieved.
     * Deletes test recipe to finish.
     */
    @Test
    void testFindRecipeAccessById() {
        User user = userDao.findUserById("6249cadaa1f0c07dba837007");
        Recipe recipe = new Recipe("DELETE ME", "authId64", null, null, "half moon", true, true, 0, null, "cuis1", null, null);
        recipeDao.addRecipe(recipe);

        List<Recipe> returnedRecipes = recipeDao.findRecipeByRecipeName("DELETE ME");
        Recipe intermediate = returnedRecipes.get(0);
        String id = intermediate.getRecipeId();

        assertTrue(recipeDao.findRecipeAccessById("read", id));
        assertTrue(recipeDao.findRecipeAccessById("write", id));

        recipeDao.deleteRecipeById(id);
        assertNull(recipeDao.findRecipeById(id));
    }

    @Test
    public void testRecipesWithCuisineTag() {
        LookupOperation lookupOperation = LookupOperation.newLookup()
                .from("t_cuisine")//Associated From Table Name
                .localField("cuisineId")//Related fields in the main table
                .foreignField("_id")// Fields associated from a table
                .as("cuisineRecipes");//Search result name
        Aggregation aggregation = Aggregation.newAggregation(lookupOperation);

        AggregationResults<RecipesCuisineVo> results1 = mongotemplate.aggregate(aggregation,
                "t_recipe", RecipesCuisineVo.class);

        ArrayList<RecipesCuisineVo> recipesCuisineVos = new ArrayList<>();
        for (RecipesCuisineVo result:results1
        ) {
            if(result.getCuisineId()!=null && result.get_id()!=null)
            {
                recipesCuisineVos.add(result);
            }
        }
        for (RecipesCuisineVo r : recipesCuisineVos) {
            System.out.println(r);
        }

    }

    @Test
    void testAddRecipeWithIngredientList(){
        Recipe recipe = new Recipe();
        recipe.setRecipeName("carrot cake");

        List<IngredientItem> ingredients = new ArrayList<>();
        IngredientItem ingredientItem1 = new IngredientItem(new Ingredient("sugar",  10.00));
        IngredientItem ingredientItem2 = new IngredientItem(new Ingredient("flour",  200.00));
        IngredientItem ingredientItem3 = new IngredientItem(new Ingredient("butter",  200.00));
        ingredients.add(ingredientItem1);
        ingredients.add(ingredientItem2);
        ingredients.add(ingredientItem3);
        recipe.setIngredients(ingredients);

        recipeDao.addRecipe(recipe);
    }

    @Test
    void testFindRecipeWithIngredientList(){
        Recipe carrot_cake = recipeDao.findRecipeById("624d5c4556aa8979cdb6b60c");

        List<IngredientItem> ingredients = carrot_cake.getIngredients();
        for (IngredientItem i: ingredients
        ) {
            System.out.println(i.getIngredient());
        }

    }


    @Test
    void testGetAllRecipes() {
        recipeDao.findAllRecipe();
    }
}
