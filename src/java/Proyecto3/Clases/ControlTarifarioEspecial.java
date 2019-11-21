package Proyecto3.Clases;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author jara
 */
@WebServlet("/ControlTarifarioEspecial")
public class ControlTarifarioEspecial extends HttpServlet{
    
    Connection cn = ConnectorToDB.conexion();
    public static String nameEs;
    public static float costoEs;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        nameEs = request.getParameter("nameoe");
        costoEs = Float.parseFloat(request.getParameter("costooe"));

        String sql1 = "INSERT INTO Tarifario_Especialista VALUES(?,?,?)";
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, 0);
            ps.setString(2, nameEs);
            ps.setFloat(3,costoEs);
            ps.executeUpdate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("TarifarioEspecial.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Administrador.jsp");
            dispatcher.forward(request, response);
        }
    }  
    
}
