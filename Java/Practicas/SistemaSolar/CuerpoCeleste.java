package Practicas.SistemaSolar;

abstract class CuerpoCeleste implements Comparable<CuerpoCeleste> {
    private String nombre;
    private double diametro; // en km
    private double distanciaAlSol; // en millones de km; // en kg
    private int lunas;

    protected CuerpoCeleste(String nombre, double diametro, double distanciaAlSol, int lunas) {
        if (distanciaAlSol < 0) throw new IllegalArgumentException("La distancia al sol no puede ser negativa."); // Validación para distancia al sol
        this.nombre = nombre;
        this.diametro = diametro;
        this.distanciaAlSol = distanciaAlSol;
        this.lunas = lunas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getLunas() {
        return lunas;
    }

    public double getDiametro() {
        return diametro;
    }

    public double getDistanciaAlSol() {
        return distanciaAlSol;
    }

    // Metodo Informacion //
    public abstract String mostrarInformacion();

    //Metodo para calcular la gravedad en la superficie del cuerpo celeste
    public double calcularDistanciaSol() {
        return distanciaAlSol;
    }

    // Metodo para calcular los a;os que tarda en orbitar el sol

    public double calcularAnio(){
        //Distancia viene en millones de km, convertimos a AU (1 AU = 149.6 millones de km)
        //Para la formula simplificada en años terrestres: T^2 = a^3, donde T es el periodo orbital en años y a es la distancia al sol en AU
        double distanciaAU = distanciaAlSol / 149.6;
        double periodoOrbital = Math.sqrt(Math.pow(distanciaAU, 3)); // Ley de Kepler
        return periodoOrbital; // El resultado es en años terrestres
    }

    // Metodo para comparar la distancia al sol de dos cuerpos celestes, para ordenarlos por cercania al sol
    @Override
    public int compareTo(CuerpoCeleste otro) {
        //Logica de comparacion
        //Si esta mas cerca, negativo, si es igual, 0, si esta mas lejos, positivo
        return Double.compare(this.distanciaAlSol, otro.distanciaAlSol);
    }
    

}