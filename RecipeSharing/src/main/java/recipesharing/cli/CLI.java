package recipesharing.cli;

import java.util.Scanner;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

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
    public static void main (String[] args) throws IOException {
        runCLI(args);
    }

    /**
     * This method pings the user for input and returns relevant information given what the user has input.
     * @param args is the input arguments
     */
    public static void runCLI(String[] args) throws IOException {
        boolean done = false;
        Scanner sc = new Scanner(System.in);
        // keep the user in the interface until they enter 'q' to quit the program
        while(!done) {
            System.out.println("Input a command or type -help for a list of options.");
            String userInput = sc.nextLine();
            // for each case, retrieve user input (if necessary) and send the query to one of the api call methods
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
                    String query = "getRecipeByTitle?title=" + recipe;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createGetAPICall(sanitizedQuery);
                } break;

                case "recipe-id" : {
                    System.out.println("---------------------------------");
                    System.out.println("Enter a recipe id to search for: ");
                    System.out.println("---------------------------------");
                    String recipeId = sc.nextLine();
                    String query = "getRecipeById?recipeId=" + recipeId;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createGetAPICall(sanitizedQuery);
                } break;

                case "recipe-author-id" : {
                    System.out.println("---------------------------------");
                    System.out.println("Enter an author id to search for: ");
                    System.out.println("---------------------------------");
                    String recipeId = sc.nextLine();
                    String query = "getRecipeByAuthorId?authorId=" + recipeId;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createGetAPICall(sanitizedQuery);
                } break;

                case "recipe-cuisine-id" : {
                    System.out.println("---------------------------------");
                    System.out.println("Enter a cuisine id to search for: ");
                    System.out.println("---------------------------------");
                    String cuisineId = sc.nextLine();
                    String query = "getRecipesByCuisine?cuisineId=" + cuisineId;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createGetAPICall(sanitizedQuery);
                } break;

                case "recipe-ingredient-id" : {
                    System.out.println("---------------------------------");
                    System.out.println("Enter an ingredient name to search for: ");
                    System.out.println("---------------------------------");
                    String ingredientId = sc.nextLine();
                    String query = "getRecipesWithIngredient?ingredientName=" + ingredientId;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createGetAPICall(sanitizedQuery);
                } break;

                case "check-recipe-access" : {
                    System.out.println("---------------------------------");
                    System.out.println("Check a recipes access: ");
                    System.out.println("---------------------------------");
                    System.out.println("Enter an access type: ");
                    String accessType = sc.nextLine();
                    System.out.println("Enter a recipe id: ");
                    String recipeId = sc.nextLine();
                    String query = "getRecipeAccessById?accessType=" + accessType + "&recipeId=" + recipeId;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createGetAPICall(sanitizedQuery);
                } break;

                case "check-user-access" : {
                    System.out.println("---------------------------------");
                    System.out.println("Check if a user has access to a given recipe: ");
                    System.out.println("---------------------------------");
                    System.out.println("Enter a user id : ");
                    String userId = sc.nextLine();
                    System.out.println("Enter a recipe id : ");
                    String recipeId = sc.nextLine();
                    String query = "getUserWriteAccessById?userId=" + userId + "&recipeId=" + recipeId;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createGetAPICall(sanitizedQuery);
                } break;

                case "get-all-recipes" : {
                    String query = "getAllRecipes";
                    createGetAPICall(query);
                } break;

                case "get-all-cuisines" : {
                    String query = "getAllCuisines";
                    createGetAPICall(query);
                } break;

                case "get-all-ingredients" : {
                    String query = "findAllIngredients";
                    createGetAPICall(query);
                } break;

                case "get-user-by-id" : {
                    System.out.println("---------------------------------");
                    System.out.println("Return a user given a user id: ");
                    System.out.println("---------------------------------");
                    String userId = sc.nextLine();
                    String query = "user/getUserById?id=" + userId;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createGetAPICall(sanitizedQuery);
                } break;

                case "get-user-by-name" : {
                    System.out.println("---------------------------------");
                    System.out.println("Return a user given a user's name: ");
                    System.out.println("---------------------------------");
                    String userName = sc.nextLine();
                    String query = "user/getUserByName?name=" + userName;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createGetAPICall(sanitizedQuery);
                } break;

                case "get-user-by-email" : {
                    System.out.println("---------------------------------");
                    System.out.println("Return a user given a user's email: ");
                    System.out.println("---------------------------------");
                    String userEmail = sc.nextLine();
                    String query = "user/getUserByEmail?email=" + userEmail;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createGetAPICall(sanitizedQuery);
                } break;

                case "get-all-admins" : {
                    String query = "admin/getAllAdmins";
                    createGetAPICall(query);
                } break;

                case "get-admin-by-id" : {
                    System.out.println("---------------------------------");
                    System.out.println("Return an admin given an admin's id: ");
                    System.out.println("---------------------------------");
                    String adminId = sc.nextLine();
                    String query = "admin/getAdminWithId?id=" + adminId;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createGetAPICall(sanitizedQuery);
                } break;

                case "get-admin-by-name" : {
                    System.out.println("---------------------------------");
                    System.out.println("Return an admin given an admin's name: ");
                    System.out.println("---------------------------------");
                    String adminName = sc.nextLine();
                    String query = "admin/getAdminsWithName?name=" + adminName;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createGetAPICall(sanitizedQuery);
                } break;

                case "get-admin-by-email" : {
                    System.out.println("---------------------------------");
                    System.out.println("Return an admin given an admin's email: ");
                    System.out.println("---------------------------------");
                    String adminEmail = sc.nextLine();
                    String query = "admin/getAdminWithEmail?email=" + adminEmail;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createGetAPICall(sanitizedQuery);
                } break;

                case "create-new-recipe" : {
                    System.out.println("---------------------------------");
                    System.out.println("Create a new recipe by specifying some parameters: ");
                    System.out.println("---------------------------------");
                    System.out.println("Enter a recipe name: ");
                    String title = sc.nextLine();
                    System.out.println("Enter a recipe description: ");
                    String description = sc.nextLine();
                    System.out.println("Enter a user id who wrote the recipe: ");
                    String ownerId = sc.nextLine();
                    System.out.println("Enter a list of instructions, each step separated by a comma: ");
                    String instructionsList = sc.nextLine();
                    System.out.println("Enter a list of ingredient names, each name separated by a comma: ");
                    String ingredientsList = sc.nextLine();
                    System.out.println("Enter a list of ingredient quantities, each quantity separated by a comma: ");
                    System.out.println("Make sure the ingredient quantities are ordered in the same way as the list of ingredient names: ");
                    String quantitiesList = sc.nextLine();
                    System.out.println("Enter a cuisine name: ");
                    String cuisineName = sc.nextLine();
                    String query = "createNewRecipe?title=" + title + "&description=" + description + "&ownerId=" + ownerId + "&instructions=" + instructionsList + "&ingredientNames=" + ingredientsList + "&ingredientQuantities=" + quantitiesList + "&cuisineName=" + cuisineName;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createPostAPICall(sanitizedQuery);
                } break;

                case "create-new-cuisine" : {
                    System.out.println("---------------------------------");
                    System.out.println("Add a new cuisine: ");
                    System.out.println("---------------------------------");
                    String cuisine = sc.nextLine();
                    String query = "addOneCuisine?name=" + cuisine;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createPostAPICall(sanitizedQuery);
                } break;

                case "invite-user-to-edit" : {
                    System.out.println("---------------------------------");
                    System.out.println("Invites a user to edit one recipe: ");
                    System.out.println("---------------------------------");
                    System.out.println("Enter a user id: ");
                    String userId = sc.nextLine();
                    System.out.println("Enter a recipe id: ");
                    String recipeId = sc.nextLine();
                    String query = "user/inviteUserToEditRecipe?invitedUserId=" + userId + "&recipeId=" + recipeId;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createPostAPICall(sanitizedQuery);
                } break;

                case "create-new-admin" : {
                    System.out.println("---------------------------------");
                    System.out.println("Create a new admin by specifying some parameters: ");
                    System.out.println("---------------------------------");
                    System.out.println("Enter the admin's name: ");
                    String adminName = sc.nextLine();
                    System.out.println("Enter the admin's email: ");
                    String adminEmail = sc.nextLine();
                    System.out.println("Enter the admin's password: ");
                    String adminPassword = sc.nextLine();
                    String query = "admin/addNewAdmin?name=" + adminName + "&email=" + adminEmail + "&password=" + adminPassword;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createPostAPICall(sanitizedQuery);
                } break;

                case "register-new-user" : {
                    System.out.println("---------------------------------");
                    System.out.println("Create a new user by specifying some parameters: ");
                    System.out.println("---------------------------------");
                    System.out.println("Enter the user's name: ");
                    String userName = sc.nextLine();
                    System.out.println("Enter the user's email: ");
                    String userEmail = sc.nextLine();
                    System.out.println("Enter the user's password: ");
                    String userPassword = sc.nextLine();
                    String query = "admin/addNewAdmin?name=" + userName + "&email=" + userEmail + "&password=" + userPassword;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createPostAPICall(sanitizedQuery);
                } break;

                case "delete-recipe" : {
                    System.out.println("---------------------------------");
                    System.out.println("Delete a recipe given a recipe ID: ");
                    System.out.println("---------------------------------");
                    String recipeId = sc.nextLine();
                    String query = "deleteRecipeByID?recipeID=" + recipeId;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createDeleteAPICall(sanitizedQuery);
                } break;

                case "delete-cuisine" : {
                    System.out.println("---------------------------------");
                    System.out.println("Delete a cuisine given a cuisine ID: ");
                    System.out.println("---------------------------------");
                    String cuisine = sc.nextLine();
                    String query = "deleteCuisineByID?id=" + cuisine;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createDeleteAPICall(sanitizedQuery);
                } break;

                case "delete-user" : {
                    System.out.println("---------------------------------");
                    System.out.println("Delete a user given a user ID: ");
                    System.out.println("---------------------------------");
                    String userId = sc.nextLine();
                    String query = "user/deleteUserById?id=" + userId;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createDeleteAPICall(sanitizedQuery);
                } break;

                case "delete-admin" : {
                    System.out.println("---------------------------------");
                    System.out.println("Delete an admin given an admin ID: ");
                    System.out.println("---------------------------------");
                    String adminId = sc.nextLine();
                    String query = "admin/deleteAdminById?id=" + adminId;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createDeleteAPICall(sanitizedQuery);
                } break;

                case "delete-ingredient" : {
                    System.out.println("---------------------------------");
                    System.out.println("Delete an ingredient given an ingredient name: ");
                    System.out.println("---------------------------------");
                    String ingredient = sc.nextLine();
                    String query = "deleteIngredientByTitle?title=" + ingredient;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createDeleteAPICall(sanitizedQuery);
                } break;

                case "change-user-permissions" : {
                    System.out.println("---------------------------------");
                    System.out.println("Change a user's permissions on a recipe: ");
                    System.out.println("---------------------------------");
                    System.out.println("Enter the user's name: ");
                    String userName = sc.nextLine();
                    System.out.println("Enter the user's email: ");
                    String userEmail = sc.nextLine();
                    System.out.println("Enter the recipe id: ");
                    String recipeId = sc.nextLine();
                    String query = "changePermissionsOnRecipe?name=" + userName + "&email=" + userEmail + "&recipeID=" + recipeId;
                    String sanitizedQuery = query.replaceAll(" ", "_");
                    createPutAPICall(sanitizedQuery);
                } break;

                default : {
                    System.out.println("---------------------------------");
                    System.out.println("Input not recognized as an option. Try again.");
                    System.out.println("---------------------------------");
                } break;
            }
        }
        sc.close();
    }

    /**
     * This method creates a 'GET' api call with a given query string.
     * @param query is the query to search for
     */
    public static void createGetAPICall(String query) throws IOException {
        // make specific API call from a generic API call
        // specifics are the given query string: GET + tag
        try {
            // create the url with the appropriate uri
            StringBuilder uri = new StringBuilder("http://localhost:8080/");
            uri.append(query);
            System.out.println(uri.toString());
            URL url = new URL(uri.toString());
            // create the connection, making sure to correctly identify the request method
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setDoOutput(true);
            conn.connect();
            // check the response, only reading the data if the response code is 200
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                System.out.println(content.toString());
                System.out.println("Do you want the JSON in a more readable format?");
                Scanner sc = new Scanner(System.in);
                String answer = sc.nextLine();
                if (answer.equals("Yes") || answer.equals("Y") || answer.equals("yes") || answer.equals("y")) {
                    printJSON(content.toString());
                }
            } else {
                System.out.println("Query failed with response code: " + conn.getResponseCode());
                System.out.println(conn.getResponseMessage());
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }        
    }

    /**
     * This method creates a 'POST' api call with a given query string.
     * @param query is the query to search for
     */
    public static void createPostAPICall(String query) {
        // make specific API call from a generic API call
        // specifics are the given query string: POST + tag
        try {
            // create the url with the appropriate uri
            StringBuilder uri = new StringBuilder("http://localhost:8080/");
            uri.append(query);
            System.out.println(uri.toString());
            URL url = new URL(uri.toString());
            // create the connection, making sure to correctly identify the request method
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);
            conn.connect();
            // check the response, only reading the data if the response code is 200
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                System.out.println(content.toString());
                System.out.println("Do you want the JSON in a more readable format?");
                Scanner sc = new Scanner(System.in);
                String answer = sc.nextLine();
                if (answer.equals("Yes") || answer.equals("Y") || answer.equals("yes") || answer.equals("y")) {
                    printJSON(content.toString());
                }
            } else {
                System.out.println("Query failed with response code: " + conn.getResponseCode());
                System.out.println(conn.getResponseMessage());
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates a 'DELETE' api call with a given query string.
     * @param query is the query to search for
     */
    public static void createDeleteAPICall(String query) {
        // make specific API call from a generic API call
        // specifics are the given query string: DELETE + tag
        try {
            // create the url with the appropriate uri
            StringBuilder uri = new StringBuilder("http://localhost:8080/");
            uri.append(query);
            System.out.println(uri.toString());
            URL url = new URL(uri.toString());
            // create the connection, making sure to correctly identify the request method
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setDoOutput(true);
            conn.connect();
            // check the response, only reading the data if the response code is 200
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                System.out.println(content.toString());
                System.out.println("Do you want the JSON in a more readable format?");
                Scanner sc = new Scanner(System.in);
                String answer = sc.nextLine();
                if (answer.equals("Yes") || answer.equals("Y") || answer.equals("yes") || answer.equals("y")) {
                    printJSON(content.toString());
                }
            } else {
                System.out.println("Query failed with response code: " + conn.getResponseCode());
                System.out.println(conn.getResponseMessage());
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method creates a 'PUT' api call with a given query string.
     * @param query is the query to search for
     */
    public static void createPutAPICall(String query) {
        // make specific API call from a generic API call
        // specifics are the given query string: PUT + tag
        try {
            // create the url with the appropriate uri
            StringBuilder uri = new StringBuilder("http://localhost:8080/");
            uri.append(query);
            System.out.println(uri.toString());
            URL url = new URL(uri.toString());
            // create the connection, making sure to correctly identify the request method
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setDoOutput(true);
            conn.connect();
            // check the response, only reading the data if the response code is 200
            if (conn.getResponseCode() == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuffer content = new StringBuffer();
                while ((inputLine = in.readLine()) != null) {
                    content.append(inputLine);
                }
                in.close();
                System.out.println(content.toString());
                System.out.println("Do you want the JSON in a more readable format?");
                Scanner sc = new Scanner(System.in);
                String answer = sc.nextLine();
                if (answer.equals("Yes") || answer.equals("Y") || answer.equals("yes") || answer.equals("y")) {
                    printJSON(content.toString());
                }
            } else {
                System.out.println("Query failed with response code: " + conn.getResponseCode());
                System.out.println(conn.getResponseMessage());
            }
            conn.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method prints a list of input options for the user.
     */
    public static void printOptions() {
        System.out.println("----------------");
        System.out.println("--Options List--");
        System.out.println("----------------");
        System.out.println("q - exit the command line interface");
        System.out.println();
        System.out.println("GET methods:");
        System.out.println("recipe - returns a list of recipes given a recipe title"); // GET /getRecipeByTitle
        System.out.println("recipe-id - returns a recipe given a recipe id if the id exists"); // GET /getRecipesById
        System.out.println("recipe-author-id - returns a list of recipes given an author"); // GET /getRecipesByAuthorId
        System.out.println("recipe-cuisine-id - returns a list of recipes given a cuisine");
        System.out.println("recipe-ingredient-id - returns a list of recipes given an ingredient");
        System.out.println("check-recipe-access - checks if a recipe has the specified access type");
        System.out.println("check-user-access - checks if the specified user has the access to a given recipe");
        System.out.println("get-all-recipes - searches for all recipes in the database and returns them as a list if any exist");
        System.out.println("get-all-cuisines - searches for all cuisines in the database and returns them as a list if any exist");
        System.out.println("get-all-ingredients - searches for all ingredients in the database and returns them as a list if any exist");
        System.out.println("get-all-admins - searches for all admins in the database and returns them as a list if any exist");
        System.out.println("get-user-by-id - searches for a user in the database given a user id");
        System.out.println("get-user-by-name - searches for a user in the database given a user's name");
        System.out.println("get-user-by-email - searches for a user in the database given a user's email");
        System.out.println("get-admin-by-id - searches for an admin in the database given an admin's id");
        System.out.println("get-admin-by-name - searches for an admin in the database given an admin's name");
        System.out.println("get-admin-by-email - searches for an admin in the database given an admin's email");
        System.out.println();
        System.out.println("POST methods:");
        System.out.println("create-new-recipe - create a new recipe by specifying some parameters");
        System.out.println("create-new-cuisine - adds a cuisine to the database if it does not already exist");
        System.out.println("invite-user-to-edit - invites a user to edit one recipe");
        System.out.println("create-new-admin - adds a new admin to the database");
        System.out.println("register-new-user - creates and adds a new user to the database");
        System.out.println();
        System.out.println("DELETE methods:");
        System.out.println("delete-recipe - deletes a recipe from the database given a unique recipe ID");
        System.out.println("delete-cuisine - deletes a cuisine from the database given a unique cuisine ID");
        System.out.println("delete-user - deletes a user from the database given a unique user ID");
        System.out.println("delete-admin - deletes an admin from the database given a unique admin ID");
        System.out.println("delete-ingredient - deletes an ingredient from the database if it exists");
        System.out.println();
        System.out.println("PUT methods:");
        System.out.println("change-user-permissions - changes a user's permissions on a recipe");
        System.out.println("----------------");
        System.out.println("----------------");
        System.out.println("----------------");

    }

    /**
     * This method prints JSON to the command line in a more readable way.
     * @param jsonAsString is the string representation of the JSON
     */
    public static void printJSON(String jsonAsString) {
        String[] objects = jsonAsString.split(",");
        for (String object : objects) {
            System.out.println(object);
            //todo print in nicer way than just one JSON object per line
        }
    }
}
