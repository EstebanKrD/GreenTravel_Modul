package Greentrvel_Modul.Crud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    public BadRequestException(String mensaje) {
        super(mensaje);
    }

    public BadRequestException(String campo, String detalle) {
        super(String.format("Error en el campo '%s': %s", campo, detalle));
    }
}