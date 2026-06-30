package Greentrvel_Modul.Crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String mensaje) {
        super(mensaje);
    }

    public UnauthorizedException() {
        super("No tiene autorización para acceder a este recurso");
    }
}