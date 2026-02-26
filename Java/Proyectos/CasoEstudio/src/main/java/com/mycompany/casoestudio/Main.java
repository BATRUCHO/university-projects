package com.mycompany.casoestudio;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
/**
 * @author braya
 */

public class Main {
    public static void main(String[] args) {
        ControladorEstudiante controladorGlobal = new ControladorEstudiante();
    
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaMenu(controladorGlobal).setVisible(true);
        });
    }
}