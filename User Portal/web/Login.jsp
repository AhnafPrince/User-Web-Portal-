<%-- 
    Document   : Login
    Created on : Nov 5, 2019, 10:44:22 PM
    Author     : ahnaf
--%>
<%@page import="java.lang.String"%>
<%@page import="Servlets.Login"%> 
<%@page import="Servlets.Register"%> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Page</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"> 
        <style>
            to{
                padding: 10px;
            }

            div{
                width: 60%;
                border: 2px solid black;
                border-radius: 5px;
                background-color: grey;
            }
        </style>

    </head>
    <body>

        <br><br>



    <center><h1><strong>Login Panel</strong></h1></center> 
    <br>
    
    <%-- if login failed showing the reason of failure --%>
    <% String s = (String) request.getAttribute("failedLogin");
        if (s != null) {%>

    <center><td> <font color=red>  <%= s%></td> </center> 

    <%}%>

    <br><br><br>

    <center>
        <form name="form1" action="Login" method="POST">
            <div>
                <table>
                    <tr>
                    <br>
                    <td><b><pre><br>Email Address  </pre></b></td>
                    <td><input type="text" class="form-control" name="email"></td>

                    </tr>

                    <tr>

                        <td><b><pre><br><br>   Password</pre></b></td>
                        <td><br><input type="password" class="form-control" name="passWord"></td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align: center"> <br><br> <input class="btn btn-success" type="submit" value="Login"></td>
                        <td colspan="2" style="text-align: left"> <br><br> <input class="btn btn-success" type="reset" value="Clear"></td>
                    </tr>
                    <br>
                    <tr>
                        <td><b><pre><br><br>Are you new here ?  </pre></b></td>

                        <!-- <td colspan="2" style="text-align: justify"><br> <input class="btn btn-success" type="submit" value="Register" onclick="register()"></td> -->

                        <td colspan="2" style="text-align: justify"><br> <a href="Register.jsp"> Register Now</a></td>

                    </tr>

                </table>
            </div>
        </form>
    </center>



</body>
</html>
