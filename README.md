

-----

# 🛡️ CompuNet | OSINT Report System

**CompuNet** es un ecosistema de confianza digital diseñado para realizar auditorías de inteligencia avanzada[cite: 1, 3, 191]. [cite_start]Utilizando técnicas de **OSINT** (Open Source Intelligence), el sistema rastrea infraestructuras digitales para detectar activos expuestos, analizar riesgos de seguridad y generar reportes ejecutivos automatizados.

-----

## 🚀 Características Principales

**Horizon Hero Section:** Experiencia de usuario inmersiva con un motor espacial 3D desarrollado en **Three.js** y **GSAP**.
**Protocolo de Auditoría:** Escaneo profundo de dominios corporativos mediante integración con APIs de inteligencia.
  * **Capacidades Analíticas:**
      * **Detección de Activos:** Rastreo en Clear y Deep Web para identificar fugas de información.
      * **Análisis de Riesgo:** Evaluación dinámica basada en vectores de explotación reales.
      ***Reporte Ejecutivo:** Generación de certificaciones técnicas en formato PDF listas para remediación.
  * **UI/UX Premium:** Interfaz minimalista con diseño de cristal (*Glassmorphism*), tipografía **Montserrat** y componentes interactivos (*Flip Cards*).

-----

## 🛠️ Stack Tecnológico

### Backend

  * **Java 17+**
  * **Spring Boot 3.x**
  * **Spring Web:** Arquitectura MVC y REST Controllers
  * **Thymeleaf:** Motor de plantillas para vistas dinámicas y PDF

### Frontend

  * **Three.js:** Animaciones espaciales de fondo y efectos 3D.
  * **GSAP (GreenSock):** Control de animaciones basadas en scroll.
  * **Bootstrap 5:** Sistema de rejilla y utilidades CSS.
  * **Bootstrap Icons:** Paquete de iconografía técnica.

-----

## 📁 Estructura del Proyecto

```text
OSINT-REPORT-SYSTEM/
├── src/main/java/com/compunet/osint/
│   ├── controller/      # Controladores REST (ReportController)
│   ├── model/           # DTOs y Objetos de Dominio
│   ├── service/         # Lógica de negocio y Mock de servicios
│   └── util/            # Clases de utilidad
├── src/main/resources/
│   ├── static/          # Recursos estáticos (CSS, JS, Imágenes)
│   ├── templates/       # Vistas Thymeleaf (index.html, plantilla-reporte)
│   └── application.yaml # Configuración de la aplicación
└── pom.xml              # Dependencias de Maven
```

[cite_start][cite: 1]

-----

## ⚙️ Configuración e Instalación

### Requisitos previos

  * JDK 17 o superior.
  * Maven 3.6+.
  * Conexión a internet (para librerías vía CDN).

### Pasos para ejecución

1.  **Clonar el repositorio:**
    ```bash
    git clone https://github.com/tu-usuario/osint-report-system.git
    ```
2.  **Navegar al directorio:**
    ```bash
    cd osint-report-system
    ```
3.  **Ejecutar la aplicación:**
    ```bash
    ./mvnw spring-boot:run
    ```
4.  **Acceder localmente:**
    Abre tu navegador en `http://localhost:8080`

-----

## 🖥️ Uso del Sistema

1.  **Exploración:** Desliza el scroll para navegar a través del campo estelar y revelar el panel de control.
2.  **Auditoría:** Ingresa el dominio corporativo objetivo (ej. `empresa.com`) en el Altar de Escaneo.
3.  **Procesamiento:** Observa la consola de logs en tiempo real mientras el sistema compila los vectores de riesgo.
4.  **Resultado:** El sistema generará automáticamente un reporte PDF descargable con los hallazgos.

-----

## 🔒 Privacidad y Ética

Este sistema opera bajo los principios de **Hacking Ético**. Todas las consultas se realizan sobre fuentes de datos públicas y abiertas (**OSINT**), cumpliendo con los estándares de privacidad vigentes.
-----

### 👨‍💻 Autor
Bruno Valenzuela
