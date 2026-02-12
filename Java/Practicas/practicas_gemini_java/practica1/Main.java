package Practicas.practicas_gemini_java.practica1;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main (String[] args) {
       
        List<CuentaBancaria> listaCuentas = new ArrayList<>();

        listaCuentas.add(new CuentaAhorro("123456789", "Juan Pérez", new BigDecimal("1000.00"), new BigDecimal("0.05")));
        listaCuentas.add(new CuentaAhorro("987654321", "María Gómez", new BigDecimal("2000.00"), new BigDecimal("0.03")));
        listaCuentas.add(new CuentaAhorro("555555555", "Carlos López", new BigDecimal("500.00"), new BigDecimal("1000.00")));
        listaCuentas.add(new CuentaAhorro("111111111", "Ana Torres", new BigDecimal("3500.00"), new BigDecimal("0.04")));


        // Crear un mapa para acceso rápido por número de cuenta //
        Map<String, CuentaBancaria> mapaCuentas = new HashMap<>();
        for (CuentaBancaria cuenta : listaCuentas) {
            mapaCuentas.put(cuenta.getNumeroCuenta(), cuenta);
        }

         System.out.print("----Reporte de Cuentas Bancarias----\n");

        // Generar reporte para cada cuenta //
        
        System.out.print("----Cuentas VIP ------(Saldos mayores a 1000)----\n");
        BigDecimal saldoTotal = listaCuentas.stream().map(cuenta -> cuenta.getSaldo())
            .map(CuentaBancaria :: getSaldo)
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Saldo total de todas las cuentas: $" + saldoTotal);
        
        // Realizar operaciones de retiro y depósito con manejo de excepciones //
         System.out.print("----Operaciones de Retiro y Depósito----\n");
        for (CuentaBancaria cuenta : listaCuentas) {
            try {
                cuenta.retirar(new BigDecimal("1500.00"));
            } catch (SaldoInsuficienteException e) {
                System.out.println("Error al retirar: " + e.getMensaje());
            } 

            try {
                cuenta.depositar(new BigDecimal("-100.00"));
            } catch (MontoInvalidoException e) {
                System.out.println("Error al depositar: " + e.getMensaje());
            }

            
        }

    }

}
    