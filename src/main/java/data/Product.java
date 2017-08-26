/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import data.enumeration.Unit;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 *
 * @author Jakub
 */
@Entity 
@Table(name = "Product")
public class Product extends BaseEntity{    
    
    private String name;
    private Date expirationDate;
    private double prize;
    private double quantity;
    private Unit unit;

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public double getPrize() {
        return prize;
    }

    public void setPrize(double prize) {
        this.prize = prize;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
