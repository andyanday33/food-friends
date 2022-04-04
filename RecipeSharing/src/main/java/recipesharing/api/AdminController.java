package recipesharing.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import recipesharing.db.AdminDao;
import recipesharing.logic.Admin;
import recipesharing.service.AdminService;
import recipesharing.service.LoginService;
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

    @PostMapping("/login")
    //TODO NEED TO ENCRYPT PWD
    public Result adminLogin(@RequestParam String email, @RequestParam String password) {
        return loginService.adminLogin(email, password);
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
        } catch (Exception e) {
            e.printStackTrace();
            // return result "fail"
            // 404 as cannot be found?
            return Result.fail(404, "error msg");
        }
    }

}
