package Greentrvel_Modul.Crud.repository;

import Greentrvel_Modul.Crud.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por email (lo usa Spring Security al hacer login)
    Optional<Usuario> findByEmail(String email);

    // Verificar si un email ya existe (para el registro)
    boolean existsByEmail(String email);

    // Listar usuarios activos o inactivos
    List<Usuario> findByActivo(Boolean activo);

    // Contar usuarios activos (para el dashboard)
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.activo = true")
    Long countUsuariosActivos();

    // Contar usuarios inactivos (para el dashboard)
    @Query("SELECT COUNT(u) FROM Usuario u WHERE u.activo = false")
    Long countUsuariosInactivos();
}