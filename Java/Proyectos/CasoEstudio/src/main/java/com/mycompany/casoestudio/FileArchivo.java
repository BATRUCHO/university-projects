package com.mycompany.casoestudio;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileArchivo {
    private static final String RUTA_ARCHIVO = "C:\\Users\\braya\\Documents\\GitHub\\university-projects\\Java\\Proyectos\\CasoEstudio\\src\\main\\java\\com\\mycompany\\Files\\datos.dat";

    public static void guardarDatos(Object data) {
        try (FileOutputStream fileOut = new FileOutputStream(RUTA_ARCHIVO);
             ObjectOutputStream out = new ObjectOutputStream(fileOut)) {
            out.writeObject(data);
        } catch (IOException e) {
            System.err.println("Error al guardar: " + e.getMessage());
        }
    }

    public static Object cargarDatos() {
        File archivo = new File(RUTA_ARCHIVO);
        if (!archivo.exists()) return null;
        try (FileInputStream fileIn = new FileInputStream(archivo);
             ObjectInputStream in = new ObjectInputStream(fileIn)) {
            return in.readObject();
        } catch (Exception e) {
            return null;
        }
    }
}