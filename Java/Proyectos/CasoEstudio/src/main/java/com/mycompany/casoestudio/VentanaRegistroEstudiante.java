package com.mycompany.casoestudio;

import java.awt.GridLayout;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaRegistroEstudiante extends JFrame {
    private ControladorEstudiante controlador;
    private JTextField txtId, txtNombre, txtApellido, txtCorreo, txtPrograma;
    private JFrame ventanaMenu; 

    public VentanaRegistroEstudiante(ControladorEstudiante controlador, JFrame ventanaMenu) {
        this.controlador = controlador;
        this.ventanaMenu = ventanaMenu;
        initUI();
    }

    private void initUI() {
        setTitle("Registrar Nuevo Estudiante");
        setSize(400, 400);
        setLayout(new GridLayout(7, 2, 10, 10));

        add(new JLabel("Cédula / ID:"));
        txtId = new JTextField(); add(txtId);

        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(); add(txtNombre);

        add(new JLabel("Apellido:"));
        txtApellido = new JTextField(); add(txtApellido);

        add(new JLabel("Correo:"));
        txtCorreo = new JTextField(); add(txtCorreo);

        add(new JLabel("Programa Académico:"));
        txtPrograma = new JTextField(); add(txtPrograma);

        JButton btnRegistrar = new JButton("Registrar Estudiante");
        btnRegistrar.addActionListener(e -> registrar());
        add(btnRegistrar);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
        @Override
        public void windowClosing(java.awt.event.WindowEvent e) {
            ventanaMenu.setVisible(true);
        }
    });
    }
    

    private void registrar() {
        try {
            // Creamos el objeto (usando una fecha por defecto para simplificar)
            Estudiante nuevo = new Estudiante(
                txtNombre.getText(),
                txtApellido.getText(),
                txtId.getText(),
                LocalDate.now(), 
                txtCorreo.getText(),
                txtPrograma.getText()
            );

        controlador.registrarEstudiante(nuevo);
        JOptionPane.showMessageDialog(this, "Estudiante registrado con éxito.");
        
        ventanaMenu.setVisible(true); // Aseguramos que el menú vuelva a aparecer
        this.dispose(); 
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
    }
    }
}