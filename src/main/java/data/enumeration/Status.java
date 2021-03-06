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
public enum Status {
    
    NEW ("New"),
    ACTIVE ("Active"),
    SUSPENDED ("Suspended"),
    CLOSED ("Closed"),
    CANCELED ("Canceld");
    
    private final String name;       

    private Status(String s) {
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
