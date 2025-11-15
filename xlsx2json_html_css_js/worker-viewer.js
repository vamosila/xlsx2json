/*
Filename: worker-viewer.js
Author: László Ádám Vámosi
Location: Córdoba, Spain
Date: 2025-11-15

Copyright (c) 2025 László Ádám Vámosi
All rights reserved.

---

Nombre de archivo: worker-viewer.js
Autor: László Ádám Vámosi
Ubicación: Córdoba, España
Fecha: 2025-11-15

© 2025 László Ádám Vámosi
Todos los derechos reservados.
*/

document.getElementById("loadBtn").addEventListener("click", () => {
    const inp = document.getElementById("jsonFile");
    if (!inp.files.length) return alert("Seleccione un archivo JSON generado por el procesador.");

    const reader = new FileReader();
    reader.onload = e => {
        try {
            const data = JSON.parse(e.target.result);
            render(data);
        } catch {
            alert("Archivo JSON no válido.");
        }
    };
    reader.readAsText(inp.files[0]);
});

function render(data){
    const container = document.getElementById("output");
    container.innerHTML = "";

    const table = document.createElement("table");
    table.innerHTML = `
      <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Minutos ajustados totales</th>
        <th>Horas mensuales</th>
        <th>Detalles</th>
      </tr>
    `;

    Object.values(data).forEach(worker => {
        const row = document.createElement("tr");

        const horas = worker.totalMonthlyFormatted.horas;
        const minutos = worker.totalMonthlyFormatted.minutos.toString().padStart(2,"0");

        row.innerHTML = `
          <td>${worker.id}</td>
          <td>${worker.name}</td>
          <td>${worker.totalRounded}</td>
          <td>${horas}:${minutos}</td>
          <td><button class="btn">Mostrar</button></td>
        `;

        table.appendChild(row);

        const detRow = document.createElement("tr");
        detRow.classList.add("detalle");
        detRow.style.display = "none";

        const detCell = document.createElement("td");
        detCell.colSpan = 5;

        let html = "";

        html += "<h3>Sesiones originales</h3>";
        html += `
          <table style="width:100%; border-collapse: collapse;">
            <tr>
              <th>Fecha</th>
              <th>Inicio</th>
              <th>Fin</th>
              <th>Minutos</th>
              <th>Lugar</th>
              <th>Nº Parte</th>
            </tr>
        `;

        worker.sessions.forEach(s => {
            html += `
              <tr>
                <td>${s.date}</td>
                <td>${s.start}</td>
                <td>${s.end}</td>
                <td>${s.duration}</td>
                <td>${s.location || "-"}</td>
                <td>${s.part || "-"}</td>
              </tr>
            `;
        });

        html += "</table><br>";

        html += "<h3>Bloques aplicados (según reglas)</h3>";
        html += `
          <table style="width:100%; border-collapse: collapse;">
            <tr>
              <th>Sesiones en bloque</th>
              <th>Minutos brutos</th>
              <th>Minutos ajustados</th>
            </tr>
        `;

        worker.blocks.forEach(b => {
            let lista = b.sessions
                .map(x => `${x.date} ${x.start}-${x.end} (${x.duration} min)`)
                .join("<br>");

            html += `
              <tr>
                <td>${lista}</td>
                <td>${b.raw}</td>
                <td>${b.rounded}</td>
              </tr>
            `;
        });

        html += "</table>";

        detCell.innerHTML = html;
        detRow.appendChild(detCell);
        table.appendChild(detRow);

        row.querySelector(".btn").addEventListener("click", () => {
            const visible = detRow.style.display === "table-row";
            detRow.style.display = visible ? "none" : "table-row";
            row.querySelector(".btn").textContent = visible ? "Mostrar" : "Ocultar";
        });
    });

    container.appendChild(table);
}