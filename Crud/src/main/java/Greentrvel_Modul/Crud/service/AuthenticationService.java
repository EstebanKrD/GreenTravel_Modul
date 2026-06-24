package Greentrvel_Modul.Crud.service;

import Greentrvel_Modul.Crud.dto.request.LoginRequest;
import Greentrvel_Modul.Crud.dto.request.RegistroRequest;
import Greentrvel_Modul.Crud.dto.response.AuthResponse;
import Greentrvel_Modul.Crud.entity.Usuario;
import Greentrvel_Modul.Crud.repository.UsuarioRepository;
import Greentrvel_Modul.Crud.security.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(UsuarioRepository usuarioRepository, JwtService jwtService, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Registra un nuevo usuario en el sistema.
     * Verifica que el correo no exista, encripta la contraseña, almacena el usuario y genera un token JWT.
     */
    public AuthResponse register(RegistroRequest request) {

        /**
         * Verifica si el correo electrónico ya se encuentra registrado.
         */
        if (usuarioRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El correo ya se encuentra registrado");
        }

        /**
         * Construye la entidad Usuario a partir de los datos recibidos.
         */
        Usuario usuario = Usuario.builder()
                .nombre(request.getNombre())
                .apellido(request.getApellido())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(request.getRol())
                .activo(true)
                .build();

        /**
         * Guarda el usuario en la base de datos.
         */
        usuarioRepository.save(usuario);

        /**
         * Genera un token JWT para el usuario registrado.
         */
        String token = jwtService.generarToken(usuario.getEmail());

        /**
         * Retorna la información de autenticación.
         */
        return AuthResponse.builder()
                .token(token)
                .email(usuario.getEmail())
                .rol(usuario.getRol().name())
                .build();
    }

    /**
     * Autentica un usuario en el sistema.
     * Verifica que el correo exista, valida la contraseña
     * y genera un token JWT para las peticiones autenticadas.
     */
    public AuthResponse login(LoginRequest request) {

        /**
         * Busca el usuario utilizando el correo electrónico proporcionado.
         * Si el usuario no existe, se lanza una excepción.
         */
        Usuario usuario = usuarioRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Credenciales inválidas"));

        /**
         * Verifica que la contraseña ingresada coincida con la
         * contraseña almacenada en la base de datos.
         */
        if (!passwordEncoder.matches(
                request.getPassword(),
                usuario.getPassword())) {

            throw new RuntimeException("Credenciales inválidas");
        }

        /**
         * Genera un token JWT para el usuario autenticado.
         */
        String token = jwtService.generarToken(usuario.getEmail());

        /**
          Retorna la información de autenticación junto con el token.
         */
        return AuthResponse.builder()
                .token(token)
                .email(usuario.getEmail())
                .rol(usuario.getRol().name())
                .build();
    }
}
