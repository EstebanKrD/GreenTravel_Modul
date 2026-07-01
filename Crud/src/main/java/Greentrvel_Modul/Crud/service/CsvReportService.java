package Greentrvel_Modul.Crud.service;

import Greentrvel_Modul.Crud.dto.ConsumoAguaDTO;
import Greentrvel_Modul.Crud.dto.ConsumoEnergiaDTO;
import Greentrvel_Modul.Crud.dto.DashboardResponseDTO;
import Greentrvel_Modul.Crud.dto.EstadisticaConsumoDTO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

@Service
public class CsvReportService {

    private final DashboardService dashboardService;
    private final IotReportService iotReportService;

    public CsvReportService(DashboardService dashboardService,
                            IotReportService iotReportService) {
        this.dashboardService = dashboardService;
        this.iotReportService = iotReportService;
    }

    public byte[] generarDashboardCsv() {

        DashboardResponseDTO dashboard = dashboardService.obtenerResumen();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (
                OutputStreamWriter writer =
                        new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

                CSVPrinter csvPrinter =
                        new CSVPrinter(writer,
                                CSVFormat.DEFAULT.withHeader(
                                        "Categoría",
                                        "Indicador",
                                        "Valor"))
        ) {

            csvPrinter.printRecord("Usuarios", "Total", dashboard.getTotalUsuarios());
            csvPrinter.printRecord("Usuarios", "Activos", dashboard.getUsuariosActivos());
            csvPrinter.printRecord("Usuarios", "Inactivos", dashboard.getUsuariosInactivos());

            csvPrinter.printRecord("Reservas", "Total", dashboard.getTotalReservas());
            csvPrinter.printRecord("Reservas", "Activas", dashboard.getReservasActivas());
            csvPrinter.printRecord("Reservas", "Canceladas", dashboard.getReservasCanceladas());

            csvPrinter.printRecord("Servicios", "Activos", dashboard.getServiciosActivos());
            csvPrinter.printRecord("Servicios", "Inactivos", dashboard.getServiciosInactivos());

            csvPrinter.flush();

        } catch (IOException e) {
            throw new RuntimeException("Error al generar el CSV del Dashboard.", e);
        }

        return outputStream.toByteArray();
    }

    public byte[] generarIotCsv() {

        ConsumoAguaDTO agua = iotReportService.obtenerReporteAgua();
        ConsumoEnergiaDTO energia = iotReportService.obtenerReporteEnergia();
        EstadisticaConsumoDTO estadisticas = iotReportService.obtenerEstadisticas();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try (
                OutputStreamWriter writer =
                        new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);

                CSVPrinter csvPrinter =
                        new CSVPrinter(writer,
                                CSVFormat.DEFAULT.withHeader(
                                        "Categoría",
                                        "Indicador",
                                        "Valor",
                                        "Unidad"))
        ) {

            csvPrinter.printRecord("Agua", "Consumo Total", agua.getConsumoTotal(), agua.getUnidad());
            csvPrinter.printRecord("Agua", "Consumo Diario", agua.getConsumoDiario(), agua.getUnidad());
            csvPrinter.printRecord("Agua", "Consumo Semanal", agua.getConsumoSemanal(), agua.getUnidad());
            csvPrinter.printRecord("Agua", "Consumo Mensual", agua.getConsumoMensual(), agua.getUnidad());

            csvPrinter.printRecord("Energía", "Consumo Total", energia.getConsumoTotal(), energia.getUnidad());
            csvPrinter.printRecord("Energía", "Consumo Diario", energia.getConsumoDiario(), energia.getUnidad());
            csvPrinter.printRecord("Energía", "Consumo Semanal", energia.getConsumoSemanal(), energia.getUnidad());
            csvPrinter.printRecord("Energía", "Consumo Mensual", energia.getConsumoMensual(), energia.getUnidad());

            csvPrinter.printRecord("Estadísticas", "Promedio Agua", estadisticas.getPromedioAgua(), "");
            csvPrinter.printRecord("Estadísticas", "Máximo Agua", estadisticas.getMaxAgua(), "");
            csvPrinter.printRecord("Estadísticas", "Mínimo Agua", estadisticas.getMinAgua(), "");

            csvPrinter.printRecord("Estadísticas", "Promedio Energía", estadisticas.getPromedioEnergia(), "");
            csvPrinter.printRecord("Estadísticas", "Máximo Energía", estadisticas.getMaxEnergia(), "");
            csvPrinter.printRecord("Estadísticas", "Mínimo Energía", estadisticas.getMinEnergia(), "");

            csvPrinter.flush();

        } catch (IOException e) {
            throw new RuntimeException("Error al generar el CSV IoT.", e);
        }

        return outputStream.toByteArray();
    }
}