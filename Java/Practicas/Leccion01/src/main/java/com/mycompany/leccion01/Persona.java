/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.leccion01;
import java.sql.Date;

/**
 *
 * @author Vane
 */
public class Persona {
    private String IdPersona;
    private String Nombre;
    private String Apellidos;
    private Date FechaNacimiento;
    private String Genero;
          
    //public
    //protected
    
    public Persona(){
    }

    public Persona(String IdPersona, String Nombre, String Apellidos, Date FechaNacimiento, String Genero) {
        this.IdPersona = IdPersona;
        this.Nombre = Nombre;
        this.Apellidos = Apellidos;
        this.FechaNacimiento = FechaNacimiento;
        this.Genero = Genero;
    }

    public String getIdPersona() {
        return IdPersona;
    }

    public void setIdPersona(String IdPersona) {
        this.IdPersona = IdPersona;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public Date getFechaNacimiento() {
        return FechaNacimiento;
    }

    public void setFechaNacimiento(Date FechaNacimiento) {
        this.FechaNacimiento = FechaNacimiento;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }

    @Override
    public String toString() {
        return "Persona{" + "IdPersona=" + IdPersona + ", Nombre=" + Nombre + ", Apellidos=" + Apellidos + ", FechaNacimiento=" + FechaNacimiento + ", Genero=" + Genero + '}';
    }
    
    
    
}
