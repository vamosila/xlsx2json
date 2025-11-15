/*
Filename: xlsx2json.js
Author: László Ádám Vámosi
Location: Córdoba, Spain
Date: 2025-11-15

Copyright (c) 2025 László Ádám Vámosi
All rights reserved.

---

Nombre de archivo: xlsx2json.js
Autor: László Ádám Vámosi
Ubicación: Córdoba, España
Fecha: 2025-11-15

© 2025 László Ádám Vámosi
Todos los derechos reservados.
*/

const config = {
    workerId: "Nº de trabajador",
    workerName: "Nombre del trabajador",
    dateRange: "Fecha de inicio y fin",
    location: "Lugar",
    partNumber: "Nº Parte",
    extraFields: []
};

function parseTime(t){const p=t.split(":").map(Number);return p[0]*60+p[1];}

function parseDate(d){
    const [dd,mm,yy]=d.split("/").map(Number);
    return new Date(yy,mm-1,dd);
}

function extractRange(str){
    const parts=str.split(" de ");
    if(parts.length<2)return null;
    const dateStr=parts[0].trim();
    const times=parts[1].split(" a ");
    if(times.length<2)return null;
    return{
        date:dateStr,
        start:times[0].trim(),
        end:times[1].trim(),
        dateObj:parseDate(dateStr),
        startMin:parseTime(times[0].trim()),
        endMin:parseTime(times[1].trim())
    };
}

function rawDuration(s,e){return e>=s?e-s:(e+1440)-s;}

function roundBlock(m,isFirst){return isFirst&&m<60?60:Math.round(m/10)*10;}

function roundMonthly(m){return Math.ceil(m/30)*30;}

function processWorkers(rows){
    let workers={};
    rows.forEach(r=>{
        const id=r[config.workerId];
        if(!id||!r[config.dateRange])return;
        if(!workers[id])workers[id]={id:id,name:r[config.workerName],sessions:[],blocks:[],totalRounded:0};

        const rng=extractRange(r[config.dateRange]);
        if(!rng)return;

        workers[id].sessions.push({
            dateRange:r[config.dateRange],
            date:rng.date,
            dateObj:rng.dateObj,
            start:rng.start,
            end:rng.end,
            startMin:rng.startMin,
            endMin:rng.endMin,
            duration:rawDuration(rng.startMin,rng.endMin),
            location:r[config.location],
            part:r[config.partNumber]
        });
    });

    Object.values(workers).forEach(w=>{
        w.sessions.sort((a,b)=>{
            if(a.dateObj.getTime()===b.dateObj.getTime())return a.startMin-b.startMin;
            return a.dateObj-b.dateObj;
        });

        let block=null;
        w.sessions.forEach(s=>{
            if(!block){
                block={sessions:[s],raw:s.duration};
                return;
            }
            const prev=block.sessions[block.sessions.length-1];
            const isConsecutive=prev.date===s.date&&prev.endMin===s.startMin;
            if(isConsecutive){
                block.sessions.push(s);
                block.raw+=s.duration;
            }else{
                w.blocks.push(block);
                block={sessions:[s],raw:s.duration};
            }
        });
        if(block)w.blocks.push(block);

        w.blocks.forEach(b=>{
            b.rounded=roundBlock(b.raw,true);
            w.totalRounded+=b.rounded;
        });

        w.totalMonthly=roundMonthly(w.totalRounded);
        w.totalMonthlyFormatted={
            horas:Math.floor(w.totalMonthly/60),
            minutos:w.totalMonthly%60
        };
    });

    return workers;
}

document.getElementById("processBtn").addEventListener("click",()=>{
    const f=document.getElementById("fileInput").files[0];
    if(!f){alert("Seleccione un archivo .xlsx");return;}

    const reader=new FileReader();
    reader.onload=e=>{
        const wb=XLSX.read(new Uint8Array(e.target.result),{type:"array"});
        const sh=wb.Sheets[wb.SheetNames[0]];
        const rows=XLSX.utils.sheet_to_json(sh);
        const data=processWorkers(rows);
        const blob=new Blob([JSON.stringify(data,null,2)],{type:"application/json"});
        const url=URL.createObjectURL(blob);
        const a=document.createElement("a");
        a.href=url;
        a.download="trabajadores.json";
        a.click();
        URL.revokeObjectURL(url);
        alert("Procesamiento completado. Archivo JSON generado.");
    };
    reader.readAsArrayBuffer(f);
});