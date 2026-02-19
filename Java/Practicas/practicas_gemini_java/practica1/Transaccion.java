package Practicas.practicas_gemini_java.practica1;

import java.io.Serializable;
import java.math.BigDecimal;

public class Transaccion implements Serializable{

    private final int idTransaccion;
    private final TipoTransaccion tipoTransaccion;
    private final BigDecimal monto;

    public Transaccion(int idTransaccion, TipoTransaccion tipoTransaccion, BigDecimal monto) {
        this.idTransaccion = idTransaccion;
        this.tipoTransaccion = tipoTransaccion;
        this.monto = monto;
    }
    // getters //

    public int getIdTransaccion() {
        return idTransaccion;
    }
    public TipoTransaccion getTipoTransaccion() {
        return tipoTransaccion;
    }
    public BigDecimal getMonto() {
        return monto;
    }
}

enum TipoTransaccion {
        DEPOSITO,
        RETIRO;
    }