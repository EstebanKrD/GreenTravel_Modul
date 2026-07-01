package Greentrvel_Modul.Crud.controller;

import Greentrvel_Modul.Crud.dto.ConsumoAguaDTO;
import Greentrvel_Modul.Crud.dto.ConsumoEnergiaDTO;
import Greentrvel_Modul.Crud.dto.EstadisticaConsumoDTO;
import Greentrvel_Modul.Crud.service.IotReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/iot-reports")
@Tag(name = "Reportes IoT", description = "Consulta de consumo de agua, energía y estadísticas obtenidas de sensores IoT")
public class IotReportController {

    private final IotReportService iotReportService;

    public IotReportController(IotReportService iotReportService) {
        this.iotReportService = iotReportService;
    }

    /**
     * Reporte completo de consumo de agua.
     */
    @GetMapping("/agua")
    @Operation(
            summary = "Obtener reporte de consumo de agua",
            description = "Retorna el consumo total, diario, semanal y mensual de agua registrado por los sensores IoT."
    )
    @ApiResponse(responseCode = "200", description = "Reporte obtenido correctamente")
    public ConsumoAguaDTO obtenerReporteAgua() {
        return iotReportService.obtenerReporteAgua();
    }

    /**
     * Reporte completo de consumo de energía.
     */
    @GetMapping("/energia")
    @Operation(
            summary = "Obtener reporte de consumo de energía",
            description = "Retorna el consumo total, diario, semanal y mensual de energía registrado por los sensores IoT."
    )
    @ApiResponse(responseCode = "200", description = "Reporte obtenido correctamente")
    public ConsumoEnergiaDTO obtenerReporteEnergia() {
        return iotReportService.obtenerReporteEnergia();
    }

    /**
     * Estadísticas generales de consumo.
     */
   @GetMapping("/estadisticas")
    @Operation(
            summary = "Obtener estadísticas generales de consumo",
            description = "Retorna estadísticas comparativas y generales del consumo de recursos (agua y energía)."
    )
    @ApiResponse(responseCode = "200", description = "Estadísticas obtenidas correctamente")
    public EstadisticaConsumoDTO obtenerEstadisticas() {
        return iotReportService.obtenerEstadisticas();
    }
}