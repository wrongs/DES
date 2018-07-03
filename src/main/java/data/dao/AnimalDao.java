/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dao;

import data.entites.Animal;
import data.entites.Farm;
import data.enumeration.Gender;

/**
 *
 * @author Jakub
 */
public class AnimalDao extends DaoBase<Animal> implements IAnimalDao{

    @Override
    public Class<Animal> getEntityClass() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Animal findByIDTag(String idTag) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Animal create(String idTag, int age, String species, Gender gender, Farm farm) {
        Animal animal = new Animal();
        animal.setLicencePlate(idTag);
        animal.setAge(age);
        animal.setSpecies(species);
        animal.setGender(gender);
        animal.setFarm(farm);
        insert(animal);
        return animal;
    }
    
}
