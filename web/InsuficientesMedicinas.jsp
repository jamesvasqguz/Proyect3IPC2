<%-- 
    Document   : InsuficientesMedicinas
    Created on : 20/11/2019, 03:52:58 PM
    Author     : jara
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="Proyecto3.Clases.Medicamentos"%>
<%@page import="Proyecto3.Clases.MedicamentosInsuficientes"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        
        <div class="datagrid">
            <%  MedicamentosInsuficientes mi = new MedicamentosInsuficientes();
                Medicamentos m = new Medicamentos();
                ArrayList<Medicamentos> listar = mi.Listar_Medicamentos();
            %> 
            <table>
                <thead>
                    <tr>
                        <th>Nombre del Medicamento</th>
                        <th>Cantidad Actual</th>
                        <th>Cantidad Minima</th>
                        <th>Comprar Mas Medicamentos</th>
                    </tr>
                </thead>
                <tfoot>
                    <tr>
                        <td colspan="4">
                            <div id="paging">
                                <ul><li><a href="#"><span>Previous</span></a></li><li><a href="#" class="active"><span>1</span></a></li><li><a href="#"><span>2</span></a></li><li><a href="#"><span>3</span></a></li><li><a href="#"><span>4</span></a></li><li><a href="#"><span>5</span></a></li><li><a href="#"><span>Next</span></a></li></ul>
                            </div>
                    </tr>
                </tfoot>
                <tbody>
                    <%if (listar.size() > 0) {
                            for (Medicamentos listar2 : listar) {
                                m = listar2;
                    %>
                    <tr>
                        <td><%=m.get%></td>
                        <td><%=m.getNombre()%></td>
                        <td><%=m.getAutor()%></td>
                        <td><%=m.getFechaCreacion()%></td>
                        <td>
                            <%
                                if (re.getPdf() != null) {
                            %>
                            <a href="ShowPdf?autor='<%=re.getAutor()%>'" target="_blank" ><img src="images/1.png" height="50px" width="50px"  title="pdf" /></a>
                                <%
                                    } else {
                                        out.print("Vacio");
                                    }
                                %>
                        </td>
                        <td>
                            <a href="" target="_blank"><img src="images/rea.png" height="50px" width="50px"  title="Reacciones" /></a>
                        </td>
                    </tr>
                    <%}
                            }%>
                </tbody>
            </table>
        </div>
                
    </body>
</html>
