package Practicas.SistemaSolar;

public class PlanetaGaseoso extends CuerpoCeleste implements IPlaneta {
    private boolean tieneAnillos;

    public PlanetaGaseoso(String nombre, double diametro, double distanciaAlSol, boolean tieneAnillos, int lunas) {
        super(nombre, diametro, distanciaAlSol, lunas);
        this.tieneAnillos = tieneAnillos;
    }

    @Override
    public String mostrarInformacion() {
        return "Planeta Gaseoso: " + getNombre() + "\nDiametro: " + getDiametro() + " km\nDistancia al Sol: " + getDistanciaAlSol() + " millones de km\nTiene anillos: " + (tieneAnillos ? "Sí" : "No") + "\nNúmero de lunas: " + getLunas();
    }
    
    @Override
    public double calcularAnio() {
        // Distancia viene en millones de km, convertimos a AU (1 AU = 149.6 millones de km)
        double distanciaAU = getDistanciaAlSol() / 149.6;
        // Para la formula simplificada en años terrestres: T^2 = a^3, donde T es el periodo orbital en años y a es la distancia al sol en AU
        double periodoOrbital = Math.sqrt(Math.pow(distanciaAU, 3)); // Ley de Kepler
        return periodoOrbital; // El resultado es en años terrestres
    }

    @Override
    public String obtenerResumen() {
        return String.format(
        "NOMBRE: %s (%s)\n" +
        "Distancia: %.2f M de km\n" +
        "Año Planetario: %.2f días terrestres\n" +
        "Lunas: %d",
        getNombre(), (this instanceof PlanetaGaseoso ? "Gaseoso" : "Rocoso"),
        getDistanciaAlSol(), calcularAnio(), (int)getLunas()
    );
    }   
  
}