package recipesharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import recipesharing.logic.User;
import recipesharing.service.LoginService;
import recipesharing.service.UserService;
import recipesharing.vo.Result;

import javax.servlet.http.HttpSession;

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
    @PostMapping("/login")
    //TODO NEED TO ENCRYPT PWD
    public Result userLogin(@RequestParam String email, @RequestParam String password){
        return loginService.userLogin(email, password);
    }

    @PostMapping("/logout")
    public Result userLogout(){
        return Result.success(null);
    }

    @PostMapping("/register")
    public Result register(@RequestBody User user){
        return loginService.register(user);
    }
}
