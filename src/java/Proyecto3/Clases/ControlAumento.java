package Proyecto3.Clases;

import static Proyecto3.Clases.ControlLogin.nameL;
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
@WebServlet("/ControlAumento")
public class ControlAumento extends HttpServlet {

    Connection cn = ConnectorToDB.conexion();
    public static int dpiAumento, nuevoSueldo;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        dpiAumento = Integer.parseInt(request.getParameter("dpiA"));
        nuevoSueldo = Integer.parseInt(request.getParameter("nuevoS"));
        String sql = "SELECT dpi FROM Empleado WHERE dpi='" + dpiAumento + "'";
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                String sql1 = "INSERT INTO Aumento VALUES(?,?,?,?)";
                PreparedStatement ps1 = null;
                Date now = new Date(System.currentTimeMillis());
                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    ps1 = cn.prepareStatement(sql1);
                    ps1.setInt(1, 0);
                    ps1.setInt(2, dpi);
                    ps1.setDate(3, Date.valueOf(date.format(now)));
                    ps1.setInt(4, nuevoSueldo);
                    ps1.executeUpdate();
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Administador.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    request.setAttribute("error", true);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Aumento.jsp");
                    dispatcher.forward(request, response);
                }
            } else {
                request.setAttribute("error", true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("AumentoSueldos.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            request.setAttribute("error", true);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Aumento.jsp");
            dispatcher.forward(request, response);
        }

    }
}
