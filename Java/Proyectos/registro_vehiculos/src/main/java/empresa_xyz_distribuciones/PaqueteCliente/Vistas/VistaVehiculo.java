package empresa_xyz_distribuciones.PaqueteCliente.Vistas;

import java.awt.BorderLayout;
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

import empresa_xyz_distribuciones.PaqueteCliente.Controlador.ControladorVehiculo;
import empresa_xyz_distribuciones.PaqueteComun.modelos.Vehiculo;

public class VistaVehiculo extends JPanel{

    private DefaultTableModel modeloVehiculo;
    private JTable tablaVehiculos;
    private JTextField txtPlaca, txtMarca, txtModelo, txtKilometraje;
    private JButton btnRegistrar, btnListar,btnOptenerPorId, btnBuscarPorPlaca;

    private ControladorVehiculo ctrVehiculo;

    public VistaVehiculo(ControladorVehiculo ctrVehiculo) {
        this.ctrVehiculo = ctrVehiculo;
        setLayout(new BorderLayout());
        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        JPanel formulario = new JPanel(new java.awt.GridLayout(4, 2, 10, 10));
        formulario.setBorder(BorderFactory.createTitledBorder("Registro de Vehículo"));

        txtPlaca = new JTextField();
        txtMarca = new JTextField();
        txtModelo = new JTextField();
        txtKilometraje = new JTextField();

        formulario.add(new JLabel("Placa:"));
        formulario.add(txtPlaca);
        formulario.add(new JLabel("Marca:"));
        formulario.add(txtMarca);
        formulario.add(new JLabel("Modelo:"));
        formulario.add(txtModelo);
        formulario.add(new JLabel("Kilometraje:"));
        formulario.add(txtKilometraje);

        btnRegistrar = new JButton("Registrar Vehículo");
        btnListar = new JButton("Listar Vehículos");
        btnOptenerPorId = new JButton("Obtener Vehículo por ID");
        btnBuscarPorPlaca = new JButton("Buscar Vehículo por Placa");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnListar);
        panelBotones.add(btnOptenerPorId);
        panelBotones.add(btnBuscarPorPlaca);

        modeloVehiculo = new DefaultTableModel(new Object[]{"ID", "Placa", "Marca", "Modelo", "KM"}, 0);
        tablaVehiculos = new JTable(modeloVehiculo);

        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(formulario, BorderLayout.CENTER);
        panelNorte.add(panelBotones, BorderLayout.SOUTH);

        add(panelNorte, BorderLayout.NORTH);
        add(new JScrollPane(tablaVehiculos), BorderLayout.CENTER);
    }

    private void configurarEventos() {
       
        btnRegistrar.addActionListener(e -> {
            try {
                String placa = txtPlaca.getText().trim();
                String marca = txtMarca.getText().trim();
                String modelo = txtModelo.getText().trim();
                int km = Integer.parseInt(txtKilometraje.getText().trim());

                if (placa.isEmpty() || marca.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "La placa y marca son obligatorias.");
                    return;
                }

                // Llamada al controlador
                String res = ctrVehiculo.registrarVehiculo(placa, marca, modelo, km);
                JOptionPane.showMessageDialog(this, res);
                
                limpiarCampos();
                actualizarTabla(); // Refrescar automáticamente
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "El kilometraje debe ser un número entero.");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
            }
        });

        
        btnListar.addActionListener(ev -> actualizarTabla());

        
        btnOptenerPorId.addActionListener(ev -> {
            String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID del vehículo:");
            if (idStr != null && !idStr.isEmpty()) {
                try {
                    int id = Integer.parseInt(idStr);
                    Vehiculo v = ctrVehiculo.buscarPorId(id);
                    mostrarResultadoBusqueda(v);
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(this, "ID inválido.");
                }
            }
        });

        
        btnBuscarPorPlaca.addActionListener(ev -> {
            String placa = JOptionPane.showInputDialog(this, "Ingrese la placa a buscar:");
            if (placa != null && !placa.isEmpty()) {
                Vehiculo v = ctrVehiculo.buscarPorPlaca(placa);
                mostrarResultadoBusqueda(v);
            }
        });
    }

    private void actualizarTabla() {
        modeloVehiculo.setRowCount(0);
        List<Vehiculo> lista = ctrVehiculo.obtenerListaVehiculos();
        if (lista != null) {
            for (Vehiculo v : lista) {
                modeloVehiculo.addRow(new Object[]{
                    v.getId(), v.getPlaca(), v.getMarca(), v.getModelo(), v.getKilometraje()
                });
            }
        }
    }

    private void mostrarResultadoBusqueda(Vehiculo v) {
        if (v != null) {
            modeloVehiculo.setRowCount(0);
            modeloVehiculo.addRow(new Object[]{
                v.getId(), v.getPlaca(), v.getMarca(), v.getModelo(), v.getKilometraje()
            });
        } else {
            JOptionPane.showMessageDialog(this, "Vehículo no encontrado.");
        }
    }

    private void limpiarCampos() {
        txtPlaca.setText("");
        txtMarca.setText("");
        txtModelo.setText("");
        txtKilometraje.setText("");
    }
}


