/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.casoestudio;

/**
 *
 * @author braya
 */
public class Curso implements java.io.Serializable  {

    private int idCurso;
    private String nombreCurso;
    private double creditos;

    public Curso(int idCurso, String nombreCurso, double creditos) {
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.creditos = creditos;
        this.ValidarCreditos();
    }

   //Metodos getters
    public int getIdCurso() {
        return idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public double getCreditos() {
        return creditos;
    }
    
    //metodos

    public void ValidarCreditos() {
        if (creditos < 0) {
            throw new IllegalArgumentException("Los créditos no pueden ser negativos.");
        }
    }
    
    
}
