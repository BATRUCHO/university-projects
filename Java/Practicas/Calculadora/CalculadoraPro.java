package Practicas.Calculadora;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraPro extends JFrame implements ActionListener {
    private JTextField pantalla;
    private JPanel panelBotones;
    private String[] etiquetas = {
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "0", "C", "=", "+"
    };

    public CalculadoraPro() {
        // Configuración del JFrame
        setTitle("Calculadora Sistemas - Nivel Pro");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 1. La Pantalla (JLabel o JTextField)
        pantalla = new JTextField("0");
        pantalla.setEditable(false);
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setFont(new Font("Arial", Font.BOLD, 24));
        add(pantalla, BorderLayout.NORTH);

        // 2. Panel de Botones
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(4, 4, 5, 5));

        for (String texto : etiquetas) {
            JButton boton = new JButton(texto);
            boton.addActionListener(this); // Todos los botones reportan aquí
            panelBotones.add(boton);
        }
        add(panelBotones, BorderLayout.CENTER);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        // Aquí programarás la lógica de cálculo
        pantalla.setText(comando); 
    }

    public static void main(String[] args) {
        new CalculadoraPro();
    }
}