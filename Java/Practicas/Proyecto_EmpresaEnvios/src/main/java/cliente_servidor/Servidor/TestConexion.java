package cliente_servidor.Servidor;

import java.sql.Connection;

import cliente_servidor.Servidor.BD.ConexionMySQL;

public class TestConexion {
    public static void main(String[] args) {
        Connection c = ConexionMySQL.getConexion();
        if (c != null) {
            System.out.println("🚀 ¡Felicidades! El puente Java-MySQL está activo.");
            try { c.close(); } catch (Exception e) {}
        } else {
            System.out.println("💀 La conexión falló. Revisa el usuario/password o el Driver.");
        }
    }
}
