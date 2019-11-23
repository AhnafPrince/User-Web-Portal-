/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DatabaseHandle.DB_Connection;
import PasswordEncryption.ProtectUserPassword;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ahnaf
 */
public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            boolean nameFlag = false, addressFlag = false, phoneFlag = false, emailFlag = false, passFlag = false;

            DB_Connection db = new DB_Connection();
            HttpSession session = request.getSession();

            String email = request.getParameter("email");

            if (db.checkEmailAvailable(email)) {

                if (email == "" || email == null) {
                    out.println("<font color=red><b>" + email + "</b> Email id is required!</font>");
                    emailFlag = false;
                } else if (db.isValid(email)) {

                    out.println("<font color=green><b>" + email + "</b> is avaliable</font>");
                    emailFlag = true;

                } else {
                    out.println("<font color=red><b>" + email + "</b> is an invalid email!</font>");
                    emailFlag = false;
                }
            } else {
                out.println("<font color=red><b>" + email + "</b> is already in use</font>");
                emailFlag = false;
            }

            String firstName = request.getParameter("firstName");
            if (firstName == "" || firstName == null) {
                out.println("<font color=red> FirstName is required!</font>");
                out.println();
                nameFlag = false;
            } else {
                nameFlag = true;
            }
            String lastName = request.getParameter("lastName");
            String address = request.getParameter("address");
            if (address == "" || address == null) {
                out.println("<font color=red> Address is required!</font>");
                out.println();
                addressFlag = false;
            } else {
                addressFlag = true;
            }
            String phone = request.getParameter("phone");
            if (phone == "" || phone == null) {
                out.println("<font color=red> Phone No is required!</font>");
                out.println();
                phoneFlag = false;
            } else {
                phoneFlag = true;
            }
            String date = request.getParameter("birthDate");

            String passWord = request.getParameter("passWord");
            String salt = "";
            String securePassword = "";

            if (passWord == "") {
                out.println("<font color=red> Password is required!</font>");
                out.println();
                passFlag = false;
            } else {
                passFlag = true;

                ProtectUserPassword pup = new ProtectUserPassword();
                salt = pup.getSaltValue();
                securePassword = pup.getSecurePassword(passWord, salt);

            }

            if (nameFlag==true && addressFlag==true && emailFlag==true && phoneFlag==true && passFlag==true) {
                if (db.isValid(email) && db.checkEmailAvailable(email)) {

                    db.registerUser(firstName, lastName, address, phone, email, date, securePassword, salt, out);

                    String[] profileData = db.getUserProfile(email, out);
                    profileData[5] = email;
                    session.setAttribute("userProfileData", profileData);
                    RequestDispatcher rd = request.getRequestDispatcher("UserProfile.jsp");
                    rd.forward(request, response);
                }
            } else {
                out.println("<font color=red> Insert failed!</font>");
                request.setAttribute("failedRegister", "All fields including valid email is required for registration!");
                RequestDispatcher rd = request.getRequestDispatcher("Register.jsp");
                rd.forward(request, response);
            }

        }
    }

    //A method for checking valid email 
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        doPost(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
