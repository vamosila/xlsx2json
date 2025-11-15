# Testing Documentation for Excel-to-JSON GUI Project

## Project Overview
This Java GUI project reads an Excel (`.xlsx`) file containing worker interventions and outputs a JSON file summarizing adjusted durations and totals per worker. The project is implemented as a Maven project targeting **Java 1.8** and uses **Apache POI** for Excel parsing, **Gson** for JSON serialization, and **JavaFX/Swing** for the GUI.

### Main Functionalities
1. GUI to select Excel input file and output directory.
2. Parse Excel rows for worker ID, name, location, date range, report number, and duration.
3. Apply duration adjustment rules:
   - Minimum 1 hour for non-consecutive interventions.
   - Round durations >1 hour to the nearest 10 minutes.
   - Sum all adjusted durations and round totals up to 30-minute blocks.
   - Consecutive interventions are treated specially (minimum duration rule is skipped).
4. Display progress and errors in the GUI.
5. Output JSON with per-worker interventions and total adjusted hours.

---

## Test Plan

### 1. Objectives
- Verify GUI file selection works and valid files can be loaded.
- Validate correct parsing of Excel input.
- Validate duration adjustment rules.
- Ensure accurate calculation of total adjusted hours per worker.
- Confirm correct JSON structure and content.
- Check proper display of progress and error messages in GUI.

### 2. Test Environment
- Java: 1.8
- IDE: Visual Studio Code
- Build tool: Maven
- Dependencies: Apache POI, Gson, JavaFX/Swing
- OS: Cross-platform (Windows/Linux/Mac)

---

## Test Cases

| ID | Scenario | Input | Expected Output | Notes |
|----|---------|-------|----------------|-------|
| TC01 | GUI file selection | Valid Excel | File loaded successfully | File chooser works |
| TC02 | Single intervention < 1 hour | 0:45 | Adjusted: 1:00 | Rule 1 applies |
| TC03 | Single intervention > 1 hour | 1:08 | Adjusted: 1:10 | Rule 2 rounding |
| TC04 | Consecutive interventions <1 hour | 0:50 + 0:25 | Adjusted combined: 1:20 | Rule 4 skips minimum rule |
| TC05 | Total monthly hours rounding | 7:20 | Total: 7:30 | Rule 3 rounding |
| TC06 | Total monthly hours rounding | 7:40 | Total: 8:00 | Rule 3 rounding |
| TC07 | Intervention spanning midnight | 23:30–01:15 | Adjusted: 1:45 | Test overnight calculation |
| TC08 | Excel numeric worker ID | 12345 | JSON: "12345" | Ensure numeric ID handled as string |
| TC09 | Empty Excel row | empty | Skip row | Should not throw exceptions |
| TC10 | Malformed date range | "07/07/2025 11:00 - 13:00" | Skip row / log error | Parsing failure |
| TC11 | JSON output validation | Full sheet | Matches expected JSON | Validate all keys and formatting |
| TC12 | GUI error display | Invalid file | Error message displayed | GUI shows proper error |

---

## Edge Cases

1. Interventions exactly 1 hour.
2. Interventions of 0 minutes (should be rounded to 1 hour unless consecutive).
3. Interventions spanning multiple days (>24h) — currently not fully supported.
4. Excel cells with extra spaces or different separators in the date range (`"de"`, `"a"`).
5. Very large number of interventions for a single worker.
6. Cancel file selection mid-operation.

---

## Testing Steps

1. Prepare sample Excel sheets covering all test cases.
2. Launch GUI application.
3. Use file chooser to select input Excel and output directory.
4. Run conversion and monitor GUI for progress updates and errors.
5. Open generated `Example.json` and check:
   - Correct JSON format.
   - Correct adjusted durations for each intervention.
   - Correct total adjusted hours per worker.
6. Compare JSON output against expected values.
7. Test edge cases manually or using automated GUI testing tools.

---


## License & Copyright

© 2025 László Ádám Vámosi, Cordoba, Spain.  
All rights reserved.

---

# Documentación de Pruebas para el Proyecto GUI de Conversión Excel a JSON

## Descripción del Proyecto
Este proyecto Java con GUI lee un archivo Excel (`.xlsx`) que contiene intervenciones de trabajadores y genera un archivo JSON resumiendo las duraciones ajustadas y totales por trabajador. El proyecto es un proyecto **Maven** dirigido a **Java 1.8** y utiliza **Apache POI** para el análisis de Excel, **Gson** para la serialización JSON y **JavaFX/Swing** para la interfaz gráfica.

