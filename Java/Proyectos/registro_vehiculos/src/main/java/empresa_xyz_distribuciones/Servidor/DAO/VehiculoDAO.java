package empresa_xyz_distribuciones.Servidor.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import empresa_xyz_distribuciones.PaqueteComun.modelos.Vehiculo;
import empresa_xyz_distribuciones.Servidor.BD.ConexionMySQL;

public class VehiculoDAO {

    public boolean insertar(Vehiculo v) {
        String sql = "INSERT INTO vehiculo (placa, marca, modelo, kilometraje) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = ConexionMySQL.getConexion().prepareStatement(sql)) {
            ps.setString(1, v.getPlaca());
            ps.setString(2, v.getMarca());
            ps.setString(3, v.getModelo());
            ps.setInt(4, v.getKilometraje());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            return false;
        }
    }

    public List<Vehiculo> listarVehiculos() {
        List<Vehiculo> listaVehiculos = new ArrayList<>();
        String sql = "SELECT * FROM vehiculo";
        try (Connection conexion = ConexionMySQL.getConexion();
             PreparedStatement ps = conexion.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Vehiculo v = new Vehiculo(
                    rs.getInt("id"),
                    rs.getString("placa"),
                    rs.getString("marca"),
                    rs.getString("modelo"),
                    rs.getInt("kilometraje")
                );
                listaVehiculos.add(v);
            }
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
        return listaVehiculos;
    }

    public boolean buscarPorPlaca(String placa) {
        String sql = "SELECT * FROM vehiculo WHERE placa = ?";
        try {PreparedStatement ps = ConexionMySQL.getConexion().prepareStatement(sql);
            ps.setString(1, placa);
            ResultSet rs = ps.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
        }
        return false;
    }


    public boolean obtenerPorId(int id) {
        String sql = "SELECT * FROM vehiculo WHERE id = ?";
        try {PreparedStatement ps = ConexionMySQL.getConexion().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
                 
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());   
        }
        return false;
    }

}
