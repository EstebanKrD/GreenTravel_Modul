package Greentrvel_Modul.Crud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponseDTO {

    // --- Estadísticas de Usuarios ---
    private Long totalUsuarios;
    private Long usuariosActivos;
    private Long usuariosInactivos;

    // --- Estadísticas de Reservas ---
    private Long totalReservas;
    private Long reservasActivas;
    private Long reservasCanceladas;

    // --- Estadísticas de Servicios ---
    private Long serviciosActivos;
    private Long serviciosInactivos;
}