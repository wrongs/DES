/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dao;

import data.entites.Field;

/**
 *
 * @author Jakub
 */
public interface IFieldDao extends IDaoBase<Field>{
    public Field create(String name, String licencePlate, Double Area); 
}
