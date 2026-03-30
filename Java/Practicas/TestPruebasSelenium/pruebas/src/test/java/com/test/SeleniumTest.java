package pruebas;

import static org.junit.jupiter.api.Assertions.assertTrue; 
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

    @Test
    public void miPrimeraPrueba() {
        WebDriver driver = new ChromeDriver();
        
        try {
            driver.get("https://www.google.com");
            String titulo = driver.getTitle();
            System.out.println("El título es: " + titulo);
            
            assertTrue(titulo.contains("Google"));
        } finally {
            driver.quit();
        }
    }

    @Test
    public void miSegundaPrueba(){
        WebDriver driver = new ChromeDriver();
        
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
        driver.manage().window().maximize();

        try {
            driver.get("https://the-internet.herokuapp.com/login");

            driver.findElement(By.id("username")).sendKeys("tomsmith");

            driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

            driver.findElement(By.cssSelector("button[type='submit']")).click();

            String mensajeEsperado = "Logueo Correcto!";
            String mensajeActual = driver.findElement(By.id("flash")).getText();

            assertTrue(mensajeActual.contains(mensajeEsperado), "El mensaje de éxito no aparece");

            String urlActual = driver.getCurrentUrl();
            assertTrue(urlActual.contains("secure"), "La URL no es la esperada tras el login");
            
            System.out.println("----------------------------------------------");
            System.out.println("RESULTADO DE SIMULACIÓN: EXITOSO");
            System.out.println("USUARIO: tomsmith | ESTADO: Autenticado");
            System.out.println("----------------------------------------------");

        } catch (Exception e) {
            System.out.println("La prueba falló por: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

}