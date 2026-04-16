package empresa_xyz_distribuciones.Servidor.DAO;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import empresa_xyz_distribuciones.PaqueteComun.modelos.Combustible;
import empresa_xyz_distribuciones.Servidor.BD.ConexionMySQL;

public class CombustibleDAO {

    public boolean insertar(Combustible c) {
        String sql = "INSERT INTO combustible (vehiculo_id, fecha, litros, costoTotal) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = ConexionMySQL.getConexion().prepareStatement(sql)) {
        
            ps.setInt(1, c.getVehiculoId());
            ps.setDate(2, new java.sql.Date(c.getFecha().getTime()));
            ps.setBigDecimal(3, c.getLitros());
            ps.setBigDecimal(4, c.getCostoTotal());
            return ps.executeUpdate() > 0;       
        }catch (SQLException e) {
            System.err.println("Error SQL: " + e.getMessage());
            return false;
        }
    }

    public boolean obtenerConsumoTotal(int vehiculoId){
        String sql = "SELECT SUM(litros) FROM combustible WHERE vehiculo_id = ?";
        try {PreparedStatement ps = ConexionMySQL.getConexion().prepareStatement(sql);
            ps.setInt(1, vehiculoId);
            ResultSet rs = ps.executeQuery();
            return rs.next();
            
        } catch (SQLException e) {
            System.err.println("Err or SQL" + e.getMessage());
            return false;
        }
    }



}
