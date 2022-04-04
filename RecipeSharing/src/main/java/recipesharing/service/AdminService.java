package recipesharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import recipesharing.db.AdminDao;
import recipesharing.logic.Admin;

import java.util.List;

/**
 * the functions for admin
 */
@Service
public class AdminService {

    @Autowired
    AdminDao adminDao;

    /**
     *  find all admin users
     * @return list of all admins
     */
    public List<Admin> findAllAdmins() {
        return adminDao.findAllAdmins();
    }

    /**
     *  find admin by _id
     * @param id _id of the admin
     * @return admin class
     */
    public Admin findAdminById(String id) {
        return adminDao.findAdminById(id);
    }

    /**
     *  find admins by the admin name, can be not unique
     * @param name admin name
     * @return admin list
     */
    public List<Admin> findAdminByAdminName(String name) {
        return adminDao.findAdminByAdminName(name);
    }

    /**
     *  find admin by its email address
     * @param email admin email
     * @return admin class
     */
    public Admin findAdminByEmail(String email){
        return adminDao.findAdminByEmail(email);
    }

    /**
     *  add one admin
     * @param admin admin class obj that needs to be added into the collection
     */
    public void addAdmin(Admin admin){
        adminDao.addAdmin(admin);
    }

    /**
     * delete the admin by the admin id
     * @param id admin _id
     */
    public void deleteAdminById(String id){
        adminDao.deleteAdminById(id);
    }

    /**
     *  update the admin by its id
     * @param newAdmin the admin obj that has already changed the attributes
     */
    public void updateAdminById(Admin newAdmin){
        adminDao.updateAdminById(newAdmin);
    }

}
