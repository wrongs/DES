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
interface IAnimalActivityDao {
    
     public AnimalActivity create(String name, Animal animal, Status status);
}
