/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Jakub
 */
public class EMFProvider {
    
    private static final EMFProvider instance = new EMFProvider();
    
    protected EntityManagerFactory emFactory;
    
    public static EMFProvider getInstance() {
         return instance;
    }
    
    private EMFProvider() {}
    
    public EntityManagerFactory getEntityManagerFactory() {
        if (emFactory == null)
        {
            createEntityManagerFactory();
        }
        return emFactory;
    }
      
    private void createEntityManagerFactory() {
        this.emFactory = Persistence.createEntityManagerFactory("manager");
    }
    
    public void closeEntityManagerFactory() {
        if (emFactory != null) {
            emFactory.close();
            emFactory = null;
        }
    }
}
