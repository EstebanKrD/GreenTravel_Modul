package Greentrvel_Modul.Crud.repository;

import Greentrvel_Modul.Crud.entity.Reserva;
import Greentrvel_Modul.Crud.entity.Reserva.EstadoReserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {

    // Buscar reservas por estado (ACTIVA, CANCELADA, etc.)
    List<Reserva> findByEstado(EstadoReserva estado);

    // Ver todas las reservas de un usuario específico
    List<Reserva> findByUsuarioId(Long usuarioId);

    // Ver todas las reservas de un servicio específico
    List<Reserva> findByServicioId(Long servicioId);

    // Contar reservas activas (para el dashboard)
    @Query("SELECT COUNT(r) FROM Reserva r WHERE r.estado = 'ACTIVA'")
    Long countReservasActivas();

    // Contar reservas canceladas (para el dashboard)
    @Query("SELECT COUNT(r) FROM Reserva r WHERE r.estado = 'CANCELADA'")
    Long countReservasCanceladas();
}