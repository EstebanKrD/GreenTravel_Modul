package Greentrvel_Modul.Crud.service;

import Greentrvel_Modul.Crud.dto.ConsumoAguaDTO;
import Greentrvel_Modul.Crud.dto.ConsumoEnergiaDTO;
import Greentrvel_Modul.Crud.dto.EstadisticaConsumoDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IotReportService {

    public IotReportService() {
    }

    // ==========================================
    // 1. RESPONSABILIDAD: Consumo Total
    // ==========================================
    public double calcularTotalAgua(List<ConsumoAguaDTO> lecturas) {
        if (lecturas == null || lecturas.isEmpty()) return 0.0;
        double total = 0.0;
        for (ConsumoAguaDTO dto : lecturas) {
            total += dto.getCantidadLitros();
        }
        return total;
    }

    public double calcularTotalEnergia(List<ConsumoEnergiaDTO> lecturas) {
        if (lecturas == null || lecturas.isEmpty()) return 0.0;
        double total = 0.0;
        for (ConsumoEnergiaDTO dto : lecturas) {
            total += dto.getCantidadKwh();
        }
        return total;
    }

    // ==========================================
    // 2, 3 y 4. RESPONSABILIDAD: Consumo Diario, Semanal y Mensual
    // ==========================================
    public double obtenerConsumoDiarioAgua(List<ConsumoAguaDTO> lecturas, String fecha) {
        double total = 0.0;
        for (ConsumoAguaDTO dto : lecturas) {
            if (dto.getFecha().equals(fecha)) total += dto.getCantidadLitros();
        }
        return total;
    }

    public double obtenerConsumoMensualAgua(List<ConsumoAguaDTO> lecturas, String mesAnio) {
        double total = 0.0;
        for (ConsumoAguaDTO dto : lecturas) {
            if (dto.getFecha().startsWith(mesAnio)) total += dto.getCantidadLitros();
        }
        return total;
    }

    public double obtenerConsumoSemanalAgua(List<ConsumoAguaDTO> lecturas, int numeroSemana) {
        // Filtro lógico por rango de días (se completará con la persistencia)
        return 0.0;
    }

    // ==========================================
    // 5, 6 y 7. RESPONSABILIDAD: Promedio, Máximo y Mínimo (Estadísticas)
    // ==========================================
    public EstadisticaConsumoDTO generarEstadisticasAgua(List<ConsumoAguaDTO> lecturas) {
        if (lecturas == null || lecturas.isEmpty()) {
            return new EstadisticaConsumoDTO(0.0, 0.0, 0.0, 0.0);
        }

        double total = calcularTotalAgua(lecturas);
        double promedio = total / lecturas.size();
        double maximo = Double.MIN_VALUE;
        double minimo = Double.MAX_VALUE;

        for (ConsumoAguaDTO dto : lecturas) {
            if (dto.getCantidadLitros() > maximo) maximo = dto.getCantidadLitros();
            if (dto.getCantidadLitros() < minimo) minimo = dto.getCantidadLitros();
        }

        return new EstadisticaConsumoDTO(total, promedio, maximo, minimo);
    }
}