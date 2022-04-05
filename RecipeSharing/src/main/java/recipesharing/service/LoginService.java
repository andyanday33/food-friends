package recipesharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestParam;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.logic.Admin;
import recipesharing.logic.User;
import recipesharing.util.JWTUtil;
import recipesharing.vo.Result;
import recipesharing.vo.TransStatusCode;

import java.nio.charset.StandardCharsets;

/**
 *  used by controller when login
 */
@Service
public class LoginService {

    @Autowired
    private UserService userService;
    @Autowired
    private AdminService adminService;


    private static final String encoded_password_string = "cs5031!group!~d!";

    public Result userLogin(String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return Result.fail(TransStatusCode.PARAMS_ERROR.getCode(), TransStatusCode.PARAMS_ERROR.getMsg());
        }
        User userByEmail = userService.findUserByEmail(email);

        if (userByEmail == null) {
            return Result.fail(TransStatusCode.ACCOUNT_PWD_NOT_EXIST.getCode(), TransStatusCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        if (userByEmail.getEmail().equals(email) && userByEmail.getPassword().equals(password)) {
            System.out.println("success!!");
            //JDK HIGH ISSUE
/*            String token = JWTUtil.createToken(userByEmail.getUserId());

            String encodedPswd = password + encoded_password_string;
            password = DigestUtils.md5DigestAsHex(encodedPswd.getBytes(StandardCharsets.UTF_8));
            // forward encrypted user
            userByEmail.setToken(token);
            userByEmail.setPassword(password);*/
//            userByEmail.setToken(token);
            return Result.success(userByEmail);
        }
        // TODO REDIS
        // TODO ONLY RETURN THE TOKEN
        return Result.fail(TransStatusCode.FAIL_NOT_FOUND.getCode(), TransStatusCode.FAIL_NOT_FOUND.getMsg());
    }

    /**
     *  user login via email (not an auth 0 way)
     * @param email user's login email
     * @param password user's password
     * @return success message with the user
     */
    public Result userLogin(String email, String password, String token) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return Result.fail(TransStatusCode.PARAMS_ERROR.getCode(), TransStatusCode.PARAMS_ERROR.getMsg());
        }
        User userByEmail = userService.findUserByEmail(email);

        if (userByEmail == null) {
            return Result.fail(TransStatusCode.ACCOUNT_PWD_NOT_EXIST.getCode(), TransStatusCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        if (userByEmail.getEmail().equals(email) && userByEmail.getPassword().equals(password)) {
            System.out.println("success!!");
            //JDK HIGH ISSUE
/*            String token = JWTUtil.createToken(userByEmail.getUserId());

            String encodedPswd = password + encoded_password_string;
            password = DigestUtils.md5DigestAsHex(encodedPswd.getBytes(StandardCharsets.UTF_8));
            // forward encrypted user
            userByEmail.setToken(token);
            userByEmail.setPassword(password);*/
            userByEmail.setToken(token);
            return Result.success(userByEmail);
        }
        // TODO REDIS
        // TODO ONLY RETURN THE TOKEN
        return Result.fail(TransStatusCode.FAIL_NOT_FOUND.getCode(), TransStatusCode.FAIL_NOT_FOUND.getMsg());
    }

    /**
     *  register a user (not an auth 0 way)
     * @param user registered user
     * @return success message with the user
     */
    public Result register(User user) {
        if (StringUtils.isEmpty(user.getEmail())
                || StringUtils.isEmpty(user.getPassword())) {
            return Result.fail(TransStatusCode.PARAMS_ERROR.getCode(), TransStatusCode.PARAMS_ERROR.getMsg());
        }
        if(userService.findUserByEmail(user.getEmail())!=null){
            return Result.fail(TransStatusCode.ACCOUNT_EXIST.getCode(), TransStatusCode.ACCOUNT_EXIST.getMsg());
        }
        userService.addUser(user);
        return Result.success(null);
    }

    /**
     *  admin login (not an auth 0 way)
     * @param email admin login's email
     * @param password admin's password
     * @return success message with the admin
     */
    public Result adminLogin(String email, String password) {
        if (StringUtils.isEmpty(email) || StringUtils.isEmpty(password)) {
            return Result.fail(TransStatusCode.PARAMS_ERROR.getCode(), TransStatusCode.PARAMS_ERROR.getMsg());
        }
        try {
            Admin adminByEmail = adminService.findAdminByEmail(email);
            if (adminByEmail.getEmail().equals(email) && adminByEmail.getPassword().equals(password)) {

/*            String token = JWTUtil.createToken(adminByEmail.getId());

            String encodedPswd = password + encoded_password_string;
            password = DigestUtils.md5DigestAsHex(encodedPswd.getBytes(StandardCharsets.UTF_8));
            // forward encrypted user
            adminByEmail.setEmail(token);
            adminByEmail.setPassword(password);*/

                return Result.success(adminByEmail);
            }
        } catch (NotFoundDBException e) {
            return Result.fail(TransStatusCode.ACCOUNT_PWD_NOT_EXIST.getCode(), TransStatusCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }


        // TODO REDIS
        // TODO ONLY RETURN THE TOKEN
        return Result.fail(TransStatusCode.FAIL_NOT_FOUND.getCode(), TransStatusCode.FAIL_NOT_FOUND.getMsg());
    }
}
