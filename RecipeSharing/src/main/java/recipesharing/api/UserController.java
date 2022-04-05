package recipesharing.api;

import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import recipesharing.logic.User;
import recipesharing.service.LoginService;
import recipesharing.service.UserService;
import recipesharing.util.JWTUtil;
import recipesharing.vo.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    @PostMapping("/login")
    //TODO NEED TO ENCRYPT PWD
    public Result userLogin( @RequestParam String email, @RequestParam String password){

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
    @PostMapping("/loginjwttest")
    public Result userLoginWithJWT( @RequestParam String email, @RequestParam String password){
        String token = JWTUtil.getToken(email, password);

        return loginService.userLogin(email, password, token);
    }

}
