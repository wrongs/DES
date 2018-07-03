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
public class FieldDao extends DaoBase<Field> implements IFieldDao{

    @Override
    public Class<Field> getEntityClass() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Field create(String name, String licencePlate, Double area) {
        Field field = new Field();
        field.setName(name);
        field.setLicencePlate(licencePlate);
        field.setArea(area);
        insert(field);
        return field;
    }
    
}
