package recipesharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipesharing.customExceptions.NotFoundDBException;
import recipesharing.db.AdminDao;
import recipesharing.db.RecipeDao;
import recipesharing.logic.Admin;
import recipesharing.service.AdminService;
import recipesharing.service.LoginService;
import recipesharing.service.RecipeService;
import recipesharing.service.UserService;
import recipesharing.util.JWTUtil;
import recipesharing.vo.Result;

import java.util.List;

/**
 * Contains admin related endpoints for the API.
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    AdminService adminService;

    @Autowired
    LoginService loginService;

    @Autowired
    RecipeService recipeService;

    @Autowired
    UserService userService;

    @PostMapping("/login")
    public Result adminLogin(@RequestParam String email, @RequestParam String password) {
        return loginService.adminLogin(email, password);
    }

    @PostMapping("/loginwithtoken")
    public Result adminLoginWithJWT( @RequestParam String email, @RequestParam String password){
        String token = JWTUtil.getToken(email, password);

        return loginService.userLogin(email, password);
    }
    @PostMapping("/logout")
    public Result adminLogout() {
        return Result.success(null);
    }

    // *** Admin related API endpoints *** //

    /**
     * Find all admins in the database and return them as a list.
     * Returns status code with the list. If there are no admins, then 404 will be sent along with an error message.
     * @return a list of admins.
     */
    @GetMapping("/getAllAdmins")
    public Result getAllAdmins() {
        try {
            return Result.success(adminService.findAllAdmins());
        } catch (NotFoundDBException e) {
            return Result.fail(404, e.getMessage());
        }
    }

    /**
     * Add a new admin to the database.
     * @param name - the name of the admin.
     * @param email - the email of the admin.
     * @param password - the password for the admin.
     * @return
     */
    @PostMapping("/addNewAdmin")
    public Result addNewAdmin(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String password
    ) {
        Admin admin = new Admin(name, email, password);
        adminService.addAdmin(admin);
        return Result.success(null);
    }

}
