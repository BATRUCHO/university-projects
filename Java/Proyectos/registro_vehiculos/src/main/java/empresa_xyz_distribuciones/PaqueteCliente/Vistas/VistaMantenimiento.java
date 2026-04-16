package empresa_xyz_distribuciones.PaqueteCliente.Vistas;

import java.awt.BorderLayout;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import empresa_xyz_distribuciones.PaqueteCliente.Controlador.ControladorMantenimiento;
import empresa_xyz_distribuciones.PaqueteComun.modelos.Mantenimiento;


public class VistaMantenimiento extends JPanel { 

    private DefaultTableModel modeloMantenimiento;
    private JTable tablaMantenimiento;
    private JTextField txtVehiculoId, txtTipo, txtFecha, txtKilometraje;
    private JButton btnRegistrar, btnListar;

    private ControladorMantenimiento ctrMantenimiento;

    public VistaMantenimiento(ControladorMantenimiento ctrMantenimiento) {
        this.ctrMantenimiento = ctrMantenimiento;
        setLayout(new BorderLayout());
        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        JPanel formulario = new JPanel(new java.awt.GridLayout(4, 2, 10, 10));
        formulario.setBorder(BorderFactory.createTitledBorder("Registro de Mantenimiento"));

        txtVehiculoId = new JTextField();
        txtTipo = new JTextField(); // Ej: Cambio de Aceite
        txtFecha = new JTextField(); // YYYY-MM-DD
        txtKilometraje = new JTextField();

        formulario.add(new JLabel("ID Vehículo:"));
        formulario.add(txtVehiculoId);
        formulario.add(new JLabel("Tipo de Trabajo:"));
        formulario.add(txtTipo);
        formulario.add(new JLabel("Fecha (AAAA-MM-DD):"));
        formulario.add(txtFecha);
        formulario.add(new JLabel("Kilometraje:"));
        formulario.add(txtKilometraje);

        btnRegistrar = new JButton("Registrar Mantenimiento");
        btnListar = new JButton("Ver Historial");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnListar);

        // Columnas correctas para el examen
        modeloMantenimiento = new DefaultTableModel(new Object[]{"ID", "Vehículo", "Tipo", "Fecha", "KM"}, 0);
        tablaMantenimiento = new JTable(modeloMantenimiento);
        
        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(formulario, BorderLayout.CENTER);
        panelNorte.add(panelBotones, BorderLayout.SOUTH);

        add(panelNorte, BorderLayout.NORTH);
        add(new JScrollPane(tablaMantenimiento), BorderLayout.CENTER);
    }

    private void configurarEventos() {
        btnRegistrar.addActionListener(e -> {
            try {
                int vId = Integer.parseInt(txtVehiculoId.getText());
                String tipo = txtTipo.getText();
                String fechaStr = txtFecha.getText();
                int km = Integer.parseInt(txtKilometraje.getText());

                Date fecha = new SimpleDateFormat("yyyy-MM-dd").parse(fechaStr);

                String res = ctrMantenimiento.registrarMantenimiento(0, vId, tipo, fecha, km);
                JOptionPane.showMessageDialog(this, res);
                
                limpiarCampos();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: Verifique los datos. " + ex.getMessage());
            }
        });

        btnListar.addActionListener(ev -> {
            actualizarTabla();
        });
    }

    private void limpiarCampos() {
        txtVehiculoId.setText("");
        txtTipo.setText("");
        txtFecha.setText("");
        txtKilometraje.setText("");
    }

    private void actualizarTabla() {
        modeloMantenimiento.setRowCount(0);
        // El payload aquí podría ser el ID de un vehículo si quieres filtrar
        List<Mantenimiento> lista = ctrMantenimiento.obtenerListaMantenimientos(); 
        
        if (lista != null) {
            for (Mantenimiento m : lista) {
                modeloMantenimiento.addRow(new Object[]{
                    m.getId(), m.getVehiculo_id(), m.getTipo(), m.getFecha(), m.getKilometraje()
                });
            }
        }
    }
}