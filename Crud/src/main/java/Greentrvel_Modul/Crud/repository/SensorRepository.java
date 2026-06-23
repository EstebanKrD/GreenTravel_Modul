package Greentrvel_Modul.Crud.repository;

import Greentrvel_Modul.Crud.entity.Sensor;
import Greentrvel_Modul.Crud.entity.Sensor.TipoSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Long> {

    // Buscar sensor por código único, ej: "SENS-AGUA-001"
    Optional<Sensor> findByCodigo(String codigo);

    // Buscar sensores por tipo (AGUA, ENERGIA, etc.)
    List<Sensor> findByTipo(TipoSensor tipo);

    // Buscar sensores activos o inactivos
    List<Sensor> findByActivo(Boolean activo);

    // Buscar sensores por tipo Y estado
    List<Sensor> findByTipoAndActivo(TipoSensor tipo, Boolean activo);
}