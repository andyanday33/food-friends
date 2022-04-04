package recipesharing.customExceptions;

/**
 * Custom exception class for situations where a resource cannot be found in the database.
 */
public class NotFoundDBException extends Exception{
    String message;
    NotFoundDBException(String str) {
        message = str;
    }

    public String toString() {
        return ("Not Found in Database Exception Occured : " + message);
    }
}
