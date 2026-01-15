package ProyectoED01;

import javax.swing.JOptionPane;

public class Main {

    private static final int MAX_ATTEMPTS = 3;

    public static void main(String[] args) {
        Login login = new Login();
        boolean autenticador = false;

        // SE INICIA CON EL LOGIN DEL USUARIO
        while (!autenticador) {
            String[] opcionesLogin = {"Crear Nuevo Usuario", "Ingresar Usuario y Contraseña", "Salir"};
            int seleccion = JOptionPane.showOptionDialog(null, "Elija una opción", "Menú de Login",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opcionesLogin, opcionesLogin[0]);

            switch (seleccion) {
                case 0 ->
                    crearNuevoUsuario(login);
                case 1 ->
                    autenticador = iniciarSesion(login);
                case 2 -> {
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema.");
                    System.exit(0);
                }
                default ->
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
            }
        }

        //CONFIGURAR EL BANCO Y LAS CAJAS 
        ConfiguracionCaja.configurar();  //AQUI EL BANCCO
        ConfiguracionCola.configurarColas(); //AQUI LAS CAJAS/COLAS
        JOptionPane.showMessageDialog(null, "Configuración cargada correctamente para " + ConfiguracionCaja.getNombreBanco());

        // MENÚ DE OPCIONES EN EL SISTEMA
        while (true) {
            String[] opciones = {"Creación Tiquete", "Atención Tiquete", "Generar Reporte", "Salir"};
            int eleccion = JOptionPane.showOptionDialog(null, "Elija una opción", "Sistema de Gestión Bancaria",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (eleccion) {
                case 0 ->
                    crearTiquete();
                case 1 ->
                    atenderTiquete();
                case 2 ->
                    generarReporte();
                case 3 -> {
                    JOptionPane.showMessageDialog(null, "Saliendo del sistema.");
                    System.exit(0);
                }
                default ->
                    JOptionPane.showMessageDialog(null, "Opción inválida. Intente nuevamente.");
            }
        }
    }

    private static void crearNuevoUsuario(Login login) {
        String username = JOptionPane.showInputDialog("Ingrese el nombre de usuario para el registro:");
        String password = JOptionPane.showInputDialog("Ingrese la contraseña para el registro:");
        login.guardar(username, password);
        JOptionPane.showMessageDialog(null, "Nuevo usuario creado exitosamente.");
    }

    private static boolean iniciarSesion(Login login) {
        boolean autenticador = false;
        int attempts = 0;

        while (attempts < MAX_ATTEMPTS && !autenticador) {
            String loginUsername = JOptionPane.showInputDialog("Ingrese el nombre de usuario:");
            String loginPassword = JOptionPane.showInputDialog("Ingrese la contraseña:");

            if (login.verificar(loginUsername, loginPassword)) {
                autenticador = true;
                JOptionPane.showMessageDialog(null, "Bienvenido al sistema de gestión bancaria!");
            } else {
                attempts++;
                if (attempts < MAX_ATTEMPTS) {
                    JOptionPane.showMessageDialog(null, "Credenciales incorrectas. Inténtelo de nuevo.");
                } else {
                    JOptionPane.showMessageDialog(null, "Número máximo de intentos alcanzado. Acceso denegado.");
                }
            }
        }
        return autenticador;
    }

    private static void crearTiquete() {
        // Obtener los datos del usuario
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del cliente:");
        String id = JOptionPane.showInputDialog("Ingrese el ID del cliente:");
        int edad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la edad del cliente:"));

        // MENÚ PARA LA SELECCIÓN DE TRAMITE 
        String[] tramites = {"Depósitos", "Retiros", "Cambio de Divisas"};
        String tramite = (String) JOptionPane.showInputDialog(null, "Seleccione el trámite", "Trámite",
                JOptionPane.DEFAULT_OPTION, null, tramites, tramites[0]);

        // MENÚ PARA LA SELECCIÓN DE TIPO DE TIQUETE
        String[] tipos = {"Preferencial", "Un solo trámite", "Dos o más trámites"};
        String tipoTiquete = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de tiquete", "Tipo de Tiquete",
                JOptionPane.DEFAULT_OPTION, null, tipos, tipos[0]);

        //AQUÍ SE DETERMINA A CUÁL COLA DEBE IR EL TIQUETE X TIPO TIQUETE 
        Cola colaAsignada = ConfiguracionCola.balanceador(tipoTiquete);//ESTO ES EL BALANCEADOR
        if (colaAsignada != null) {
            Tiquete tiquete = new Tiquete(nombre, id, edad, tramite, tipoTiquete);
            colaAsignada.agregar(tiquete);

            //MUESTRA LA INFORMACIÓN DEL TIQUETE CREADO
            String infoTiquete = String.format("Tiquete creado:%nNombre: %s%nID: %s%nEdad: %d%nTrámite: %s%nTipo de Tiquete: %s%nHora de Creación: %s%n",
                    tiquete.getNombre(), tiquete.getId(), tiquete.getEdad(), tiquete.getTramite(), tiquete.getTipoTiquete(), tiquete.getHoraCreacionLegible());
            JOptionPane.showMessageDialog(null, infoTiquete);

            // AQUÍ SE OBTIENE EL NÚMERO DE TIQUETES QUE TIENE LA COLA
            int tiquetesEnCola = colaAsignada.getContadorTiquetesporCaja();

            //OPERADOR TERNARIO (? :), SIRVE COMO CONDICIONAL SÍ ES IGUAL A CERO MUESTRA ES SU TURNO, SINO SOLO NUMERO DE PERSONAS
            String mensaje = tiquetesEnCola == 0 ? "Es su turno de atención." : "Número de personas en la cola delante de usted: " + tiquetesEnCola;
            JOptionPane.showMessageDialog(null, "Tiquete creado. " + mensaje);

        } else {
            JOptionPane.showMessageDialog(null, "Tipo de tiquete no válido.");
        }
    }

    private static void atenderTiquete() {
        String[] opciones = {"Caja Preferencial", "Caja Rápida", "Caja Normal"};
        int seleccion = JOptionPane.showOptionDialog(null, "Seleccione una opción para atender el tiquete:",
                "Atención de Tiquete", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, opciones, opciones[0]);

        Cola[] colas = null;

        switch (seleccion) {
            case 0 ->
                colas = ConfiguracionCola.getColasPreferenciales();
            case 1 ->
                colas = ConfiguracionCola.getColasRapidas();
            case 2 ->
                colas = ConfiguracionCola.getColasNormales();
            default -> {
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                return;
            }
        }

        if (colas == null || colas.length == 0) {
            JOptionPane.showMessageDialog(null, "No hay colas configuradas.");
            return;
        }

        //AQUÍ EMPIEZA A ATENDER LAS COLAS
        for (Cola cola : colas) {
            if (!cola.esVacia()) {
                Tiquete tiqueteAtendido = cola.atiende();
                if (tiqueteAtendido != null) {
                    tiqueteAtendido.setHoraAtencion(System.currentTimeMillis());
                    JOptionPane.showMessageDialog(null, "Tiquete atendido: \n" + tiqueteAtendido.toString());
                    return;
                }
            }
        }

        JOptionPane.showMessageDialog(null, "No hay tiquetes en ninguna de las colas.");
    }

    private static void generarReporte() {
        // POR DESARROLLAR.
    }
}
