Excel-to-JSON Worker Intervention Processor
===========================================

English
-------

This script processes an Excel file containing worker interventions, applies specific rounding rules, and outputs a JSON summary.

### Features

*   Parses date ranges in the format: 02/07/2025 de 23:15 a 00:30.
    
*   Rounds intervention durations:
    
    *   Minimum 1 hour if the intervention is not consecutive.
        
    *   Rounds to the nearest 10 minutes if greater than 1 hour or consecutive.
        
*   Rounds total monthly hours to 30-minute blocks.
    
*   Detects consecutive interventions automatically.
    
*   Handles missing or unreadable Excel files with error messages.
    

### Requirements

*   Python 3.x
    
*   openpyxl library
    

Install dependencies with:

`   pip install openpyxl   `

### Usage

1.  Place the input Excel file (e.g., Example.xlsx) in the same folder as the script.
    
2.  Run the script:
    
`   python xlsx_to_json.py   `

1.  The script will generate a JSON file (e.g., Example.json) and print a summary in the console.

## License & Copyright

© 2025 László Ádám Vámosi, Cordoba, Spain.  
All rights reserved.
    

Español
-------

Procesador de Intervenciones de Trabajadores de Excel a JSON
============================================================

Este script procesa un archivo Excel con intervenciones de trabajadores, aplica reglas de redondeo y genera un resumen en formato JSON.

### Funcionalidades

*   Analiza rangos de fechas como: 02/07/2025 de 23:15 a 00:30.
    
*   Redondea la duración de las intervenciones:
    
    *   Mínimo 1 hora si la intervención no es consecutiva.
        
    *   Redondea al múltiplo de 10 minutos si dura más de 1 hora o es consecutiva.
        
*   Redondea las horas mensuales totales a bloques de 30 minutos.
    
*   Detecta automáticamente intervenciones consecutivas.
    
*   Gestiona archivos Excel faltantes o ilegibles mostrando mensajes de error.
    

### Requisitos

*   Python 3.x
    
*   Librería openpyxl
    

Instalar dependencias con:

`   pip install openpyxl   `

### Uso

1.  Coloca el archivo Excel de entrada (por ejemplo, Example.xlsx) en la misma carpeta que el script.
    
2.  Ejecuta el script:
    
`   python xlsx_to_json.py   `

1.  El script generará un archivo JSON (por ejemplo, Example.json) y mostrará un resumen en la consola.

## Licencia y Copyright

© 2025 László Ádám Vámosi, Córdoba, España.  
Todos los derechos reservados.