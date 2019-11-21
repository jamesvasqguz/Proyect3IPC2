<%-- 
    Document   : Inicio
    Created on : 29/10/2019, 08:21:53 PM
    Author     : jara
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Inicio</title>
        <link href="css/css1.css" rel="stylesheet" type="text/css"/>
    </head>
    <body class="fondo">
        <div class="format">
            <%@include file="html/Start.html" %>
            <table class="tb">
                <tr>
                    <th><a href="NewAccount.jsp"> <img src="image/1.png" height="100px" width="100px"></a></th>
                    <th><a href="Administrador.jsp"> <img src="image/Admin.png" height="100px" width="100px"></a></th>
                    <th><a href="Farmacia.jsp"> <img src="image/farm.png" height="100px" width="100px"></a></th>
                    <th><a href="Recepcionista.jsp"> <img src="image/Recep.png" height="100px" width="100px"></a></th>
                    <th><a href="Doctor.jsp"> <img src="image/Doc.png" height="100px" width="100px"></a></th>
                    <th><a href="JefeDeEmpleados.jsp"> <img src="image/bos.png" height="100px" width="100px"></a></th>
                </tr>
            </table>
        </div>
    </body>
</html>
