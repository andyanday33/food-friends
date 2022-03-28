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
    private List<Recipe> recipes = new ArrayList<>();
    private List<Recipe> readAccess = new ArrayList<>();
    private List<Recipe> writeAccess = new ArrayList<>();

    public Person(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
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
}
