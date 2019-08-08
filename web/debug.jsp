<%-- 
    Document   : debug
    Created on : Mar 15, 2019, 1:59:05 PM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" type="text/css" href="css/mainstyle.css">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <script src="https://ajax.aspnetcdn.com/ajax/jQuery/jquery-3.3.1.min.js"></script>
        <script>
            $(document).ready(function () {
                $("#newTable").on("click", "td", function () {
                    $(this).toggleClass("selectClass");
                    var x = document.getElementById("zz");
                    document.write(x.value);
                });
                // jQuery methods go here...

            });

        </script>
    </head>
    <body>
        <h1>Hello World!</h1>
        <table id="newTable" style="border: 1px solid red; height: 50px; witdh: 50px">
            <tr style="width: 50px">
                <td style="border: 1px solid red; width: 50px">a</td>
                <td style="border: 1px solid red;width: 50px" >b</td>
                <td style="border: 1px solid red;width: 50px" >c</td>
                <td style="border: 1px solid red;width: 50px" >d</td>
            </tr>
        </table>
    </body>
</html>
