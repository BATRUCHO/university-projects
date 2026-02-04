package Practicas.practicas_gemini_java.practica1;

import java.math.BigDecimal;

public class CuentaAhorro extends CuentaBancaria {

    public BigDecimal tasaInteres;

    public CuentaAhorro(String numeroCuenta, String titular, BigDecimal saldo, BigDecimal tasaInteres) {
        super(numeroCuenta, titular, saldo);
        this.tasaInteres = tasaInteres;
    }

    // getter //
    public BigDecimal getTasaInteres() {
        return tasaInteres;
    }

    // método para aplicar interés //
    public BigDecimal aplicarInteres() {
        BigDecimal interes = this.saldo.multiply(this.tasaInteres);
        this.saldo = this.saldo.add(interes);
        return interes;
    }

    // implementación de la interfaz IAuditable //
    public String generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("----- Reporte de Cuenta de Ahorro -----\n");
        reporte.append("Número de Cuenta: ").append(this.numeroCuenta).append("\n");
        reporte.append("Titular: ").append(this.titular).append("\n");
        reporte.append(String.format("Saldo Actual: $%.2f%n", this.saldo));
        reporte.append(String.format("Tasa de Interés: %.2f%%%n", this.tasaInteres.multiply(BigDecimal.valueOf(100))));
        System.out.println(reporte.toString());
        return reporte.toString();
    }
    
}
