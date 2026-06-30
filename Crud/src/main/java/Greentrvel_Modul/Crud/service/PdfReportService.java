package Greentrvel_Modul.Crud.service;

import Greentrvel_Modul.Crud.dto.ConsumoAguaDTO;
import Greentrvel_Modul.Crud.dto.ConsumoEnergiaDTO;
import Greentrvel_Modul.Crud.dto.DashboardResponseDTO;
import Greentrvel_Modul.Crud.dto.EstadisticaConsumoDTO;
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
    private final IotReportService iotReportService;

    public PdfReportService(DashboardService dashboardService,
                            IotReportService iotReportService) {
        this.dashboardService = dashboardService;
        this.iotReportService = iotReportService;
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
            document.add(new Paragraph("Total: " + dashboard.getTotalUsuarios(), contenido));
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
            throw new RuntimeException("Error al generar el PDF del dashboard.", e);
        }

        return outputStream.toByteArray();
    }

    public byte[] generarIotPdf() {

        ConsumoAguaDTO agua = iotReportService.obtenerReporteAgua();
        ConsumoEnergiaDTO energia = iotReportService.obtenerReporteEnergia();
        EstadisticaConsumoDTO estadisticas = iotReportService.obtenerEstadisticas();

        Document document = new Document();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, outputStream);

            document.open();

            Font titulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Font subtitulo = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
            Font contenido = FontFactory.getFont(FontFactory.HELVETICA, 12);

            document.add(new Paragraph("Reporte IoT", titulo));
            document.add(new Paragraph(" "));

            document.add(new Paragraph("Consumo de Agua", subtitulo));
            document.add(new Paragraph("Consumo total: " + agua.getConsumoTotal() + " " + agua.getUnidad(), contenido));
            document.add(new Paragraph("Consumo diario: " + agua.getConsumoDiario() + " " + agua.getUnidad(), contenido));
            document.add(new Paragraph("Consumo semanal: " + agua.getConsumoSemanal() + " " + agua.getUnidad(), contenido));
            document.add(new Paragraph("Consumo mensual: " + agua.getConsumoMensual() + " " + agua.getUnidad(), contenido));

            document.add(new Paragraph(" "));

            document.add(new Paragraph("Consumo de Energía", subtitulo));
            document.add(new Paragraph("Consumo total: " + energia.getConsumoTotal() + " " + energia.getUnidad(), contenido));
            document.add(new Paragraph("Consumo diario: " + energia.getConsumoDiario() + " " + energia.getUnidad(), contenido));
            document.add(new Paragraph("Consumo semanal: " + energia.getConsumoSemanal() + " " + energia.getUnidad(), contenido));
            document.add(new Paragraph("Consumo mensual: " + energia.getConsumoMensual() + " " + energia.getUnidad(), contenido));

            document.add(new Paragraph(" "));

            document.add(new Paragraph("Estadísticas Generales", subtitulo));

            document.add(new Paragraph("Promedio de agua: " + estadisticas.getPromedioAgua(), contenido));
            document.add(new Paragraph("Consumo máximo de agua: " + estadisticas.getMaxAgua(), contenido));
            document.add(new Paragraph("Consumo mínimo de agua: " + estadisticas.getMinAgua(), contenido));

            document.add(new Paragraph(" "));

            document.add(new Paragraph("Promedio de energía: " + estadisticas.getPromedioEnergia(), contenido));
            document.add(new Paragraph("Consumo máximo de energía: " + estadisticas.getMaxEnergia(), contenido));
            document.add(new Paragraph("Consumo mínimo de energía: " + estadisticas.getMinEnergia(), contenido));

            document.close();

        } catch (DocumentException e) {
            throw new RuntimeException("Error al generar el PDF IoT.", e);
        }

        return outputStream.toByteArray();
    }

}