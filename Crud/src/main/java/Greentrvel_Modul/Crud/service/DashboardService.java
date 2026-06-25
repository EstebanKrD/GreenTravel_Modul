package Greentrvel_Modul.Crud.service;

import Greentrvel_Modul.Crud.dto.DashboardResponseDTO;
import Greentrvel_Modul.Crud.repository.ReservaRepository;
import Greentrvel_Modul.Crud.repository.ServicioRepository;
import Greentrvel_Modul.Crud.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

/*
 Servicio encargado de consolidar las estadísticas del dashboard administrativo.
 Consulta los repositorios de usuarios, reservas y servicios para armar el resumen.

 */
@Service
public class DashboardService {

    private final UsuarioRepository usuarioRepository;
    private final ReservaRepository reservaRepository;
    private final ServicioRepository servicioRepository;

    // Constructor manual (sin @Autowired, Spring lo inyecta automáticamente)
    public DashboardService(UsuarioRepository usuarioRepository,
                            ReservaRepository reservaRepository,
                            ServicioRepository servicioRepository) {
        this.usuarioRepository = usuarioRepository;
        this.reservaRepository = reservaRepository;
        this.servicioRepository = servicioRepository;
    }


    public Long getTotalUsuarios() {
        return usuarioRepository.count();
    }

    public Long getUsuariosActivos() {
        return usuarioRepository.countUsuariosActivos();
    }


    public Long getUsuariosInactivos() {
        return usuarioRepository.countUsuariosInactivos();
    }


    public Long getTotalReservas() {
        return reservaRepository.count();
    }

    public Long getReservasActivas() {
        return reservaRepository.countReservasActivas();
    }

    /
    public Long getReservasCanceladas() {
        return reservaRepository.countReservasCanceladas();
    }


    public Long getServiciosActivos() {
        return servicioRepository.countServiciosActivos();
    }

    public Long getServiciosInactivos() {
        return servicioRepository.countServiciosInactivos();
    }


    public DashboardResponseDTO obtenerResumen() {
        return DashboardResponseDTO.builder()
                .totalUsuarios(getTotalUsuarios())
                .usuariosActivos(getUsuariosActivos())
                .usuariosInactivos(getUsuariosInactivos())
                .totalReservas(getTotalReservas())
                .reservasActivas(getReservasActivas())
                .reservasCanceladas(getReservasCanceladas())
                .serviciosActivos(getServiciosActivos())
                .serviciosInactivos(getServiciosInactivos())
                .build();
    }
}