package empresa_xyz_distribuciones.Servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import empresa_xyz_distribuciones.PaqueteComun.Red.MensajeRed;
import empresa_xyz_distribuciones.PaqueteComun.modelos.Combustible;
import empresa_xyz_distribuciones.PaqueteComun.modelos.Mantenimiento;
import empresa_xyz_distribuciones.PaqueteComun.modelos.Vehiculo;
import empresa_xyz_distribuciones.Servidor.DAO.CombustibleDAO;
import empresa_xyz_distribuciones.Servidor.DAO.MantenimientoDAO;
import empresa_xyz_distribuciones.Servidor.DAO.VehiculoDAO;

public class HiloCliente extends Thread  {
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

    public MensajeRed procesarPeticion(MensajeRed peticion) {
        String accion = peticion.getAccion().trim();

        VehiculoDAO vehiculoDAO = new VehiculoDAO();
        MantenimientoDAO mantenimientoDAO = new MantenimientoDAO();
        CombustibleDAO combustibleDAO = new CombustibleDAO();

        try{
            switch (accion) {
                // ---- MÓDULO VEHÍCULOS ----
                case "REGISTRAR_VEHICULO":
                    Vehiculo v = (Vehiculo) peticion.getPayload();
                    return new MensajeRed("REGISTRAR_VEHICULO", vehiculoDAO.insertar(v), true, "Vehículo registrado");

                case "LISTAR_VEHICULOS":
                    // No necesita payload porque trae todos
                    return new MensajeRed("LISTAR_VEHICULOS", vehiculoDAO.listarVehiculos(), true, "Lista obtenida");
                case "BUSCAR_VEHICULO_ID":
                    int idBusqueda = (int) peticion.getPayload();
                    return new MensajeRed("BUSCAR_VEHICULO_ID", vehiculoDAO.obtenerPorId(idBusqueda), true, "Encontrado");

                case "BUSCAR_VEHICULO_PLACA":
                    String placaBusqueda = (String) peticion.getPayload();
                    return new MensajeRed("BUSCAR_VEHICULO_PLACA", vehiculoDAO.buscarPorPlaca(placaBusqueda), true, "Encontrado");    

                // ---- MÓDULO MANTENIMIENTO ----
                case "REGISTRAR_MANTENIMIENTO":
                    Mantenimiento m = (Mantenimiento) peticion.getPayload();
                    return new MensajeRed("REGISTRAR_MANTENIMIENTO", mantenimientoDAO.insertar(m), true, "Mantenimiento registrado");

                case "LISTAR_MANTENIMIENTOS":
                    // El payload es el ID del vehículo seleccionado en la tabla
                    int vId = (int) peticion.getPayload();
                    return new MensajeRed("LISTAR_MANTENIMIENTOS", mantenimientoDAO.listarMantenimientos(), true, "Lista de mantenimientos");

                // ---- MÓDULO COMBUSTIBLE ----
                case "REGISTRAR_COMBUSTIBLE":
                    // Faltaba este caso
                    Combustible c = (Combustible) peticion.getPayload();
                    return new MensajeRed("REGISTRAR_COMBUSTIBLE", combustibleDAO.insertar(c), true, "Combustible registrado correctamente");

                case "OBTENER_CONSUMO_TOTAL":
                    // Faltaba este caso (El payload es el ID del vehículo)
                    int idVehiculoCombustible = (int) peticion.getPayload();
                    return new MensajeRed("OBTENER_CONSUMO_TOTAL", combustibleDAO.obtenerConsumoTotal(idVehiculoCombustible), true, "Consumo total calculado");

                default:
                    return new MensajeRed("ERROR", null, false, "Acción no reconocida por el servidor");
            }
        }catch(Exception e) {
            return new MensajeRed("Error", null, false , e.getMessage());
            }
    }

    private void cerrarConexiones() {
        try {
            if(socketCliente != null) socketCliente.close();
        }catch (IOException e) {
            System.err.println("Error al cerrar conexiones: " + e.getMessage());
        }
    }

}

 