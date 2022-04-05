package recipesharing.db;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import recipesharing.logic.Admin;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Tests AdminDao object and its methods.
 */
@SpringBootTest
class AdminDaoTest {

    @Autowired
    AdminDao adminDao;

    /**
     * Tests that all Admins can be found and there are more than zero.
     */
    @Test
    void findAllAdmins() {
        List<Admin> allAdmins = adminDao.findAllAdmins();
        assertTrue(allAdmins.size() > 0);
    }

    /**
     * Tests that an admin can be created and added to the database.
     */
    @Test
    void addAdmin() {
        Admin admin = new Admin("addMin","admin1@st-andrews.ac.uk", "password");
        adminDao.addAdmin(admin);

        //Tests that the test admin has been added to the database.
        List<Admin> returned =  adminDao.findAdminByAdminName("addMin");
        assertTrue(returned.contains(admin));

        //delete test admin.
        String id = returned.get(0).getId();
        adminDao.deleteAdminById(id);
    }

    /**
     * Tests that all admins can be found via their ID.
     */
    @Test
    void findAdminById() {
        Admin admin = new Admin("findID","admin1@st-andrews.ac.uk", "password");
        adminDao.addAdmin(admin);

        //Retrieve admin and get ID
        List<Admin> returned =  adminDao.findAdminByAdminName("findID");
        String id = returned.get(0).getId();

        //use id to retrieve admin
        Admin foundAdmin = adminDao.findAdminById(id);
        assertEquals(admin, foundAdmin);

        //delete test admin
        adminDao.deleteAdminById(id);
    }

    /**
     * Tests that admins can be found by name.
     */
    @Test
    void findAdminByAdminName() {
        Admin admin = new Admin("Jimothy","admin1@st-andrews.ac.uk", "password");
        adminDao.addAdmin(admin);

        //Retrieve admin and get ID
        List<Admin> returned =  adminDao.findAdminByAdminName("Jimothy");
        assertEquals(returned.get(0), admin);

        //delete test admin
        adminDao.deleteAdminById(returned.get(0).getId());
    }

    /**
     * Tests that admins can be found by their email.
     */
    @Test
    void findAdminByEmail() {
        Admin admin = new Admin("emailTest","AaronAdmin@st-andrews.ac.uk", "password");
        adminDao.addAdmin(admin);

        //Retrieve admin and get ID
        Admin returnedAdmin =  adminDao.findAdminByEmail("AaronAdmin@st-andrews.ac.uk");
        assertEquals(returnedAdmin, admin);

        //delete test admin
        adminDao.deleteAdminById(returnedAdmin.getId());
    }

    /**
     * Tests that admins can be deleted from the database.
     */
    @Test
    void deleteAdminById() {
        Admin admin = new Admin("testDelete","admin1@st-andrews.ac.uk", "password");
        adminDao.addAdmin(admin);

        //Retrieve admin and get ID
        List<Admin> returned =  adminDao.findAdminByAdminName("testDelete");
        assertEquals(returned.get(0), admin);

        //Delete this admin
        String id = returned.get(0).getId();
        adminDao.deleteAdminById(id);

        //check deleted
        assertNull(adminDao.findAdminById(id));
    }

    /**
     * Tests that admin details can be updated.
     */
    @Test
    void updateAdminById() {
        //TODO implement when update and upsert logic finalised.
    }
}