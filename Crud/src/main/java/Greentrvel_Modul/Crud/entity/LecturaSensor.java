package Greentrvel_Modul.Crud.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "lecturas_sensor")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LecturaSensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Muchas lecturas pertenecen a un sensor
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sensor_id", nullable = false)
    private Sensor sensor;

    // El valor medido, ej: 12.5 kWh o 3.2 m³
    @Column(nullable = false, precision = 12, scale = 4)
    private BigDecimal valor;

    // Unidad de medida, ej: "kWh", "m³"
    @Column(length = 20)
    private String unidad;

    @Column(name = "fecha_lectura", nullable = false)
    private LocalDateTime fechaLectura;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        if (fechaLectura == null) {
            fechaLectura = LocalDateTime.now();
        }
    }
}