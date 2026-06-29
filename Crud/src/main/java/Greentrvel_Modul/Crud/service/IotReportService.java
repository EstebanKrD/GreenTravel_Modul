package Greentrvel_Modul.Crud.service;

import Greentrvel_Modul.Crud.dto.ConsumoAguaDTO;
import Greentrvel_Modul.Crud.dto.ConsumoEnergiaDTO;
import Greentrvel_Modul.Crud.dto.EstadisticaConsumoDTO;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class IotReportService {

    // Constructor tradicional vacío por ahora (luego inyectaremos el repositorio aquí)
    public IotReportService() {
    }

    /**
     * Calcula las estadísticas completas procesando una lista de consumos.
     * Aquí se cumple tu responsabilidad de: Consumo Total, Promedio, Máximo y Mínimo.
     */
    public EstadisticaConsumoDTO calcularEstadisticasAgua(List<ConsumoAguaDTO> listaConsumos) {
        if (listaConsumos == null || listaConsumos.isEmpty()) {
            return new EstadisticaConsumoDTO(0.0, 0.0, 0.0, 0.0);
        }

        double total = 0.0;
        double maximo = Double.MIN_VALUE;
        double minimo = Double.MAX_VALUE;

        for (ConsumoAguaDTO consumo : listaConsumos) {
            double cantidad = consumo.getCantidadLitros();
            total += cantidad;
            
            if (cantidad > maximo) {
                maximo = cantidad;
            }
            if (cantidad < minimo) {
                minimo = cantidad;
            }
        }

        double promedio = total / listaConsumos.size();

        // Retornamos el DTO tradicional con los cálculos matemáticos listos
        return new EstadisticaConsumoDTO(total, promedio, maximo, minimo);
    }

    /**
     * Lógica para obtener el consumo de un día específico (Consumo Diario)
     */
    public double obtenerConsumoDiarioAgua(List<ConsumoAguaDTO> listaConsumos, String fecha) {
        double totalDia = 0.0;
        for (ConsumoAguaDTO consumo : listaConsumos) {
            if (consumo.getFecha().equals(fecha)) {
                totalDia += consumo.getCantidadLitros();
            }
        }
        return totalDia;
    }

    /**
     * Lógica para obtener el consumo agrupado por semanas (Consumo Semanal)
     */
    public double obtenerConsumoSemanalAgua(List<ConsumoAguaDTO> listaConsumos, int numeroSemana) {
        // En el futuro aquí filtraremos las fechas que correspondan a la semana seleccionada
        return 0.0;
    }

    /**
     * Lógica para obtener el consumo agrupado por meses (Consumo Mensual)
     */
    public double obtenerConsumoMensualAgua(List<ConsumoAguaDTO> listaConsumos, String mesAnio) {
        double totalMes = 0.0;
        for (ConsumoAguaDTO consumo : listaConsumos) {
            // Ejemplo: si la fecha empieza por "2026-06", sumamos el consumo de ese mes
            if (consumo.getFecha().startsWith(mesAnio)) {
                totalMes += consumo.getCantidadLitros();
            }
        }
        return totalMes;
    }
}