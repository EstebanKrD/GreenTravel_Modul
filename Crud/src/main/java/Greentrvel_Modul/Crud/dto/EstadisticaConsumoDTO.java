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
@Schema(description = "Estadísticas generales de consumo de recursos IoT")
public class EstadisticaConsumoDTO {

    @Schema(description = "Promedio de consumo de agua", example = "116.69")
    private BigDecimal promedioAgua;

    @Schema(description = "Consumo máximo de agua", example = "250.00")
    private BigDecimal maxAgua;

    @Schema(description = "Consumo mínimo de agua", example = "50.00")
    private BigDecimal minAgua;

    @Schema(description = "Promedio de consumo de energía", example = "416.67")
    private BigDecimal promedioEnergia;

    @Schema(description = "Consumo máximo de energía", example = "800.00")
    private BigDecimal maxEnergia;

    @Schema(description = "Consumo mínimo de energía", example = "100.00")
    private BigDecimal minEnergia;
}