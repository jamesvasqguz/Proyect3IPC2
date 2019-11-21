package Proyecto3.Clases;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author jara
 */
public class ConnectorToDB {
     public static Connection conexion(){
        String user="vasqguz";
        String password="skyfall007";
        String stringConnection = "jdbc:mysql://localhost:3306/Hospital";
        try{
            Connection cn = DriverManager.getConnection(stringConnection, user, password);           
            System.out.println("Entre");
            return cn;
        }
        catch (SQLException e) {
            System.out.println("Fallo la conexion.");
            System.out.println(e);
        }
        return (null);
    }    
}
