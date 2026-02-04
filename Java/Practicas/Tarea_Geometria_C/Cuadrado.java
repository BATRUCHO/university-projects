package Practicas.Tarea_Geometria_C;

public class Cuadrado extends Forma {
    private double lado;

    public Cuadrado(double lado) {
        this.lado = lado;
    }

    @Override
    public double getArea() {
        return lado * lado;
    }

    @Override
    public double getPerimetro() {
        return 4 * lado;
    }

    // Getter
    public double getLado() {
        return lado;
    }
    
}
