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

    


    
}
