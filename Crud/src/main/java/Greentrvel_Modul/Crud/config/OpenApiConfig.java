package Greentrvel_Modul.Crud.config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "GreenTravel - Backend Módulo de Reportes",
                version = "1.0.0",
                description = "API REST para la gestión de reportes administrativos, " +
                              "consumo de recursos IoT y exportación de informes.",
                contact = @Contact(
                        name = "Equipo GreenTravel",
                        email = "equipo@greentravel.com"
                )
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "Servidor local")
        },
        security = @SecurityRequirement(name = "bearerAuth")
)
@SecurityScheme(
        name = "bearerAuth",
        description = "Token JWT obtenido en POST /auth/login. Ingrese: Bearer {token}",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
    // Toda la configuración se hace con las anotaciones de arriba
}
