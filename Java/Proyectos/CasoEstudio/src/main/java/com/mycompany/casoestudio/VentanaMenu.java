package com.mycompany.casoestudio;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 * @author braya
 */

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class VentanaMenu extends JFrame {
    private ControladorEstudiante controlador;

    public VentanaMenu(ControladorEstudiante controlador) {
        this.controlador = controlador;
        setTitle("Instituto Futuro - Sistema Académico");
        setSize(300, 200);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton btnRegistro = new JButton("Registrar Estudiantes");
        JButton btnNotas = new JButton("Gestionar Notas");

        btnRegistro.addActionListener(e -> {
            new VentanaRegistroEstudiante(controlador, this).setVisible(true);
            this.setVisible(false); // Oculta el menú
        });

        btnNotas.addActionListener(e -> {
            VentanaNotas vn = new VentanaNotas(controlador, this);
            vn.refrescarEstudiantes();
            vn.setVisible(true);
            this.setVisible(false); // Oculta el menú
        });    

        add(btnRegistro);
        add(btnNotas);
        setLocationRelativeTo(null);
    }
}