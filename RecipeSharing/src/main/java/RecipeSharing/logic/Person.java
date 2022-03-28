package RecipeSharing.logic;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for each person, parent class of admin and user
 */
public class Person {

    //TODO add something to do with permissions.
    private String name;
    private String email;
    private List<Recipe> authoredRecipes = new ArrayList<>();
    private List<Recipe> likedRecipes = new ArrayList<>();
    private List<Recipe> readAccess = new ArrayList<>();
    private List<Recipe> writeAccess = new ArrayList<>();

    /**
     * Constructor for class.
     * @param name person's name.
     * @param email person's email address.
     */
    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    /**
     * Add one recipe to this person's recipe list.
     * @param recipe recipe to be added.
     */
    public void addRecipe(Recipe recipe) {
        this.authoredRecipes.add(recipe);
    }

    /**
     * Get the list of recipes that this person is the owner of.
     * @return list of recipes.
     */
    public List<Recipe> getAuthoredRecipes() {
        return authoredRecipes;
    }

    /**
     * Set the list of recipes that this person is the owner of.
     */
    public void setAuthoredRecipes(List<Recipe> authoredRecipes) {
        this.authoredRecipes = authoredRecipes;
    }

    public List<Recipe> getReadAccess() {
        return readAccess;
    }

    public void setReadAccess(List<Recipe> readAccess) {
        this.readAccess = readAccess;
    }

    public List<Recipe> getWriteAccess() {
        return writeAccess;
    }

    public void setWriteAccess(List<Recipe> writeAccess) {
        this.writeAccess = writeAccess;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Recipe> getLikedRecipes() {
        return likedRecipes;
    }

    public void setLikedRecipes(List<Recipe> likedRecipes) {
        this.likedRecipes = likedRecipes;
    }
}
