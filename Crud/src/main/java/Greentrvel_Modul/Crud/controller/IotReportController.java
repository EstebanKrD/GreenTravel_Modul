package Greentrvel_Modul.Crud.controller;

import Greentrvel_Modul.Crud.dto.ConsumoAguaDTO;
import Greentrvel_Modul.Crud.dto.ConsumoEnergiaDTO;
import Greentrvel_Modul.Crud.dto.EstadisticaConsumoDTO;
import Greentrvel_Modul.Crud.service.IotReportService;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@RestController
@RequestMapping("/api/v1/iot-reports")
public class IotReportController {

    private final IotReportService iotReportService;

    // Constructor tradicional para inyectar el servicio
    public IotReportController(IotReportService iotReportService) {
        this.iotReportService = iotReportService;
    }

    // ==========================================
    // 1. Endpoint para Estadísticas Integradas (Agua y Energía)
    // ==========================================
    @PostMapping("/estadisticas")
    public EstadisticaConsumoDTO generarEstadisticas(@RequestBody ConsumoAguaDTO aguaDto) {
        // Creamos un DTO vacío simulado de energía para cumplir con la firma del servicio de estadísticas
        ConsumoEnergiaDTO energiaSimulado = ConsumoEnergiaDTO.builder()
                .consumoTotal(BigDecimal.ZERO)
                .consumoDiario(BigDecimal.ZERO)
                .build();
                
        return iotReportService.generarEstadisticasAgua(aguaDto, energiaSimulado);
    }

    // ==========================================
    // 2. Endpoint para Consumo Diario de Agua
    // ==========================================
    @PostMapping("/diario/agua")
    public BigDecimal obtenerConsumoDiarioAgua(@RequestBody ConsumoAguaDTO dto) {
        return iotReportService.obtenerConsumoDiarioAgua(dto);
    }

    // ==========================================
    // 3. Endpoint para Consumo Mensual de Agua
    // ==========================================
    @PostMapping("/mensual/agua")
    public BigDecimal obtenerConsumoMensualAgua(@RequestBody ConsumoAguaDTO dto) {
        return iotReportService.obtenerConsumoMensualAgua(dto);
    }

    // ==========================================
    // 4. Endpoint para Consumo Semanal de Agua
    // ==========================================
    @PostMapping("/semanal/agua")
    public BigDecimal obtenerConsumoSemanalAgua(@RequestBody ConsumoAguaDTO dto) {
        return iotReportService.obtenerConsumoSemanalAgua(dto);
    }
}