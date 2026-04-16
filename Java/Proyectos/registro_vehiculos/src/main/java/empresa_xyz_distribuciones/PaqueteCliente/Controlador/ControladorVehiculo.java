package empresa_xyz_distribuciones.PaqueteCliente.Controlador;

import java.util.ArrayList;
import java.util.List;

import empresa_xyz_distribuciones.PaqueteCliente.ModeloRed.ClienteSocket;
import empresa_xyz_distribuciones.PaqueteComun.Red.MensajeRed;
import empresa_xyz_distribuciones.PaqueteComun.modelos.Vehiculo;


public class ControladorVehiculo {

    public ClienteSocket clienteSocket;

    public ControladorVehiculo(ClienteSocket clienteSocket) {
        this.clienteSocket = clienteSocket;
    }

    public String registrarVehiculo(String placa, String marca, String modelo, int kilometraje) {
        Vehiculo nuevo = new Vehiculo(placa, marca, modelo, kilometraje);
        MensajeRed peticion = new MensajeRed("INSERTAR_VEHICULO", nuevo, false, null);
        MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);
        return respuesta.isEstadoExito() ? "✅ Guardado" : "❌ Error: " + respuesta.getMensajeRespuesta();
    }

    public String actualizarVehiculo(int id, String placa, String marca, String modelo, int kilometraje) {
        Vehiculo vehiculo = new Vehiculo(id, placa, marca, modelo, kilometraje);
        MensajeRed peticion = new MensajeRed("ACTUALIZAR_VEHICULO", vehiculo, false, null);
        MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);
        return respuesta.isEstadoExito() ? "✅ Actualizado" : "❌ Error: " + respuesta.getMensajeRespuesta();
    }

    public String eliminarVehiculo(int id) {
        MensajeRed peticion = new MensajeRed("ELIMINAR_VEHICULO", id, false, null);
        MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);
        return respuesta.isEstadoExito() ? "✅ Eliminado" : "❌ Error: " + respuesta.getMensajeRespuesta();
    }

    public List<Vehiculo> obtenerListaVehiculos() {
        MensajeRed peticion = new MensajeRed("LISTAR_VEHICULOS", null, false, null);
        MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);

        if (respuesta.isEstadoExito()) {
            return (List<Vehiculo>) respuesta.getPayload();
        }
        return new ArrayList<>();
    }

    public Vehiculo buscarPorId(int id) {
    try {
        MensajeRed peticion = new MensajeRed("BUSCAR_VEHICULO_ID", id, false, null);
        MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);

        if (respuesta != null && respuesta.isEstadoExito()) {
            return (Vehiculo) respuesta.getPayload();
        }
    } catch (Exception e) {
        System.err.println("Error al buscar por ID: " + e.getMessage());
    }
    return null;
    }

    public Vehiculo buscarPorPlaca(String placa) {
        try {
            MensajeRed peticion = new MensajeRed("BUSCAR_VEHICULO_PLACA", placa, false, null);
            MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);

            if (respuesta != null && respuesta.isEstadoExito()) {
                return (Vehiculo) respuesta.getPayload();
            }
        } catch (Exception e) {
            System.err.println("Error al buscar por placa: " + e.getMessage());
        }
        return null;
    }




}
