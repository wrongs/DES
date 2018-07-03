/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dao;

import data.entites.User;

/**
 *
 * @author Jakub
 */
public interface IUserDao extends IDaoBase<User>{
    
    public User findByName (String username);
    public User findByNamePassword (String username, String password);
    public User create (final String username, final String password);
}
