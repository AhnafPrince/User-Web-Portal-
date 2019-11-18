/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import DatabaseHandle.DB_Connection;
import java.io.IOException;
import java.io.PrintWriter;
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
public class ChangePassword extends HttpServlet {

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
           

            HttpSession session = request.getSession();

            DB_Connection db = new DB_Connection();
            String email = session.getAttribute("emailIdChangePassword").toString();
            String previousPassword = request.getParameter("previousPassword");
            String newPassword = request.getParameter("newPassword");
            String confirmPassword = request.getParameter("confirmPassword");

            if (previousPassword != null && newPassword != null && confirmPassword.equals(newPassword)) {
                boolean loginConfirmed = db.loginUser(email, previousPassword, out);
                if (!loginConfirmed) {
                    request.setAttribute("wrongPreviousPassword", "Previous Password is wrong!");
                    RequestDispatcher rd = request.getRequestDispatcher("ChangePassword.jsp");
                    rd.forward(request, response);
                } else {

                    out.println("Previous Password is Correct!");

                    if (db.changeUserPassword(email, newPassword, out)) {

                        out.println("password Updated");

                        String[] profileData = db.getUserProfile(email, out);
                        profileData[5] = email;
                        request.setAttribute("userProfileData", profileData);
                        RequestDispatcher rd = request.getRequestDispatcher("UserProfile.jsp");
                        rd.forward(request, response);

                    } else {
                        request.setAttribute("wrongPreviousPassword", "Wrong Info, Please try again!");
                        RequestDispatcher rd = request.getRequestDispatcher("ChangePassword.jsp");
                        rd.forward(request, response);
                    }

                }

            } else {
                request.setAttribute("wrongPreviousPassword", "Wrong Info, Please try again! ");
                RequestDispatcher rd = request.getRequestDispatcher("ChangePassword.jsp");
                rd.forward(request, response);
            }

        }
    }

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
