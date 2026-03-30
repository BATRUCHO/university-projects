package pruebas;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SimulacionTest {
    
    WebDriver driver;

    @BeforeEach
    public void setup() {
        // Forzar el uso del cliente HTTP nativo de Java (mejor soporte en JDK 23)
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testSemana11_SauceDemo() {
        driver.get("https://www.saucedemo.com/");
        
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        String titulo = driver.findElement(By.className("title")).getText();
        assertEquals("Products", titulo, "El título de la página no coincide con el inventario esperado");
        
        System.out.println("Validación Exitosa: Pantalla de Productos cargada.");
    }

    @Test
    public void testSemana12_Demoqa() {
        driver.get("https://demoqa.com/automation-practice-form");

        // 1. Campos de texto 
        driver.findElement(By.id("firstName")).sendKeys("Carlos");
        driver.findElement(By.id("lastName")).sendKeys("Jose");
        driver.findElement(By.id("userEmail")).sendKeys("d3aanxxx@gmail.com");

        // 2. Radio Button de Género 
        
        driver.findElement(By.xpath("//label[@for='gender-radio-1']")).click();

        // 3. Otros campos
        driver.findElement(By.id("userNumber")).sendKeys("88888888"); 
        
        // 4. Hobbies (Usando labels por la misma razón que el género)
        driver.findElement(By.xpath("//label[@for='hobbies-checkbox-1']")).click();

        // 5. Carga de archivos
        driver.findElement(By.id("uploadPicture")).sendKeys("C:/Users/braya/Downloads/Practica Programada 3.pdf");

        driver.findElement(By.id("currentAddress")).sendKeys("Del supercarmon 25s , 25o , casa 199a");

        // 6. VALIDACIÓN (Punto clave para tu nota)

        String nombreIngresado = driver.findElement(By.id("firstName")).getAttribute("value");
        assertTrue(nombreIngresado.equals("Carlos"), "El nombre no coincide");

        System.out.println("Validación de formulario: Exitosa.");
        
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
