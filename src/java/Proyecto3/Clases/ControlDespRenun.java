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
@WebServlet("/ControlDespRenun")
public class ControlDespRenun extends HttpServlet {

    Connection cn = ConnectorToDB.conexion();
    public static int dpi_empleado;
    public static String caso, desc;
    Date now = new Date(System.currentTimeMillis());
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dpi_empleado = Integer.parseInt(request.getParameter("dpiD"));
        caso = request.getParameter("caso");
        desc = request.getParameter("desc");
        if (desc.equals("")) {
           desc="Vacio";
        }

        String sql1 = "INSERT INTO Renuncias_Despedidas VALUES(?,?,?,?,?)";
        String sql2 = "SELECT dpi FROM Empleado WHERE dpi='" + dpi_empleado + "'";
        String sql3 = "INSERT INTO Empleado despedido_renuncia=? WHERE dpi='" + dpi_empleado + "'";
        PreparedStatement ps, ps1, ps2 = null;
        ResultSet rs, rs1 = null;

        try {
            ps = cn.prepareStatement(sql2);
            rs = ps.executeQuery();
            if (rs.next()) {
                ps1 = cn.prepareStatement(sql1);
                ps1.setInt(1, 0);
                ps1.setInt(2, dpi_empleado);
                ps1.setString(3, caso);
                ps1.setString(4, desc);
                ps1.setDate(5, now);
                ps1.executeUpdate();
                try {
                    ps2 = cn.prepareStatement(sql3);
                    ps2.setBoolean(6, true );
                    ps2.executeUpdate();
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Administrador.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    request.setAttribute("error", true);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("RenunDespi.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                request.setAttribute("error", true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("RenunDespi.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("RenunDespi.jsp");
            dispatcher.forward(request, response);
        }
    }

}
