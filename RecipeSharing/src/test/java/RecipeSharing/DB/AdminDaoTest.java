package RecipeSharing.DB;

import RecipeSharing.logic.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
@SpringBootTest
class AdminDaoTest {

    @Autowired
    AdminDao adminDao;
/*
    @Test
    void findAllAdmin() {
        adminDao.findAllAdmins();
    }
*/

    @Test
    void addOneAdmin() {
        adminDao.addOneAdmin(new Admin("admin", "admin@st-andrews.ac.uk"));
    }
}