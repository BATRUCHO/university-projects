package Practicas.practicas_gemini_java.practica1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.SwingUtilities;

public class Main {
    public static void main (String[] args) {
       
        List<CuentaBancaria> listaCuentas = new ArrayList<>();

        // Crear un mapa para acceso rápido por número de cuenta //
        Map<String, CuentaBancaria> mapaCuentas = new HashMap<>();
        for (CuentaBancaria cuenta : listaCuentas) {
            mapaCuentas.put(cuenta.getNumeroCuenta(), cuenta);
            
            SwingUtilities.invokeLater(() -> {
            VentanaPrincipal ventana = new VentanaPrincipal(listaCuentas);
            ventana.setVisible(true); // ¡Si no pones esto, no verás nada!
            });
        }
        
        // Realizar operaciones de retiro y depósito con manejo de excepciones //
    
        for (CuentaBancaria cuenta : listaCuentas) {
            try {
                cuenta.retirar(new BigDecimal("1500.00"));
            } catch (SaldoInsuficienteException e) {
                
            } 

            try {
                cuenta.depositar(new BigDecimal("-100.00"));
            } catch (MontoInvalidoException e) {
                
            }

            
        }

    }

}
    