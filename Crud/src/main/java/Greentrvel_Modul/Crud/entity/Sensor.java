package Greentrvel_Modul.Crud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sensores")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Código único del sensor, ej: "SENS-AGUA-001"
    @Column(nullable = false, unique = true, length = 100)
    private String codigo;

    @Column(nullable = false, length = 100)
    private String nombre;

    // Tipo: AGUA o ENERGIA (para filtrar reportes IoT)
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSensor tipo;

    @Column(length = 200)
    private String ubicacion;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;

    @Column(name = "fecha_instalacion")
    private LocalDateTime fechaInstalacion;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    // Un sensor tiene muchas lecturas
     @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
     private List<LecturaSensor> lecturas;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }

    public enum TipoSensor {
        AGUA,
        ENERGIA,
        TEMPERATURA,
        HUMEDAD
    }
}