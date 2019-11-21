package Proyecto3.Clases;

import static Proyecto3.Clases.ControlAumento.dpiAumento;
import static Proyecto3.Clases.ControlAumento.nuevoSueldo;
import static Proyecto3.Clases.ControlNewAccount.dpi;
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
@WebServlet("/ControlPago")
public class ControlPago extends HttpServlet {

    Connection cn = ConnectorToDB.conexion();
    public static int dpiPago, montoAPagar;

    Date now = new Date(System.currentTimeMillis());
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dpiPago = Integer.parseInt(request.getParameter("dpiE"));

        String sql1 = "INSERT INTO Pago VALUES(?,?,?,?)";
        String sql2 = "SELECT dpi, aumento FROM Aumento WHERE dpi='" + dpiPago + "'";
        String sql3 = "SELECT dpi, sueldo FROM Empleado WHERE dpi='" + dpiPago + "'";
        PreparedStatement ps, ps1, ps2 = null;
        ResultSet rs, rs1 = null;
        try {
            ps1 = cn.prepareStatement(sql2);
            rs1 = ps1.executeQuery();
            if (rs1.next()) {
                montoAPagar = rs1.getInt("aumento");
            } else {
                montoAPagar = 0;
            }
        } catch (SQLException e) {
            System.err.println("No se encontro al dpi en aumento" + e);
        }

        try {
            ps = cn.prepareStatement(sql3);
            rs = ps.executeQuery();
            if (rs.next()) {
                montoAPagar = rs.getInt("sueldo");
            } else {
                request.setAttribute("error", true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("PagoEmpleados.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("PagoEmpleados.jsp");
            dispatcher.forward(request, response);
        }
        try {
            ps2 = cn.prepareStatement(sql1);
            ps2.setInt(1, 0);
            ps2.setInt(2, dpiPago);
            ps2.setDate(3, Date.valueOf(date.format(now)));
            ps2.setInt(4, montoAPagar);
            ps2.executeUpdate();
            RequestDispatcher dispatcher = request.getRequestDispatcher("Administrador.jsp");
            dispatcher.forward(request, response);
        } catch (SQLException e) {
            request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("PagoEmpleados.jsp");
            dispatcher.forward(request, response);
        }
    }

}
