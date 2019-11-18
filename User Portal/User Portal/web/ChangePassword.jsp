<%-- 
    Document   : ChangePassword
    Created on : Nov 9, 2019, 12:27:09 PM
    Author     : ahnaf
--%>

<%@page import="java.lang.String"%>
<%@page import="Servlets.Login"%> 
<%@page import="Servlets.Register"%> 
<%@page import="Servlets.ChangePassword"%> 
<%@page import="Servlets.UserProfile"%> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profile Page</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"> 
        <style>

            header {
                background-color: #666;
                padding: 30px;
                text-align: right;
                font-size: 10px;
                color: white;
            }

            .dropbtn {
                background-color: #3498DB;
                color: white;
                padding: 16px;
                font-size: 16px;
                border: none;
                cursor: pointer;
            }

            .dropbtn:hover, .dropbtn:focus {
                background-color: #2980B9;
            }

            .dropdown {
                position: relative;
                display: inline-block;
            }

            .dropdown-content {
                display: none;
                position: absolute;
                background-color: #f1f1f1;
                min-width: 20px;
                overflow: auto;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .dropdown-content a {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .dropdown a:hover {background-color: #ddd;}

            .show {display: block;}

            /* Create two columns/boxes that floats next to each other */
            nav {
                float: left;
                width: 20%;
                height: 500px; /* only for demonstration, should be removed */
                background: #ccc;
                padding: 20px;
            }

            /* Style the list inside the menu */
            nav ul {
                list-style-type: none;
                padding: 0;
            }

            article {
                float: right;
                padding: 20px;
                width: 80%;
                background-color: #f1f1f1;
                height: 500px; /* only for demonstration, should be removed */
                text-align: center;
            }

            /* Clear floats after the columns */
            section:after {
                content: "";
                display: table;
                clear: both;
            }


            /* Responsive layout - makes the two columns/boxes stack on top of each other instead of next to each other, on small screens */
            @media (max-width: 600px) {
                nav, article {
                    width: 100%;
                    height: auto;
                }
            }




        </style>

    </head>
    <body>

        <%String firstName = session.getAttribute("firstNameUser").toString();  %>
        <%String emailId = session.getAttribute("emailIdUser").toString();  %>
        <% session.setAttribute("emailIdChangePassword", emailId);%>

        <header>
            <h2 style="text-align: left">Navigation</h2>

            <div class="dropdown">
                <button onclick="myFunction()" class="dropbtn" id="b"><%= firstName%></button>
                <div id="myDropdown" class="dropdown-content">

                    <a href="Logout" method="POST"><b>Log Out</b></a>

                </div>
            </div>

            <script>
                /* When the user clicks on the button, 
                 toggle between hiding and showing the dropdown content */
                function myFunction() {
                    document.getElementById("myDropdown").classList.toggle("show");
                }

// Close the dropdown if the user clicks outside of it
                window.onclick = function (event) {
                    if (!event.target.matches('.dropbtn')) {
                        var dropdowns = document.getElementsByClassName("dropdown-content");
                        var i;
                        for (i = 0; i < dropdowns.length; i++) {
                            var openDropdown = dropdowns[i];
                            if (openDropdown.classList.contains('show')) {
                                openDropdown.classList.remove('show');
                            }
                        }
                    }
                }
            </script>

        </header>



        <section>
            <nav>
                <ul>
                    
                    <br>
                    <li><a href="UserProfile" method="POST">User Profile</a></li>

                </ul>
            </nav>



            <article>
                <h1 style="text-align: center; background: gray" ><b>Change Password</b></h1>
                <br>

                <% String s = (String) request.getAttribute("wrongPreviousPassword");
                    if (s != null) {%>

                <center><td> <font color=red>  <%= s%></td> </center> 

                <%}%>
                <br>

                <center>
                    <form name="form1" action="ChangePassword" method="POST">
                        <div>
                            <table>
                                <tr>
                                <br>
                                <td><b><pre><br>Previous Password  </pre></b></td>
                                <td><input type="password" class="form-control" name="previousPassword"></td>

                                </tr>

                                <tr>

                                    <td><b><pre><br><br>New Password</pre></b></td>
                                    <td><br><input type="password" class="form-control" name="newPassword"></td>
                                </tr>


                                <tr>

                                    <td><b><pre><br><br>Confirm Password</pre></b></td>
                                    <td><br><input type="password" class="form-control" name="confirmPassword"></td>
                                </tr>

                                <tr>
                                    <td colspan="2" style="text-align: center"> <br><br> <input class="btn btn-success" type="submit" value="Change Password"></td>
                                    <td colspan="2" style="text-align: left"> <br><br> <input class="btn btn-success" type="reset" value="Clear"></td>
                                </tr>
                                <br>

                            </table>
                        </div>
                    </form>
                </center>  

            </article>


        </section>


    </body>
</html>
