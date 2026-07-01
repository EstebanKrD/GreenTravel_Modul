package Greentrvel_Modul.Crud.controller;

import Greentrvel_Modul.Crud.service.CsvReportService;
import Greentrvel_Modul.Crud.service.PdfReportService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/export")
public class ExportController {

    private final PdfReportService pdfReportService;
    private final CsvReportService csvReportService;

    public ExportController(PdfReportService pdfReportService,
                            CsvReportService csvReportService) {
        this.pdfReportService = pdfReportService;
        this.csvReportService = csvReportService;
    }

    @GetMapping("/pdf/dashboard")
    public ResponseEntity<byte[]> exportarDashboardPdf() {

        byte[] pdf = pdfReportService.generarDashboardPdf();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=dashboard-report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @GetMapping("/pdf/iot")
    public ResponseEntity<byte[]> exportarIotPdf() {

        byte[] pdf = pdfReportService.generarIotPdf();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=iot-report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }

    @GetMapping("/csv/dashboard")
    public ResponseEntity<byte[]> exportarDashboardCsv() {

        byte[] csv = csvReportService.generarDashboardCsv();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=dashboard-report.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csv);
    }

    @GetMapping("/csv/iot")
    public ResponseEntity<byte[]> exportarIotCsv() {

        byte[] csv = csvReportService.generarIotCsv();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=iot-report.csv")
                .contentType(MediaType.parseMediaType("text/csv"))
                .body(csv);
    }
}