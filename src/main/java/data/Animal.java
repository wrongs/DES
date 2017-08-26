/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jakub
 */
@Entity 
@Table(name = "Animal")
public class Animal extends BaseEntity{
    
    private String licencePlate;
    private String gender;
    private String species;
    private String age;
    private String Farm;
    private Product product; 
    
    @OneToMany(mappedBy = "id")
    private Set<Activity> activites;

    public String getGender() {
        return gender;
    }

    public void setGender(String Gender) {
        this.gender = Gender;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String Species) {
        this.species = Species;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String Age) {
        this.age = Age;
    }

    public String getFarm() {
        return Farm;
    }

    public void setFarm(String Farm) {
        this.Farm = Farm;
    }
    
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public Set<Activity> getActivites() {
        return activites;
    }

    public void setActivites(Set<Activity> activites) {
        this.activites = activites;
    }
    
    
}
