/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PasswordEncryption;

/**
 *
 * @author ahnaf
 */
public class ProtectUserPassword {

    public String getSaltValue() {
        
        // Generate Salt. The generated value can be stored in DB. 
        String salt = PasswordUtils.getSalt(30);

       return salt;
    }
    
    public String getSecurePassword(String passWord , String salt ){
        
        // Protect user's password. The generated value can be stored in DB.
        String securePassword = PasswordUtils.generateSecurePassword(passWord, salt);
        
        return securePassword;
    }
            
            
}
