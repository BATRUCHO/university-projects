package Curso_Java.Curso_Cliente_Servidor;

import java.util.Objects;
import java.sql.Date;

public class Clase_Usuario extends Persona {

    private final int id;
    private String password;
    private static int contador_usuarios; 
    private Clase_Rol rol;

    public Clase_Usuario(String dni, Date fechaNacimiento, String nombre, String apellido, String email, String telefono, String password, Clase_Rol rol) {

        super(dni, fechaNacimiento, nombre, apellido, email, telefono);
        this.password = Objects.requireNonNull(password, "La contraseña no puede ser nula");
        this.id = ++contador_usuarios; // arreglar despues para que no sea un thread unsafe // multihilo
        this.rol = Objects.requireNonNull(rol, "El rol no puede ser nulo");
    }

    ////////GETTERS/////////
    public String getPassword() {
        return password;
    }
    public int getId() {
        return id;
    }
    public static int getContadorUsuarios() {
        return contador_usuarios;
    }
     public Clase_Rol getRol() {
        return rol;
    }

    /////////SETTERS/////////
   
    public void setPassword(String password) { // revisar temas de seguridad
        this.password = password;
    }
    public void setRol(Clase_Rol rol) {  // revisar validaciones
        if (rol == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo");
        } 
        if (!rol.isActivo()) {
            throw new IllegalArgumentException("El rol no está activo");
        } else {
            this.rol = rol;
        }
    }
}