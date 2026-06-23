package Greentrvel_Modul.Crud.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * Servicio encargado de la generación, validación
 * y lectura de tokens JWT utilizados para la
 * autenticación de usuarios.
 */
@Service
public class JwtService {

    /**
     * Clave secreta utilizada para firmar
     * y validar los tokens JWT.
     */
    private final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * Tiempo de expiración del token expresado
     * en milisegundos.
     * 900000 ms equivalen a 15 minutos.
     */
    private final long jwtExpiration = 900000;

    /**
     * Genera un token JWT para un usuario autenticado.
     * El token almacena el nombre del usuario, la fecha
     * de creación y la fecha de expiración.
     */
    public String generarToken(String username) {

        // Fecha de creación del token.
        Date issuedAt = new Date();

        // Fecha de expiración calculada a partir del tiempo configurado.
        Date expirationDate = new Date(System.currentTimeMillis() + jwtExpiration);

        // Construcción y firma del token JWT.
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(issuedAt)
                .setExpiration(expirationDate)
                .signWith(key)
                .compact();
    }

    /**
     * Obtiene el nombre de usuario almacenado
     * dentro de un token JWT válido.
     */
    public String obtenerUsername(String token) {

        // Obtiene los datos contenidos dentro del token.
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        // Retorna el nombre de usuario almacenado en el Subject.
        return claims.getSubject();
    }

    /**
     * Verifica la validez de un token JWT,
     * comprobando su firma, estructura y
     * fecha de expiración.
     */
    public boolean validarToken(String token) {

        try {

            // Valida el token utilizando la clave secreta.
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            return true;

        } catch (JwtException | IllegalArgumentException e) {

            return false;

        }
    }
}