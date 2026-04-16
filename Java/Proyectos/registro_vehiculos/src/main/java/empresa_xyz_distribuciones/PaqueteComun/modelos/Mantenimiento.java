package empresa_xyz_distribuciones.PaqueteComun.modelos;

import java.io.Serializable;
import java.util.Date;

public class Mantenimiento implements Serializable {
    
    private int id;
    private int vehiculo_id;
    private String tipo;
    private Date fecha;
    private int kilometraje;

    public Mantenimiento(int id, int vehiculo_id, String tipo, Date fecha, int kilometraje) {
        this.id = id;
        this.vehiculo_id = vehiculo_id;
        this.tipo = tipo;
        this.fecha = fecha;
        this.kilometraje = kilometraje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehiculo_id() {
        return vehiculo_id;
    }

    public void setVehiculo_id(int vehiculo_id) {
        this.vehiculo_id = vehiculo_id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }




}
