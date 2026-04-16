package cliente_servidor.PaqueteCliente.Controlador;

import java.util.ArrayList;
import java.util.List;

import cliente_servidor.PaqueteCliente.Modelo_red.ClienteSocket;
import cliente_servidor.PaqueteComun.modelos.Almacen;
import cliente_servidor.PaqueteComun.red.MensajeRed;

public class ControladorAlmacen {

    private ClienteSocket clienteSocket;

    public ControladorAlmacen(ClienteSocket clienteSocket) {
        this.clienteSocket = clienteSocket;
    }

    public String registrarAlmacen(int cod, String lug, int cap){
        Almacen nuevo = new Almacen(cod ,lug, cap);
        MensajeRed peticion = new MensajeRed("INSERTAR_ALMACEN", nuevo, false, null);
        MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);
        return respuesta.isEstadoExito() ? "✅ Guardado" : "❌ Error: " + respuesta.getMensajeRespuesta();

    }

    public String eliminarAlmacen(int codigo ){
        MensajeRed peticion = new MensajeRed("ELIMINAR_ALMACEN", codigo, false, null);
        MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);
        return respuesta.isEstadoExito() ? "✅ Eliminado" : "❌ Error: " + respuesta.getMensajeRespuesta();
    }

     public List<Almacen> obtenerListaAlmacen(){
        MensajeRed peticion = new MensajeRed("LISTAR_ALMACENES", null, false, null);
        MensajeRed respuesta = clienteSocket.enviarPeticion(peticion);
        
        if (respuesta.isEstadoExito()) {
            return (List<Almacen>) respuesta.getPayload();
        }
        return new ArrayList<>(); // Retornar lista vacía si falla
    }
    
    
}


