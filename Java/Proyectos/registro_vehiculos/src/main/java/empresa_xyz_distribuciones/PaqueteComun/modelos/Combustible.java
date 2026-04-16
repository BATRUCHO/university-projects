package empresa_xyz_distribuciones.PaqueteComun.modelos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Combustible implements Serializable {
    private int id;
    private int vehiculo_id; // Obligatorio para la FK
    private java.util.Date fecha;
    private BigDecimal litros;
    private BigDecimal costoTotal;

    public Combustible(int vehiculoId, java.util.Date fecha, BigDecimal litros, BigDecimal costoTotal) {
        this.vehiculo_id = vehiculoId;
        this.fecha = fecha;
        this.litros = litros;
        this.costoTotal = costoTotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVehiculoId() {
        return vehiculo_id;
    }

    public void setVehiculoId(int vehiculoId) {
        this.vehiculo_id = vehiculoId;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public BigDecimal getLitros() {
        return litros;
    }

    public void setLitros(BigDecimal litros) {
        this.litros = litros;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCosto(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    
}
