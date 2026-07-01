package Greentrvel_Modul.Crud.service;

import Greentrvel_Modul.Crud.dto.ConsumoAguaDTO;
import Greentrvel_Modul.Crud.dto.ConsumoEnergiaDTO;
import Greentrvel_Modul.Crud.dto.EstadisticaConsumoDTO;
import Greentrvel_Modul.Crud.repository.LecturaSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

@Service
public class IotReportService {

    @Autowired
    private LecturaSensorRepository lecturaSensorRepository;

    public IotReportService() {
    }

    // ==========================================
    // 1. RESPONSABILIDAD: Consumo Total (Agua y Energía)
    // ==========================================
    public BigDecimal calcularTotalAgua(ConsumoAguaDTO dto) {
        if (dto == null || dto.getConsumoTotal() == null) return BigDecimal.ZERO;
        return dto.getConsumoTotal();
    }

    public BigDecimal calcularTotalEnergia(ConsumoEnergiaDTO dto) {
        if (dto == null || dto.getConsumoTotal() == null) return BigDecimal.ZERO;
        return dto.getConsumoTotal();
    }

    // ==========================================
    // 2, 3 y 4. RESPONSABILIDAD: Consumo Diario, Semanal y Mensual
    // ==========================================
    public BigDecimal obtenerConsumoDiarioAgua(ConsumoAguaDTO dto) {
        if (dto == null || dto.getConsumoDiario() == null) return BigDecimal.ZERO;
        return dto.getConsumoDiario();
    }

    public BigDecimal obtenerConsumoMensualAgua(ConsumoAguaDTO dto) {
        if (dto == null || dto.getConsumoMensual() == null) return BigDecimal.ZERO;
        return dto.getConsumoMensual();
    }

    public BigDecimal obtenerConsumoSemanalAgua(ConsumoAguaDTO dto) {
        if (dto == null || dto.getConsumoSemanal() == null) return BigDecimal.ZERO;
        return dto.getConsumoSemanal();
    }

    // ==========================================
    // 5, 6 y 7. RESPONSABILIDAD: Estadísticas Integradas
    // ==========================================
    public EstadisticaConsumoDTO generarEstadisticasAgua(ConsumoAguaDTO aguaDto, ConsumoEnergiaDTO energiaDto) {
        // Creamos el objeto usando el Builder que tu compañero implementó con Lombok
        return EstadisticaConsumoDTO.builder()
                // Mapeo de datos de Agua si existen
                .promedioAgua(aguaDto != null ? aguaDto.getConsumoDiario() : BigDecimal.ZERO)
                .maxAgua(aguaDto != null ? aguaDto.getConsumoTotal() : BigDecimal.ZERO)
                .minAgua(aguaDto != null ? aguaDto.getConsumoDiario() : BigDecimal.ZERO)
                
                // Mapeo de datos de Energía si existen
                .promedioEnergia(energiaDto != null ? energiaDto.getConsumoDiario() : BigDecimal.ZERO)
                .maxEnergia(energiaDto != null ? energiaDto.getConsumoTotal() : BigDecimal.ZERO)
                .minEnergia(energiaDto != null ? energiaDto.getConsumoDiario() : BigDecimal.ZERO)
                .build();
    }
}