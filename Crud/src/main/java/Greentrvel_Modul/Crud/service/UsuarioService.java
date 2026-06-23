package Greentrvel_Modul.Crud.service;

import Greentrvel_Modul.Crud.dto.UsuarioRegistroDTO;
import Greentrvel_Modul.Crud.entity.Usuario;
import Greentrvel_Modul.Crud.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UsuarioService {

    // Conectamos el repositorio de forma tradicional usando el constructor
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    // Método para registrar un usuario aplicando lógica
    public Usuario registrarUsuario(UsuarioRegistroDTO dto) {
        // 1. Validamos si el email ya está registrado (Regla de negocio)
        if (usuarioRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("El correo electrónico ya está en uso");
        }

        // 2. Pasamos los datos del DTO a una Entidad tradicional
        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setNombre(dto.getNombre());
        nuevoUsuario.setEmail(dto.getEmail());
        nuevoUsuario.setPassword(dto.getPassword()); // En el futuro, aquí se encriptará con JWT/Security

        // 3. Guardamos la entidad usando las funciones automáticas del Repositorio
        return usuarioRepository.save(nuevoUsuario);
    }

    // Método para listar todos los usuarios
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }
}