package recipesharing.vo;

import lombok.Getter;

/**
 *  status code for http connection
 */
@Getter
public enum TransStatusCode {

    SUCCESS(200, "OK"),
    FAIL(400, "BAD REQUEST"),

    FAIL_FORBIDDEN(403, "Forbidden"),
    FAIL_NOT_FOUND(404, "Not Found"),
    FAIL_METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    FAIL_BAD_GATE_AWAY(502, "Bad Gateway"),

    PARAMS_ERROR(10001,"PARAMS_ERROR"),
    ACCOUNT_PWD_NOT_EXIST(10002,"ACCOUNT_PWD_NOT_EXIST"),
    ACCOUNT_EXIST(10003,"ACCOUNT_EXIST"),
    NO_PERMISSION(70001,"NO_PERMISSION"),
    SESSION_TIME_OUT(90001,"SESSION_TIME_OUT"),
    NO_LOGIN(90002,"NO_LOGIN"),;

    private int code;

    private String msg;

    TransStatusCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
