package ProyectoED01;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Tiquete {

    private String nombre;
    private final String id;
    private int edad;
    private final long horaCreacion;
    private long horaAtencion;
    private final String tramite;
    private final String tipoTiquete;

    public Tiquete(String nombre, String id, int edad, String tramite, String tipoTiquete) {
        this.nombre = nombre;
        this.id = id;
        this.edad = edad;
        this.horaCreacion = System.currentTimeMillis();
        this.horaAtencion = -1;
        this.tramite = tramite;
        this.tipoTiquete = tipoTiquete;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public long getHoraCreacion() {
        return horaCreacion;
    }

    public String getHoraCreacionLegible() { //EXTRAÍDO DE CHAT GPT
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date(horaCreacion));
    }

    public void setHoraAtencion(long horaAtencion) {
        this.horaAtencion = horaAtencion;
    }

    public long getHoraAtencion() {
        return horaAtencion;
    }

    public String getTramite() {
        return tramite;
    }

    public String getTipoTiquete() {
        return tipoTiquete;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return "Información del Tiquete:"
                + "\nNombre: " + nombre
                + "\nID: " + id
                + "\nEdad: " + edad
                + "\nHora de Creación: " + dateFormat.format(new Date(horaCreacion))
                + "\nHora de Atención: " + (horaAtencion == -1 ? "No atendido aún" : dateFormat.format(new Date(horaAtencion)))
                + "\nTrámite: " + tramite
                + "\nTipo de Tiquete: " + tipoTiquete;
    }
}
