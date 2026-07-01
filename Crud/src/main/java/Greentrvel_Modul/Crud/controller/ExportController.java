package Greentrvel_Modul.Crud.controller;

import Greentrvel_Modul.Crud.service.CsvReportService;
import Greentrvel_Modul.Crud.service.PdfReportService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/export")
@Tag(name = "Exportación de reportes", description = "Descarga de reportes del dashboard e IoT en formato PDF y CSV")
public class ExportController {

    private final PdfReportService pdfReportService;
    private final CsvReportService csvReportService;

    public ExportController(PdfReportService pdfReportService,
                            CsvReportService csvReportService) {
        this.pdfReportService = pdfReportService;
        this.csvReportService = csvReportService;
    }

    @GetMapping("/pdf/dashboard")
    @Operation(
            summary = "Exportar dashboard en PDF",
            description = "Genera y descarga un archivo PDF con el resumen del dashboard administrativo."
    )
    @ApiResponse(responseCode = "200", description = "PDF generado correctamente")
    @ApiResponse(responseCode = "401", description = "No autenticado")
    public ResponseEntity<byte[]> exportarDashboardPdf() {

        byte[] pdf = pdfReportService.generarDashboardPdf();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=dashboard-report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @GetMapping("/pdf/iot")
    @Operation(
            summary = "Exportar reporte IoT en PDF",
            description = "Genera y descarga un archivo PDF con el reporte de consumo de recursos IoT (agua y energía)."
    )
    @ApiResponse(responseCode = "200", description = "PDF generado correctamente")
    @ApiResponse(responseCode = "401", description = "No autenticado")
    public ResponseEntity<byte[]> exportarIotPdf() {

        byte[] pdf = pdfReportService.generarIotPdf();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=iot-report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @GetMapping("/csv/dashboard")
    @Operation(
            summary = "Exportar dashboard en CSV",
            description = "Genera y descarga un archivo CSV con el resumen del dashboard administrativo."
    )
    @ApiResponse(responseCode = "200", description = "CSV generado correctamente")
    @ApiResponse(responseCode = "401", description = "No autenticado")
    public ResponseEntity<byte[]> exportarDashboardCsv() {

        byte[] csv = csvReportService.generarDashboardCsv();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=dashboard-report.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csv);
    }

    @GetMapping("/csv/iot")
    @Operation(
            summary = "Exportar reporte IoT en CSV",
            description = "Genera y descarga un archivo CSV con el reporte de consumo de recursos IoT (agua y energía)."
    )
    @ApiResponse(responseCode = "200", description = "CSV generado correctamente")
    @ApiResponse(responseCode = "401", description = "No autenticado")
    public ResponseEntity<byte[]> exportarIotCsv() {

        byte[] csv = csvReportService.generarIotCsv();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=iot-report.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csv);
    }
}