package Greentrvel_Modul.Crud.service;

import org.springframework.stereotype.Service;

@Service
public class PdfReportService {

    private final DashboardService dashboardService;

    public PdfReportService(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

}
