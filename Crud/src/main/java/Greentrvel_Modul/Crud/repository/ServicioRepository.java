package Greentrvel_Modul.Crud.repository;

import Greentrvel_Modul.Crud.entity.Servicio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServicioRepository extends JpaRepository<Servicio, Long> {

    // Listar servicios activos o inactivos
    List<Servicio> findByActivo(Boolean activo);

    // Contar servicios activos (para el dashboard)
    @Query("SELECT COUNT(s) FROM Servicio s WHERE s.activo = true")
    Long countServiciosActivos();

    // Contar servicios inactivos (para el dashboard)
    @Query("SELECT COUNT(s) FROM Servicio s WHERE s.activo = false")
    Long countServiciosInactivos();
}