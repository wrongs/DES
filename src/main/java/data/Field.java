/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Set;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Jakub
 */
@Entity 
@Table(name = "Field")
public class Field extends BaseEntity{
        
    private String licencePlate;
    
    @OneToMany(mappedBy = "id")
    private Set<Activity> activites;

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public Set<Activity> getActivites() {
        return activites;
    }

    public void setActivites(Set<Activity> activites) {
        this.activites = activites;
    }
    
    
}
