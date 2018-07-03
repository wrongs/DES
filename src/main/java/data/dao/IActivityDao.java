/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dao;

import data.entites.Activity;
import data.entites.Animal;
import data.enumeration.Status;

/**
 *
 * @author Jakub
 */
public interface IActivityDao extends IDaoBase<Activity>{
    public Activity create(String name, Status status); 
}
