package com.mycompany.casoestudio;

import java.util.ArrayList;

public class Estudiante extends Persona {
    private String programaAcademico;
    private  ArrayList<Calificacion> calificaciones;

    protected  Estudiante(String nombre, String apellido, String id) {
        super(nombre, apellido, id);
        this.programaAcademico = programaAcademico;
        this.calificaciones = new ArrayList<>();

    }
     //metodos getters

    public String getProgramaAcademico() {
        return programaAcademico;
    }

    //metodos //

    public void obtenerPromedioGeneral() {
        // Implementar lógica para calcular el promedio general del estudiante
    }

    @Override
    public String toString() {
        return "Estudiante{" + "programaAcademico=" + programaAcademico + ", calificaciones=" + calificaciones + '}';
    }

    

}
