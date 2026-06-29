package Greentrvel_Modul.Crud.controller;

import Greentrvel_Modul.Crud.dto.ConsumoAguaDTO;
import Greentrvel_Modul.Crud.dto.EstadisticaConsumoDTO;
import Greentrvel_Modul.Crud.service.IotReportService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/iot-reports")
public class IotReportController {

    private final IotReportService iotReportService;

    // Constructor tradicional para inyectar el servicio
    public IotReportController(IotReportService iotReportService) {
        this.iotReportService = iotReportService;
    }

    // Endpoint para estadísticas (Total, Promedio, Máximo, Mínimo)
    @PostMapping("/estadisticas/agua")
    public EstadisticaConsumoDTO obtenerEstadisticasAgua(@RequestBody List<ConsumoAguaDTO> lecturas) {
        return iotReportService.generarEstadisticasAgua(lecturas);
    }

    // Endpoint para consumo diario
    @PostMapping("/diario/agua")
    public double obtenerConsumoDiarioAgua(@RequestBody List<ConsumoAguaDTO> lecturas, @RequestParam String fecha) {
        return iotReportService.obtenerConsumoDiarioAgua(lecturas, fecha);
    }

    // Endpoint para consumo mensual
    @PostMapping("/mensual/agua")
    public double obtenerConsumoMensualAgua(@RequestBody List<ConsumoAguaDTO> lecturas, @RequestParam String mesAnio) {
        return iotReportService.obtenerConsumoMensualAgua(lecturas, mesAnio);
    }

    // Endpoint para consumo semanal
    @PostMapping("/semanal/agua")
    public double obtenerConsumoSemanalAgua(@RequestBody List<ConsumoAguaDTO> lecturas, @RequestParam int numeroSemana) {
        return iotReportService.obtenerConsumoSemanalAgua(lecturas, numeroSemana);
    }
}