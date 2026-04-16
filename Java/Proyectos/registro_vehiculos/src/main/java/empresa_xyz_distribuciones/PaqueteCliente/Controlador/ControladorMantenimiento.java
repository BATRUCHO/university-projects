package empresa_xyz_distribuciones.PaqueteCliente.Controlador;

import java.util.ArrayList;
import java.util.List;

import empresa_xyz_distribuciones.PaqueteCliente.ModeloRed.ClienteSocket;
import empresa_xyz_distribuciones.PaqueteComun.Red.MensajeRed;
import empresa_xyz_distribuciones.PaqueteComun.modelos.Mantenimiento;


public class ControladorMantenimiento {

    private ClienteSocket clienteSocket;

    public ControladorMantenimiento(ClienteSocket clienteSocket) {
        this.clienteSocket = clienteSocket;
    }

    public String registrarMantenimiento(int id, int vehiculo_id, String tipo, java.util.Date fecha, int kilometraje) {
        Mantenimiento nuevo = new Mantenimiento(id,vehiculo_id, tipo, fecha, kilometraje);
        MensajeRed peticion = new MensajeRed("INSERTAR_MANTENIMIENTO", nuevo, false, null);
        MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);
        return respuesta.isEstadoExito() ? "✅ Guardado" : "❌ Error: " + respuesta.getMensajeRespuesta();
    }

    public String eliminarMantenimiento(int id) {
        MensajeRed peticion = new MensajeRed("ELIMINAR_MANTENIMIENTO", id, false, null);
        MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);
        return respuesta.isEstadoExito() ? "✅ Eliminado" : "❌ Error: " + respuesta.getMensajeRespuesta();
    }

    public List<Mantenimiento> obtenerListaMantenimientos() {
        MensajeRed peticion = new MensajeRed("LISTAR_MANTENIMIENTOS", null, false, null);
        MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);

        if (respuesta.isEstadoExito()) {
            return (List<Mantenimiento>) respuesta.getPayload();
        }
        return new ArrayList<>();
    }


}
