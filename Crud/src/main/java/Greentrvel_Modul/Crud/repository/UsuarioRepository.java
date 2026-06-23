package Greentrvel_Modul.Crud.repository;

import Greentrvel_Modul.Crud.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    
    // Este método busca un usuario en la base de datos usando su correo
    // Nos servirá muchísimo para el inicio de sesión y validaciones
    Optional<Usuario> findByEmail(String email);
}