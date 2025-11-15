# Excel-to-JSON Converter – Project Documentation

# 🇬🇧 English Version

## Project Summary
This project is a Java tool that reads an Excel file `Example.xlsx`, processes worker intervention records according to specific time rules, and exports a structured JSON file `Example.json`. The program reads worker data and interventions from Excel, parses start/end times (including interventions crossing midnight), applies four duration-adjustment rules, calculates total adjusted hours per worker, and exports JSON with all interventions and totals.

The project is modular with classes in `src/main/java/com/example`:
- `Main` – main executable
- `ExcelProcessor` – Excel reading logic
- `DurationRules` – rules for adjusting duration
- `Worker` & `Intervention` – data models

## Business Rules

**Rule 1 — Minimum 1 hour**  
If an intervention lasts less than 60 minutes, round **up to 60 minutes**, unless consecutive with the previous intervention.

**Rule 2 — Round durations over 1 hour to nearest 10 minutes**  
Examples:  
- 1h 08m → 1h 10m  
- 2h 33m → 2h 30m

**Rule 3 — Monthly totals rounded up to 30-minute blocks**  
Examples:  
- 7h 20m → 7h 30m  
- 7h 40m → 8h 00m

**Rule 4 — Consecutive interventions**  
Two interventions are consecutive if `previous.end == next.start`. In this case, Rule 1 is not applied.

**Example:**  
11:10–13:00  (1h 50m)  
13:00–13:25  (25m, consecutive → no minimum)  
= 1h 15m → rounded to 1h 20m (Rule 2)

## Running the Project

**Option 1 — Run in VS Code**  
Requirements:
- VS Code with Java Extension Pack
- Java 8+
- Apache POI & Gson libraries included

Steps:
1. Clone repository.
2. Open folder in VS Code.
3. Run `Main.java` from `src/main/java/com/example`.
4. Place `Example.xlsx` in project root.

**Output:**  
- `Example.json`  
- The JAR file generated via VS Code Maven build will also appear in the **project root**.

**Option 2 — Run using JAR**  
Build JAR with Maven:  
```bash
mvn clean package
```

## Run JAR (from project root)

```bash
java -jar xlsx2json_java_gui.jar
```

Place `Example.xlsx` in the same directory as the JAR.

## Input & Output Files

**Input:** `Example.xlsx`  
Columns:  
- A → Worker ID  
- B → Worker Name  
- C → Location  
- D → Start/End date string  
- E → Report number

**Output:** `Example.json`  
Contains:  
- Workers grouped by ID  
- All interventions  
- Original & adjusted durations  
- Total monthly adjusted hours

## Project Structure

```
src/
└── main/java/com/example/
    ├── Main.java
    ├── ExcelProcessor.java
    ├── DataParser.java
    ├── DurationRules.java
    ├── Worker.java
    └── Intervention.java
Example.xlsx
README.md
xlsx2json_java_gui.jar (after build)
```

## Requirements

- Java 8+  
- VS Code or terminal with `java`  
- Apache POI  
- Gson

## License & Copyright

© 2025 László Ádám Vámosi, Cordoba, Spain.  
All rights reserved.

# 🇪🇸 Versión en Español

## Resumen del Proyecto

Esta herramienta en Java lee un archivo Excel `Example.xlsx`, procesa intervenciones de trabajadores según reglas de tiempo y genera `Example.json`. El programa lee datos e intervenciones desde Excel, interpreta inicio/fin (incluso cruzando medianoche), aplica cuatro reglas de duración, calcula horas ajustadas totales por trabajador y exporta JSON con todas las intervenciones y totales.

Clases modulares en `src/main/java/com/example`:  
- Main – ejecutable principal  
- ExcelProcessor – lógica de lectura Excel 
- DataParser – parseo de los datos de Excel en objetos utilizables 
- DurationRules – reglas de ajuste de duración  
- Worker e Intervention – modelos de datos

## Reglas de Negocio

**Regla 1 — Mínimo 1 hora**  
Si una intervención dura menos de 60 min, redondear a 60 min, salvo que sea consecutiva.

**Regla 2 — Redondeo a 10 minutos si > 1 hora**  
Ejemplos:  
- 1h 08m → 1h 10m  
- 2h 33m → 2h 30m

**Regla 3 — Totales mensuales redondeados a bloques de 30 min**  
Ejemplos:  
- 7h 20m → 7h 30m  
- 7h 40m → 8h 00m

**Regla 4 — Intervenciones consecutivas**  
Dos intervenciones son consecutivas si `fin anterior == inicio siguiente`. En este caso, Regla 1 no se aplica.

**Ejemplo:**  
11:10–13:00 (1h 50m)  
13:00–13:25 (25m, consecutiva → sin mínimo)  
= 1h 15m → redondeado a 1h 20m (Regla 2)

## Ejecución del Proyecto

**Opción 1 — VS Code**  
Requisitos:  
- VS Code con Java Extension Pack  
- Java 8+  
- Apache POI y Gson  

Pasos:  
1. Clonar repositorio.  
2. Abrir carpeta en VS Code.  
3. Ejecutar `Main.java` desde `src/main/java/com/example`.  
4. Colocar `Example.xlsx` en la raíz del proyecto.  

**Salida:**  
- `Example.json`  
- El archivo JAR generado mediante la compilación Maven en VS Code también aparecerá en la raíz del proyecto.

**Opción 2 — JAR**  
Construir JAR con Maven:  
```bash
mvn clean package
```

Ejecutar (desde raíz del proyecto):  
```bash
java -jar xlsx2json_java_gui.jar
```

Colocar `Example.xlsx` en la misma carpeta que el JAR.

## Archivos de Entrada y Salida

**Entrada:** `Example.xlsx`  
Columnas:  
- A → ID trabajador  
- B → Nombre  
- C → Ubicación  
- D → Fecha/hora inicio-fin  
- E → Nº de parte

**Salida:** `Example.json`  
Contiene:  
- Trabajadores agrupados por ID  
- Todas las intervenciones  
- Duración original y ajustada  
- Total mensual ajustado

## Estructura del Proyecto

```
src/
└── main/java/com/example/
    ├── Main.java
    ├── ExcelProcessor.java
    ├── DataParser.java
    ├── DurationRules.java
    ├── Worker.java
    └── Intervention.java
Example.xlsx
README.md
xlsx2json_java.jar (después de build)
```

## Requisitos

- Java 8+  
- VS Code o terminal con `java`  
- Apache POI  
- Gson

## Licencia y Copyright

© 2025 László Ádám Vámosi, Córdoba, España.  
Todos los derechos reservados.