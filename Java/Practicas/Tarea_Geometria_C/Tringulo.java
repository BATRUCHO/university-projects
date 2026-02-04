package Practicas.Tarea_Geometria_C;

public class Tringulo extends Forma {
    private double base;
    private double altura;

    public Tringulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    @Override
    public double getArea() {
        return base * altura / 2;
    }

    @Override
    public double getPerimetro() {
        return base + altura + Math.hypot(base, altura); // math.hypot calcula la hipotenusa
    }

    // Getters
    public double getBase() {
        return base;
    }

    public double getAltura() {
        return altura;
    }
}