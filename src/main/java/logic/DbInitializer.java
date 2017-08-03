/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import data.User;
import javax.persistence.EntityManager;

/**
 *
 * @author Jakub
 */
public class DbInitializer {

    public DbInitializer() {
         User admin = new User(1, "Admin", "pass");
         PersistenceManager persistenceManager = new PersistenceManager();
         EntityManager manager = persistenceManager.getEntityManager();
         
        try {
        /*
            User user = new User(1, "admin", "pass");
            
            entityManager.persist(user);  
            // tu používame správcu entít na prácu s objektami
            Employee employee = new Employee("Samuel", "Joseph", "Wurzelbacher");
                    */
            User user = new User(1, "admin", "pass");
        
            manager.getTransaction().begin();
            manager.persist(user);
            manager.getTransaction().commit();
       
        } finally {
            //upratat’ po sebe, ˇciže uzatvorit’ správcu entít.
            manager.close();
        }
    }
    
}
