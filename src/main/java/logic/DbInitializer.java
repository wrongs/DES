/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import data.dao.IUserDao;
import data.entites.User;   
import data.dao.UserDao;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;

/**
 *
 * @author Jakub
 */
public class DbInitializer {

    public void initialize() 
    {
        if (isAdmindefault())
        {
            System.out.println("admin is set to defauld and should be changed becasue of security");
        }   
    }
    
    // load properties from db
    
    private boolean isAdmindefault(){
        IUserDao userDao = new UserDao();
        User admin = userDao.find(1);
        if (admin == null)
        {
            /*
            String encryptPass = null;
            
            try {
                encryptPass = CryptoProvider.encrypt("Admin");
            } catch (Exception ex) {
                Logger.getLogger(DbInitializer.class.getName()).log(Level.SEVERE, null, ex);
            }
            userDao.create("Admin", encryptPass);
           */
            userDao.create("Admin", "Admin");
            return true;
        }
        else if (admin.getPassword().equals("Admin"))
        {
            return false;
        }
        return false;
    }
}
