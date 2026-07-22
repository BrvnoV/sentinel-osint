# 🛡️ Sentinel OSINT — Asset Exposure & Intelligence System

[![Java](https://img.shields.io/badge/Java-17+-007396?style=flat&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?style=flat&logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![Three.js](https://img.shields.io/badge/Frontend-Three.js%20%2B%20GSAP-black?style=flat&logo=three.js&logoColor=white)](https://threejs.org/)
[![Security](https://img.shields.io/badge/Category-Cybersecurity%20%2F%20OSINT-red?style=flat)](https://en.wikipedia.org/wiki/Open-source_intelligence)
[![License](https://img.shields.io/badge/License-MIT-green.svg)](LICENSE)

**Sentinel OSINT** es una plataforma web para la auditoría de inteligencia digital y análisis de exposición en fuentes abiertas. A través de técnicas de **OSINT (Open Source Intelligence)**, el sistema permite rastrear dominios corporativos, detectar activos o credenciales expuestas y evaluar vectores de riesgo, generando reportes ejecutivos en PDF optimizados para equipos de remediación y ciberseguridad.

---

## 📸 Vista Previa e Interfaz

| Hero Section 3D (Three.js) | Panel de Auditoría OSINT | Reporte Ejecutivo Generado |
| :---: | :---: | :---: |
| *(Agrega imagen aquí)* | *(Agrega imagen aquí)* | *(Agrega imagen aquí)* |

---

## ✨ Características Principales

* 🌌 **Experiencia Inmersiva (Hero Section):** Motor espacial 3D desarrollado con **Three.js** y animaciones controladas por scroll mediante **GSAP**.
* 🔍 **Escaneo y Detección de Activos:** Integración para el procesamiento y análisis de exposición en fuentes públicas y bases de inteligencia.
* 📊 **Evaluación Dinámica de Riesgo:** Análisis automatizado basado en vectores de explotación y vulnerabilidades conocidas.
* 📄 **Generación de Reportes Ejecutivos:** Compilación de certificados e informes técnicos en formato PDF mediante el motor de plantillas **Thymeleaf**.
* 💎 **UI/UX Premium:** Interfaz responsive basada en estilo *Glassmorphism* (diseño de cristal), Bootstrap 5 y tipografía técnica.

---

## 🛠️ Stack Tecnológico

### Backend
- **Lenguaje:** Java 17+
- **Framework:** Spring Boot 3.x (Spring Web, Spring MVC)
- **Motor de Plantillas:** Thymeleaf (renderizado de vistas y generación de documentos PDF)
- **Gestión de Dependencias:** Maven

### Frontend
- **3D & Renderizado:** Three.js
- **Animaciones:** GSAP (GreenSock Animation Platform)
- **Framework CSS:** Bootstrap 5 & Bootstrap Icons
- **Diseño:** Glassmorphism + CSS3 avanzado

---

## 🏗️ Estructura del Proyecto

```text
src/
├── main/
│   ├── java/com/compunet/osint/
│   │   ├── controller/      # Controladores REST (ReportController, etc.)
│   │   ├── model/           # DTOs y Objetos de Dominio
│   │   ├── service/         # Lógica de negocio e integración con APIs
│   │   └── util/            # Helpers y utilidades para generación de PDF
│   └── resources/
│       ├── static/          # Assets estáticos (CSS, JS 3D, imágenes)
│       ├── templates/       # Vistas Thymeleaf e impresiones PDF
│       └── application.yaml # Configuración de la aplicación
└── pom.xml                  # Archivo de configuración Maven
