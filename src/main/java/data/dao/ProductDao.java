/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dao;

import data.entites.Product;
import data.entites.ProductiveEntity;
import data.enumeration.Unit;
import java.util.Date;
/**
 *
 * @author Jakub
 */
public class ProductDao extends DaoBase<Product> implements IProductDao{

    @Override
    public Product create(String name, Date expirationDate, double prize, int quantity, Unit unit)
    {
        Product product = new Product();
        product.setName(name);
        product.setExpirationDate(expirationDate);  
        product.setPrize(prize);
        product.setQuantity(quantity);
        product.setUnit(unit);
        insert(product);
        return product;
    }

    @Override
    public Class<Product> getEntityClass() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
