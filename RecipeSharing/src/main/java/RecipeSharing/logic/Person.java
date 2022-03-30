package RecipeSharing.logic;

import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for each person, parent class of admin and user
 */
@Document("Person")
public abstract class Person {

    //TODO add something to do with permissions.
    private String name;
    private String email;
    private String password;
    private List<Recipe> authoredRecipes = new ArrayList<>();
    private List<Recipe> likedRecipes = new ArrayList<>();
    private List<Recipe> readAccess = new ArrayList<>();
    private List<Recipe> writeAccess = new ArrayList<>();
    private List<Recipe> history = new ArrayList<>();

    /**
     * Constructor for class.
     * @param name person's name.
     * @param email person's email address.
     */
    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public Person(){

    }

    /**
     * Add one recipe to this person's recipe list.
     * @param recipe recipe to be added.
     */
    public void addRecipe(Recipe recipe) {
        this.authoredRecipes.add(recipe);
    }

    /**
     * Remove one recipe from this person's recipe list.
     * @param recipe recipe to be removed.
     */
    public void removeRecipe(Recipe recipe) {
        this.authoredRecipes.remove(recipe);
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

    /**
     * Get the list of recipes this Person has read access to.
     * @return list of recipes.
     */
    public List<Recipe> getReadAccess() {
        return readAccess;
    }

    /**
     * Add an individual recipe to the read access list.
     * @param recipe recipe to be added.
     */
    public void addReadAccess(Recipe recipe) {
        this.readAccess.add(recipe);
    }

    /**
     * Remove an individual recipe from the read access list.
     * @param recipe recipe to be removed.
     */
    public void removeReadAccess(Recipe recipe) {
        this.readAccess.remove(recipe);
    }

    /**
     * Set the list of recipes this Person has read access to.
     */
    public void setReadAccess(List<Recipe> readAccess) {
        this.readAccess = readAccess;
    }

    /**
     * Add an individual recipe to this person's write access list.
     * @param recipe recipe to be added.
     */
    public void addWriteAccess(Recipe recipe) {
        this.writeAccess.add(recipe);
    }

    /**
     * Remove an individual recipe from this person's write access list.
     * @param recipe recipe to remove.
     */
    public void removeWriteAccess(Recipe recipe) {
        this.writeAccess.remove(recipe);
    }

    /**
     * Get the recipes this individual is allowed to edit.
     * @return
     */
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

    public void addLikedRecipe(Recipe recipe) {
        this.likedRecipes.add(recipe);
    }

    public void removeLikedRecipe(Recipe recipe) {
        this.likedRecipes.remove(recipe);
    }

    public List<Recipe> getLikedRecipes() {
        return likedRecipes;
    }

    public void setLikedRecipes(List<Recipe> likedRecipes) {
        this.likedRecipes = likedRecipes;
    }

    public void addToHistory(Recipe recipe) {
        this.history.add(recipe);
    }

    public List<Recipe> getHistory() {
        return history;
    }
}
