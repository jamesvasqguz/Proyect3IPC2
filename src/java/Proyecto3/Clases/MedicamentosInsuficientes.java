package Proyecto3.Clases;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author jara
 */
public class MedicamentosInsuficientes {
    Connection cn = ConnectorToDB.conexion();
    
    /*Metodo listar*/
    public ArrayList<Medicamentos> Listar_Medicamentos() {

        ArrayList<Medicamentos> list = new ArrayList<Medicamentos>();
        String sql = "SELECT nombre_medicamento, cantidad_actual,cantidad_minima FROM Inventario WHERE cantidad_actual=cantidad_minima";
        ResultSet rs = null;
        PreparedStatement ps = null;
        try {
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Medicamentos m = new Medicamentos();
                m.setNombreM(rs.getString("nombre_medicamento"));
                m.setCantIngreso(rs.getInt("cantidad_actual"));
                m.setCantMin(rs.getInt("cantidad_minima"));
                list.add(m);
            }
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        } finally {
            try {
                ps.close();
                rs.close();
                cn.close();
            } catch (Exception ex) {
            }
        }
        return list;
    }
    
}
