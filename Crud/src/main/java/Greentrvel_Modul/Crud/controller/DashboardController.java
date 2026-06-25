package Greentrvel_Modul.Crud.controller;

import Greentrvel_Modul.Crud.dto.DashboardResponseDTO;
import Greentrvel_Modul.Crud.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 Controlador del dashboard administrativo.
 Expone el endpoint GET /reportes/dashboard que retorna el resumen
 de usuarios, reservas y servicios.
 */
@RestController
@RequestMapping("/reportes")
@Tag(name = "Dashboard", description = "Reportes generales para administradores y anfitriones")
public class DashboardController {

    private final DashboardService dashboardService;

    public DashboardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping("/dashboard")
    @Operation(
            summary = "Obtener resumen del dashboard",
            description = "Retorna estadísticas generales de usuarios, reservas y servicios del sistema."
    )
    @ApiResponse(responseCode = "200", description = "Resumen obtenido correctamente")
    @ApiResponse(responseCode = "403", description = "Acceso denegado - se requiere autenticación JWT")
    public ResponseEntity<DashboardResponseDTO> getDashboard() {
        DashboardResponseDTO resumen = dashboardService.obtenerResumen();
        return ResponseEntity.ok(resumen);
    }
}