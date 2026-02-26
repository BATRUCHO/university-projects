/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.casoestudio;

import java.math.BigDecimal;

/**
 *
 * @author braya
 */
public class Calificacion implements java.io.Serializable {

    private BigDecimal notaExamen1;
    private BigDecimal notaExamen2;
    private BigDecimal Proyecto;
    private RangosCalificacion rango;
    private Curso curso;

    public Calificacion(BigDecimal notaExamen1, BigDecimal notaExamen2, BigDecimal Proyecto, RangosCalificacion rango, Curso curso) {
        this.notaExamen1 = notaExamen1;
        this.notaExamen2 = notaExamen2;
        this.Proyecto = Proyecto;
        this.rango = rango;
        this.curso = curso;
    }
     //Metodos getters

    public BigDecimal getNotaExamen1() {
        return notaExamen1;
    }
    public BigDecimal getNotaExamen2() {
        return notaExamen2;
    }
    public BigDecimal getProyecto() {
        return Proyecto;
    }
    public RangosCalificacion getRango() {
        return rango;
    }
    public Curso getCurso() {
        return curso;
    }

    //metodos

    public BigDecimal calcularPromedio() {
        BigDecimal notaExamen1Multiplicada = notaExamen1.multiply(BigDecimal.valueOf(0.3));
        BigDecimal notaExamen2Multiplicada = notaExamen2.multiply(BigDecimal.valueOf(0.3));
        BigDecimal proyectoMultiplicado = Proyecto.multiply(BigDecimal.valueOf(0.4));
        return notaExamen1Multiplicada.add(notaExamen2Multiplicada).add(proyectoMultiplicado);
    }

    public void determinarEstado() {
        BigDecimal promedio = calcularPromedio();
        if (promedio.compareTo(BigDecimal.valueOf(70)) < 0) {
            rango = RangosCalificacion.REPROBADO;
        } else if (promedio.compareTo(BigDecimal.valueOf(70)) >= 0 && promedio.compareTo(BigDecimal.valueOf(80)) < 0) {
            rango = RangosCalificacion.APROBADO;
        } else if (promedio.compareTo(BigDecimal.valueOf(80)) >= 0 && promedio.compareTo(BigDecimal.valueOf(90)) < 0) {
            rango = RangosCalificacion.NOTABLE;
        } else {
            rango = RangosCalificacion.SOBRESALIENTE;
        }
    }

    public boolean validarRango() {
        BigDecimal promedio = calcularPromedio();
        if (promedio.compareTo(BigDecimal.valueOf(0)) < 0 || promedio.compareTo(BigDecimal.valueOf(100)) > 0) {
            throw new IllegalArgumentException("El promedio debe estar entre 0 y 100.");
        }
        return true;
    }

    public String asignarNota() {
        determinarEstado();
        return rango.name();
    }
    
}
