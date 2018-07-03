/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dao;

import data.entites.Activity;
import data.entites.Animal;
import data.entites.Farm;
import data.entites.Product;
import data.enumeration.Gender;

/**
 *
 * @author Jakub
 */
public interface IAnimalDao extends IDaoBase<Animal>{
    
    public Animal findByIDTag(final String idTag);
    //public Animal findByNamePassword (String username, String password);
    public Animal create(String idTag, int age, String species, Gender gender, Farm farm); 
}
