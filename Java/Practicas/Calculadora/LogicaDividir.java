package Practicas.Calculadora;

class logicaDividir extends Operaciones {

    @Override
    public double operar(double num1, double num2) {
        if(num2 == 0) throw new ArithmeticException("No se puede dividir entre cero");
        return num1 / num2;
    }
}
