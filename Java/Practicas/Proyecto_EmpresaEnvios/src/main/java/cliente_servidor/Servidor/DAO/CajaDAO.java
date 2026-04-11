package cliente_servidor.Servidor.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cliente_servidor.PaqueteComun.modelos.Caja;
import cliente_servidor.Servidor.BD.ConexionMySQL;


public class CajaDAO {
    public  boolean insertar(Caja c){
        String sql = "INSERT INTO cajas (num_referencia, contenido, precio, almacen_codigo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = ConexionMySQL.getConexion().prepareStatement(sql)) {
            ps.setInt(1, c.getNumReferencia());
            ps.setString(2, c.getContenido());
            ps.setBigDecimal(3, c.getPrecio());
            ps.setInt(4, c.getAlmacenCodigo());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            return false;
        }
    }

    public List<Caja> listarTodo() {
        List<Caja> listaCajas = new ArrayList<>();
        String sql = "SELECT * FROM cajas";
        try (Connection conexion = ConexionMySQL.getConexion();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Caja c = new Caja(
                    rs.getInt("num_referencia"),
                    rs.getString("contenido"),
                    rs.getBigDecimal("precio"),
                    rs.getInt("almacen_codigo")
                );
                listaCajas.add(c);
            }
        }catch(SQLException e){
            System.err.println("Error SQL: " + e.getMessage());
        }
        return listaCajas;
    }

    public Boolean eliminarCaja(int numReferencia) {
        String sql = "DELETE FROM cajas WHERE num_referencia = ?";
        try (PreparedStatement ps = ConexionMySQL.getConexion().prepareStatement(sql)) {
            ps.setInt(1, numReferencia);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            return false;
        }
    }

}
