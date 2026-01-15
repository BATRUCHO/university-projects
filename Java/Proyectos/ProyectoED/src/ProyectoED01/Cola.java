package ProyectoED01;

public class Cola {

    private Nodo cabeza, ultimo;
    private int contadorTiquetesporCaja;

    public Cola() {
        cabeza = null;
        ultimo = null;
        contadorTiquetesporCaja = -1;
    }

    public boolean esVacia() {
        return cabeza == null;
    }

    public void agregar(Tiquete dato) {
        Nodo nuevo = new Nodo(dato);

        if (esVacia()) {
            cabeza = ultimo = nuevo;
        } else {
            ultimo.setSiguiente(nuevo);
            ultimo = nuevo;
        }

        contadorTiquetesporCaja++;
    }

    public Tiquete atiende() {
        if (esVacia()) {
            System.out.println("La cola no contiene elementos");
            return null;
        } else {
            Tiquete tiqueteAtendido = cabeza.getDato();
            tiqueteAtendido.setHoraAtencion(System.currentTimeMillis()); //ACTUALIZA CON LA HORA DE ATENCIÓN
            
            if (cabeza == ultimo) {
                cabeza = ultimo = null;
            } else {
                cabeza = cabeza.getSiguiente();
            }
            contadorTiquetesporCaja--;
            return tiqueteAtendido;
        }
    }

    public int getContadorTiquetesporCaja() {
        return contadorTiquetesporCaja; //ME RETORNA LA INFO DE LA VARIABLE
    }
    
    @Override
    public String toString() {
        String resultado = "";
        Nodo aux = cabeza;
        while (aux != null) {
            resultado += aux.toString(); // ALMACENA LA INFO
            aux = aux.getSiguiente();
        }
        return resultado;
    }
}
