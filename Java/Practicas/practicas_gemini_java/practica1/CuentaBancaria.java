package Practicas.practicas_gemini_java.practica1;

import java.math.BigDecimal;

public abstract class CuentaBancaria {
    
    protected String numeroCuenta;
    protected String titular;
    protected BigDecimal saldo;
    
    protected CuentaBancaria(String numeroCuenta, String titular, BigDecimal saldo) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.saldo = saldo;
    }

    // constructor simplificado //
    public CuentaBancaria(String numeroCuenta, String titular) { 
        this(numeroCuenta, titular, BigDecimal.ZERO);

    //getters //
    }
    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(String numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

        // interfaz //

    public interface IAuditable {
        void generarReporte();
        
    }



}