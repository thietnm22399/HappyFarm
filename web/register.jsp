<%-- 
    Document   : register
    Created on : Mar 12, 2019, 10:39:33 AM
    Author     : nguye
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/mainstyle.css">
        <title>JSP Page</title>
    </head>
    <body>
        <div align="center" style="margin-top: 100px">
            <img src="img/Register.png" />
            <form action="register" method="post">
                <table>
                    <tr>
                        <td>Username: </td>
                        <td><input style="width: 200px" type="text" name="username" /></td>
                    </tr>
                    <tr>
                        <td>Password: </td>
                        <td><input style="width: 200px" type="password" name="password" /> </td>
                    </tr>
                    <br/>
                </table>
                <input style="width: 80px" type="submit" value="Submit" name="submit">
            </form>
        </div>
    </body>
</html>
