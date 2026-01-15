package ProyectoED01;

import java.io.BufferedReader; //LEER TEXTO DESDE ARCHIVOS
import java.io.FileReader;//ABRIR ARCHIVO PARA LEER EL CONTENIDO
import javax.swing.JOptionPane;//CUADROS DE DIALOGO
import java.io.FileWriter;//ESCRIBE DATOS (CARACTERES)
import java.io.IOException;//MANEJO DE EXCEPCIONES
import java.io.PrintWriter;//IMPRIMIR TEXTO

public class Login {

    public void guardar(String username, String password) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("Credenciales.txt", true))) {
            writer.println("Usuario: " + username);
            writer.println("Contraseña: " + password);
            writer.println();

            JOptionPane.showMessageDialog(null, "Credenciales guardadas exitosamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar las credenciales: " + e.getMessage());
        }
    }

    public boolean verificar(String username, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader("Credenciales.txt"))) {
            String line;
            String storedUsername = null;
            String storedPassword = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Usuario: ")) {
                    storedUsername = line.substring("Usuario: ".length());
                } else if (line.startsWith("Contraseña: ")) {
                    storedPassword = line.substring("Contraseña: ".length());
                }

                if (storedUsername != null && storedPassword != null) {
                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        return true;
                    }
                    storedUsername = null;
                    storedPassword = null;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al leer las credenciales: " + e.getMessage());
        }
        return false;
    }
}
