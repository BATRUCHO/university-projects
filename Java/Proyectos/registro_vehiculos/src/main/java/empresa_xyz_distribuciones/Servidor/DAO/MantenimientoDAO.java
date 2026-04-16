package empresa_xyz_distribuciones.Servidor.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import empresa_xyz_distribuciones.PaqueteComun.modelos.Mantenimiento;
import empresa_xyz_distribuciones.Servidor.BD.ConexionMySQL;

public class MantenimientoDAO {

    public boolean insertar(Mantenimiento m){
        String sql = "INSERT INTO mantenimiento (vehiculo_id, tipo, fecha, kilometraje) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = ConexionMySQL.getConexion().prepareStatement(sql)){
            ps.setInt(1, m.getVehiculo_id());
            ps.setString(2, m.getTipo());
            ps.setDate(3, new java.sql.Date(m.getFecha().getTime()));
            ps.setInt(4, m.getKilometraje());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            return false; 
        }
    }

    public List<Mantenimiento> listarMantenimientos(){
        List<Mantenimiento> lista_Mantenimientos = new ArrayList<>();
        String sql = "SELECT * FROM mantenimiento WHERE vehiculo_id = ?";
        try (Connection conexion = ConexionMySQL.getConexion();
            PreparedStatement ps = conexion.prepareStatement(sql)){
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mantenimiento m = new Mantenimiento(
                    rs.getInt("id"),
                    rs.getInt("vehiculo_id"),
                    rs.getString("tipo"),
                    rs.getDate("fecha"),
                    rs.getInt("kilometraje")
                );
                lista_Mantenimientos.add(m);
            }
        }catch(SQLException e){
            System.err.println("Error SQL: " + e.getMessage());
        }
        return lista_Mantenimientos;
    }
}
