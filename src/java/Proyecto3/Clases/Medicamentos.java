package Proyecto3.Clases;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author jara
 */
public class Medicamentos {

    private int cantIngreso, cantMin;
    private String nombreM;
    private float precio;

    public Medicamentos() {
    }

    public Medicamentos(HttpServletRequest request) throws IOException, ServletException {
        nombreM = request.getParameter("nameM");
        cantIngreso = Integer.parseInt(request.getParameter("cantI"));
        cantMin = Integer.parseInt(request.getParameter("cantM"));
        precio = Float.parseFloat(request.getParameter("precio"));
    }

    public int getCantIngreso() {
        return cantIngreso;
    }

    public void setCantIngreso(int cantIngreso) {
        this.cantIngreso = cantIngreso;
    }

    public int getCantMin() {
        return cantMin;
    }

    public void setCantMin(int cantMin) {
        this.cantMin = cantMin;
    }

    public String getNombreM() {
        return nombreM;
    }

    public void setNombreM(String nombreM) {
        this.nombreM = nombreM;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
}
