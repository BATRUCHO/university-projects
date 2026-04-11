package cliente_servidor.Servidor;

public class ServidorMain {
    private static final int PUERTO = 5000;

    public static void main(String[] args) {
        try (java.net.ServerSocket servidor = new java.net.ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);

            while (true) {
                java.net.Socket cliente = servidor.accept();
                System.out.println("Nuevo cliente conectado: " + cliente.getInetAddress().getHostAddress());

                // Iniciar un hilo para manejar al cliente
                new Thread(new HiloCliente(cliente)).start();
            }
        } catch (java.io.IOException e) {
            System.err.println("Error en el servidor: " + e.getMessage());
        }
    } 
    

}
