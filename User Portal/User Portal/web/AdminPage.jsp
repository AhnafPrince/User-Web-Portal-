<%-- 
    Document   : AdminPage
    Created on : Nov 9, 2019, 10:23:14 PM
    Author     : ahnaf
--%>

<%@page import="DatabaseHandle.UserList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.String"%>
<%@page import="Servlets.Login"%> 
<%@page import="Servlets.Register"%> 

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>User List</title>
        <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" rel="stylesheet"> 


        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.11.2/css/all.css">
        <!-- Bootstrap core CSS -->
        <link href="Bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <!-- Material Design Bootstrap -->
        <link href="Bootstrap/css/mdb.min.css" rel="stylesheet">

        <!-- Showing table & searching library bootstrap -->
        <link href="Bootstrap/css/addons/datatables.css" rel="stylesheet">

        <style>
            table.dataTable thead .sorting:after,
            table.dataTable thead .sorting:before,
            table.dataTable thead .sorting_asc:after,
            table.dataTable thead .sorting_asc:before,
            table.dataTable thead .sorting_asc_disabled:after,
            table.dataTable thead .sorting_asc_disabled:before,
            table.dataTable thead .sorting_desc:after,
            table.dataTable thead .sorting_desc:before,
            table.dataTable thead .sorting_desc_disabled:after,
            table.dataTable thead .sorting_desc_disabled:before {
                bottom: .5em;
            }

        </style>

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

        <header>
            <h2 style="text-align: left">Navigation</h2>

            <div class="dropdown">
                <button onclick="myFunction()" class="dropbtn" id="b">Administrator</button>
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
                    <li><a href="ShowUserList" method="POST"><b>User List</b> </a></li>

                </ul>
            </nav>

            <article>
                <h1 style="text-align: left; background: gray" ><b>User List</b></h1>
                <br>

                <table id="dtBasicExample" class="table table-striped table-bordered table-sm" cellspacing="0" width="100%">
                    <thead>
                        <tr>
                            <th class="th-sm">Name
                            </th>
                            <th class="th-sm">Age
                            </th>
                            <th class="th-sm">Email
                            </th>
                            <th class="th-sm">Phone
                            </th>

                        </tr>
                    </thead>
                    <tbody>



                        <%-- Fetching the attributes of the request object 
              which was previously set by the servlet  
               
                        --%>  
                        <%ArrayList<UserList> std
                    = (ArrayList<UserList>) session.getAttribute("userList");
            for (UserList s : std) {%> 
                        <%-- Arranging data in tabular form 
                        --%> 
                        <tr> 
                            <td><%=s.getName() %></td> 
                            <td><%=s.getDate() %></td> 
                            <td><%=s.email() %></td>
                            <td><%=s.phone() %></td>
                        </tr> 
                        <%}%> 



                    </tbody>

                </table>

            </article>

        </section>


        <!-- SCRIPTS -->
        <!-- JQuery -->
        <script type="text/javascript" src="Bootstrap/js/jquery-3.4.1.min.js"></script>
        <!-- Bootstrap tooltips -->
        <script type="text/javascript" src="Bootstrap/js/popper.min.js"></script>
        <!-- Bootstrap core JavaScript -->
        <script type="text/javascript" src="Bootstrap/js/bootstrap.min.js"></script>
        <!-- MDB core JavaScript -->
        <script type="text/javascript" src="Bootstrap/js/mdb.min.js"></script>
        <!-- MDB core datatable -->
        <script type="text/javascript" src="Bootstrap/js/addons/datatables.js"></script>

        <script>

                $(document).ready(function () {
                    $('#dtBasicExample').DataTable();
                    $('.dataTables_length').addClass('bs-select');
                });

        </script>


    </body>
</html>
