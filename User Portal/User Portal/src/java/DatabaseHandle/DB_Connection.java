/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DatabaseHandle;

import PasswordEncryption.ProtectUserPassword;
import PasswordEncryption.VerifyProvidedPassword;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author ahnaf
 */

//method for getting connection to database
public class DB_Connection {

    public Connection connection = null;

    public Connection getConnection() {
        try {

            String driverName = "oracle.jdbc.OracleDriver";
            Class.forName(driverName);
            String serverName = "localhost";
            String serverPort = "1521";
            String sId = "XE";
            String url = "jdbc:oracle:thin:@" + serverName + ":" + serverPort + ":" + sId;
            String userName = "mascot";
            String passWord = "123";

            connection = DriverManager.getConnection(url, userName, passWord);

        } catch (ClassNotFoundException e) {
            System.out.println("Could not found the Database Driver " + e.getMessage());
        } catch (SQLException ex) {
            Logger.getLogger(DB_Connection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return connection;

    }
    
    //method for getting user information
    public ArrayList<UserList> getUserList(PrintWriter out) throws ParseException {

        ArrayList<UserList> userList = new ArrayList<UserList>();

        try {
            DB_Connection db = new DB_Connection();
            connection = db.getConnection();
            Statement stmt = connection.createStatement();
            String query = "select firstname, lastname , to_char(birthdate,'dd/MM/yyyy' ), email , phone from  userinfo";
            ResultSet result = stmt.executeQuery(query);

            while (result.next()) {

                String firstName = result.getString(1);
                String lastName = result.getString(2);
                String name = firstName + " " + lastName;
                String date = result.getString(3);
                String email = result.getString(4);
                String phone = result.getString(5);
                phone = "+88" + phone;

                if (!email.equals("admin@localhost.local")) {

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date d = sdf.parse(date);
                    Calendar c = Calendar.getInstance();
                    c.setTime(d);
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH) + 1;
                    int dateInt = c.get(Calendar.DATE);
                    LocalDate l1 = LocalDate.of(year, month, dateInt);
                    LocalDate now1 = LocalDate.now();
                    Period diff1 = Period.between(l1, now1);
                    int ageInt = diff1.getYears();
                    String age = Integer.toString(ageInt);

                    userList.add(new UserList(name, age, email, phone));
                }
            }
            connection.close();
        } catch (Exception ex) {
            out.println("Could not get user List information " + ex.getMessage());
        }

        return userList;
    }
    
    // a method for checking email availability for registration
    public boolean checkEmailAvailable(String email) {

        try {
            DB_Connection db = new DB_Connection();
            connection = db.getConnection();
            Statement stmt = connection.createStatement();
            String query = "Select Email from userInfo where Email= '" + email + "'";
            ResultSet rs = stmt.executeQuery(query);

            if (!rs.next()) {
                return true;
            } else {
                return false;
            }
            
            
        } catch (SQLException ex) {
            Logger.getLogger(DB_Connection.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    //this method is for getting logged in user information
    public String[] getUserProfile(String email, PrintWriter out) {

        String[] profileData = new String[6];
        DB_Connection db = new DB_Connection();
        try {

            connection = db.getConnection();
            Statement stmt = connection.createStatement();
            String query = "select firstname,lastname,address,phone,to_char(birthdate,'DD-MON-YYYY') from userinfo where email='" + email + "'";
            ResultSet result = stmt.executeQuery(query);
            if (result.next()) {
                for (int i = 0, j = 1; i <= 4 && j <= 5; i++, j++) {

                    profileData[i] = result.getString(j);
                }
                out.println(" got result ! ");
            }
            connection.close();
        } catch (SQLException ex) {
            out.println("Could not get user profile information " + ex.getMessage());
        }
        return profileData;
    }

    //this method is for verifing user email & password for login purpose
    public boolean loginUser(String email, String passWord, PrintWriter out) {

        try {

            VerifyProvidedPassword vpf = new VerifyProvidedPassword();

            DB_Connection db = new DB_Connection();
            connection = db.getConnection();
            Statement stmt = connection.createStatement();
            String query = "select securePassword , salt from userinfo where email='" + email + "'";
            ResultSet result = stmt.executeQuery(query);

            if (!result.next()) {
                return false;
            } else {

                String securePassword = result.getString(1);
                String salt = result.getString(2);

                if (vpf.passwordValidation(passWord, securePassword, salt)) {
                    return true;
                } else {
                    return false;
                }
            }
        } catch (SQLException ex) {
            out.println("Could not login user " + ex.getMessage());
            return false;
        }

    }
    
    //this method is for changing user password 
    public boolean changeUserPassword(String email, String newPassword, PrintWriter out) {

        try {

            ProtectUserPassword pup = new ProtectUserPassword();
            String salt = pup.getSaltValue();
            String securePassword = pup.getSecurePassword(newPassword, salt);

            DB_Connection db = new DB_Connection();
            connection = db.getConnection();
            Statement stmt = connection.createStatement();
            String query = "update userinfo set securepassword='" + securePassword + "',salt='" + salt + "' where email='" + email + "'";
            int x = stmt.executeUpdate(query);
            connection.close();
            
            if (x > 0) {

                out.println("Password Successfully Updated");
                return true;

            } else {
                System.out.println("ERROR OCCURED :(");
                return false;
            }

        } catch (SQLException ex) {
            out.println("Error occured " + ex.getMessage());
            return false;
        }

    }

    //this method is for registration of user
    public void registerUser(String firstName, String lastName, String address, String phone,
            String email, String birthDate, String securePassword, String salt, PrintWriter out) {

        try {

            DB_Connection db = new DB_Connection();
            connection = db.getConnection();
            Statement stmt = connection.createStatement();
            String query = "insert into USERINFO (FIRSTNAME,LASTNAME,ADDRESS,PHONE,EMAIL,BIRTHDATE,SECUREPASSWORD,SALT) values('" + firstName + "','" + lastName + "','" + address + "','" + phone + "','" + email + "',TO_DATE('" + birthDate + "','YYYY-MM-DD'),'" + securePassword + "','" + salt + "')";

            int x = stmt.executeUpdate(query);
            stmt.executeQuery("commit");
            connection.close();
            if (x > 0) {
                out.println("Successfully Inserted");
            } else {
                out.println("Insert Failed");
            }

        } catch (SQLException ex) {
            out.println("Could not insert into database " + ex.getMessage());
        }

    }

    //this method is for checking whether the email syntax is valid or not
    public boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."
                + "[a-zA-Z0-9_+&*-]+)*@"
                + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
                + "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

}
