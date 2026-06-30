package Greentrvel_Modul.Crud.service;

import Greentrvel_Modul.Crud.dto.DashboardResponseDTO;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
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
        DashboardResponseDTO dashboard = dashboardService.obtenerResumen();

        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, outputStream);

            document.open();

            Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font subtitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Font contenido = FontFactory.getFont(FontFactory.HELVETICA, 12);

            document.add(new Paragraph("Reporte Dashboard Administrativo", titulo));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Usuarios", subtitulo));
            document.add(new Paragraph("Total: " +dashboard.getTotalUsuarios(), contenido));
            document.add(new Paragraph("Activos: " + dashboard.getUsuariosActivos(), contenido));
            document.add(new Paragraph("Inactivos: " + dashboard.getUsuariosInactivos(), contenido));

            document.add(new Paragraph(" "));

            document.add(new Paragraph("Reservas", subtitulo));
            document.add(new Paragraph("Total: " + dashboard.getTotalReservas(), contenido));
            document.add(new Paragraph("Activas: " + dashboard.getReservasActivas(), contenido));
            document.add(new Paragraph("Canceladas: " + dashboard.getReservasCanceladas(), contenido));

            document.add(new Paragraph(" "));

            document.add(new Paragraph("Servicios", subtitulo));
            document.add(new Paragraph("Activos: " + dashboard.getServiciosActivos(), contenido));
            document.add(new Paragraph("Inactivos: " + dashboard.getServiciosInactivos(), contenido));

            document.close();

        } catch (DocumentException e) {
            throw new RuntimeException("Error al generar el PDF.", e);
        }

        return outputStream.toByteArray();
    }
}