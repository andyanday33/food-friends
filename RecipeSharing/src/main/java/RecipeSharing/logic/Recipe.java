package RecipeSharing.logic;

import java.util.List;

/**
 * Class for recipes, contains all pertinent information.
 */
public class Recipe {

    private String title;
    private String description;
    private Person owner;
    private boolean shareable;
    private List<Person> readAccess;
    private List<Person> writeAccess;
    private List<Cuisine> cuisines;
    private Meal meal;
    private List<Ingredient> ingredients;
    private double rating;
    private int numRatings;

    //TODO decide what fields are needed for constructor
    public Recipe() {

    }

    public void updateRating(int userRating) {
        double total = (rating * numRatings) + userRating;
        this.numRatings++;
        this.rating = total / numRatings;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getOwner() {
        return owner;
    }

    public boolean isShareable() {
        return shareable;
    }

    public void setShareable(boolean shareable) {
        this.shareable = shareable;
    }

    public List<Person> getReadAccess() {
        return readAccess;
    }

    public void setReadAccess(List<Person> readAccess) {
        this.readAccess = readAccess;
    }

    public List<Person> getWriteAccess() {
        return writeAccess;
    }

    public void setWriteAccess(List<Person> writeAccess) {
        this.writeAccess = writeAccess;
    }

    public List<Cuisine> getCuisines() {
        return cuisines;
    }

    public void setCuisines(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }
}
