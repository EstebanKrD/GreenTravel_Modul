package Greentrvel_Modul.Crud.service;

import org.springframework.stereotype.Service;

@Service
public class CsvReportService {

    private final DashboardService dashboardService;

    public CsvReportService(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

}