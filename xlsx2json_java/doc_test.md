# Testing Documentation for Excel-to-JSON Conversion Project

## Project Overview
This Java project reads an Excel (`.xlsx`) file containing worker interventions and outputs a JSON file summarizing adjusted durations and totals per worker. The project is implemented as a Maven project targeting **Java 1.8** and uses **Apache POI** for Excel parsing and **Gson** for JSON serialization.

### Main Functionalities
1. Parse Excel rows for worker ID, name, location, date range, report number, and duration.
2. Apply duration adjustment rules:
   - Minimum 1 hour for non-consecutive interventions.
   - Round durations >1 hour to the nearest 10 minutes.
   - Sum all adjusted durations and round totals up to 30-minute blocks.
   - Consecutive interventions are treated specially (minimum duration rule is skipped).
3. Output JSON with per-worker interventions and total adjusted hours.

---

## Test Plan

### 1. Objectives
- Verify correct parsing of Excel input.
- Validate duration adjustment rules (minimum 1 hour, rounding to nearest 10 minutes, consecutive interventions).
- Ensure accurate calculation of total adjusted hours per worker.
- Confirm correct JSON structure and content.

### 2. Test Environment
- Java: 1.8
- IDE: Visual Studio Code
- Build tool: Maven
- Dependencies: Apache POI, Gson
- OS: Cross-platform (Windows/Linux/Mac)

---

## Test Cases

| ID | Scenario | Input | Expected Output | Notes |
|----|---------|-------|----------------|-------|
| TC01 | Single intervention < 1 hour | 0:45 | Adjusted: 1:00 | Rule 1 applies |
| TC02 | Single intervention > 1 hour | 1:08 | Adjusted: 1:10 | Rule 2 rounding |
| TC03 | Consecutive interventions <1 hour | 0:50 + 0:25 | Adjusted combined: 1:20 | Rule 4 consecutive skips minimum rule |
| TC04 | Total monthly hours rounding | 7:20 | Total: 7:30 | Rule 3 rounding to 30-min block |
| TC05 | Total monthly hours rounding | 7:40 | Total: 8:00 | Rule 3 rounding |
| TC06 | Intervention spanning midnight | 23:30–01:15 | Adjusted: 1:45 | Test overnight calculation |
| TC07 | Excel numeric worker ID | 12345 | JSON: "12345" | Ensure numeric ID handled as string |
| TC08 | Empty Excel row | empty | Skip row | Should not throw exceptions |
| TC09 | Malformed date range | "07/07/2025 11:00 - 13:00" | Skip row / log error | Parsing failure |
| TC10 | JSON output validation | Full sheet | Matches expected JSON | Validate all keys and formatting |

---

## Edge Cases

1. Interventions exactly 1 hour.
2. Interventions of 0 minutes (should be rounded to 1 hour unless consecutive).
3. Interventions spanning multiple days (>24h) — currently not fully supported.
4. Excel cells with extra spaces or different separators in the date range (`"de"`, `"a"`).
5. Very large number of interventions for a single worker.

---

## Testing Steps

1. Prepare sample Excel sheets covering all test cases.
2. Run the `Main` class in VSCode or via Maven `mvn exec:java`.
3. Verify the console log for errors.
4. Open generated `Example.json` and check:
   - Correct JSON format.
   - Correct adjusted durations for each intervention.
   - Correct total adjusted hours per worker.
5. Compare JSON output against expected values.
6. Test edge cases manually or using unit tests.

---

## License & Copyright

© 2025 László Ádám Vámosi, Cordoba, Spain.  
All rights reserved.

---

# Documentación de Pruebas para el Proyecto de Conversión Excel a JSON

## Descripción del Proyecto
Este proyecto Java lee un archivo Excel (`.xlsx`) que contiene intervenciones de trabajadores y genera un archivo JSON resumiendo las duraciones ajustadas y totales por trabajador. El proyecto es un proyecto **Maven** dirigido a **Java 1.8** y utiliza **Apache POI** para el análisis de Excel y **Gson** para la serialización JSON.

