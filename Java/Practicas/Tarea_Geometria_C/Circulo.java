package Practicas.Tarea_Geometria_C;

public class Circulo extends Forma {
    private double radio;

    public Circulo(double radio) {
        this.radio = radio;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radio, 2); // Math.PI calcula el valor de PI y Math.pow eleva al cuadrado
    }

    @Override
    public double getPerimetro() {
        return 2 * Math.PI * radio;
    }

}
