package empresa_xyz_distribuciones.PaqueteComun.modelos;

import java.io.Serializable;

public class Vehiculo implements Serializable {

    private int id;
    private String placa;
    private String marca;
    private String modelo;
    private int kilometraje;

    public Vehiculo(int id, String placa, String marca, String modelo, int kilometraje) {
        this.id = id;
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
    }
    public Vehiculo(String placa, String marca, String modelo, int kilometraje){
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.kilometraje = kilometraje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }


}
