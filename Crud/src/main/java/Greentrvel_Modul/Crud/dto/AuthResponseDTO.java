package Greentrvel_Modul.Crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Respuesta de autenticación con el token JWT")
public class AuthResponseDTO {

    @Schema(description = "Token JWT para autorizar peticiones", example = "eyJhbGciOiJIUzI1NiJ9...")
    private String token;

    @Schema(description = "Tipo de token", example = "Bearer")
    @Builder.Default
    private String tipo = "Bearer";

    @Schema(description = "Email del usuario autenticado", example = "admin@greentravel.com")
    private String email;

    @Schema(description = "Rol del usuario autenticado", example = "ADMINISTRADOR")
    private String rol;
};