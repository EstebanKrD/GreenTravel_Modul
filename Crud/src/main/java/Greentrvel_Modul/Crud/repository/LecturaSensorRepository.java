package Greentrvel_Modul.Crud.repository;

import Greentrvel_Modul.Crud.entity.LecturaSensor;
import Greentrvel_Modul.Crud.entity.Sensor.TipoSensor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface LecturaSensorRepository extends JpaRepository<LecturaSensor, Long> {

    // Todas las lecturas de un sensor específico
    List<LecturaSensor> findBySensorId(Long sensorId);

    // Lecturas filtradas por tipo de sensor (AGUA o ENERGIA)
    List<LecturaSensor> findBySensor_Tipo(TipoSensor tipo);

    // Lecturas por tipo y rango de fechas (diario, semanal, mensual)
    List<LecturaSensor> findBySensor_TipoAndFechaLecturaBetween(
            TipoSensor tipo,
            LocalDateTime inicio,
            LocalDateTime fin);

    // Consumo TOTAL acumulado por tipo de sensor
    @Query("SELECT COALESCE(SUM(l.valor), 0) FROM LecturaSensor l WHERE l.sensor.tipo = :tipo")
    BigDecimal sumConsumoTotal(@Param("tipo") TipoSensor tipo);

    // Consumo PROMEDIO por tipo de sensor
    @Query("SELECT COALESCE(AVG(l.valor), 0) FROM LecturaSensor l WHERE l.sensor.tipo = :tipo")
    BigDecimal avgConsumo(@Param("tipo") TipoSensor tipo);

    // Consumo MÁXIMO registrado por tipo
    @Query("SELECT COALESCE(MAX(l.valor), 0) FROM LecturaSensor l WHERE l.sensor.tipo = :tipo")
    BigDecimal maxConsumo(@Param("tipo") TipoSensor tipo);

    // Consumo MÍNIMO registrado por tipo
    @Query("SELECT COALESCE(MIN(l.valor), 0) FROM LecturaSensor l WHERE l.sensor.tipo = :tipo")
    BigDecimal minConsumo(@Param("tipo") TipoSensor tipo);

    // Consumo en un rango de fechas (para calcular diario/semanal/mensual)
    @Query("SELECT COALESCE(SUM(l.valor), 0) FROM LecturaSensor l " +
            "WHERE l.sensor.tipo = :tipo AND l.fechaLectura BETWEEN :inicio AND :fin")
    BigDecimal sumConsumoPorRango(
            @Param("tipo") TipoSensor tipo,
            @Param("inicio") LocalDateTime inicio,
            @Param("fin") LocalDateTime fin);
}