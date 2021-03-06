/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dao;

import data.entites.BaseEntity;
import data.entites.Product;
import data.entites.ProductiveEntity;
import data.enumeration.Unit;
import java.util.Date;
/**
 *
 * @author Jakub
 */
public interface IProductDao extends IDaoBase<Product>{
    public Product create(String name, Date expirationDate, double prize, int quantity, Unit unit);
}
