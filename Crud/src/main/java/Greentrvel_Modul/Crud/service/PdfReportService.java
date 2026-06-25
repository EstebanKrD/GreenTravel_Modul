package Greentrvel_Modul.Crud.service;

import Greentrvel_Modul.Crud.dto.DashboardResponseDTO;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfReportService {

    private final DashboardService dashboardService;

    public PdfReportService(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    public byte[] generarDashboardPdf() {

        DashboardResponseDTO dashboard =
                dashboardService.obtenerResumen();

        try {

            ByteArrayOutputStream output =
                    new ByteArrayOutputStream();

            Document document = new Document();

            PdfWriter.getInstance(document, output);

            document.open();

            document.add(new Paragraph("REPORTE DASHBOARD"));
            document.add(new Paragraph(" "));

            document.add(new Paragraph(
                    "Total usuarios: "
                            + dashboard.getTotalUsuarios()));

            document.add(new Paragraph(
                    "Usuarios activos: "
                            + dashboard.getUsuariosActivos()));

            document.add(new Paragraph(
                    "Usuarios inactivos: "
                            + dashboard.getUsuariosInactivos()));

            document.add(new Paragraph(
                    "Total reservas: "
                            + dashboard.getTotalReservas()));

            document.add(new Paragraph(
                    "Reservas activas: "
                            + dashboard.getReservasActivas()));

            document.add(new Paragraph(
                    "Reservas canceladas: "
                            + dashboard.getReservasCanceladas()));

            document.add(new Paragraph(
                    "Servicios activos: "
                            + dashboard.getServiciosActivos()));

            document.add(new Paragraph(
                    "Servicios inactivos: "
                            + dashboard.getServiciosInactivos()));

            document.close();

            return output.toByteArray();

        } catch (Exception e) {
            throw new RuntimeException("Error al generar el PDF.");
        }
    }
}