package Practicas.SistemaSolar;

import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<IPlaneta> sistemaSolar = new ArrayList<>();
        boolean continuar = true;

        while (continuar) {
            String[] opciones = {"Agregar Planeta Rocoso", "Agregar Planeta Gaseoso", "Ver Sistema Solar", "Salir"};
            int seleccion = JOptionPane.showOptionDialog(null, "Gestor de Sistema Solar - Nivel Profesional",
                    "Menú Principal", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            switch (seleccion) {
                case 0: // Rocoso
                    sistemaSolar.add(crearPlaneta(true));
                    break;
                case 1: // Gaseoso
                    sistemaSolar.add(crearPlaneta(false));
                    break;
                case 2:
                    mostrarReporte(sistemaSolar);
                    break;
                default:
                    continuar = false;
                    break;
            }
        }
    }

    private static IPlaneta crearPlaneta(boolean esRocoso) {
    while (true) { // Bucle para reintentar si hay error
        try {
            String nombre = JOptionPane.showInputDialog("Nombre del planeta:");
            if (nombre == null) return null; // Por si el usuario cancela

            double diametro = Double.parseDouble(JOptionPane.showInputDialog("Diámetro (km):"));
            double distancia = Double.parseDouble(JOptionPane.showInputDialog("Distancia al Sol (millones de km):"));
            int lunas = Integer.parseInt(JOptionPane.showInputDialog("Número de lunas:"));

            if (esRocoso) {
                boolean tieneAtmosfera = JOptionPane.showConfirmDialog(null, "¿Tiene atmósfera?", "Info", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                return new PlanetaRocoso(nombre, diametro, distancia, tieneAtmosfera, lunas);
            } else {
                boolean tieneAnillos = JOptionPane.showConfirmDialog(null, "¿Tiene anillos?", "Info", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
                return new PlanetaGaseoso(nombre, diametro, distancia, tieneAnillos, lunas);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Debes ingresar valores numéricos válidos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            // El bucle continuará y volverá a pedir los datos
        }
    }
}
    private static void mostrarReporte(List<IPlaneta> planetas) {
        if (planetas.isEmpty()) {
            JOptionPane.showMessageDialog(null, "El sistema solar está vacío.");
            return;
        }
        //Ordenamiento: Convertimos la lista de IPlaneta a una lista de CuerpoCeleste para usar el método compareTo
        planetas.sort((p1, p2) -> Double.compare(((CuerpoCeleste)p1).getDistanciaAlSol(), ((CuerpoCeleste)p2).getDistanciaAlSol()));

        StringBuilder reporte = new StringBuilder("--- Reporte de Planetas ---\n");
        for (IPlaneta p : planetas) {
            reporte.append(p.obtenerResumen()).append("\n------------------\n");
        }
        
        JOptionPane.showMessageDialog(null, reporte.toString());
    }
}