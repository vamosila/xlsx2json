# 🇬🇧 **Testing Documentation (English)**

## Objective
Verify that both tools — **Worktime Processor** and **Worktime JSON Viewer** — work correctly, apply all business rules, and produce accurate and readable output.

---

# 1. Worktime Processor – Test Plan

## 1.1 File Input Tests
| Test Case | Description | Expected Result |
|----------|-------------|-----------------|
| Valid Excel file | Load a `.xlsx` with correct column headers | File is processed and JSON is downloaded |
| Missing required columns | Remove “Fecha de inicio y fin” or “Nº de trabajador” | Error message or incomplete rows skipped |
| Empty Excel file | No usable rows | No crash, JSON contains no workers |
| Unexpected additional columns | Extra irrelevant columns | Processor ignores them without errors |

---

## 1.2 Duration Calculation Tests

### A. Minimum 1-hour rule
- Input: a session shorter than 60 minutes (e.g., **45 minutes**)  
- Expected: **60 minutes**

### B. Rounding to nearest 10 minutes
- Input: **1h 08m**  
- Expected: **1h 10m**  

### C. Cross-midnight sessions
- Input: `23:15 → 00:30`  
- Expected: correct calculation (**75 minutes**) before rounding

### D. Consecutive session merging
Two sessions:
1. `11:10 → 13:00`  
2. `13:00 → 13:25`

- Expected:  
  - They are merged  
  - Combined duration = **1h 15m**  
  - Rounded = **1h 20m**  
  - No 1-hour minimum applied to the second session

### E. Monthly rounding (30-minute increments)
| Total Minutes | Expected |
|--------------|----------|
| 7h 20m | 7h 30m |
| 7h 40m | 8h 00m |
| 8h 01m | 8h 30m |

---

## 1.3 JSON Output Tests
Check that generated JSON contains:

- Worker ID and name
- All sessions in correct order
- Original & adjusted durations
- Total adjusted minutes
- Monthly rounded hours (0.5 increments)
- Extra fields if present

---

# 2. Worktime JSON Viewer – Test Plan

## 2.1 JSON Loading
| Test Case | Expected Result |
|-----------|-----------------|
| Load valid JSON from processor | Table appears with workers |
| Load invalid JSON | Error message “Invalid JSON file” |
| Load JSON with empty workers | Empty table, no crash |

---

## 2.2 Table Display Tests
Check:

- Worker rows show correct total hours
- “Show / Hide” button expands detailed sessions
- All session fields display correctly:
  - Date
  - Start
  - End
  - Location
  - Part number
  - Original duration
  - Adjusted duration
  - Extra fields

---

## 2.3 UI Interaction Tests
| Action | Expected |
|--------|----------|
| Repeated open/close of details | No inconsistencies |
| Load multiple JSON files consecutively | Table updates correctly |
| Large dataset (1000+ sessions) | Viewer remains responsive |

---

# 3. Final Validation (End-to-End)

1. Load Excel in Processor  
2. Download JSON  
3. Load JSON in Viewer  
4. Compare displayed results with manual calculations  
5. Confirm all business rules applied correctly  

## License & Copyright

© 2025 László Ádám Vámosi, Cordoba, Spain.  
All rights reserved.

---

# 🇪🇸 **Documentación de Pruebas (Español)**

## Objetivo
Verificar que ambas herramientas — **Procesador de Horas Laborales** y **Visor de JSON** — funcionen correctamente, apliquen todas las reglas y produzcan resultados precisos y legibles.

---

# 1. Procesador de Horas – Plan de Pruebas

## 1.1 Pruebas de Entrada de Archivo
| Caso | Descripción | Resultado esperado |
|------|-------------|--------------------|
| Archivo Excel válido | Cargar `.xlsx` con columnas correctas | Se procesa y se descarga el JSON |
| Columnas obligatorias faltan | Quitar “Fecha de inicio y fin” o “Nº de trabajador” | Mensaje de error o filas omitidas |
| Archivo vacío | Sin filas útiles | No se bloquea, JSON vacío |
| Columnas adicionales | Columnas irrelevantes | Se ignoran sin error |

---

## 1.2 Pruebas de Cálculo de Duraciones

### A. Regla del mínimo de 1 hora
- Entrada: sesión de **45 minutos**  
- Resultado: **60 minutos**

### B. Redondeo a 10 minutos
- Entrada: **1h 08m**  
- Resultado: **1h 10m**

### C. Sesiones que cruzan medianoche
- Entrada: `23:15 → 00:30`  
- Resultado: **75 minutos** antes del redondeo

### D. Fusión de sesiones consecutivas
Dos sesiones:
1. `11:10 → 13:00`  
2. `13:00 → 13:25`

- Resultado:  
  - Se fusionan  
  - Duración combinada = **1h 15m**  
  - Redondeada = **1h 20m**  
  - No se aplica la regla del mínimo de 1 hora a la segunda sesión

### E. Redondeo mensual (bloques de 30 minutos)
| Total | Esperado |
|-------|----------|
| 7h 20m | 7h 30m |
| 7h 40m | 8h 00m |
| 8h 01m | 8h 30m |

---

## 1.3 Pruebas del JSON generado
Verificar que contenga:

- ID y nombre del trabajador  
- Todas las sesiones en orden  
- Duración original y ajustada  
- Minutos ajustados totales  
- Horas mensuales redondeadas en pasos de 0,5  
- Campos extra si existen  

---

# 2. Visor de JSON – Plan de Pruebas

## 2.1 Carga de JSON
| Caso | Resultado esperado |
|------|--------------------|
| JSON válido | Muestra la tabla con trabajadores |
| JSON inválido | “Invalid JSON file” |
| JSON sin datos | Tabla vacía |

---

## 2.2 Pruebas de Visualización
Se comprueba que aparezcan:

- Trabajadores con total mensual correcto  
- Sesiones individuales  
- Bloques fusionados  
- Duraciones originales y ajustadas  
- Ubicación, fecha, Nº de parte  
- Tablas expandibles  

---

## 2.3 Pruebas de Interacción
| Acción | Resultado esperado |
|--------|--------------------|
| Abrir/cerrar detalles repetidamente | Sin fallos |
| Cargar varios JSON seguidos | La tabla se actualiza |
| Datos grandes | El visor sigue funcionando |

---

# 3. Validación Final (Extremo a Extremo)

1. Cargar Excel en el Procesador  
2. Descargar JSON  
3. Cargar JSON en el Visor  
4. Comparar resultados con cálculos manuales  
5. Confirmar que todas las reglas se aplican correctamente  

---

## Licencia y Copyright

© 2025 László Ádám Vámosi, Córdoba, España.  
Todos los derechos reservados.