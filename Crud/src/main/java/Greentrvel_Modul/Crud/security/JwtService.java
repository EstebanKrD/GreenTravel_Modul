package Greentrvel_Modul.Crud.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;

@Service
public class JwtService {

    private  final SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    private final long jwtExpiration = 900000; // esto equivale a 15 minutos que dura el token una vez generado

    public String generarToken(String username) {

    }


}
