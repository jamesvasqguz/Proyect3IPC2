package Proyecto3.Clases;

import static Proyecto3.Clases.ControlLogin.nameL;
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
@WebServlet("/ControlNewAccount")
public class ControlNewAccount extends HttpServlet {

    Connection cn = ConnectorToDB.conexion();
    public static String newName, newPass, nivelUsuario;
    public static int dpi;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        newName = request.getParameter("nombre");
        newPass = request.getParameter("pass");
        dpi = Integer.parseInt(request.getParameter("dpi"));
        String sql = "SELECT dpi, tipo_usuario FROM Empleado WHERE dpi='" + dpi + "'";
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                nivelUsuario = rs.getString("tipo_usuario");
                try {
                    PreparedStatement ps1 = cn.prepareStatement("INSERT INTO User VALUES (?,?,?,?,?)");
                    ps1.setInt(1, dpi);
                    ps1.setString(2, newName);
                    ps1.setString(3, newPass);
                    ps1.setString(4, nivelUsuario);
                    ps1.setBoolean(5, true);
                    ps1.executeUpdate();
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    System.out.println("Error al comprobar Usuario");
                    System.err.println(e);
                }
            } else if (!rs.next()) {
                request.setAttribute("error", true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("NewAccount.jsp");
                dispatcher.forward(request, response);
            }

        } catch (SQLException e) {
            request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("NewAccount.jsp");
            dispatcher.forward(request, response);
        }
    }
}
