/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dao;

import data.entites.Animal;
import data.entites.AnimalActivity;
import data.enumeration.Status;

/**
 *
 * @author Jakub
 */
public class AnimalActivityDao extends DaoBase<AnimalActivity> implements IAnimalActivityDao{
     
    @Override
    public AnimalActivity create(String name, Animal animal, Status status){
        AnimalActivity activity = new AnimalActivity();
        activity.setName(name);
        activity.setStatus(status);
        activity.setAnimal(animal);
        insert(activity);
        return activity;
    }

    @Override
    public Class<AnimalActivity> getEntityClass() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
