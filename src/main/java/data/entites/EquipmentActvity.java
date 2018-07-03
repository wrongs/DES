/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.entites;

import javax.persistence.ManyToOne;

/**
 *
 * @author Jakub
 */
public class EquipmentActvity extends Activity{
    
    @ManyToOne
    private Equipment equipment;
    
    public Equipment getEquipment() {
        return equipment;
    }

    public void setAnimal(Equipment equipment) {
        this.equipment = equipment;
    }
}
