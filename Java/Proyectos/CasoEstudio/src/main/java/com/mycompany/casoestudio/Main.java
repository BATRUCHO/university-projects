package com.mycompany.casoestudio;

public class Main {
    public static void main(String[] args) {
        ControladorEstudiante controladorGlobal = new ControladorEstudiante();
    
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaMenu(controladorGlobal).setVisible(true);
        });
    }
}