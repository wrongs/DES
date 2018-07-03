/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.entites;

import java.util.Set;
import javax.persistence.OneToMany;

/**
 *
 * @author Jakub
 */
public class ProductiveEntity extends BaseEntity{
    
    @OneToMany(mappedBy = "id")
    private Set<Product> products;

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
}
