package ProyectoED01;

public class ConfiguracionCola {

    //CREO UN ARREGLO DE TIPO COLA, ESTO ME VA A PERMITIR CREAR LAS COLAS QUE NECESITARÉ
    private static Cola[] colasPreferenciales;
    private static Cola[] colasRapidas;
    private static Cola[] colasNormales;

    public static void configurarColas() {
        //COMO LA CONFIGURACION DE CAJA YA CONTIENE LA CANT DE COLAS, LA OBTENGO DESDE ALLÍ
        int numPreferenciales = ConfiguracionCaja.getCajaPreferencial();
        int numRapidas = ConfiguracionCaja.getCajaRapida();
        int numNormales = ConfiguracionCaja.getCajaNormal();

        // SE INICIALIZAN LOS ARREGLOS CON LOS PARAMETOS DE LAS CAJAS 
        colasPreferenciales = new Cola[numPreferenciales];
        colasRapidas = new Cola[numRapidas];
        colasNormales = new Cola[numNormales];

        //AQUÍ SEA CREA Y CONFIGURA LA CAJA P
        for (int i = 0; i < numPreferenciales; i++) {
            colasPreferenciales[i] = new Cola();
        }

        //AQUÍ SEA CREA Y CONFIGURA LA CAJA A
        for (int i = 0; i < numRapidas; i++) {
            colasRapidas[i] = new Cola();
        }

        //AQUÍ SEA CREA Y CONFIGURA LA CAJA B
        for (int i = 0; i < numNormales; i++) {
            colasNormales[i] = new Cola();
        }
    }

    public static Cola balanceador(String tipoTiquete) {
        Cola[] colas = null;//ALMACENA LA INFO DE LOS TIQUETES

        //AQUÍ LO QUE HACE ES QUE CATEGORIZA POR TIPO
        switch (tipoTiquete) {
            case "Preferencial":
                colas = colasPreferenciales;
                break;
            case "Un solo trámite":
                colas = colasRapidas;
                break;
            case "Dos o más trámites":
                colas = colasNormales;
                break;
        }

        if (colas == null || colas.length == 0) { //VALIDA SI EL ARREGLO DE COLAS ES VACIO
            return null;
        }

        Cola colaSeleccionada = colas[0]; //ARRANCA CON LA PRIMERA COLA
        int minTiquetes = colaSeleccionada.getContadorTiquetesporCaja();//DE AQUÍ SACO LOS TQIEUTES POR CAJA

        //AQUÍ CON ESTE CONDICIONAL SE RECORRE EL ARREGLO PARA ENCONTRAR LA COLA CON MENOR CANTIDAD DE TIQUETES
        for (Cola cola : colas) {
            int tiquetesEnCola = cola.getContadorTiquetesporCaja();
            if (tiquetesEnCola < minTiquetes) {
                minTiquetes = tiquetesEnCola;
                colaSeleccionada = cola;
            }
        }

        return colaSeleccionada;//AQUÍ ME DEVUELVE LA COLA CON EL MINIMO DE TIQUETES 
    }

    //ME DEVUELVE LAS COLAS
    public static Cola[] getColasPreferenciales() {
        return colasPreferenciales;
    }

    public static Cola[] getColasRapidas() {
        return colasRapidas;
    }

    public static Cola[] getColasNormales() {
        return colasNormales;
    }
}
