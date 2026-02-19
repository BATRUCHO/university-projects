package Practicas.practicas_gemini_java.practica1;

import java.awt.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;


public class VentanaPrincipal extends JFrame {

    private List<CuentaBancaria> cuentas;
    private JTabbedPane tabs;

    public VentanaPrincipal(List<CuentaBancaria> cuentasIniciales){
        this.cuentas = cuentasIniciales;

        tabs = new JTabbedPane();

        // Pestaña de Registro
        tabs.addTab("Abrir Cuenta", crearPanelRegistro());

        //// Pestaña de Dashboard
        tabs.addTab("Dashboard Analitico", crearPanelDashboard());

        add(tabs);
        setTitle("Sistema de Cuentas Bancarias");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    private JPanel crearPanelRegistro() {
            JPanel panel = new JPanel(new GridLayout(5, 2));
            JTextField txtNumero = new JTextField();
            JTextField txtTitular = new JTextField();
            JTextField txtSaldo = new JTextField();
            JButton btnCrear = new JButton("Registrar en Sistema");

            btnCrear.addActionListener(e -> {
                // AQUÍ la magia: creas la cuenta dinámicamente
                CuentaAhorro nueva = new CuentaAhorro(
                    txtNumero.getText(), 
                    txtTitular.getText(), 
                    new BigDecimal(txtSaldo.getText()), 
                    new BigDecimal("0.05")
                );
                cuentas.add(nueva);
                JOptionPane.showMessageDialog(this, "Cuenta Creada y Lista para Análisis");
            });

            panel.add(new JLabel("Número:")); panel.add(txtNumero);
            panel.add(new JLabel("Titular:")); panel.add(txtTitular);
            panel.add(new JLabel("Saldo Inicial:")); panel.add(txtSaldo);
            panel.add(new JLabel("")); panel.add(btnCrear);
            
            return panel;
        }

}


    


