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
 *
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
     *
     * @return a list of admins.
     */
    @GetMapping("/getAllAdmins")
    public Result getAllAdmins() {
        // TODO This should call the function in adminDao which prints out a system println statement but it doesn't reach that point
        // Breaks before it can be called.
        return Result.success(adminService.findAllAdmins());
    }

}
