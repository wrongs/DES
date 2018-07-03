package data.entites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import logic.CryptoProvider;

@Entity 
@Table(name = "User")
public class User extends BaseEntity{
        
    private String username;
    private String password;  

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username =  username;
    }

    public String getPassword() {
        return CryptoProvider.decrypt(password);
    }

    public void setPassword(String password) {
        this.password = CryptoProvider.encrypt(password);
    }
}