### Funcionalidades Principales
1. GUI para seleccionar archivo Excel de entrada y carpeta de salida.
2. Analizar filas de Excel para ID de trabajador, nombre, ubicación, rango de fechas, número de informe y duración.
3. Aplicar reglas de ajuste de duración:
   - Duración mínima de 1 hora para intervenciones no consecutivas.
   - Redondear duraciones >1 hora al múltiplo de 10 minutos más cercano.
   - Sumar todas las duraciones ajustadas y redondear totales al alza en bloques de 30 minutos.
   - Las intervenciones consecutivas ignoran la regla de duración mínima.
4. Mostrar progreso y errores en la GUI.
5. Generar JSON con intervenciones por trabajador y total de horas ajustadas.

---

## Plan de Pruebas

### 1. Objetivos
- Verificar que la selección de archivos en la GUI funciona y se pueden cargar archivos válidos.
- Validar análisis correcto del archivo Excel.
- Validar reglas de ajuste de duración.
- Asegurar cálculo correcto de horas ajustadas totales por trabajador.
- Confirmar estructura y contenido correcto del JSON.
- Comprobar la correcta visualización de progreso y mensajes de error en la GUI.

### 2. Entorno de Pruebas
- Java: 1.8
- IDE: Visual Studio Code
- Herramienta de construcción: Maven
- Dependencias: Apache POI, Gson, JavaFX/Swing
- SO: Multiplataforma (Windows/Linux/Mac)

---

## Casos de Prueba

| ID | Escenario | Entrada | Salida Esperada | Notas |
|----|---------|-------|----------------|-------|
| TC01 | Selección de archivo GUI | Excel válido | Archivo cargado correctamente | Selector de archivos funciona |
| TC02 | Intervención única < 1 hora | 0:45 | Ajustado: 1:00 | Aplica regla 1 |
| TC03 | Intervención única > 1 hora | 1:08 | Ajustado: 1:10 | Redondeo regla 2 |
| TC04 | Intervenciones consecutivas <1 hora | 0:50 + 0:25 | Ajuste combinado: 1:20 | Regla 4 consecutiva |
| TC05 | Redondeo de horas totales mensuales | 7:20 | Total: 7:30 | Regla 3 redondeo |
| TC06 | Redondeo de horas totales mensuales | 7:40 | Total: 8:00 | Regla 3 redondeo |
| TC07 | Intervención que cruza medianoche | 23:30–01:15 | Ajustado: 1:45 | Prueba cálculo nocturno |
| TC08 | ID de trabajador numérico en Excel | 12345 | JSON: "12345" | Asegura manejo correcto |
| TC09 | Fila vacía en Excel | vacía | Omitir fila | No debe lanzar excepción |
| TC10 | Rango de fechas mal formado | "07/07/2025 11:00 - 13:00" | Omitir / registrar error | Error de análisis |
| TC11 | Validación de salida JSON | Hoja completa | Coincide con JSON esperado | Validar todas las claves y formato |
| TC12 | Visualización de errores GUI | Archivo inválido | Mensaje de error mostrado | GUI muestra error correctamente |

---

## Casos Especiales

1. Intervenciones exactamente de 1 hora.
2. Intervenciones de 0 minutos (deben redondearse a 1 hora a menos que sean consecutivas).
3. Intervenciones que duran más de un día (>24h) — actualmente no soportadas.
4. Celdas de Excel con espacios extra o separadores diferentes en el rango de fechas (`"de"`, `"a"`).
5. Gran cantidad de intervenciones para un solo trabajador.
6. Cancelar selección de archivo a mitad de operación.

---

## Pasos de Prueba

1. Preparar hojas de Excel de muestra que cubran todos los casos de prueba.
2. Lanzar la aplicación GUI.
3. Usar el selector de archivos para elegir Excel de entrada y carpeta de salida.
4. Ejecutar conversión y monitorizar progreso y errores en GUI.
5. Abrir `Example.json` generado y verificar:
   - Formato JSON correcto.
   - Duraciones ajustadas correctas para cada intervención.
   - Total de horas ajustadas correcto por trabajador.
6. Comparar la salida JSON con los valores esperados.
7. Probar casos especiales manualmente o mediante herramientas de test GUI automatizadas.

## Licencia y Copyright

© 2025 László Ádám Vámosi, Córdoba, España.  
Todos los derechos reservados.