package recipesharing.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import recipesharing.logic.Admin;

import java.util.List;

/**
 *
 */
@SpringBootTest
class AdminServiceTest {

    @Autowired
    AdminService adminService;
    @Test
    void findAllAdmins() {
        //adminService.findAllAdmins();
    }

    @Test
    void findAllAdminsWithPageLimit() {

        List<Admin> allAdminsWithPageLimit = adminService.findAllAdminsWithPageLimit(1, 5);
        allAdminsWithPageLimit.forEach(System.out::println);
    }
}