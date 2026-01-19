package Proyectos.Curso_Cliente_Servidor;

import java.util.EnumSet;
import java.util.Set;

public class Rol {

    private final String nombreRol;
    private String descripcion;  // descripcion podría ser final (si decides que no cambia)
    private boolean activo; // activo podría usarse para soft-disable de roles
    private final Set<Permiso> permisosAsignados; // Podrías validar permisosAsignados ≠ null (más adelante)

    public Rol(String nombreRol, String descripcion, boolean activo, Set<Permiso> permisosAsignados) {
        this.nombreRol = nombreRol;
        this.descripcion = descripcion;
        this.activo = activo;
        this.permisosAsignados = EnumSet.copyOf(permisosAsignados);
    }
    public String getNombreRol() {
        return nombreRol;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public boolean isActivo() {
        return activo;
    }
    public Set<Permiso> getPermisosAsignados() {
        return EnumSet.copyOf(permisosAsignados);
    }
}