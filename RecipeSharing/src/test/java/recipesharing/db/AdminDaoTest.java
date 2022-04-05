package recipesharing.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import recipesharing.logic.Admin;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests AdminDao object and its methods.
 */
@SpringBootTest
class AdminDaoTest {

    @Autowired
    AdminDao adminDao;

    /**
     * Tests that all Admins can be found and printed.
     */
    @Test
    void findAllAdmins() {
        List<Admin> allAdmins = adminDao.findAllAdmins();
        assertTrue(allAdmins.size() > 0);
        allAdmins.forEach(System.out::println);
    }

    /**
     * Tests that all admins can be found via their ID.
     */
    @Test
    void findAdminById() {

    }

    /**
     * Tests that admins can be found by name.
     */
    @Test
    void findAdminByAdminName() {
    }

    /**
     * Tests that admins can be found by their email.
     */
    @Test
    void findAdminByEmail() {
    }

    /**
     * Tests that an admin can be created and added to the database.
     */
    @Test
    void addAdmin() {
        Admin admin = new Admin("admin1","admin1@st-andrews.ac.uk", "password");
        adminDao.addAdmin(admin);
    }

    /**
     * Tests that admins can be deleted from the database.
     */
    @Test
    void deleteAdminById() {
    }

    /**
     * Tests that admin details can be updated.
     */
    @Test
    void updateAdminById() {
    }
}