package empresa_xyz_distribuciones.PaqueteCliente.Controlador;

import java.math.BigDecimal;

import empresa_xyz_distribuciones.PaqueteCliente.ModeloRed.ClienteSocket;
import empresa_xyz_distribuciones.PaqueteComun.Red.MensajeRed;
import empresa_xyz_distribuciones.PaqueteComun.modelos.Combustible;

public class ControladorCombustible {

    private ClienteSocket clienteSocket;

    public ControladorCombustible(ClienteSocket clienteSocket) {
        this.clienteSocket = clienteSocket;
    }

    public String registrarCombustible(int vehiculoId, java.util.Date fecha, BigDecimal litros, BigDecimal costo) {
        Combustible nuevo = new Combustible(vehiculoId, fecha, litros, costo);
        MensajeRed peticion = new MensajeRed("INSERTAR_COMBUSTIBLE", nuevo, false, null);
        MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);
        return respuesta.isEstadoExito() ? "✅ Guardado" : "❌ Error: " + respuesta.getMensajeRespuesta();
    }

    public String obtenerConsumoTotal(int vehiculoId){
        MensajeRed peticion = new MensajeRed("OBTENER_CONSUMO_TOTAL", vehiculoId, false, null);
        MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);
        return respuesta.isEstadoExito() ? respuesta.getPayload().toString() : "❌ Error: " + respuesta.getMensajeRespuesta();
    }

}