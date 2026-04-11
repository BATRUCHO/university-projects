package cliente_servidor.PaqueteComun.modelos;

import java.io.Serializable;

public class Almacen implements Serializable {
    private int codigo; 
    private String lugar;
    private int capacidad;

public Almacen(int codigo, String lugar, int capacidad) {
    this.codigo = codigo;
    this.lugar = lugar;
    this.capacidad = capacidad;
}

// Getters y setters

public int getCodigo() {
    return codigo;
}

public void setCodigo(int codigo) {
    this.codigo = codigo;
}
public String getLugar() {
    return lugar;
}
public void setLugar(String lugar) {
    this.lugar = lugar;
}
public int getCapacidad() {
    return capacidad;
}
public void setCapacidad(int capcidad) {
    this.capacidad = capcidad;
}

// Metodos

@Override
public String toString() {
    return "Almacen [codigo=" + codigo + ", lugar=" + lugar + ", capacidad=" + capacidad + "]";
}

}
