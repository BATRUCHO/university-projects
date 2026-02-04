package Practicas.Tarea_Geometria_C;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        String[] opciones = {"Círculo", "Cuadrado", "Triángulo"};
        int eleccion = JOptionPane.showOptionDialog(null, "Seleccione una forma geométrica:", "Geometría",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]); //JoptionPane para crear una ventana emergente

        Forma forma = null;

        // Crear la forma según la elección del usuario

        switch (eleccion) {
            case 0: // Círculo
                double radio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el radio del círculo:"));
                forma = new Circulo(radio);
                break;
            case 1: // Cuadrado
                double lado = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el lado del cuadrado:"));
                forma = new Cuadrado(lado);
                break;
            case 2: // Triángulo
                double base = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la base del triángulo:"));
                double altura = Double.parseDouble(JOptionPane.showInputDialog("Ingrese la altura del triángulo:"));
                forma = new Tringulo(base, altura);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Opción no válida.");
                System.exit(0);
        }

        // Mostrar el área y el perímetro de la forma creada

        String resultado = String.format("Área: %.2f\nPerímetro: %.2f", forma.getArea(), forma.getPerimetro());
        JOptionPane.showMessageDialog(null, resultado);
    }
    
}
