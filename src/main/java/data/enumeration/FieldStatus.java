/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data.enumeration;

/**
 *
 * @author Jakub
 */
public enum FieldStatus {
    
    NEW ("New"),
    PLOWED("Plowed"),
    PLANTED ("Planted"),
    MATURED ("Matured"),
    HARVESTED("Harvested");

    private final String name;       

    private FieldStatus(String s) {
        name = s;
    }
    
    public boolean equalsName(String otherName) {
        // (otherName == null) check is not needed because name.equals(null) returns false 
        return name.equals(otherName);
    }

    @Override
    public String toString() {
       return this.name;
    }
}
