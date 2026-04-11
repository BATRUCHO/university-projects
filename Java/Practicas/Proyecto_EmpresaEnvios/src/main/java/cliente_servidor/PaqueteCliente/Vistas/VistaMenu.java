package cliente_servidor.PaqueteCliente.Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import cliente_servidor.PaqueteCliente.Controlador.ControladorAlmacen;
import cliente_servidor.PaqueteCliente.Controlador.ControladorCaja;
import cliente_servidor.PaqueteCliente.Modelo_red.ClienteSocket;


public class VistaMenu extends JFrame {

    private JPanel panelCentral;
    private JPanel menuLateral;
    
    private VistaAlmacen vistaAlmacen;
    private VistaCaja vistaCaja;

    // 1. Declaramos los objetos de comunicación y control como atributos
    private ClienteSocket clienteSocket;
    private ControladorAlmacen ctrAlmacen;
    private ControladorCaja ctrCaja;

    public VistaMenu() {
        // 2. Inicializamos la infraestructura PRIMERO
        this.clienteSocket = new ClienteSocket();
        this.ctrAlmacen = new ControladorAlmacen(clienteSocket);
        this.ctrCaja = new ControladorCaja(clienteSocket);

        // 3. Inicializamos las vistas pasando los controladores
        this.vistaAlmacen = new VistaAlmacen(ctrAlmacen);
        this.vistaCaja = new VistaCaja(ctrCaja);

        // 4. Configuración de la Ventana
        setTitle("Sistema de Gestión de Envíos - Dashboard");
        setSize(1100, 750); // Un poco más ancho para el menú lateral
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        armarMenuLateral();
        armarPanelCentral();

        // Vista inicial
        cambiarPanel(vistaAlmacen);
        
        setVisible(true); // ¡No olvides hacerlo visible!
    }

    private void armarMenuLateral() {
        menuLateral = new JPanel();
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));
        menuLateral.setPreferredSize(new Dimension(220, 0));
        menuLateral.setBackground(new Color(45, 45, 45)); // Gris oscuro profesional

        JButton btnIrAlmacen = new JButton("Módulo Almacenes");
        JButton btnIrCaja = new JButton("Módulo Cajas");

        // Estilo rápido para botones (Opcional pero recomendado)
        btnIrAlmacen.setMaximumSize(new Dimension(200, 40));
        btnIrCaja.setMaximumSize(new Dimension(200, 40));
        btnIrAlmacen.setAlignmentX(CENTER_ALIGNMENT);
        btnIrCaja.setAlignmentX(CENTER_ALIGNMENT);

        btnIrAlmacen.addActionListener(e -> cambiarPanel(vistaAlmacen));
        btnIrCaja.addActionListener(e -> cambiarPanel(vistaCaja));

        menuLateral.add(Box.createVerticalStrut(30));
        menuLateral.add(btnIrAlmacen);
        menuLateral.add(Box.createVerticalStrut(15));
        menuLateral.add(btnIrCaja);

        add(menuLateral, BorderLayout.WEST);
    }

    private void armarPanelCentral() {
        panelCentral = new JPanel(new BorderLayout());
        panelCentral.setBackground(Color.WHITE);
        add(panelCentral, BorderLayout.CENTER);
    }

    private void cambiarPanel(JPanel nuevoPanel) {
        panelCentral.removeAll();
        panelCentral.add(nuevoPanel, BorderLayout.CENTER);
        panelCentral.revalidate();
        panelCentral.repaint();
    }
}


    


    







    
    
