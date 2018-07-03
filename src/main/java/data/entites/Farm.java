/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.entites;

import data.entites.Field;
import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jakub
 */
@Entity 
@Table(name = "Farm") 
public class Farm extends BaseEntity{
    String licencePlate;
    
    @OneToMany(mappedBy = "id")
    Set<Field> fields;
    
    @OneToMany(mappedBy = "id")
    Set<Animal> animals;

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public Set<Field> getFields() {
        return fields;
    }

    public void setFields(Set<Field> fields) {
        this.fields = fields;
    }

    public Set<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(Set<Animal> animals) {
        this.animals = animals;
    }
    
   @Override
   public String toString() {
       return licencePlate;
   }
   
   /*
   @Override
    public int hashCode() {
       // int hash = 0;
       // hash += (TCountryCode != null ? TCountryCode.hashCode() : 0);
        return 0;
    }

    @Override
    public boolean equals(Object object) {
        /*
        String otherTCountryCode = "";
        if (object instanceof Country) {
            otherTCountryCode = ((Country)object).TCountryCode;
        } else if(object instanceof String){
            otherTCountryCode = (String)object;
        } else {
            return false;
        }   

        if ((this.TCountryCode == null && otherTCountryCode != null) || (this.TCountryCode != null && !this.TCountryCode.equals(otherTCountryCode))) {
            return false;
        }
        return true;
      }     
*/
}
