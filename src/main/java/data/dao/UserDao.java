    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dao;

import data.entites.User;
import logic.CryptoProvider;
import logic.PersistenceManager;

/**
 *
 * @author Jakub
 */
public class UserDao extends DaoBase<User> implements IUserDao{

    @Override
    public User create(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        insert(user);
        return user;
    }

    @Override
    public Class<User> getEntityClass() {
        return User.class;  
    }
    
    @Override
    public User findByName(String username) {   
        PersistenceManager pm = new PersistenceManager();
        User user = pm.getHqlSingleResult(getEntityClass(), "from User where username = ?1", username);
        return user;
    }

    @Override
    public User findByNamePassword(String username, String password) {   
        PersistenceManager pm = new PersistenceManager();
        User user = pm.getHqlSingleResult(getEntityClass(), "from User where username = ?1 and password = ?2", username, CryptoProvider.encrypt(password));
        return user;
    }
}
