package ProyectoED01;

import javax.swing.JOptionPane;
import java.io.*; // LEER Y ESCRIBIR DATOS EN UN ARCHIVO

public class ConfiguracionCaja {

    private static final String CONFIG_FILE = "prod.txt"; //NOMBRE DEL ARCHIVO
    private static String nombreBanco;
    private static int totalCajas;
    private static int cajaPreferencial;
    private static int cajaRapida;
    private static int cajaNormal;

    public static void configurar() {
        File file = new File(CONFIG_FILE);
        if (file.exists()) {
            cargarConfiguracion();
        } else {
            nombreBanco = JOptionPane.showInputDialog(null, "Ingrese el nombre del banco:");
            totalCajas = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número total de cajas (mínimo 3):"));
            cajaPreferencial = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de cajas preferenciales:"));
            cajaRapida = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de cajas rápidas:"));

            if (totalCajas < 3) {
                JOptionPane.showMessageDialog(null, "El número total de cajas debe ser al menos 3.");
                configurar();
                return;
            }

            if (cajaPreferencial + cajaRapida > totalCajas) {
                JOptionPane.showMessageDialog(null, "El número total de cajas preferenciales y rápidas no puede ser mayor que el total de cajas.");
                configurar();
                return;
            }

            //AQUI SE CALCULA EL RESTANTE DEL TOTAL PARA CAJAS NORMALES
            cajaNormal = totalCajas - cajaPreferencial - cajaRapida;

            //LE MUESTRA AL USUARIO LA CONFIGURACIÓN QUE VA A GUARDAR
            String configuracionInfo = String.format(
                    "Nombre del Banco: %s%n"
                    + "Número Total de Cajas: %d%n"
                    + "Número de Cajas Preferenciales: %d%n"
                    + "Número de Cajas Rápidas: %d%n"
                    + "Número de Cajas Normales: %d%n",
                    nombreBanco, totalCajas, cajaPreferencial, cajaRapida, cajaNormal);
            JOptionPane.showMessageDialog(null, "Configuración cajas de atención:\n" + configuracionInfo);

            guardarConfiguracion();
        }
    }

    private static void guardarConfiguracion() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CONFIG_FILE))) {
            writer.write("Nombre del Banco: " + nombreBanco + "\n");
            writer.write("Número Total de Cajas: " + totalCajas + "\n");
            writer.write("Caja Preferencial: " + cajaPreferencial + " caja(s)\n");
            writer.write("Caja Rápida: " + cajaRapida + " caja(s)\n");
            writer.write("Caja Normal: " + cajaNormal + " caja(s)\n");
            JOptionPane.showMessageDialog(null, "Configuración guardada exitosamente.");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar la configuración: " + e.getMessage());
        }
    }

    private static void cargarConfiguracion() {
        try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Nombre del Banco: ")) {
                    nombreBanco = line.substring("Nombre del Banco: ".length());
                } else if (line.startsWith("Número Total de Cajas: ")) {
                    totalCajas = Integer.parseInt(line.substring("Número Total de Cajas: ".length()));
                } else if (line.startsWith("Caja Preferencial: ")) {
                    cajaPreferencial = Integer.parseInt(line.substring("Caja Preferencial: ".length(), line.indexOf(" caja(s)")));
                } else if (line.startsWith("Caja Rápida: ")) {
                    cajaRapida = Integer.parseInt(line.substring("Caja Rápida: ".length(), line.indexOf(" caja(s)")));
                } else if (line.startsWith("Caja Normal: ")) {
                    cajaNormal = Integer.parseInt(line.substring("Caja Normal: ".length(), line.indexOf(" caja(s)")));
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al cargar la configuración: " + e.getMessage());
        }
    }

    public static String getNombreBanco() {
        return nombreBanco;
    }

    public static int getTotalCajas() {
        return totalCajas;
    }

    public static int getCajaPreferencial() {
        return cajaPreferencial;
    }

    public static int getCajaRapida() {
        return cajaRapida;
    }

    public static int getCajaNormal() {
        return cajaNormal;
    }
}
