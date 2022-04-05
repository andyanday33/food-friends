package recipesharing.commandLineInterface;

import recipesharing.api.AdminController;
import recipesharing.api.ImageController;
import recipesharing.api.IngredientController;
import recipesharing.api.RecipeController;
import recipesharing.api.UserController;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.logic.Recipe;
import recipesharing.service.RecipeService;
import recipesharing.vo.Result;

import java.util.Scanner;

/**
 * This class contains the logic for accessing the recipe sharing application via
 * the command line.
 * 
 * @author nah21
 */
public class CLI {

    /**
     * This is the main method of the CLI class which passes the input arguments
     * to another method.
     * @param args is the input arguments
     */
    public static void main (String[] args) {
        runCLI(args);
    }

    /**
     * This method pings the user for input and returns relevant information given what the user has input.
     * @param args is the input arguments
     */
    public static void runCLI(String[] args) {
        boolean done = false;
        Scanner sc = new Scanner(System.in);

        while(!done) {
            System.out.println("Input a command or type -help for a list of options.");
            String userInput = sc.nextLine();

            switch (userInput) {
                case "q" : {
                    done = true;
                } break;

                case "-help" : {
                    printOptions();
                } break;

                case "recipe" : {
                    System.out.println("------------------------------");
                    System.out.println("Enter a recipe to search for: ");
                    System.out.println("------------------------------");
                    String recipe = sc.nextLine();
                    String query = "";
                    createGetAPICall(query);
                } break;

                case "api" : {
                    String query = "";
                    createGetAPICall(query);
                } break;

                case "recipe-id" : {
                    System.out.println("---------------------------------");
                    System.out.println("Enter a recipe id to search for: ");
                    System.out.println("---------------------------------");
                    String recipeId = sc.nextLine();
                    String query = "";
                    createGetAPICall(query);
                } break;

                case "" : {
                    
                } break;

            }

        }
        sc.close();

    }

    /**
     * This method searches for a given recipe, making an API call and returning
     * the output to the command line.
     * @param recipe is the recipe the user is searching for
     */
    public static void searchRecipe(String recipe) {
        //todo make API call with recipe as the query


        //todo parse JSON into a readable format

    }

    /**
     * This method creates a 'GET' api call with a given query string.
     * @param query is the query to search for
     */
    public static void createGetAPICall(String query) {
        //todo make specific API call from a generic API call
        // specifics are the given query string: GET + tag
        
    }

    /**
     * This method creates a 'POST' api call with a given query string.
     * @param query is the query to search for
     */
    public static void createPostAPICall(String query) {
        //todo make specific API call from a generic API call
        // specifics are the given query string: POST + tag
        
    }

    /**
     * This method creates a 'DELETE' api call with a given query string.
     * @param query is the query to search for
     */
    public static void createDeleteAPICall(String query) {
        //todo make specific API call from a generic API call
        // specifics are the given query string: DELETE + tag
    }

    /**
     * This method creates a 'PUT' api call with a given query string.
     * @param query is the query to search for
     */
    public static void createPutAPICall(String query) {
        //todo make specific API call from a generic API call
        // specifics are the given query string: PUT + tag
        
    }

    /**
     * This method prints a list of input options for the user.
     */
    public static void printOptions() {
        System.out.println("----------------");
        System.out.println("--Options List--");
        System.out.println("----------------");
        // working
        System.out.println("q - exit the command line interface");

        // not yet implemented
        System.out.println("recipe - returns a list of recipes given a recipe title"); // GET /getRecipeByTitle
        System.out.println("api - returns information on how to use the api"); // GET /api
        System.out.println("recipe-id - returns a recipe given a recipe id if the id exists"); // GET /getRecipesById
        System.out.println("recipe-author-id - returns a list of recipes given an author"); // GET /getRecipesByAuthorId
        //System.out.println("");
        //System.out.println("");
        //System.out.println("");
        System.out.println("----------------");
        System.out.println("----------------");
        System.out.println("----------------");

    }

}
