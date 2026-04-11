package cliente_servidor.PaqueteComun.modelos;

import java.io.Serializable;
import java.math.BigDecimal;

public class Caja implements Serializable {
    private int numReferencia;
    private String contenido;
    private BigDecimal precio;
    private int almacenCodigo;

public Caja(int numReferencia, String contenido, BigDecimal precio, int almacenCodigo) {
    this.numReferencia = numReferencia;
    this.contenido = contenido;
    this.precio = precio;
    this.almacenCodigo = almacenCodigo;
}

// Getters y setters    

    public int getNumReferencia() {
        return numReferencia;
    }

    public String getContenido() {
        return contenido;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public int getAlmacenCodigo() {
        return almacenCodigo;
    }

    public void setNumReferencia(int numReferencia) {
        this.numReferencia = numReferencia;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public void setAlmacenCodigo(int almacenCodigo) {
        this.almacenCodigo = almacenCodigo;
    }


    //Metodos

    @Override
    public String toString() {
        return "Caja [numReferencia=" + numReferencia + ", contenido=" + contenido + ", precio=" + precio
                + ", almacenCodigo=" + almacenCodigo + "]";
    }

    



}
