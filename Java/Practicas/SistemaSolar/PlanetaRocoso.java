package Practicas.SistemaSolar;

public class PlanetaRocoso extends CuerpoCeleste implements IPlaneta {
    private boolean tieneAtmosfera;

    public PlanetaRocoso(String nombre, double diametro, double distanciaAlSol, boolean tieneAtmosfera, int lunas) {
        super(nombre, diametro, distanciaAlSol, lunas);
        this.tieneAtmosfera = tieneAtmosfera;
    }

    @Override
    public String mostrarInformacion() {
        return "Planeta Rocoso: " + getNombre() + "\nDiametro: " + getDiametro() + " km\nDistancia al Sol: " + getDistanciaAlSol() + " millones de km\nTiene atmósfera: " + (tieneAtmosfera ? "Sí" : "No") + "\nNúmero de lunas: " + getLunas();
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
        getNombre(), (this instanceof PlanetaRocoso ? "Rocoso" : "Gaseoso"),
        getDistanciaAlSol(), calcularAnio(), (int)getLunas()
    );
    }

}