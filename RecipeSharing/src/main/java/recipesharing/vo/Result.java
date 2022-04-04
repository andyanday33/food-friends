package recipesharing.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *  View Object class Result
 *  the return type for controllers
 */
@Data
@AllArgsConstructor
public class Result {

    private boolean success;

    private int code;

    private String message;

    private Object data;

    public static Result success(Object data) {
        return new Result(true, 200, "success", data);
    }
    public static Result fail(int code, String message){
        return new Result(false, code, message, null);
    }

}
