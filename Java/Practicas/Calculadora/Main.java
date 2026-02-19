package Practicas.Calculadora;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Main extends JFrame implements ActionListener {
    private JTextField pantalla;
    private double primerNumero = 0;
    private String operacionActual = "";
    private boolean nuevaEntrada = true;
    private Historial historial = new Historial();

    private String[] etiquetas = {
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "0", "C", "=", "+",
        "H"
    };

    public Main() {
        // 1. Configuración de la Ventana
        setTitle("Calculadora Sistemas Pro");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // 2. Pantalla
        pantalla = new JTextField("0"); 
        pantalla.setEditable(false);
        pantalla.setHorizontalAlignment(JTextField.RIGHT);
        pantalla.setFont(new Font("Consolas", Font.BOLD, 30));
        pantalla.setBackground(Color.BLACK);
        pantalla.setForeground(Color.WHITE); 
        add(pantalla, BorderLayout.NORTH);

        // 3. Panel de Botones
        JPanel panelBotones = new JPanel(new GridLayout(5, 4, 5, 5));
        for (String texto : etiquetas) {
            JButton boton = new JButton(texto);
            boton.addActionListener(this);
            
            // Estilo visual profesional
            boton.setFocusPainted(false);
            boton.setFont(new Font("Segoe UI", Font.BOLD, 18));
            
            if ("/*-+=H".contains(texto)) {
                boton.setBackground(new Color(255, 165, 0)); 
                boton.setForeground(Color.BLACK);
            } else {
                boton.setBackground(new Color(50, 50, 50)); 
                boton.setForeground(Color.WHITE); 
            }
            panelBotones.add(boton);
        }
        add(panelBotones, BorderLayout.CENTER);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if ("0123456789".contains(comando)) {
            if (nuevaEntrada) { pantalla.setText(comando); nuevaEntrada = false; }
            else { pantalla.setText(pantalla.getText() + comando); }
        } 
        else if (comando.equals("C")) {
            pantalla.setText("0"); primerNumero = 0; operacionActual = ""; nuevaEntrada = true;
        } 
        else if (comando.equals("H")) {
            JOptionPane.showMessageDialog(this, historial.obtenerTodo());
        }
        else if (comando.equals("=")) {
            resolver();
            nuevaEntrada = true;
        } 
        else { // Operadores
            primerNumero = Double.parseDouble(pantalla.getText());
            operacionActual = comando;
            nuevaEntrada = true;
        }
    }

    private void resolver() {
        try {
            double segundoNumero = Double.parseDouble(pantalla.getText());
            Operaciones op = null; // Declaración correcta

            switch (operacionActual) {
                case "+": op = new logicaSumar(); break;
                case "-": op = new logicaRestar(); break;
                case "*": op = new logicaMultiplicar(); break;
                case "/": op = new logicaDividir(); break;
            }

            if (op != null) {
                double resultado = op.operar(primerNumero, segundoNumero);
                pantalla.setText(String.valueOf(resultado));
                historial.guardar(primerNumero, operacionActual, segundoNumero, resultado);
            }
        } catch (ArithmeticException e) {
            pantalla.setText("Error: Div/0");
        } catch (Exception e) {
            pantalla.setText("Error");
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}