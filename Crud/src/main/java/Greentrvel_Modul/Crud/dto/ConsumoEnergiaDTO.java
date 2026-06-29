package Greentrvel_Modul.Crud.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Reporte de consumo de energía registrado por sensores IoT")
public class ConsumoEnergiaDTO {

    @Schema(description = "Consumo total acumulado de energía", example = "12500.00")
    private BigDecimal consumoTotal;

    @Schema(description = "Consumo promedio diario de energía", example = "416.67")
    private BigDecimal consumoDiario;

    @Schema(description = "Consumo promedio semanal de energía", example = "2916.67")
    private BigDecimal consumoSemanal;

    @Schema(description = "Consumo promedio mensual de energía", example = "12500.00")
    private BigDecimal consumoMensual;

    @Schema(description = "Unidad de medida", example = "kWh")
    private String unidad;
}