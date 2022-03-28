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
    private List<Byte[]> photos;

    //TODO decide what fields are needed for constructor
    public Recipe() {

    }

    /**
     * Updates average rating based on new rating input.
     * @param userRating rating from a given user.
     */
    public void updateRating(int userRating) {
        double total = (rating * numRatings) + userRating;
        this.numRatings++;
        this.rating = total / numRatings;
    }

    /**
     * Get title of recipe.
     * @return recipe title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set title of recipe.
     * @param title recipe title.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get description of recipe.
     * @return description String.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set description of the recipe.
     * @param description description of the recipe.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the owner of this recipe.
     * @return owner of this recipe.
     */
    public Person getOwner() {
        return owner;
    }

    /**
     * Get the boolean which indicates whether this recipe can be shared with people other than the owner.
     * @return true if shareable.
     */
    public boolean isShareable() {
        return shareable;
    }

    /**
     * Sets the shareable boolean.
     * @param shareable indicates whether the recipe can be shared with others.
     */
    public void setShareable(boolean shareable) {
        this.shareable = shareable;
    }

    /**
     * Get the list of people who have read access for this recipe.
     * @return list of people.
     */
    public List<Person> getReadAccess() {
        return readAccess;
    }

    /**
     * Set the list of people who have read access for this recipe.
     */
    public void setReadAccess(List<Person> readAccess) {
        this.readAccess = readAccess;
    }

    /**
     * Get the list of people who have can edit this recipe.
     * @return list of people.
     */
    public List<Person> getWriteAccess() {
        return writeAccess;
    }

    /**
     * Get the list of people who have can edit this recipe.
     */
    public void setWriteAccess(List<Person> writeAccess) {
        this.writeAccess = writeAccess;
    }

    /**
     * Get list of Cuisines associated with this recipe.
     * @return list of Cuisines.
     */
    public List<Cuisine> getCuisines() {
        return cuisines;
    }

    /**
     * Set list of Cuisines associated with this recipe.
     */
    public void setCuisines(List<Cuisine> cuisines) {
        this.cuisines = cuisines;
    }

    /**
     * Get list of ingredients associated with this recipe.
     * @return list of ingredients.
     */
    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    /**
     * Set list of ingredients associated with this recipe.
     */
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    /**
     * Set owner of the recipe.
     * @param owner owner of the recipe.
     */
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    /**
     * Get list of Byte arrays which contain photos associated with the recipe.
     * @return list of Byte arrays.
     */
    public List<Byte[]> getPhotos() {
        return photos;
    }

    /**
     * Set list of Byte arrays which contain photos associated with the recipe.
     */
    public void setPhotos(List<Byte[]> photos) {
        this.photos = photos;
    }
}
