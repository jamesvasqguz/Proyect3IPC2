package Proyecto3.Clases;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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
@WebServlet("/ControlTarifario")
public class ControlTarifario extends HttpServlet{

    Connection cn = ConnectorToDB.conexion();
    public static String nameope;
    public static float costo;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        nameope = request.getParameter("nameop");
        costo = Float.parseFloat(request.getParameter("costoop"));

        String sql1 = "INSERT INTO Tarifario VALUES(?,?,?)";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = cn.prepareStatement(sql1);
            ps.setInt(1, 0);
            ps.setString(2, nameope);
            ps.setFloat(3,costo);
            ps.executeUpdate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("Tarifario.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Administrador.jsp");
            dispatcher.forward(request, response);
        }
    }    
    
}
