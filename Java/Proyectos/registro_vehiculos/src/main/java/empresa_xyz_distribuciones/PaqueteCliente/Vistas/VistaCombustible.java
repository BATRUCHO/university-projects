package empresa_xyz_distribuciones.PaqueteCliente.Vistas;


import java.awt.BorderLayout;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import empresa_xyz_distribuciones.PaqueteCliente.Controlador.ControladorCombustible;


public class VistaCombustible extends JPanel {

    private DefaultTableModel modeloCombustible; // Corregido: antes era BoundedRange
    private JTable tablaCombustible;
    private JTextField txtVehiculoId, txtFecha, txtLitros, txtCosto;
    private JButton btnRegistrar, btnListar, btnConsumoTotal;

    private ControladorCombustible ctrCombustible;

    public VistaCombustible(ControladorCombustible ctrCombustible) {
        this.ctrCombustible = ctrCombustible;
        setLayout(new BorderLayout());
        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        JPanel formulario = new JPanel(new java.awt.GridLayout(4, 2, 10, 10));
        formulario.setBorder(BorderFactory.createTitledBorder("Gestión de Costos de Combustible"));

        txtVehiculoId = new JTextField();
        txtFecha = new JTextField(); // Formato: YYYY-MM-DD
        txtLitros = new JTextField();
        txtCosto = new JTextField();

        formulario.add(new JLabel("ID Vehículo:"));
        formulario.add(txtVehiculoId);
        formulario.add(new JLabel("Fecha (AAAA-MM-DD):"));
        formulario.add(txtFecha);
        formulario.add(new JLabel("Litros:"));
        formulario.add(txtLitros);
        formulario.add(new JLabel("Costo Total:"));
        formulario.add(txtCosto);

        btnRegistrar = new JButton("Registrar Carga");
        btnListar = new JButton("Ver Historial");
        btnConsumoTotal = new JButton("Gasto Total ($)");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnListar);
        panelBotones.add(btnConsumoTotal);

        // Configuración correcta del modelo de tabla
        modeloCombustible = new DefaultTableModel(new Object[]{"ID", "Vehículo", "Fecha", "Litros", "Costo"}, 0);
        tablaCombustible = new JTable(modeloCombustible); 
        
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(formulario, BorderLayout.CENTER);
        panelNorte.add(panelBotones, BorderLayout.SOUTH);

        add(panelNorte, BorderLayout.NORTH);
        add(new JScrollPane(tablaCombustible), BorderLayout.CENTER);
    }


    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> {
            try {
                int vId = Integer.parseInt(txtVehiculoId.getText());
                Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(txtFecha.getText());
                BigDecimal litros = new BigDecimal(txtLitros.getText());
                BigDecimal costoTotal = new BigDecimal(txtCosto.getText());
                


                // Enviamos los datos al controlador
                String res = ctrCombustible.registrarCombustible(vId, fecha, litros, costoTotal);
                JOptionPane.showMessageDialog(this, res);
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error en datos: " + ex.getMessage());
            }
        });

        btnConsumoTotal.addActionListener(ev -> {
            try {
                String input = JOptionPane.showInputDialog(this, "Ingrese el ID del vehículo para ver su gasto total:");
                if (input != null) {
                    int vId = Integer.parseInt(input);
                    String res = ctrCombustible.obtenerConsumoTotal(vId);
                    JOptionPane.showMessageDialog(this, "Reporte: " + res);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "ID inválido");
            }
        });
    }

    private void limpiarCampos() {
        txtVehiculoId.setText("");
        txtFecha.setText("");
        txtLitros.setText("");
        txtCosto.setText("");
    }
}
