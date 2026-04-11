package cliente_servidor.Servidor.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import cliente_servidor.PaqueteComun.modelos.Almacen;
import cliente_servidor.Servidor.BD.ConexionMySQL;


public class AlmacenDAO {
    public boolean insertar(Almacen a) {
        String sql = "INSERT INTO almacenes (codigo, lugar, capacidad) VALUES (?, ?, ?)";
        try (PreparedStatement ps = ConexionMySQL.getConexion().prepareStatement(sql)) {
            ps.setInt(1, a.getCodigo());
            ps.setString(2, a.getLugar());
            ps.setInt(3, a.getCapacidad());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            return false;
        }
    }

     public List<Almacen> listarAlmacen() {
        List<Almacen> listaAlmacenes = new ArrayList<>();
        String sql = "SELECT * FROM almacenes";
        try (Connection conexion = ConexionMySQL.getConexion();
                PreparedStatement ps = conexion.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Almacen a = new Almacen(
                    rs.getInt("codigo"),
                    rs.getString("lugar"),
                    rs.getInt("capacidad")
                );
                listaAlmacenes.add(a);
            }
        }catch(SQLException e){
            System.err.println("Error SQL: " + e.getMessage());
        }
        return listaAlmacenes;
    }

    public Boolean eliminarAlmacen(int codigo) {
        String sql = "DELETE FROM almacenes WHERE codigo = ?";
        try (PreparedStatement ps = ConexionMySQL.getConexion().prepareStatement(sql)) {
            ps.setInt(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            return false;
        }
    }

}