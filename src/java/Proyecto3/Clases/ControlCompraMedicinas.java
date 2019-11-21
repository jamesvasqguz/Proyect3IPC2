package Proyecto3.Clases;

import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "ControlCompraMedicinas", urlPatterns = {"/ControlCompraMedicinas"})
public class ControlCompraMedicinas extends HttpServlet {

    public static int cantAc, cantNow;
    Connection cn = ConnectorToDB.conexion();
    Date now = new Date(System.currentTimeMillis());
    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        String name = request.getParameter("nameM");
        Medicamentos m = new Medicamentos(request);
        String sql1 = "INSERT INTO Inventario VALUES(?,?,?,?,?,?)";
        String sql2 = "SELECT nombre_medicamento, cantidad_actual FROM Inventario WHERE nombre_medicamento='" + name + "'";
        PreparedStatement ps, ps1, ps2 = null;
        ResultSet rs = null;
        try {
                    ps = cn.prepareStatement(sql1);
                    ps.setString(1, m.getNombreM());
                    ps.setInt(2, m.getCantIngreso());
                    ps.setInt(3, m.getCantIngreso());
                    ps.setInt(4, m.getCantMin());
                    ps.setFloat(5, m.getPrecio());
                    ps.setDate(6, Date.valueOf(date.format(now)));
                    ps.executeUpdate();
                    RequestDispatcher dispatcher = request.getRequestDispatcher("ComprarMedicinas.jsp");
                    dispatcher.forward(request, response);
                } catch (SQLException e) {
                    request.setAttribute("error", true);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("Farmacia.jsp");
                    dispatcher.forward(request, response);
                }
//        try {
//            ps1 = cn.prepareStatement(sql2);
//            rs = ps1.executeQuery();
//            if (rs.next()) {
//                cantAc = rs.getInt("cantidad_actual");
//                cantNow = cantAc + m.getCantIngreso();
//                String sql3 = "UPDATE Inventario SET cantidad_actual='"+cantNow+"' WHERE nombre_medicamento='" + name + "'";
//                ps2 = cn.prepareStatement(sql3);
//                ps2.executeUpdate();
//            } else if (!rs.next()) {
//                
//            }
//        } catch (Exception e) {
//            request.setAttribute("error", true);
//            RequestDispatcher dispatcher = request.getRequestDispatcher("Farmacia.jsp");
//            dispatcher.forward(request, response);
//        }

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
