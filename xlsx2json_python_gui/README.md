# Excel-to-JSON Worker Intervention Processor (GUI)

English
-------

This script provides a simple GUI to process an Excel file containing worker interventions. It applies rounding rules and generates a JSON summary.

### Features

* Parses date ranges in the format: `02/07/2025 de 23:15 a 00:30`.
* Rounds intervention durations:
  * Minimum 1 hour if not consecutive.
  * Rounds to nearest 10 minutes if greater than 1 hour or consecutive.
* Rounds total monthly hours to 30-minute blocks.
* Detects consecutive interventions automatically.
* Provides a GUI for selecting the Excel file.
* Saves JSON output in the current project directory.
* Handles missing or unreadable Excel files with error messages.

### Requirements

* Python 3.x
* `openpyxl` library

Install dependencies with:

```
pip install openpyxl
```

### Usage

1. Place the script in your project directory.
2. Run the script:

```
python xlsx_to_json_tkinter.py
```

3. In the GUI window, click "Browse Excel file" to select the input file.
4. The JSON summary will be saved in the same project folder and printed to the console.

## License & Copyright

© 2025 László Ádám Vámosi, Cordoba, Spain.  
All rights reserved.

Español
-------

Procesador de Intervenciones de Trabajadores de Excel a JSON (GUI)

Este script proporciona una interfaz gráfica para procesar un archivo Excel con intervenciones de trabajadores, aplica reglas de redondeo y genera un resumen en JSON.

### Funcionalidades

* Analiza rangos de fechas como: `02/07/2025 de 23:15 a 00:30`.
* Redondea la duración de las intervenciones:
  * Mínimo 1 hora si no es consecutiva.
  * Redondea al múltiplo de 10 minutos si dura más de 1 hora o es consecutiva.
* Redondea las horas mensuales totales a bloques de 30 minutos.
* Detecta automáticamente intervenciones consecutivas.
* Proporciona una GUI para seleccionar el archivo Excel.
* Guarda el JSON resultante en el directorio del proyecto actual.
* Gestiona archivos Excel faltantes o ilegibles mostrando mensajes de error.

### Requisitos

* Python 3.x
* Librería `openpyxl`

Instalar dependencias con:

```
pip install openpyxl
```

### Uso

1. Coloca el script en tu carpeta de proyecto.
2. Ejecuta el script:

```
python xlsx_to_json_tkinter.py
```

3. En la ventana de la GUI, haz clic en "Browse Excel file" para seleccionar el archivo de entrada.
4. El resumen en JSON se guardará en la misma carpeta del proyecto y se mostrará en la consola.


## Licencia y Copyright

© 2025 László Ádám Vámosi, Córdoba, España.  
Todos los derechos reservados.