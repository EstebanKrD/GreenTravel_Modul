package Greentrvel_Modul.Crud.service;

import Greentrvel_Modul.Crud.dto.ConsumoAguaDTO;
import Greentrvel_Modul.Crud.dto.ConsumoEnergiaDTO;
import Greentrvel_Modul.Crud.dto.EstadisticaConsumoDTO;
import Greentrvel_Modul.Crud.entity.Sensor.TipoSensor;
import Greentrvel_Modul.Crud.repository.LecturaSensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class IotReportService {

    @Autowired
    private LecturaSensorRepository lecturaSensorRepository;

    public IotReportService() {
    }


    // 1. RESPONSABILIDAD: Consumo Total (Agua y Energía)

    public BigDecimal calcularTotalAgua() {
        return lecturaSensorRepository.sumConsumoTotal(TipoSensor.AGUA);
    }

    public BigDecimal calcularTotalEnergia() {
        return lecturaSensorRepository.sumConsumoTotal(TipoSensor.ENERGIA);
    }


    // 2, 3 y 4. RESPONSABILIDAD: Consumo Diario, Semanal y Mensual

    public BigDecimal obtenerConsumoDiarioAgua() {
        LocalDateTime fin = LocalDateTime.now();
        LocalDateTime inicio = fin.toLocalDate().atStartOfDay();
        return lecturaSensorRepository.sumConsumoPorRango(TipoSensor.AGUA, inicio, fin);
    }

    public BigDecimal obtenerConsumoSemanalAgua() {
        LocalDateTime fin = LocalDateTime.now();
        LocalDateTime inicio = fin.minusDays(7);
        return lecturaSensorRepository.sumConsumoPorRango(TipoSensor.AGUA, inicio, fin);
    }

    public BigDecimal obtenerConsumoMensualAgua() {
        LocalDateTime fin = LocalDateTime.now();
        LocalDateTime inicio = fin.minusDays(30);
        return lecturaSensorRepository.sumConsumoPorRango(TipoSensor.AGUA, inicio, fin);
    }

    public BigDecimal obtenerConsumoDiarioEnergia() {
        LocalDateTime fin = LocalDateTime.now();
        LocalDateTime inicio = fin.toLocalDate().atStartOfDay();
        return lecturaSensorRepository.sumConsumoPorRango(TipoSensor.ENERGIA, inicio, fin);
    }

    public BigDecimal obtenerConsumoSemanalEnergia() {
        LocalDateTime fin = LocalDateTime.now();
        LocalDateTime inicio = fin.minusDays(7);
        return lecturaSensorRepository.sumConsumoPorRango(TipoSensor.ENERGIA, inicio, fin);
    }

    public BigDecimal obtenerConsumoMensualEnergia() {
        LocalDateTime fin = LocalDateTime.now();
        LocalDateTime inicio = fin.minusDays(30);
        return lecturaSensorRepository.sumConsumoPorRango(TipoSensor.ENERGIA, inicio, fin);
    }


    public ConsumoAguaDTO obtenerReporteAgua() {
        return ConsumoAguaDTO.builder()
                .consumoTotal(calcularTotalAgua())
                .consumoDiario(obtenerConsumoDiarioAgua())
                .consumoSemanal(obtenerConsumoSemanalAgua())
                .consumoMensual(obtenerConsumoMensualAgua())
                .unidad("m³")
                .build();
    }

    public ConsumoEnergiaDTO obtenerReporteEnergia() {
        return ConsumoEnergiaDTO.builder()
                .consumoTotal(calcularTotalEnergia())
                .consumoDiario(obtenerConsumoDiarioEnergia())
                .consumoSemanal(obtenerConsumoSemanalEnergia())
                .consumoMensual(obtenerConsumoMensualEnergia())
                .unidad("kWh")
                .build();
    }


    // 5, 6 y 7. RESPONSABILIDAD: Estadísticas Integradas (promedio, máx, mín)

    public EstadisticaConsumoDTO obtenerEstadisticas() {
        return EstadisticaConsumoDTO.builder()
                // Estadísticas de Agua, calculadas directamente en BD
                .promedioAgua(lecturaSensorRepository.avgConsumo(TipoSensor.AGUA))
                .maxAgua(lecturaSensorRepository.maxConsumo(TipoSensor.AGUA))
                .minAgua(lecturaSensorRepository.minConsumo(TipoSensor.AGUA))

                // Estadísticas de Energía, calculadas directamente en BD
                .promedioEnergia(lecturaSensorRepository.avgConsumo(TipoSensor.ENERGIA))
                .maxEnergia(lecturaSensorRepository.maxConsumo(TipoSensor.ENERGIA))
                .minEnergia(lecturaSensorRepository.minConsumo(TipoSensor.ENERGIA))
                .build();
    }
}