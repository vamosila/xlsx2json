# Testing Documentation – Excel-to-JSON Worker Intervention Processor (GUI)

English
-------

This document describes testing procedures and scenarios for the GUI-based Excel-to-JSON Worker Intervention Processor.

### Test Environment

* Python 3.x
* `openpyxl` library installed
* GUI environment (Tkinter)

### Test Cases

1. **Basic Functionality**
   * Open the GUI.
   * Select a valid Excel file with one or more worker interventions.
   * Verify JSON output is generated in the project folder.
   * Confirm console output matches JSON file.

2. **Date Parsing**
   * Include rows with date ranges such as `02/07/2025 de 23:15 a 00:30`.
   * Verify that interventions crossing midnight are calculated correctly.

3. **Duration Rounding**
   * Interventions shorter than 1 hour and not consecutive → round up to 1 hour.
   * Interventions longer than 1 hour or consecutive → round to nearest 10 minutes.
   * Verify total monthly hours rounded to 30-minute blocks.

4. **Consecutive Interventions**
   * Create consecutive interventions for the same worker.
   * Check that `is_consecutive` logic correctly applies rounding.

5. **Invalid Data**
   * Include missing or malformed date ranges.
   * Verify script skips invalid rows without crashing.

6. **File Handling**
   * Attempt to select a non-Excel file.
   * Verify GUI displays error and no JSON is created.

7. **GUI Operations**
   * Ensure "Browse Excel file" opens the project directory by default.
   * Verify JSON file is saved in the same project directory.
   * Confirm GUI message box appears after successful processing.

### Notes

* Testing should include multiple workers and multiple interventions per worker.
* Verify output formats: `"original_duration"`, `"adjusted_duration"`, `"total_adjusted_hours"` are consistent (HH:MM).

## License & Copyright

© 2025 László Ádám Vámosi, Cordoba, Spain.  
All rights reserved.

Español
-------

Documentación de Pruebas – Procesador de Intervenciones de Trabajadores de Excel a JSON (GUI)

Este documento describe los procedimientos y escenarios de prueba para el procesador GUI de Excel a JSON.

### Entorno de Prueba

* Python 3.x
* Librería `openpyxl` instalada
* Entorno con GUI (Tkinter)

### Casos de Prueba

1. **Funcionalidad Básica**
   * Abrir la GUI.
   * Seleccionar un archivo Excel válido con una o más intervenciones.
   * Verificar que el archivo JSON se genere en la carpeta del proyecto.
   * Confirmar que la salida en consola coincide con el archivo JSON.

2. **Análisis de Fechas**
   * Incluir filas con rangos de fechas como `02/07/2025 de 23:15 a 00:30`.
   * Verificar que las intervenciones que cruzan la medianoche se calculen correctamente.

3. **Redondeo de Duración**
   * Intervenciones menores a 1 hora y no consecutivas → redondear a 1 hora.
   * Intervenciones mayores a 1 hora o consecutivas → redondear al múltiplo de 10 minutos.
   * Verificar que las horas mensuales totales se redondeen a bloques de 30 minutos.

4. **Intervenciones Consecutivas**
   * Crear intervenciones consecutivas para el mismo trabajador.
   * Comprobar que la lógica `is_consecutive` aplica correctamente el redondeo.

5. **Datos Inválidos**
   * Incluir rangos de fechas faltantes o mal formateados.
   * Verificar que el script omita filas inválidas sin generar errores.

6. **Manejo de Archivos**
   * Intentar seleccionar un archivo que no sea Excel.
   * Verificar que la GUI muestre error y no se cree JSON.

7. **Operaciones de la GUI**
   * Asegurarse de que "Browse Excel file" abra el directorio del proyecto por defecto.
   * Verificar que el JSON se guarde en la misma carpeta del proyecto.
   * Confirmar que aparezca un mensaje en la GUI tras el procesamiento exitoso.

### Notas

* Las pruebas deben incluir varios trabajadores y múltiples intervenciones por trabajador.
* Verificar que los formatos de salida: `"original_duration"`, `"adjusted_duration"`, `"total_adjusted_hours"` sean consistentes (HH:MM).

## Licencia y Copyright

© 2025 László Ádám Vámosi, Córdoba, España.  
Todos los derechos reservados.