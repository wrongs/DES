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
public class ActivityDao extends DaoBase<Activity> implements IActivityDao{

    @Override
    public Activity create(String name, Status status){
        Activity activity = new Activity();
        activity.setName(name);
        activity.setStatus(status);
        insert(activity);
        return activity;
    }

    @Override
    public Class<Activity> getEntityClass() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
