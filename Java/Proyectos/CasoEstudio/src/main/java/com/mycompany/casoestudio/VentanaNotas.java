package com.mycompany.casoestudio;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 * @author braya
 */

import java.awt.GridLayout;
import java.math.BigDecimal;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaNotas extends JFrame {
    private ControladorEstudiante controlador;
    private JTextField txtExamen1, txtExamen2, txtProyecto;
    private JComboBox<String> comboEstudiantes;
    private JButton btnGuardar;
    private JFrame ventanaMenu;

    public VentanaNotas(ControladorEstudiante controlador, JFrame ventanaMenu) {
        this.controlador = controlador;
        this.ventanaMenu = ventanaMenu;
        initUI();
    }

    private void initUI() {
        setTitle("Gestión de Notas");
        setSize(400, 350);
        setLayout(new GridLayout(6, 2, 10, 10));

        add(new JLabel("ID Estudiante:"));
        comboEstudiantes = new JComboBox<>();
        for (Estudiante e : controlador.getListaEstudiantes()) comboEstudiantes.addItem(e.getId());
        add(comboEstudiantes);

        add(new JLabel("Examen 1 (30%):"));
        txtExamen1 = new JTextField(); add(txtExamen1);
        add(new JLabel("Examen 2 (30%):"));
        txtExamen2 = new JTextField(); add(txtExamen2);
        add(new JLabel("Proyecto (40%):"));
        txtProyecto = new JTextField(); add(txtProyecto);

        btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> accionGuardar());
        add(btnGuardar);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                ventanaMenu.setVisible(true);
            }
        });
        setLocationRelativeTo(null);
    }

    private void accionGuardar() {
        try {
            String id = (String) comboEstudiantes.getSelectedItem();
            if (id == null) throw new Exception("No hay estudiantes seleccionados");

            Calificacion cal = new Calificacion(
                new BigDecimal(txtExamen1.getText()),
                new BigDecimal(txtExamen2.getText()),
                new BigDecimal(txtProyecto.getText()),
                null, 
                new Curso(1, "Curso General", 4.0)
            );

            controlador.agregarCalificacionAEstudiante(id, cal);
            
            JOptionPane.showMessageDialog(this, "Promedio: " + cal.calcularPromedio() + "\nEstado: " + cal.asignarNota());
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }

    public void refrescarEstudiantes() {
    comboEstudiantes.removeAllItems(); // Limpia lo viejo
    for (Estudiante e : controlador.getListaEstudiantes()) {
        comboEstudiantes.addItem(e.getId()); // Carga lo nuevo
    }
    }
}