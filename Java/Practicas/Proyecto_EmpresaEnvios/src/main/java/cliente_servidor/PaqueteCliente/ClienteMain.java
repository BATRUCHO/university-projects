package cliente_servidor.PaqueteCliente;

public class ClienteMain {
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            new cliente_servidor.PaqueteCliente.Vistas.VistaMenu();
        });
    }


}
