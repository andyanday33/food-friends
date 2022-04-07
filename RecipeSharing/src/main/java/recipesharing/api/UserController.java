package recipesharing.api;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.logic.User;
import recipesharing.service.LoginService;
import recipesharing.service.UserService;
import recipesharing.util.JWTUtil;
import recipesharing.vo.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Contains user related endpoints for the API.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    LoginService loginService;


    /**
     * return the whole user list
     * @return result message
     */
    @GetMapping("/getAllUsers")
    public Result getAllUsers() {
        try {
            return Result.success(userService.findAllUsers());
        } catch (NotFoundDBException e) {
            return Result.fail(404, e.getMessage());
        }
    }

    /**
     *  find one user data by _id
     * @param id _id of the user
     * @return the result msg with the user obj if success
     */
    @GetMapping("/getUserById")
    public Result getUserById (@RequestParam String id) {
        try {
            return Result.success(userService.findUserById(id));
        } catch (NotFoundDBException e) {
            return Result.fail(404, e.getMessage());
        }
    }

    /**
     *  find a user via its username
     * @param name username
     * @return user obj
     */
    @GetMapping("/getUserByName")
    public Result getUserByName (@RequestParam String name) {
        try {
            return Result.success(userService.findUserByUserName(name));
        } catch (NotFoundDBException e) {
            return Result.fail(404, e.getMessage());
        }
    }

    /**
     * find a user via its email
     * @param email user email
     * @return user obj
     */
    @GetMapping("/getUserByEmail")
    public Result getUserByEmail (@RequestParam String email) {
        return Result.success(userService.findUserByEmail(email));
    }

    /**
     *  find a user via its email without token
     * @param email user email
     * @param password user obj
     * @return
     */
    @PostMapping("/login")
    public Result userLogin(@RequestParam String email, @RequestParam String password) {
        return loginService.userLogin(email, password);
    }

    /**
     * user log out
     * @return result success msg
     */
    @PostMapping("/logout")
    public Result userLogout(){
        return Result.success(null);
    }

    /**
     *  register a user
     * @param userName user name
     * @param email user email
     * @param password user password
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestParam String userName,
                           @RequestParam String email,
                           @RequestParam String password) {
        User user = new User(userName, email, password, new ArrayList<>());

        return Result.success(loginService.register(user));
    }

    /**
     * only for testing
     * @param request
     * @return
     */
    @PostMapping("/jwttest")
    public Map<String, Object> test(HttpServletRequest request) {
        String token = request.getHeader("token");
        DecodedJWT verify = JWTUtil.verify(token);
        String id = verify.getClaim("id").asString();
        String name = verify.getClaim("name").asString();

        System.out.println(id+","+name);
        //TODO:业务逻辑
        Map<String, Object> map = new HashMap<>();
        map.put("status", true);
        map.put("msg", "request successfully");
        return map;
    }

    /**
     *  login with  a generated token
     *  after this stage, the front end should put the token in the request header
     *  and then everytime the client making a request reaching to the back end
     *  it should be intercepted to verify the token (defined in InterceptorConfig.class)
     * @param email user login email
     * @param password user password
     * @return user obj
     */
    @PostMapping("/loginwithtoken")
    public Result userLoginWithJWT( @RequestParam String email, @RequestParam String password){
        String token = JWTUtil.getToken(email, password);

        return loginService.userLogin(email, password);
    }

    /**
     * invite a user to edit one recipe (should be used by the author)
     * @param recipeId id of the recipe
     * @param invitedUserId id of the user being invited to edit the recipe
     * @return the result of the request
     */
    @PostMapping("/inviteUserToEditRecipe")
    public Result inviteUserById(
            @RequestParam String invitedUserId,
            @RequestParam String recipeId) {
        userService.inviteUserById(invitedUserId, recipeId);
        return Result.success(null);
    }

    /**
     * Deletes a user with the given id
     * @param id - the user's unique id
     * @return the result of the request
     */
    @DeleteMapping("/deleteUserById")
    public Result deleteUserById(@RequestParam String id) {
        userService.deleteUserById(id);
        return Result.success(null);
    }

}
