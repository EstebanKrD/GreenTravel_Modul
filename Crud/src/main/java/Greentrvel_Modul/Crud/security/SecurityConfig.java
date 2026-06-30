package Greentrvel_Modul.Crud.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


/**
 * Clase encargada de configurar la seguridad de la aplicación mediante Spring Security.
 * Define las rutas públicas, protegidas y las reglas de autenticación del sistema.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    /**
     * Filtro encargado de validar los tokens JWT presentes en las peticiones HTTP.
     */
    private final JwtFilter jwtFilter;

    /**
     * Constructor encargado de recibir el filtro JWT que será registrado en la cadena de filtros de Spring Security.
     */
    public SecurityConfig(JwtFilter jwtFilter) {
        this.jwtFilter = jwtFilter;
    }

    /**
     * Configura la cadena de filtros de Spring Security.
     * Define las rutas públicas, las rutas protegidas,
     * deshabilita CSRF, establece una política sin sesiones
     * y registra el filtro JWT para la autenticación.
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        /**
         * Deshabilita la protección CSRF debido a que la autenticación será manejada mediante JWT.
         */
        http.csrf(csrf -> csrf.disable());

        /**
         * Permite el acceso sin autenticación a los endpoints de registro e inicio de sesión.
         * Cualquier otra petición requerirá autenticación.
         */
      http.authorizeHttpRequests(auth -> auth
        .requestMatchers(
                "/auth/login",
                "/auth/register",
                "/swagger-ui/**",
                "/swagger-ui.html",
                "/api-docs/**",
                "/v3/api-docs/**"
        )
        .permitAll()
        .anyRequest()
        .authenticated()
);

        /**
         * Configura la aplicación para trabajar sin sesiones, utilizando JWT como mecanismo de autenticación.
         */
        http.sessionManagement(sesion -> sesion
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );

        /**
         * Registra el filtro JWT antes del filtro estándar de autenticación por usuario y contraseña.
         */
        http.addFilterBefore(
                jwtFilter,
                UsernamePasswordAuthenticationFilter.class
        );



        return http.build();
    }


}