package com.mycompany.casoestudio;

import java.io.Serializable;
import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */

/**
 *
 * @author brayan
 */

public abstract class Persona implements Serializable {
    //Atributos
    protected final String id;
    protected LocalDate fechaNacimiento;
    protected String nombre;
    protected String apellido;
    protected String correoElectronico;

    //Constructor
    public Persona(String id, LocalDate fechaNacimiento, String nombre, String apellido, String correoElectronico) {
        this.id = id;
        this.fechaNacimiento = fechaNacimiento;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoElectronico = correoElectronico;
    }

    //Metodos getters 
    public String getId() {
        return id;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }
 

}

