package pruebas;

import java.util.Collections;

import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ColonoTest {
    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
        public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        // Esto ayuda si la red es lenta
        options.setPageLoadStrategy(PageLoadStrategy.NORMAL); 

        driver = new ChromeDriver(options);
    }

    @Test
    public void testFinal_ColonoConstruccion() {
        // PANTALLA 1: Home
        driver.get("https://www.colonoconstruccion.com/");
        
        // Validación 1 & 2: Título y presencia de elementos base
        assertTrue(driver.getTitle().contains("Colono"), "Error: El título no coincide con la marca.");
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(By.name("q")));
        assertTrue(searchBox.isDisplayed(), "Error: El buscador no es visible.");

        // PANTALLA 2: Resultados de Búsqueda
        searchBox.clear();
        searchBox.click();
        searchBox.sendKeys("Varilla");
        searchBox.sendKeys(Keys.ENTER);
        
        // Validación 3 & 4: URL y existencia de productos
        wait.until(ExpectedConditions.urlContains("q=Varilla"));
        assertTrue(driver.getCurrentUrl().contains("q=Varilla"), "Error: La búsqueda no redirigió correctamente.");
        
        var listaProductos = driver.findElements(By.className("product-item"));
        assertFalse(listaProductos.isEmpty(), "Error: No se desplegaron resultados de productos.");
        
        // PANTALLA 3: Detalle de Producto
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", listaProductos.get(0));
        listaProductos.get(0).click(); 
        
        // Validación 5 & 6: Carga de ficha técnica y moneda
        WebElement precioElemento = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".price-wrapper .price")));
        String precioPagina = precioElemento.getText();
        
        assertNotNull(precioPagina, "Error: El precio no cargó en la ficha.");
        assertTrue(precioPagina.contains("₡"), "Error: El formato de moneda no es colones.");

        // PANTALLA 4: Carrito de Compras
        WebElement btnCarrito = wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
        btnCarrito.click();
        
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".message-success")));
        driver.get("https://www.colonoconstruccion.com/checkout/cart/");
        
        // Validación 7 & 8: Persistencia en carrito
        WebElement nombreEnCarrito = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".cart-item-name")));
        assertTrue(nombreEnCarrito.isDisplayed(), "Error: El producto no aparece en el resumen del carrito.");
        assertTrue(driver.getPageSource().contains("Carrito de Compras"), "Error: No se llegó a la pantalla de carrito.");

        // PANTALLA 5: Resumen y Validación de Datos Financieros
        WebElement subtotalElemento = driver.findElement(By.cssSelector(".subtotal .price"));
        String subtotal = subtotalElemento.getText();
        
        // Validación 9 & 10: Integridad del monto y flujo de Checkout
        assertEquals(precioPagina, subtotal, "CRÍTICO: El precio del detalle no coincide con el del carrito.");
        
        WebElement btnCheckout = driver.findElement(By.cssSelector(".checkout-methods-items button"));
        assertTrue(btnCheckout.isEnabled(), "Error: El botón de proceder al pago está deshabilitado.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}