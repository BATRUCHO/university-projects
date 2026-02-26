package com.mycompany.casoestudio;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

public class ControladorEstudiante {
    private List<Estudiante> listaEstudiantes; 

    public ControladorEstudiante() {
        Object datos = FileArchivo.cargarDatos();
        this.listaEstudiantes = (datos instanceof List) ? (List<Estudiante>) datos : new ArrayList<>();
    }

    public void registrarEstudiante(Estudiante nuevo) throws Exception {
        for (Estudiante est : listaEstudiantes) {   
            if (est.getId().equals(nuevo.getId())) {
                throw new Exception("El ID " + nuevo.getId() + " ya existe.");
            }
        }
        listaEstudiantes.add(nuevo);
        FileArchivo.guardarDatos(listaEstudiantes);
    }
    
    public void agregarCalificacionAEstudiante(String id, Calificacion nuevaCal) throws Exception {
        for (Estudiante est : listaEstudiantes) {
            if (est.getId().equals(id)) {
                nuevaCal.determinarEstado();
                est.getCalificaciones().add(nuevaCal);
                FileArchivo.guardarDatos(listaEstudiantes); // Persistencia inmediata
                return;
            }
        }
        throw new Exception("Estudiante no encontrado.");
    }

    public void exportarReporteCSV(String nombreArchivo) throws IOException {
        try (PrintWriter writer = new PrintWriter(new File(nombreArchivo))) {
            writer.println("ID;Nombre;Apellido;Programa;EstadoUltimaNota");
            for (Estudiante est : listaEstudiantes) {
                String estado = est.getCalificaciones().isEmpty() ? "N/A" : 
                                est.getCalificaciones().get(est.getCalificaciones().size()-1).getRango().name();
                writer.printf("%s;%s;%s;%s;%s%n", est.getId(), est.getNombre(), est.getApellido(), est.getProgramaAcademico(), estado);
            }
        }
    }

    public List<Estudiante> getListaEstudiantes() { return listaEstudiantes; }
}