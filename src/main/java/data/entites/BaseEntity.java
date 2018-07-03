package data.entites;

import java.io.Serializable;
import java.util.Set;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;

@MappedSuperclass
public abstract class BaseEntity implements Serializable{ 
//public abstract class BaseEntity{ 
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    //@GeneratedValue(strategy = GenerationType.TABLE)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
     
    @OneToMany(mappedBy = "id")
    private Set<Activity> activites;
    
    public int getId() {  
        return id;  
    }  
  
    protected void setId(int id) {  
        this.id = id;
    } 
    
    public Set<Activity> getActivites() {
        return activites;
    }

    public void setActivites(Set<Activity> activites) {
        this.activites = activites;
    }
}   
