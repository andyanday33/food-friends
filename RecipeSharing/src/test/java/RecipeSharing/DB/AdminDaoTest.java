package RecipeSharing.DB;

import RecipeSharing.logic.Admin;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
class AdminDaoTest {

    @Autowired
    AdminDao adminDao;
    @Test
    void findAllAdmin() {
        adminDao.findAllAdmin();
    }

    @Test
    void addOneAdmin() {
        adminDao.addOneAdmin(new Admin("admin", "admin@st-andrews.ac.uk"));
    }
}