package Practicas.practicas_gemini_java.practica1;

import java.math.BigDecimal;

public abstract class CuentaBancaria implements IAuditable { 
    
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

    // método para generar reporte //

    public void generarReporte() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("----- Reporte de Cuenta Bancaria -----\n");
        reporte.append("Número de Cuenta: ").append(this.numeroCuenta).append("\n");
        reporte.append("Titular: ").append(this.titular).append("\n");
        reporte.append(String.format("Saldo Actual: $%.2f%n", this.saldo));
        System.out.println(reporte.toString());
    }

    // metodo de retiro //

    public void retirar(BigDecimal monto)throws SaldoInsuficienteException {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
           throw new IllegalArgumentException("El monto de retiro debe ser mayor a cero.");
        }
        if (monto.compareTo(this.saldo) > 0) {
            throw new SaldoInsuficienteException(this.saldo, monto);
        }
        this.saldo = this.saldo.subtract(monto);
        System.out.println(String.format("Retiro exitoso de $%.2f. Nuevo saldo: $%.2f", monto, this.saldo));
    }
    
    // método de depósito //
    public void depositar(BigDecimal monto) throws MontoInvalidoException {
        if (monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new MontoInvalidoException(monto);
        }
        this.saldo = this.saldo.add(monto);
        System.out.println(String.format("Depósito exitoso de $%.2f. Nuevo saldo: $%.2f", monto, this.saldo));
    }

    // metodo de datosResumido //

    public String datosResumidos() {
        return String.format("Cuenta %s - Titular: %s - Saldo: $%.2f", this.numeroCuenta, this.titular, this.saldo);
    }


}