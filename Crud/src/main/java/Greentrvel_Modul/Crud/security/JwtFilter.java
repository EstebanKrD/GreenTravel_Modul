package Greentrvel_Modul.Crud.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filtro encargado de interceptar cada petición HTTP para verificar la presencia de un token JWT.
 * Si el token es válido, autentica al usuario dentro del contexto de seguridad de Spring.
 */
@Component
public class JwtFilter extends OncePerRequestFilter {

    /**
     * Servicio utilizado para validar tokens JWT y obtener la información almacenada en ellos.
     */
    private final JwtService jwtService;

    /**
     * Constructor encargado de recibir el servicio JWT necesario para procesar los tokens.
     */
    public JwtFilter(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    /**
     * Método ejecutado una vez por cada petición HTTP.
     * Obtiene el token JWT desde el encabezado Authorization, valida su contenido y registra la autenticación en el contexto de seguridad de Spring.
     */
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        /**
         * Si el encabezado no existe o no contiene el prefijo Bearer, la petición continúa sin autenticación.
         */
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        /**
         * Extrae el token JWT eliminando el prefijo Bearer.
         */
        String jwtToken = authHeader.substring(7);

        /**
         * Verifica que el token sea válido antes de procesar su contenido.
         */
        if (!jwtService.validarToken(jwtToken)) {
            filterChain.doFilter(request, response);
            return;
        }

        /**
         * Evita sobrescribir una autenticación que ya exista en el contexto de seguridad.
         */
        if (SecurityContextHolder.getContext().getAuthentication() == null) {

            /**
             * Obtiene el nombre de usuario almacenado dentro del token JWT.
             */
            String username = jwtService.obtenerUsername(jwtToken);

            /**
             * Crea un objeto de autenticación basado en la información obtenida del token.
             */
            UsernamePasswordAuthenticationToken authentication =
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            null
                    );

            /**
             * Registra la autenticación en el contexto de seguridad de Spring.
             */
            SecurityContextHolder.getContext()
                    .setAuthentication(authentication);
        }

        /**
         * Continúa la ejecución de la cadena de filtros.
         */
        filterChain.doFilter(request, response);
    }
}
