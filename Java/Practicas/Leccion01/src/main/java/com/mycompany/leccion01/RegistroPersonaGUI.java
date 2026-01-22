/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.leccion01;
import javax.swing.*;
import javax.swing.JFrame;
import java.sql.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.*;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Vane
 */
public class RegistroPersonaGUI extends JFrame implements ActionListener{
    private JTextField txtId;
    private JTextField txtNombre;
    private JTextField txtApellidos;
    private JTextField txtFechaNaci;
    private JComboBox<Genero> cmbGenero;
    private JButton btnRegistrar;
    private JTextArea taVisualizar;

    public RegistroPersonaGUI() {
        //CONFIGURACION BASICA DE LA PANTALLA
        super("Formulario Registro Personas");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10,10));
        getContentPane().setBackground(Color.DARK_GRAY);
        
        JPanel panelPrincipal = new JPanel(new GridLayout(0,2,10,10));
        //margenes arriba, izquierda, abajo, derecha
        panelPrincipal.setBorder(new EmptyBorder(15,20,15,20));
        panelPrincipal.setBackground(Color.GRAY);
        panelPrincipal.setFont(new Font(Font.SANS_SERIF,Font.BOLD,14));
        
        JLabel lblId = new JLabel("Id Persona");
        lblId.setForeground(Color.BLACK);
        panelPrincipal.add(lblId);
        
        txtId = new JTextField(50);
        panelPrincipal.add(txtId);
        
        JLabel lblNombre = new JLabel("Nombre");
        lblNombre.setForeground(Color.BLACK);
        panelPrincipal.add(lblNombre);
        
        txtNombre = new JTextField(50);
        panelPrincipal.add(txtNombre);
        
        JLabel lblApellidos = new JLabel("Apellidos");
        lblApellidos.setForeground(Color.BLACK);
        panelPrincipal.add(lblApellidos);
        
        txtApellidos= new JTextField(50);
        panelPrincipal.add(txtApellidos);
        
        JLabel lblFecha = new JLabel("Fecha Nac.");
        lblFecha.setForeground(Color.BLACK);
        panelPrincipal.add(lblFecha);
        
        txtFechaNaci = new JTextField(50);
        panelPrincipal.add(txtFechaNaci);
        
        JLabel lblGenero = new JLabel("Genero");
        lblGenero.setForeground(Color.BLACK);
        panelPrincipal.add(lblGenero);
        
        cmbGenero = new JComboBox<>(Genero.values());
        panelPrincipal.add(cmbGenero);
        
        JLabel lblEspacio = new JLabel("");
        lblEspacio.setForeground(Color.BLACK);
        panelPrincipal.add(lblEspacio);
        
        btnRegistrar = new JButton("Registrar");
        btnRegistrar.addActionListener(this);
        panelPrincipal.add(btnRegistrar);
        
        pack();// ajusta la pantalla al tamaño de los componentes
        setLocationRelativeTo(null); //ventana centrada
        this.add(panelPrincipal, "North");
        
        setVisible(true);
    }

    @Override 
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == btnRegistrar){
            //logica de boton 
        }
    }
    
    
    
}
