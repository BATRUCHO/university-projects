package empresa_xyz_distribuciones.PaqueteCliente.Vistas;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import empresa_xyz_distribuciones.PaqueteCliente.Controlador.ControladorCombustible;
import empresa_xyz_distribuciones.PaqueteCliente.Controlador.ControladorMantenimiento;
import empresa_xyz_distribuciones.PaqueteCliente.Controlador.ControladorVehiculo;
import empresa_xyz_distribuciones.PaqueteCliente.ModeloRed.ClienteSocket;


public class VistaMenu extends JFrame {

    private JPanel panelCentral;
    private JPanel menuLateral;

    private VistaCombustible vistaCombustible;
    private VistaMantenimiento vistaMantenimiento;
    private VistaVehiculo vistaVehiculo;

    private ClienteSocket clienteSocket;
    private ControladorCombustible ctrCombustible;
    private ControladorMantenimiento ctrMantenimiento;
    private ControladorVehiculo ctrVehiculo;


    public VistaMenu() {
        // 2. Inicializamos la infraestructura PRIMERO
        this.clienteSocket = new ClienteSocket();
        this.ctrCombustible= new ControladorCombustible(clienteSocket);
        this.ctrMantenimiento= new ControladorMantenimiento(clienteSocket);
        this.ctrVehiculo= new ControladorVehiculo(clienteSocket);

        // 3. Inicializamos las vistas pasando los controladores
        this.vistaCombustible = new VistaCombustible(ctrCombustible);
        this.vistaMantenimiento = new VistaMantenimiento(ctrMantenimiento);
        this.vistaVehiculo = new VistaVehiculo(ctrVehiculo);

        // 4. Configuración de la Ventana
        setTitle("Sistema de Gestión de Vehiculos - Dashboard");
        setSize(1100, 750); // Un poco más ancho para el menú lateral
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        armarMenuLateral();
        armarPanelCentral();

        // Vista inicial

        setVisible(true); // ¡No olvides hacerlo visible!
    }

   private void armarMenuLateral() {
        menuLateral = new JPanel();
        menuLateral.setLayout(new BoxLayout(menuLateral, BoxLayout.Y_AXIS));
        menuLateral.setPreferredSize(new Dimension(220, 0));
        menuLateral.setBackground(new Color(45, 45, 45));

        // 1. Declaración coherente con tus variables
        JButton btnIrVehiculo = new JButton("Módulo Vehículos");
        JButton btnIrCombustible = new JButton("Módulo Combustible");
        JButton btnIrMantenimiento = new JButton("Módulo Mantenimiento");

        // 2. Estilo (Ajustado a las nuevas variables)
        Dimension btnSize = new Dimension(200, 40);
        btnIrVehiculo.setMaximumSize(btnSize);
        btnIrCombustible.setMaximumSize(btnSize);
        btnIrMantenimiento.setMaximumSize(btnSize);
        
        btnIrVehiculo.setAlignmentX(CENTER_ALIGNMENT);
        btnIrCombustible.setAlignmentX(CENTER_ALIGNMENT);
        btnIrMantenimiento.setAlignmentX(CENTER_ALIGNMENT);

        // 3. Eventos corregidos
        btnIrVehiculo.addActionListener(e -> cambiarPanel(vistaVehiculo));
        btnIrCombustible.addActionListener(e -> cambiarPanel(vistaCombustible));
        btnIrMantenimiento.addActionListener(e -> cambiarPanel(vistaMantenimiento));

        // 4. Agregar al panel
        menuLateral.add(Box.createVerticalStrut(30));
        menuLateral.add(btnIrVehiculo);
        menuLateral.add(Box.createVerticalStrut(15));
        menuLateral.add(btnIrCombustible);
        menuLateral.add(Box.createVerticalStrut(15));
        menuLateral.add(btnIrMantenimiento);

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
