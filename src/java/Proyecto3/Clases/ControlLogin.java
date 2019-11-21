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
 @WebServlet("/ControlLogin")
public class ControlLogin extends HttpServlet{
    Connection cn = ConnectorToDB.conexion();
    public static String nameL;
    String passL;
    String get;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        nameL = request.getParameter("nombreLogin");
        passL = request.getParameter("passLogin");
        
//        HttpSession nuevaSesion = request.getSession();
//        nuevaSesion.setAttribute("SesionU",nameL);
        try {
            PreparedStatement ps = cn.prepareStatement("SELECT user_name, pass, tipo_usuario FROM User WHERE user_name='" + nameL + "' AND pass='" +passL  + "'");
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                get = rs.getString("tipo_usuario");
                if ("Administrador".equals(get)) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Administrador.jsp");
                    dispatcher.forward(request, response);
                } else if ("Farmacia".equals(get)) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Farmacia.jsp");
                    dispatcher.forward(request, response);
                } else if ("Jefe".equals(get)) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("JefeDeEmpleados.jsp");
                    dispatcher.forward(request, response);
                } else if ("Recepcionista".equals(get)) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Recepcionista.jsp");
                    dispatcher.forward(request, response);
                }else if ("Doctor".equals(get)) {
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Doctor.jsp");
                    dispatcher.forward(request, response);
                }              
            } else if (!rs.next()) {
                request.setAttribute("error", true);
                RequestDispatcher dispatcher = request.getRequestDispatcher("Inicio.jsp");
                dispatcher.forward(request, response);
            }
        } catch (SQLException e) {
            System.out.println("Error al comprobar Usuario");
            System.err.println(e);
        }
    }
 }
