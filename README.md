# 🇬🇧 **xlsx2json Project – README**

## Overview
**xlsx2json** is a project that converts Excel (`.xlsx`) timesheet data into JSON format. It applies business rules such as:

- Minimum 1-hour session duration
- Rounding session durations to the nearest 10 minutes
- Merging consecutive sessions where applicable
- Rounding total monthly hours to 0.5-hour increments

This repository demonstrates multiple implementations in different programming languages and environments.

---

## Folder Structure & Implementations

### 1. `xlsx2json_html_css_js`
- **Description:** Web-based solution using HTML, CSS, and JavaScript.
- **Features:**  
  - Drag-and-drop or file input to load `.xlsx`  
  - Processes the file in the browser  
  - Generates downloadable JSON  
  - Interactive JSON viewer included  

### 2. `xlsx2json_java`
- **Description:** Command-line Java application.
- **Features:**  
  - Load `.xlsx` via terminal  
  - Output JSON to file  
  - Suitable for automated workflows  

### 3. `xlsx2json_java_gui`
- **Description:** Java GUI version using Swing.
- **Features:**  
  - User-friendly interface  
  - Load Excel, view sessions, export JSON  
  - Visual feedback of merged sessions and adjusted durations  

### 4. `xlsx2json_python`
- **Description:** Python command-line script.
- **Features:**  
  - Reads `.xlsx` using `openpyxl`  
  - Outputs JSON with all adjustments  
  - Can be integrated into scripts or pipelines  

### 5. `xlsx2json_python_gui`
- **Description:** Python GUI version (Tkinter).
- **Features:**  
  - Load Excel and view processed data  
  - Interactive table display  
  - JSON export with session details  

---

## Usage
1. Choose the preferred implementation.  
2. Follow instructions in the corresponding folder.  
3. Process Excel files and obtain JSON output.  
4. Optional: Use GUI versions for visualization.

## License & Copyright

© 2025 László Ádám Vámosi, Cordoba, Spain.  
All rights reserved.

---

# 🇪🇸 **Proyecto xlsx2json – README**

## Descripción
**xlsx2json** es un proyecto que convierte hojas de cálculo Excel (`.xlsx`) en formato JSON, aplicando reglas de negocio como:

- Duración mínima de sesión: 1 hora  
- Redondeo de duración de sesión al múltiplo de 10 minutos  
- Fusión de sesiones consecutivas si aplica  
- Redondeo de horas mensuales a incrementos de 0,5 horas

El repositorio contiene varias implementaciones en diferentes lenguajes y entornos.

---

## Estructura de Carpetas & Implementaciones

### 1. `xlsx2json_html_css_js`
- **Descripción:** Solución web con HTML, CSS y JavaScript.
- **Características:**  
  - Arrastrar y soltar o seleccionar archivo `.xlsx`  
  - Procesamiento directamente en el navegador  
  - Genera JSON descargable  
  - Incluye visor interactivo de JSON  

### 2. `xlsx2json_java`
- **Descripción:** Aplicación Java por línea de comandos.
- **Características:**  
  - Carga `.xlsx` desde la terminal  
  - Exporta JSON a archivo  
  - Ideal para automatización y pipelines  

### 3. `xlsx2json_java_gui`
- **Descripción:** Versión Java con GUI usando Swing.
- **Características:**  
  - Interfaz amigable  
  - Carga Excel, vista de sesiones y exportación JSON  
  - Muestra visualmente sesiones fusionadas y duraciones ajustadas  

### 4. `xlsx2json_python`
- **Descripción:** Script Python por línea de comandos.
- **Características:**  
  - Lee `.xlsx` con `openpyxl` 
  - Genera JSON con todas las reglas aplicadas  
  - Integrable en scripts o pipelines  

### 5. `xlsx2json_python_gui`
- **Descripción:** Versión Python con GUI (Tkinter).
- **Características:**  
  - Carga Excel y visualiza datos procesados  
  - Tabla interactiva  
  - Exportación de JSON con detalles de sesiones  

---

## Uso
1. Elegir la implementación deseada.  
2. Seguir instrucciones de la carpeta correspondiente.  
3. Procesar archivos Excel y obtener JSON.  
4. Opcional: Usar versiones GUI para visualización.

## Licencia y Copyright

© 2025 László Ádám Vámosi, Córdoba, España.  
Todos los derechos reservados.