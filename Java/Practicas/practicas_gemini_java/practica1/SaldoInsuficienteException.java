package Practicas.practicas_gemini_java.practica1;

import java.math.BigDecimal;

public class SaldoInsuficienteException extends BaseException {

    private BigDecimal SaldoDisponible;

    public SaldoInsuficienteException(BigDecimal saldoDisponible, BigDecimal montoIntentado) {
        super(String.format("Saldo insuficiente: Saldo disponible $%.2f, monto intentado $%.2f", saldoDisponible, montoIntentado));
        this.SaldoDisponible = saldoDisponible;
    }
    
    public BigDecimal getSaldoDisponible() {
        return this.SaldoDisponible;
    }
    
}
