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
public class VerifyProvidedPassword {
    
    
    //this method is for checking the password for login 
    public boolean passwordValidation(String providedPassword,String securePassword,String salt){
        
      
        boolean passwordMatch = PasswordUtils.verifyUserPassword(providedPassword, securePassword, salt);
        
        if(passwordMatch) 
        {
            return true;
        } else {
            return false;
        }
    }
}