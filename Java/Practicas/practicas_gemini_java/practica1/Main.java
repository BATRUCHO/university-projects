package Practicas.practicas_gemini_java.practica1;
import java.math.BigDecimal;

public class Main {
    public static void main (String[] args) {
       
        CuentaAhorro cuenta = new CuentaAhorro("1234567890", "Juan Perez", BigDecimal.valueOf(1000.00), BigDecimal.valueOf(0.05));
        CuentaAhorro cuenta2 = new CuentaAhorro("0987654321", "Maria Gomez", BigDecimal.valueOf(2000.00), BigDecimal.valueOf(0.03));
        System.out.printf("Número de cuenta: %s%n", cuenta.getNumeroCuenta());
        System.out.println("Titular: " + cuenta.getTitular());
        System.out.printf("Saldo inicial: $%.2f%n", cuenta.getSaldo());
        System.out.printf("Tasa de interés: %.2f%%%n", cuenta.getTasaInteres().multiply(BigDecimal.valueOf(100)));
        System.out.println("Interés aplicado: $" + cuenta.aplicarInteres());
        System.out.printf("Saldo después de aplicar interés: $%.2f%n", cuenta.getSaldo());

        System.out.println("------------------------------".repeat(2));

        System.out.printf("Número de cuenta: %s%n", cuenta2.getNumeroCuenta());
        System.out.println("Titular: " + cuenta2.getTitular());
        System.out.printf("Saldo inicial: $%.2f%n", cuenta2.getSaldo());
        System.out.printf("Tasa de interés: %.2f%%%n", cuenta2.getTasaInteres().multiply(BigDecimal.valueOf(100)));
        System.out.println("Interés aplicado: $" + cuenta2.aplicarInteres());
        System.out.printf("Saldo después de aplicar interés: $%.2f%n", cuenta2.getSaldo());

        System.out.println("------------------------------".repeat(2));

        CuentaAhorro cuenta3 = new CuentaAhorro("1234567890", "Juan Perez", BigDecimal.valueOf(1000.00), BigDecimal.valueOf(0.05));
        cuenta3.generarReporte();
        CuentaAhorro cuenta4 = new CuentaAhorro("0987654321", "Maria Gomez", BigDecimal.valueOf(2000.00), BigDecimal.valueOf(0.03));
        cuenta4.generarReporte();
        






    }

}
