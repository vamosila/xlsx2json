# Excel-to-JSON Worker Intervention Processor – Testing Documentation

## English

### 1. Overview

This document describes the testing procedures for the Excel-to-JSON Worker Intervention Processor script. The goal is to ensure that the script correctly:

- Reads Excel input files.
- Parses date ranges accurately.
- Applies rounding rules consistently.
- Detects consecutive interventions.
- Outputs valid JSON files.
- Handles errors gracefully.

---

### 2. Test Environment

- **Python Version:** 3.x  
- **Libraries:** `openpyxl`  
- **OS:** Windows, macOS, or Linux  
- **Test Files:** Excel files with various worker intervention scenarios

---

### 3. Test Cases

| Test Case ID | Description | Input | Expected Output | Notes |
|--------------|------------|-------|----------------|-------|
| TC1 | Valid single intervention | Excel row: 02/07/2025 de 08:00 a 09:15 | JSON shows 1:15 adjusted according to rounding rules | Checks basic parsing and rounding |
| TC2 | Consecutive interventions | Two rows, consecutive times | JSON marks second as `consecutive_with_previous=True` | Checks consecutive detection |
| TC3 | Intervention crossing midnight | 23:00 to 01:00 | JSON shows duration as 2 hours | Checks date rollover logic |
| TC4 | Less than 1 hour, non-consecutive | 30-minute intervention | Rounded to 1 hour | Checks minimum duration rule |
| TC5 | Missing Excel file | Nonexistent file path | Script exits with error message | Checks file existence handling |
| TC6 | Corrupt Excel file | Invalid Excel format | Script exits with error message | Checks exception handling |

---

### 4. Test Procedure

1. Prepare the test Excel file with required rows.  
2. Run the script:

```
python xlsx_to_json.py
```


3. Verify console output for:
   - Correct adjusted durations.
   - Proper consecutive detection.
   - Total monthly hours calculation.  
4. Verify JSON file for:
   - Proper formatting.
   - Correct data for each intervention.  
5. Test error handling by renaming or corrupting the Excel file.

---

### 5. Notes

- Always back up test files before running tests.  
- Use multiple workers to test aggregation of monthly hours.  
- Include edge cases like midnight crossings, very short interventions, and multiple consecutive interventions.

## License & Copyright

© 2025 László Ádám Vámosi, Cordoba, Spain.  
All rights reserved.

---

## Español

# Procesador de Intervenciones de Trabajadores de Excel a JSON – Documentación de Pruebas

### 1. Resumen

Este documento describe los procedimientos de prueba para el script del Procesador de Intervenciones de Trabajadores de Excel a JSON. El objetivo es asegurar que el script:

- Lea archivos Excel correctamente.  
- Analice rangos de fechas de manera precisa.  
- Aplique reglas de redondeo correctamente.  
- Detecte intervenciones consecutivas.  
- Genere archivos JSON válidos.  
- Maneje errores de forma controlada.

---

### 2. Entorno de Prueba

- **Versión de Python:** 3.x  
- **Librerías:** `openpyxl`  
- **SO:** Windows, macOS o Linux  
- **Archivos de prueba:** Archivos Excel con diferentes escenarios de intervenciones

---

### 3. Casos de Prueba

| ID Prueba | Descripción | Entrada | Salida Esperada | Notas |
|-----------|------------|---------|----------------|-------|
| TC1 | Intervención válida única | Fila Excel: 02/07/2025 de 08:00 a 09:15 | JSON muestra 1:15 ajustado según reglas | Comprueba parsing básico y redondeo |
| TC2 | Intervenciones consecutivas | Dos filas, tiempos consecutivos | JSON marca segunda como `consecutive_with_previous=True` | Comprueba detección de consecutividad |
| TC3 | Intervención cruzando medianoche | 23:00 a 01:00 | JSON muestra duración de 2 horas | Comprueba la lógica de cambio de fecha |
| TC4 | Menos de 1 hora, no consecutiva | Intervención de 30 minutos | Redondeada a 1 hora | Comprueba regla de duración mínima |
| TC5 | Archivo Excel faltante | Ruta inexistente | Script finaliza con mensaje de error | Comprueba existencia de archivo |
| TC6 | Archivo Excel corrupto | Formato Excel inválido | Script finaliza con mensaje de error | Comprueba manejo de excepciones |

---

### 4. Procedimiento de Prueba

1. Preparar el archivo Excel de prueba con las filas requeridas.  
2. Ejecutar el script:

```
python xlsx_to_json.py
```

3. Verificar la salida en consola para:
   - Duraciones ajustadas correctas.  
   - Detección correcta de consecutividad.  
   - Cálculo de horas mensuales totales.  
4. Verificar el archivo JSON para:
   - Formato correcto.  
   - Datos correctos para cada intervención.  
5. Probar manejo de errores renombrando o corrompiendo el archivo Excel.

---

### 5. Notas

- Siempre hacer copia de seguridad de los archivos de prueba antes de ejecutar pruebas.  
- Usar múltiples trabajadores para probar la agregación de horas mensuales.  
- Incluir casos límite como cruces de medianoche, intervenciones muy cortas y múltiples intervenciones consecutivas.

## Licencia y Copyright

© 2025 László Ádám Vámosi, Córdoba, España.  
Todos los derechos reservados.