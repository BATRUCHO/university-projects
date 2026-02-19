package Practicas.practicas_gemini_java.practica1;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;


public class ArchivoServicio {

    public static void guardarCuentas(List<CuentaBancaria> cuentas, String nombreArchivo) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(nombreArchivo))){
            oos.writeObject(cuentas);
            System.out.println("Datos guardados");
        } catch (IOException e) {
            System.err.println("Error al guardar" + e.getMessage());
        }      
    }

    @SuppressWarnings("unchecked")
    public static List<CuentaBancaria> cargarCuentas(String nombreArchivo){
        try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))){
            return (List<CuentaBancaria>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("No se pudo cargar el archivo " + e.getMessage());
            return null;
        }
    }

}
