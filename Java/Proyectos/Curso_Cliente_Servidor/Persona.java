package Curso_Java.Curso_Cliente_Servidor;

import java.sql.Date;
import java.util.Objects;

public abstract class Persona {
    protected final String dni;
    protected final Date fechaNacimiento;
    protected String nombre;
    protected String apellido;
    protected String email;
    protected String telefono;

    protected Persona(String dni, Date fechaNacimiento, String nombre, String apellido, String email, String telefono) {
        this.dni = Objects.requireNonNull(dni,"Error: DNI no puede ser nulo");
        this.fechaNacimiento = Objects.requireNonNull(fechaNacimiento,"Error: Fecha de nacimiento no puede ser nula");
        this.nombre = Objects.requireNonNull(nombre,"Error: Nombre no puede ser nulo");
        this.apellido = Objects.requireNonNull(apellido,"Error: Apellido no puede ser nulo");
        this.email = Objects.requireNonNull(email,"Error: Email no puede ser nulo");
        this.telefono = Objects.requireNonNull(telefono,"Error: Teléfono no puede ser nulo");
    }

    // Getters
    protected String getDni() {
        return dni;
    }
    protected Date getFechaNacimiento() {
        return fechaNacimiento;
    }
    protected String getNombre() {
        return nombre;
    }
    protected String getApellido() {
        return apellido;
    }
    protected String getEmail() {
        return email;
    }
    protected String getTelefono() {
        return telefono;
    } 

}
