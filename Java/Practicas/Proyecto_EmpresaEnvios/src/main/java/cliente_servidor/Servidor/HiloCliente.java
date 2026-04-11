package cliente_servidor.Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import cliente_servidor.PaqueteComun.modelos.Almacen;
import cliente_servidor.PaqueteComun.modelos.Caja;
import cliente_servidor.PaqueteComun.red.MensajeRed;
import cliente_servidor.Servidor.DAO.AlmacenDAO;
import cliente_servidor.Servidor.DAO.CajaDAO;


public class HiloCliente extends Thread implements Runnable {
    private Socket socketCliente;
    private ObjectInputStream entrada;
    private ObjectOutputStream salida;

    public HiloCliente(Socket socketCliente) {
        this.socketCliente = socketCliente;
    }

    @Override
    public void run() { 
        try {
            salida = new ObjectOutputStream(socketCliente.getOutputStream());
            salida.flush();
            entrada = new ObjectInputStream(socketCliente.getInputStream());

            // Mientras el socket esté abierto y no se llegue al fin del flujo
            while (!socketCliente.isClosed()) {
                try {
                    Object objectRecibido = entrada.readObject();
                    
                    if (objectRecibido instanceof MensajeRed) {
                        MensajeRed peticion = (MensajeRed) objectRecibido;
                        MensajeRed respuesta = procesarPeticion(peticion);
                        salida.writeObject(respuesta);
                        salida.flush();
                    }
                } catch (java.io.EOFException e) {
                    // El cliente cerró la conexión de forma normal
                    break; 
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Cliente desconectado o error: " + e.getMessage());  
        } finally {
            cerrarConexiones();
        }
    }

    private MensajeRed procesarPeticion(MensajeRed peticion) {
        String accion = peticion.getAccion();

        AlmacenDAO almacenDAO = new AlmacenDAO();
        CajaDAO cajaDAO = new CajaDAO();

        try {
            switch (accion) {
                case "LISTAR_ALMACENES":
                    return new MensajeRed("LISTAR_ALMACENES", almacenDAO.listarAlmacen(), true, null);
                case "INSERTAR_ALMACEN":
                    Almacen almacen = (Almacen) peticion.getPayload();
                    return new MensajeRed("INSERTAR_ALMACEN", null, almacenDAO.insertar(almacen), null);
                case "ELIMINAR_ALMACEN":
                    int codigoAlmacen = (int) peticion.getPayload();
                    return new MensajeRed("ELIMINAR_ALMACEN", null, almacenDAO.eliminarAlmacen(codigoAlmacen), null);
                case "LISTAR_CAJAS":
                    return new MensajeRed("LISTAR_CAJAS", cajaDAO.listarTodo(), true, null);
                case "INSERTAR_CAJA":
                    Caja caja = (Caja) peticion.getPayload();
                    return new MensajeRed("INSERTAR_CAJAS", null, cajaDAO.insertar(caja), null);
                case "ELIMINAR_CAJA":
                    int numReferencia = (int) peticion.getPayload();
                    return new MensajeRed("ELIMINAR_CAJA", null, cajaDAO.eliminarCaja(numReferencia), null);

                default:
                    return new MensajeRed("ERROR", null, false, "Acción no reconocida");
            }
        } catch (Exception e) {
            return new MensajeRed("ERROR", null, false, e.getMessage());
        }
    }
    private void cerrarConexiones() {
        try {
            if(socketCliente != null) socketCliente.close();
        } catch (IOException e) {
            System.err.println("Error en al cerrar la conexion: " + e.getMessage());
        }
    }
            
}
    