package cliente_servidor.PaqueteCliente.Controlador;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cliente_servidor.PaqueteCliente.Modelo_red.ClienteSocket;
import cliente_servidor.PaqueteComun.modelos.Caja;
import cliente_servidor.PaqueteComun.red.MensajeRed;


public class ControladorCaja {
    private ClienteSocket clienteSocket;

    public ControladorCaja(ClienteSocket clienteSocket) {
        this.clienteSocket = clienteSocket;
    }

    // 1. Registrar: Está perfecto, solo asegúrate que la acción coincida con el switch del servidor
    public String registrarCaja(int numReferencia, String contenido, BigDecimal precio, int almacenCodigo){
        Caja nuevo = new Caja(numReferencia, contenido, precio, almacenCodigo);
        MensajeRed peticion = new MensajeRed("INSERTAR_CAJA", nuevo, false, null);
        MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);
        return respuesta.isEstadoExito() ? "✅ Guardado" : "❌ Error: " + respuesta.getMensajeRespuesta();
    }

    // 2. Eliminar: Simplificado para enviar solo el ID (como espera tu HiloCliente)
    public String eliminarCaja(int numReferencia){
        MensajeRed peticion = new MensajeRed("ELIMINAR_CAJA", numReferencia, false, null);
        MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);
        return respuesta.isEstadoExito() ? "✅ Eliminado" : "❌ Error: " + respuesta.getMensajeRespuesta();
    }

    // 3. Listar: Retornamos la lista real para la JTable
    public List<Caja> obtenerListaCajas(){
        MensajeRed peticion = new MensajeRed("LISTAR_CAJAS", null, false, null);
        MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);
        
        if (respuesta.isEstadoExito()) {
            return (List<Caja>) respuesta.getPayload();
        }
        return new ArrayList<>(); // Retornar lista vacía si falla
    }
}

