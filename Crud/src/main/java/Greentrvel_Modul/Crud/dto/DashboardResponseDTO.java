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
@Schema(description = "Resumen general del dashboard administrativo con estadísticas de usuarios, reservas y servicios")
public class DashboardResponseDTO {

    // --- Estadísticas de Usuarios ---
    @Schema(description = "Cantidad total de usuarios registrados", example = "150")
    private Long totalUsuarios;

    @Schema(description = "Cantidad de usuarios actualmente activos", example = "120")
    private Long usuariosActivos;

    @Schema(description = "Cantidad de usuarios actualmente inactivos", example = "30")
    private Long usuariosInactivos;

    // --- Estadísticas de Reservas ---
    @Schema(description = "Cantidad total de reservas registradas", example = "80")
    private Long totalReservas;

    @Schema(description = "Cantidad de reservas actualmente activas", example = "50")
    private Long reservasActivas;

    @Schema(description = "Cantidad de reservas canceladas", example = "30")
    private Long reservasCanceladas;

    // --- Estadísticas de Servicios ---
    @Schema(description = "Cantidad de servicios actualmente activos", example = "25")
    private Long serviciosActivos;

    @Schema(description = "Cantidad de servicios actualmente inactivos", example = "5")
    private Long serviciosInactivos;
}