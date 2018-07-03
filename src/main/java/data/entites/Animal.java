/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.entites;

import data.enumeration.Gender;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jakub
 */
@Entity 
@Table(name = "Animal")
public class Animal extends ProductiveEntity{
    
    private String licencePlate;
    private String species;
    private int age; 
    
    @Enumerated(value = EnumType.ORDINAL)
    private Gender gender;

    @ManyToOne
    private Farm farm;

    public Farm getFarm() {
        return farm;
    }

    public void setFarm(Farm farm) {
        this.farm = farm;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }
    
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender Gender) {
        this.gender = Gender;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String Species) {
        this.species = Species;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int Age) {
        this.age = Age;
    }
}
