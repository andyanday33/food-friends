package recipesharing.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import recipesharing.logic.Admin;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
@SpringBootTest
class AdminDaoTest {

    @Autowired
    AdminDao adminDao;

    @Test
    void findAllAdmins() {
        List<Admin> allAdmins = adminDao.findAllAdmins();
        allAdmins.forEach(System.out::println);
    }

    @Test
    void findAdminById() {
    }

    @Test
    void findAdminByAdminName() {
    }

    @Test
    void findAdminByEmail() {
    }

    @Test
    void addAdmin() {
        Admin admin = new Admin("admin1","admin1@st-andrews.ac.uk", "password");
        adminDao.addAdmin(admin);
    }

    @Test
    void deleteAdminById() {
    }

    @Test
    void updateAdminById() {
    }
}