### Funcionalidades Principales
1. Analizar filas de Excel para ID de trabajador, nombre, ubicación, rango de fechas, número de informe y duración.
2. Aplicar reglas de ajuste de duración:
   - Duración mínima de 1 hora para intervenciones no consecutivas.
   - Redondear duraciones >1 hora al múltiplo de 10 minutos más cercano.
   - Sumar todas las duraciones ajustadas y redondear totales al alza en bloques de 30 minutos.
   - Las intervenciones consecutivas ignoran la regla de duración mínima.
3. Generar JSON con intervenciones por trabajador y total de horas ajustadas.

---

## Plan de Pruebas

### 1. Objetivos
- Verificar el análisis correcto del archivo Excel.
- Validar las reglas de ajuste de duración (mínimo 1 hora, redondeo a múltiplos de 10 minutos, intervenciones consecutivas).
- Asegurar cálculo correcto de horas ajustadas totales por trabajador.
- Confirmar estructura y contenido correcto del JSON.

### 2. Entorno de Pruebas
- Java: 1.8
- IDE: Visual Studio Code
- Herramienta de construcción: Maven
- Dependencias: Apache POI, Gson
- SO: Multiplataforma (Windows/Linux/Mac)

---

## Casos de Prueba

| ID | Escenario | Entrada | Salida Esperada | Notas |
|----|---------|-------|----------------|-------|
| TC01 | Intervención única < 1 hora | 0:45 | Ajustado: 1:00 | Aplica regla 1 |
| TC02 | Intervención única > 1 hora | 1:08 | Ajustado: 1:10 | Redondeo regla 2 |
| TC03 | Intervenciones consecutivas <1 hora | 0:50 + 0:25 | Ajuste combinado: 1:20 | Regla 4 consecutiva |
| TC04 | Redondeo de horas totales mensuales | 7:20 | Total: 7:30 | Regla 3 redondeo |
| TC05 | Redondeo de horas totales mensuales | 7:40 | Total: 8:00 | Regla 3 redondeo |
| TC06 | Intervención que cruza medianoche | 23:30–01:15 | Ajustado: 1:45 | Prueba cálculo nocturno |
| TC07 | ID de trabajador numérico en Excel | 12345 | JSON: "12345" | Asegura manejo correcto |
| TC08 | Fila vacía en Excel | vacía | Omitir fila | No debe lanzar excepción |
| TC09 | Rango de fechas mal formado | "07/07/2025 11:00 - 13:00" | Omitir / registrar error | Error de análisis |
| TC10 | Validación de salida JSON | Hoja completa | Coincide con JSON esperado | Validar todas las claves y formato |

---

## Casos Especiales

1. Intervenciones exactamente de 1 hora.
2. Intervenciones de 0 minutos (deben redondearse a 1 hora a menos que sean consecutivas).
3. Intervenciones que duran más de un día (>24h) — actualmente no soportadas.
4. Celdas de Excel con espacios extra o separadores diferentes en el rango de fechas (`"de"`, `"a"`).
5. Gran cantidad de intervenciones para un solo trabajador.

---

## Pasos de Prueba

1. Preparar hojas de Excel de muestra que cubran todos los casos de prueba.
2. Ejecutar la clase `Main` en VSCode o mediante Maven `mvn exec:java`.
3. Revisar la consola para detectar errores.
4. Abrir `Example.json` generado y verificar:
   - Formato JSON correcto.
   - Duraciones ajustadas correctas para cada intervención.
   - Total de horas ajustadas correcto por trabajador.
5. Comparar la salida JSON con los valores esperados.
6. Probar casos especiales manualmente o mediante tests unitarios.

## Licencia y Copyright

© 2025 László Ádám Vámosi, Córdoba, España.  
Todos los derechos reservados.