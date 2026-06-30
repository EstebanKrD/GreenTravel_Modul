package Greentrvel_Modul.Crud.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;



@Entity                          // Le dice a JPA que esta clase es una tabla
@Table(name = "usuarios")        // Nombre de la tabla en PostgreSQL
@Data                            // Lombok: genera getters, setters, toString
@Builder                         // Lombok: permite crear objetos con patrón builder
@NoArgsConstructor               // Lombok: constructor vacío (JPA lo necesita)
@AllArgsConstructor              // Lombok: constructor con todos los campos

public class Usuario implements UserDetails{
     @Id                                                    // Es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)    // Se autogenera (1, 2, 3...)
    private Long id;

    @Column(nullable = false, length = 100)   // Campo obligatorio, máx 100 caracteres
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, unique = true, length = 150)  // No puede repetirse
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)   // Guarda el rol como texto (ADMINISTRADOR / ANFITRION)
    @Column(nullable = false)
    private Rol rol;

    @Column(nullable = false)
    @Builder.Default
    private Boolean activo = true;  // Por defecto el usuario está activo

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @Column(name = "fecha_actualizacion")
    private LocalDateTime fechaActualizacion;

    // Relación: un usuario puede tener muchas reservas
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Reserva> reservas;

    @PrePersist   // Se ejecuta automáticamente ANTES de insertar en la BD
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
        fechaActualizacion = LocalDateTime.now();
    }

    @PreUpdate    // Se ejecuta automáticamente ANTES de actualizar en la BD
    protected void onUpdate() {
        fechaActualizacion = LocalDateTime.now();
    }

    // --- Spring Security necesita estos métodos ---

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + rol.name()));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;  // El "usuario" para Spring Security es el email
    }

    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return activo; }

    // --- Enum de roles dentro de la misma clase ---
    public enum Rol {
        ADMINISTRADOR,
        ANFITRION
    }
}
