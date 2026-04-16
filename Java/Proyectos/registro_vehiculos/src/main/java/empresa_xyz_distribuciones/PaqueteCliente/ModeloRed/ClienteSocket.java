package empresa_xyz_distribuciones.PaqueteCliente.ModeloRed;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import empresa_xyz_distribuciones.PaqueteComun.Red.MensajeRed;



public class ClienteSocket {
    public MensajeRed enviarPeticion(MensajeRed peticion) {
        try (Socket socket = new Socket("localhost", 12345);
            ObjectOutputStream salida = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream entrada = new ObjectInputStream(socket.getInputStream())) {
            
            // Enviar la petición al servidor
            salida.writeObject(peticion);
            salida.flush();
            
            // Leer la respuesta que procesó el HiloCliente
            return (MensajeRed) entrada.readObject();
            
        } catch (Exception e) {
            return new MensajeRed("ERROR", null, false, "Error de conexión: " + e.getMessage());
        }
    }
}
