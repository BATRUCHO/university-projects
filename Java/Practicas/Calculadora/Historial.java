package Practicas.Calculadora;

import java.util.ArrayList;
import java.util.List;

public class Historial {
    // La lista vive dentro del objeto Historial
    private List<String> registros = new ArrayList<>();

    public void guardar(double n1, String op, double n2, double res) {
        String linea = String.format("%.2f %s %.2f = %.2f", n1, op, n2, res);
        registros.add(linea);
    }

    public String obtenerTodo() {
        if (registros.isEmpty()) return "No hay operaciones registradas.";
        StringBuilder sb = new StringBuilder("--- Historial de Operaciones ---\n");
        for (String r : registros) {
            sb.append(r).append("\n");
        }
        return sb.toString();
    }
}
