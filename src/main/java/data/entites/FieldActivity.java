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
public class FieldActivity extends Activity{
    
    @ManyToOne
    private Field field;
    
    public Field getField() {
        return field;
    }

    public void setField(Field field) {
        this.field = field;
    }
}
