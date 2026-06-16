**# Módulo de Telemetría y Monitoreo IoT (Capa Backend) - GreenTravel**  
   
**Este repositorio contiene la lógica de negocio, arquitectura de servicios y capa de datos (Backend) diseñada para el procesamiento asincrónico y persistencia de datos de telemetría IoT del ecosistema GreenTravel. El sistema está estructurado bajo el par** **adigma de Programación Orientada a Objetos (POO) utilizando Java y un Sistema de Gestión de Bases de Datos Relacionales (RDBMS) soportado en SQL.**  
   
**El módulo backend se encarga de la captura de tramas de datos analíticos, la validación de reglas de negocio ambientales, el control y despacho de alertas críticas, y la exposición de servicios de persistencia para el histórico de los sensores.**  
   
**---**  
   
**Stack Tecnológico del Backend**  
   
*** Lenguaje de Programación: Java (Object-Oriented Programming) enfocado en modularidad y control de excepciones lógicas.**  
*** Gestión de Base de Datos: SQL para la persistencia, normalización relacional y consumo de logs de eventos.**  
*** Arquitectura: Diseño basado en capas (Servicios, Repositorios/DAO y Controladores de Excepciones).**  
   
**---**  
   
**Arquitectura de Datos y Persistencia SQL**  
   
**El backend gestiona el almacenamiento relacional de las lecturas mediante un esquema SQL normalizado. Las entidades principales mapeadas para dar soporte a las interfaces de monitoreo analítico son:**  
   
1. **Entidad Sensores: Almacena el identificador único físico del nodo, el tipo de variable analizada (H2O, Energía Solar, Humedad, Temperatura) y su estado de conectividad en red (Active / Offline).**  
   
2. **Entidad Lecturas** ** ** **Telemetria: Registra el flujo asincrónico de métricas cuantitativas indexadas por fecha y hora (Timestamp) para los histogramas de consumo de las últimas 24 horas.**  
**    *** ** ** **Sensor H2O-004: Mapeo numérico de flujo de agua potable en litros por minuto (L/min).**  
**    *** ** ** **Sensor NRG-881: Registro de generación de energía solar fotovoltaica en kilovatios-hora (kW/h).**  
**    * Sensor HUM-210: Registro del porcentaje de humedad relativa del ambiente.**  
**3.** ** ** **Entidad Alertas** ** ** **Criticas: Tabla relacional encargada del registro y auditoría de anomalías críticas (ej. eventos de tipo fugas de agua). ** **Almacena el estado de la alerta, el timestamp de activación y captura la firma del usuario con rol autorizado ** **que ejecuta las acciones de mitigación como `Cerrar Válvula de Agua` o `Revisar Programación`.**  
   
**---**  
   
**Componentes Lógicos del Backend (Java)**  
   
**La capa lógica desarrollada en Java gestiona de forma robusta las siguientes reglas del negocio:**  
**1. Motor de Evaluación de Reglas e Inteligencia Predictiva**  
***   Contiene la lógica algorítmica encargada de analizar los datos de entrada de los sensores versus los parámetros de umbrales máximos tolerados.**  
***   Controla el disparo automático de excepciones operativas cuando una métrica excede el promedio histórico establecido (ej. incremento de consumo hídrico inusual en un 250% en zonas de cabañas).**  
***   Procesa datos externos integrados (como pronósticos climatológicos regionales por municipio) para despachar respuestas predictivas asincrónicas encargadas de posponer o suspender tareas automatizadas (ej. sistemas de riego en Salento).**  
**2. Gestión de Tolerancia a Fallos y Excepciones**  
- **Excepciones de Conectividad (Error 500):** Bloque lógico diseñado para capturar de forma segura las interrupciones de comunicación entre el servidor de aplicaciones, las APIs expuestas y el servidor de la base de datos SQL. Evita bloqueos en cascada o** ** corrupción de memoria en hilos concurrentes, emitiendo códigos de estado estandarizados ante caídas de red.**  
- **Control de Pérdida de Señal (Sensores Offline):** Hilo de ejecución pasivo (Background Worker) que monitorea las solicitudes de ping de los nodos físicos (ej. *Sensor TMP-009*); al no recibir respuesta en la ventana de tiempo delimitada, conmuta el es** **tado de la entidad en la base de datos a un valor nulo/deshabilitado y genera un log de advertencia en el sistema.**  
**3. Servicio de Generación de Reportes Consolidados**  
- **Componente encargado de realizar las consultas estructuradas de agregación en la base de datos SQL (promedios analíticos, índices de cumplimiento normativo por municipios y volúmenes de residuos gestionados).**  
- **Dispone de la lógica de acoplamiento de streams de datos para exportar de manera estructurada los resultados calculados en un documento legible en formato PDF para el Administrador del ** **S** **istema** **.**  
