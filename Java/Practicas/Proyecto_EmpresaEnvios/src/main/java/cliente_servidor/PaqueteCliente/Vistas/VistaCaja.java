package cliente_servidor.PaqueteCliente.Vistas;

import java.awt.BorderLayout;
import java.math.BigDecimal;
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

import cliente_servidor.PaqueteCliente.Controlador.ControladorCaja;
import cliente_servidor.PaqueteComun.modelos.Caja;

public class VistaCaja extends JPanel{ 

    private DefaultTableModel modeloCaja;
    private JTable tablaCaja;
    private JTextField txtCodigo, txtContenido, txtPrecio, txtCodigoAlmacen;
    private JButton btnRegistrarCaja, btnListarCaja, btnEliminarCaja;

    private ControladorCaja ctrCaja;
    

    public VistaCaja(ControladorCaja ctrCaja) {
        this.ctrCaja = ctrCaja;

        setLayout(new BorderLayout());

        inicializarComponentes();
        configurarEventos();
    }

    private void inicializarComponentes() {
        JPanel formulario = new JPanel(new java.awt.GridLayout(5, 2, 10, 10));
        formulario.setBorder(BorderFactory.createTitledBorder("Datos de la Caja"));

        txtCodigo = new JTextField();
        txtContenido = new JTextField();
        txtPrecio = new JTextField();
        txtCodigoAlmacen = new JTextField();

        formulario.add(new JLabel("Código:"));
        formulario.add(txtCodigo);
        formulario.add(new JLabel("Contenido:"));
        formulario.add(txtContenido);
        formulario.add(new JLabel("Precio:"));
        formulario.add(txtPrecio);
        formulario.add(new JLabel("Código Almacén:"));
        formulario.add(txtCodigoAlmacen);

        btnRegistrarCaja = new JButton("Registrar");
        btnListarCaja = new JButton("Listar");
        btnEliminarCaja = new JButton("Eliminar");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnRegistrarCaja);
        panelBotones.add(btnListarCaja);
        panelBotones.add(btnEliminarCaja);

        modeloCaja = new DefaultTableModel(new Object[]{"Código", "Contenido", "Precio", "Almacén"}, 0);
        tablaCaja = new JTable(modeloCaja);
        JScrollPane scrollTabla = new JScrollPane(tablaCaja);

        JPanel panelNorte = new JPanel(new BorderLayout());
        panelNorte.add(formulario, BorderLayout.CENTER);
        panelNorte.add(panelBotones, BorderLayout.SOUTH);

        add(panelNorte, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);

    }
   private void configurarEventos() {

    btnRegistrarCaja.addActionListener(e -> {
        try {
            int cod = Integer.parseInt(txtCodigo.getText());
            String cont = txtContenido.getText();
    
            BigDecimal precio = new BigDecimal(txtPrecio.getText()); 
            int codAlm = Integer.parseInt(txtCodigoAlmacen.getText());

            String resultado = ctrCaja.registrarCaja(cod, cont, precio, codAlm);

            JOptionPane.showMessageDialog(this, resultado);

            // Limpieza
            txtCodigo.setText("");
            txtContenido.setText("");
            txtPrecio.setText(""); 
            txtCodigoAlmacen.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Verifique los valores numéricos.");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    });

    btnListarCaja.addActionListener(ev -> {
        modeloCaja.setRowCount(0);
        List<Caja> listaCaja = ctrCaja.obtenerListaCajas(); // Asegúrate que el nombre coincida con tu controlador

        if (listaCaja == null || listaCaja.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron datos.");
        } else { 
            for (Caja caja : listaCaja) {
                modeloCaja.addRow(new Object[]{
                    caja.getNumReferencia(),
                    caja.getContenido(),
                    caja.getPrecio(),
                    caja.getAlmacenCodigo()
                });
            }
        }
    });

    btnEliminarCaja.addActionListener(ev -> {
        int filaSeleccionada = tablaCaja.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione una fila.");
            return;
        }

        
        int codigo = (int) modeloCaja.getValueAt(filaSeleccionada, 0);

        int respuestaConfirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro de eliminar la caja " + codigo + "?",
                "Confirmación", JOptionPane.YES_NO_OPTION);
                
        if (respuestaConfirmacion == JOptionPane.YES_OPTION) {
            String resultado = ctrCaja.eliminarCaja(codigo);
            JOptionPane.showMessageDialog(this, resultado);
            btnListarCaja.doClick();
        }
    });
}

}
