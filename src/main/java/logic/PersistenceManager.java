/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jakub
 */
public class PersistenceManager {
    
    private EntityManagerFactory emFactory;
    
    PersistenceManager() {
      // "jpa-example" was the value of the name attribute of the
      // persistence-unit element.
      emFactory = Persistence.createEntityManagerFactory("manager");
    }
    public EntityManager getEntityManager() {
      return emFactory.createEntityManager();
    }
    public void close() {
      emFactory.close();
    }

}
