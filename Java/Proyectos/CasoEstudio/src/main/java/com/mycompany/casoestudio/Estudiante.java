package com.mycompany.casoestudio;

import java.time.LocalDate;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
public class Estudiante extends Persona { 
    private String programaAcademico;
    private  ArrayList<Calificacion> calificaciones;

    protected  Estudiante(String nombre, String apellido, String id, LocalDate fechaNacimiento, String correoElectronico, String programaAcademico) {
        super(id, fechaNacimiento,nombre, apellido, correoElectronico);
        this.programaAcademico = programaAcademico;
        this.calificaciones = new ArrayList<>();

    }
     //metodos getters
     public ArrayList<Calificacion> getCalificaciones() {
        return calificaciones;
    }

    public String getProgramaAcademico() {
        return programaAcademico;
    }

    

}
