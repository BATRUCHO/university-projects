package cliente_servidor.PaqueteCliente.Vistas;

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

import cliente_servidor.PaqueteCliente.Controlador.ControladorAlmacen;
import cliente_servidor.PaqueteComun.modelos.Almacen;

public class VistaAlmacen extends JPanel {

    private DefaultTableModel modeloAlmacen;
    private JTable tablaAlmacen;
    private JTextField txtCodigo, txtLugar, txtCapacidad;
    private JButton btnRegistrarAlmacen, btnListarAlmacen, btnEliminarAlmacen;

    private ControladorAlmacen ctrAlmacen;

    public VistaAlmacen(ControladorAlmacen ctrAlmacen) {
        this.ctrAlmacen = ctrAlmacen;

        setLayout(new BorderLayout());

        inicializarComponentes();
        configurarEventos();
    }

     private void inicializarComponentes() {
        
        JPanel formulario = new JPanel(new java.awt.GridLayout(4, 2, 10, 10));
        formulario.setBorder(BorderFactory.createTitledBorder("Datos del Almacén"));
        
        txtCodigo = new JTextField();
        txtLugar = new JTextField();
        txtCapacidad = new JTextField();
        
        formulario.add(new JLabel("Código:"));
        formulario.add(txtCodigo);
        formulario.add(new JLabel("Lugar:"));
        formulario.add(txtLugar);
        formulario.add(new JLabel("Capacidad:"));
        formulario.add(txtCapacidad);
        
        // Botones
        btnRegistrarAlmacen = new JButton("Registrar");
        btnListarAlmacen = new JButton("Listar");
        btnEliminarAlmacen = new JButton("Eliminar");
        
        JPanel panelBotones = new JPanel();
        panelBotones.add(btnRegistrarAlmacen);
        panelBotones.add(btnListarAlmacen);
        panelBotones.add(btnEliminarAlmacen);
        
        // Tabla (Centro)
        modeloAlmacen = new DefaultTableModel(new Object[]{"Código", "Lugar", "Capacidad"}, 0);
        tablaAlmacen = new JTable(modeloAlmacen);
        JScrollPane scrollTabla = new JScrollPane(tablaAlmacen);

        // Juntar todo en el panel Almacen
        JPanel panelNorte= new JPanel(new BorderLayout());
        panelNorte.add(formulario, BorderLayout.CENTER);
        panelNorte.add(panelBotones, BorderLayout.SOUTH);
        
        add(panelNorte, BorderLayout.NORTH);
        add(scrollTabla, BorderLayout.CENTER);
    }

 private void configurarEventos() {
    // Escuchamos el clic del botón Registrar
    btnRegistrarAlmacen.addActionListener(e -> {
        try {
            // 1. Capturar datos de la UI
            int cod = Integer.parseInt(txtCodigo.getText());
            String lug = txtLugar.getText();
            int cap = Integer.parseInt(txtCapacidad.getText());

            // 2. Llamar al controlador que ya tenemos como atributo
            String resultado = ctrAlmacen.registrarAlmacen(cod, lug, cap);

            // 3. Mostrar respuesta
            JOptionPane.showMessageDialog(this, resultado);
    
            // Opcional: Limpiar campos tras éxito
            txtCodigo.setText("");
            txtLugar.setText("");
            txtCapacidad.setText("");

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: El código y la capacidad deben ser valores numéricos.");
        }
    });

    // Escuchamos el clic del botón Listar
    btnListarAlmacen.addActionListener(ev -> {
        // 1. Limpiar la tabla antes de cargar nuevos datos
        modeloAlmacen.setRowCount(0); 

        // 2. Usar el controlador que YA es atributo de la clase
        List<Almacen> listaAlmacen = ctrAlmacen.obtenerListaAlmacen();

        // 3. Validar si la lista no está vacía y llenar
        if (listaAlmacen.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No se encontraron datos o error de conexión.");
        } else {
            for (Almacen almacen : listaAlmacen) {
                modeloAlmacen.addRow(new Object[]{
                    almacen.getCodigo(), 
                    almacen.getLugar(), 
                    almacen.getCapacidad()
              
                });
            }
        }
    
    });

    btnEliminarAlmacen.addActionListener(ev -> {
        
        int filaSeleccionada = tablaAlmacen.getSelectedRow();

        if(filaSeleccionada == -1){
            JOptionPane.showMessageDialog(this, "Por favor, Selecionar un almacen de la tabla para eliminar ");
            return;
        }

        int codigo = (int) tablaAlmacen.getValueAt(filaSeleccionada, 0);

        int respuestaConfirmacion = JOptionPane.showConfirmDialog(this, 
            "Estas seguro de eliminar el alamacen con codigo" + codigo + "?",
            "Confirmacion Eliminacion",
            JOptionPane.YES_NO_OPTION);
        
        if (respuestaConfirmacion == JOptionPane.YES_OPTION) {
            String resultado = ctrAlmacen.eliminarAlmacen(codigo);
            JOptionPane.showMessageDialog(this, resultado);

            btnListarAlmacen.doClick();
        }

    });
    
}

}
