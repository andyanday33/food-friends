package recipesharing.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import recipesharing.customExceptions.NotFoundDBException;
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

    @Autowired
    MongoTemplate mongoTemplate;
    /**
     * find all admin users
     *
     * @return list of all admins
     */
    public List<Admin> findAllAdmins() throws NotFoundDBException {
        List<Admin> admins = adminDao.findAllAdmins();
        if (admins.isEmpty()) {
            throw new NotFoundDBException("There are no admins in the database.");
        }
        return adminDao.findAllAdmins();
    }

    /**
     *  find admins with page limits
     * @param page current page number
     * @param size the number of records shows up
     * @return admin list
     */
    public List<Admin> findAllAdminsWithPageLimit(int page, int size) {


        Query query = new Query();
        long count = mongoTemplate.count(query, Admin.class);

        Pageable pageable = PageRequest.of(page-1,size);

        return mongoTemplate.find(query.with(pageable).with(Sort.by(Sort.Direction.DESC, "name")), Admin.class);
    }

    /**
     * find admins with page limits and query
     * the query can specify the name, id etc
     * @param query query condition
     * @param page current
     * @param size the number of records shows up
     * @return admin list
     */
    public List<Admin> findAllAdminsByQueryWithPageLimit(Query query, int page, int size) {

        long count = mongoTemplate.count(query, Admin.class);

        Pageable pageable = PageRequest.of(page-1,size);

        return mongoTemplate.find(query.with(pageable).with(Sort.by(Sort.Direction.DESC,"name")), Admin.class);
    }
    /**
     * find admin by _id
     *
     * @param id _id of the admin
     *
     * @return admin class
     */
    public Admin findAdminById(String id) throws NotFoundDBException {
        Admin admin = adminDao.findAdminById(id);
        if (admin == null) {
            throw new NotFoundDBException("The admin with id " + id + "does not exist.");
        }
        return admin;
    }

    /**
     * find admins by the admin name, can be not unique
     *
     * @param name admin name
     *
     * @return admin list
     */
    public List<Admin> findAdminByAdminName(String name) throws NotFoundDBException {
        List<Admin> admins = adminDao.findAdminByAdminName(name);
        if (admins.isEmpty()) {
            throw new NotFoundDBException("The admin with name " + name + "does not exist.");
        }
        return admins;
    }

    /**
     * find admin by its email address
     *
     * @param email admin email
     *
     * @return admin class
     */
    public Admin findAdminByEmail(String email) throws NotFoundDBException {
        Admin admin = adminDao.findAdminByEmail(email);
        if (admin == null) {
            throw new NotFoundDBException("The admin with email " + email + "does not exist.");
        }
        return admin;
    }

    /**
     * add one admin
     *
     * @param admin admin class obj that needs to be added into the collection
     */
    public void addAdmin(Admin admin) {
        adminDao.addAdmin(admin);
    }

    /**
     * delete the admin by the admin id
     *
     * @param id admin _id
     */
    public void deleteAdminById(String id) {
        adminDao.deleteAdminById(id);
    }

    /**
     * update the admin by its id
     *
     * @param newAdmin the admin obj that has already changed the attributes
     */
    public void updateAdminById(Admin newAdmin) {
        adminDao.updateAdminById(newAdmin);
    }

}
