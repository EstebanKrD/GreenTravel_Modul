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
@Schema(description = "Reporte de consumo de agua registrado por sensores IoT")
public class ConsumoAguaDTO {

    @Schema(description = "Consumo total acumulado de agua", example = "3500.75")
    private BigDecimal consumoTotal;

    @Schema(description = "Consumo promedio diario de agua", example = "116.69")
    private BigDecimal consumoDiario;

    @Schema(description = "Consumo promedio semanal de agua", example = "816.83")
    private BigDecimal consumoSemanal;

    @Schema(description = "Consumo promedio mensual de agua", example = "3500.75")
    private BigDecimal consumoMensual;

    @Schema(description = "Unidad de medida", example = "m³")
    private String unidad;
}