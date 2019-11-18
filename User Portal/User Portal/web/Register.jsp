<%-- 
    Document   : Register
    Created on : Nov 6, 2019, 2:18:54 PM
    Author     : ahnaf
--%>
<%@page import="Servlets.Register"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Page</title>
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

        <%-- Checking email avaiabilty --%>
        <script src="jquery.js" type="text/javascript"></script>
        <script type="text/javascript">
            $(document).ready(function () {
                $(".email").change(function () {
                    var email = $(this).val();

                    $(".estatus").html("<img src='images/loading.gif'><font color=gray> Checking availability...</font>");
                    $.ajax({
                        type: "POST",
                        url: "Register",
                        data: "email=" + email,
                        success: function (msg) {

                            $(".estatus").ajaxComplete(function (event, request, settings) {

                                $(".estatus").html(msg);

                            });
                        }
                    });
                });
                             
            });
                        
        </script>
        
    </head>
    <body>

        <br><br><br>

    <center><h1><strong>Registration Panel</strong></h1></center>

    <br>
    
    <%-- if Registration failed showing the reason of failure --%>
    <% String s = (String) request.getAttribute("failedRegister");
        if (s != null) {%>

    <center><td> <font color=red>  <%= s%></td> </center> 

    <%}%>
    
    <br>

    <center>
        <form name="form1" action="Register" method="POST">
            <div>
                <table>
                    <tr>

                        <td><b><pre><br>First Name  </pre></b></td>
                      <td><input type="text" class="form-control" name="firstName"></td>

                    </tr>

                    <tr>

                        <td><b><pre><br>Last Name  </pre></b></td>
                        <td><input type="text" class="form-control" name="lastName"></td>

                    </tr>

                    <tr>

                        <td><b><pre><br> Address </pre></b></td>
                        <td><input type="text" class="form-control" name="address"></td>
                      
                    </tr>

                    <tr>

                        <td><b><pre><br>  Phone</pre></b></td>
                        <td><input type="text" class="form-control" name="phone"></td>
                   
                    </tr>

                    <tr>

                        <td><b><pre><br>  Email</pre></b></td>
                        <td>

                            <%--
                            <input type="text" id="emailInput" class="form-control" name="email">
                            --%>
                            <input type="text" class="email" class="form-control" name="email"/> <span class="estatus"></span>

                        </td>

                    </tr>

                    <tr>

                        <td><b><pre><br>Birth date</pre></b></td>
                        <td><input type="date"  class="form-control" name="birthDate"></td>

                    </tr>

                    <tr>

                        <td><b><pre><br> Password </pre></b></td>
                        <td><input type="password" class="form-control" name="passWord"></td> 

                    </tr>

                    <tr>
                        <td colspan="2" style="text-align: center"> <br> <input class="btn btn-success" type="submit" value="Register"></td>
                        <td colspan="2" style="text-align: justify"> <br> <input class="btn btn-success" type="reset" value="Clear"></td>
                    </tr>
                    <br>

                </table>
            </div>
        </form>
    </center>



</body>
</html>
