/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseHandle;

/**
 *
 * @author ahnaf
 */
//this class is for the arraylist to get all users information on admin panel
public class UserList {
    
    private String name;
    private String date;
    private String email;
    private String phone;
    
    public UserList(String name,String date,String email,String phone){
        
        this.name = name;
        this.date = date;
        this.email = email;
        this.phone = phone;
    }
    
    public String getName(){
        return name;
    }
    public String getDate(){
        return date;
    }
    public String email(){
        return email;
    }
    public String phone(){
        return phone;
    }
    
}
