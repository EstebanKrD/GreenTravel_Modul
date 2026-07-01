package Greentrvel_Modul.Crud.controller;

import Greentrvel_Modul.Crud.dto.ConsumoAguaDTO;
import Greentrvel_Modul.Crud.dto.ConsumoEnergiaDTO;
import Greentrvel_Modul.Crud.dto.EstadisticaConsumoDTO;
import Greentrvel_Modul.Crud.service.IotReportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/iot-reports")
public class IotReportController {

    private final IotReportService iotReportService;

    public IotReportController(IotReportService iotReportService) {
        this.iotReportService = iotReportService;
    }

    /**
     * Reporte completo de consumo de agua.
     */
    @GetMapping("/agua")
    public ConsumoAguaDTO obtenerReporteAgua() {
        return iotReportService.obtenerReporteAgua();
    }

    /**
     * Reporte completo de consumo de energía.
     */
    @GetMapping("/energia")
    public ConsumoEnergiaDTO obtenerReporteEnergia() {
        return iotReportService.obtenerReporteEnergia();
    }

    /**
     * Estadísticas generales de consumo.
     */
    @GetMapping("/estadisticas")
    public EstadisticaConsumoDTO obtenerEstadisticas() {
        return iotReportService.obtenerEstadisticas();
    }
}