package com.test;

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
        
        // --- CRITERIO TÉCNICO: Espera implícita de 5 segundos ---
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));
        driver.manage().window().maximize();

        try {
            driver.get("https://the-internet.herokuapp.com/login");

            driver.findElement(By.id("username")).sendKeys("tomsmith");

            driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");

            driver.findElement(By.cssSelector("button[type='submit']")).click();

            String mensajeEsperado = "You logged into a secure area!";
            String mensajeActual = driver.findElement(By.id("flash")).getText();

            assertTrue(mensajeActual.contains(mensajeEsperado), "El mensaje de éxito no aparece");
            
            System.out.println("¡Prueba exitosa! Login verificado.");

        } catch (Exception e) {
            System.out.println("La prueba falló por: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }



    @Test
    public void testCheckboxesComplejo() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));

        try {
            driver.get("https://the-internet.herokuapp.com/checkboxes");

            // 1. Localizar los checkboxes (en esta página son elementos <input>)
            // Usaremos XPath o CSS Selector porque no tienen ID claro
            var checkbox1 = driver.findElement(By.cssSelector("input:nth-child(1)"));
            var checkbox2 = driver.findElement(By.cssSelector("input:nth-child(3)"));

            // 2. LÓGICA: Si el checkbox 1 NO está seleccionado, hazle clic
            if (!checkbox1.isSelected()) {
                checkbox1.click();
                System.out.println("Checkbox 1 marcado.");
            }

            // 3. LÓGICA: Si el checkbox 2 YA está seleccionado, no hagas nada (o desmárcalo y márcalo)
            // Hagamos que el script sea inteligente:
            if (checkbox2.isSelected()) {
                System.out.println("Checkbox 2 ya estaba marcado, no se tocó.");
            }

            // 4. VALIDACIÓN PROFESIONAL
            assertTrue(checkbox1.isSelected(), "Error: El checkbox 1 debería estar marcado");
            assertTrue(checkbox2.isSelected(), "Error: El checkbox 2 debería estar marcado");

        } catch (Exception e) {
            System.out.println("Fallo técnico: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void testCuerpoTabla() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));

        try {
            driver.get("https://the-internet.herokuapp.com/challenging_dom");

            // 1. Encontrar todas las filas de la tabla
            // Usamos findElements (plural) para obtener una lista
            var filas = driver.findElements(By.cssSelector("table tbody tr"));
            System.out.println("La tabla tiene " + filas.size() + " filas de datos.");

            // 2. Acceder a una celda específica usando XPath
            // Esto es vital si no hay IDs únicos en cada celda
            var celdaEspecifica = driver.findElement(By.xpath("//table/tbody/tr[1]/td[1]"));
            
            String valorCelda = celdaEspecifica.getText();
            System.out.println("El valor de la primera celda es: " + valorCelda);

            // 3. VALIDACIÓN
            assertTrue(!valorCelda.isEmpty(), "La celda no debería estar vacía");

        } catch (Exception e) {
            System.out.println("Error al leer el cuerpo de la web: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }

    @Test
    public void extraerColumnaCompleta() {
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(5));

        try {
            driver.get("https://the-internet.herokuapp.com/challenging_dom");

            // 1. Localizar TODAS las celdas de la primera columna
            // El XPath "//table/tbody/tr/td[1]" significa: 
            // "De todas las filas (tr), dame solo la primera celda (td[1])"
            java.util.List<org.openqa.selenium.WebElement> columna = 
                driver.findElements(By.xpath("//table/tbody/tr/td[1]"));

            System.out.println("--- Iniciando extracción de datos ---");
            System.out.println("Total de registros encontrados: " + columna.size());

            // 2. Bucle para imprimir cada valor (Lógica de Programación)
            for (org.openqa.selenium.WebElement celda : columna) {
                String valor = celda.getText();
                System.out.println("Dato extraído: " + valor);
            }

            // 3. VALIDACIÓN PROFESIONAL
            // Verificamos que la lista no esté vacía antes de dar por buena la prueba
            assertTrue(columna.size() > 0, "La tabla no devolvió datos");

        } catch (Exception e) {
            System.out.println("Error en la extracción: " + e.getMessage());
        } finally {
            driver.quit();
        }
    }
}

