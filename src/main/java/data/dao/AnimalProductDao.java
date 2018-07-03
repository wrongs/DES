/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.dao;

import data.entites.Animal;
import data.entites.AnimalProduct;
import data.entites.Product;
import data.entites.ProductiveEntity;
import data.enumeration.Unit;
import java.util.Date;
/**
 *
 * @author Jakub
 */
public class AnimalProductDao extends DaoBase<Product> implements IAnimalProductDao{

    @Override
    public Product create(String name, Date expirationDate, double prize, int quantity, Unit unit, Animal animal)
    {
        AnimalProduct product = new AnimalProduct();
        product.setName(name);
        product.setExpirationDate(expirationDate);  
        product.setPrize(prize);
        product.setQuantity(quantity);
        product.setUnit(unit);
        product.setAnimal(animal);
        insert(product);
        return product;
    }

    @Override
    public Class<Product> getEntityClass() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
