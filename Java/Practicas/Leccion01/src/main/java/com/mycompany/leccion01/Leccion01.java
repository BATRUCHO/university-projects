/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.leccion01;
import java.sql.Date;
import javax.swing.SwingUtilities;

/**
 *
 * @author Vane
 */
public class Leccion01 {

    public static void main(String[] args) {
        
        System.out.println("Opciones para el genero");
        
        for(Genero OpcionGenero: Genero.values()){
            System.out.println(OpcionGenero.name());
        }
        
        Persona MiPersona = new Persona("7-0206-0059",
                                    "Jose Danilo", 
                                     "Chinchilla Cruz", 
                                    Date.valueOf("1991-07-31"),
                                    "Masculino");
     
        System.out.println(MiPersona.toString());
        
        SwingUtilities.invokeLater(()-> new RegistroPersonaGUI());
    }
}
