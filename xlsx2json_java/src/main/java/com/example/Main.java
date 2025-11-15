/*
Filename: Main.java
Author: László Ádám Vámosi
Location: Córdoba, Spain
Date: 2025-11-15

Copyright (c) 2025 László Ádám Vámosi
All rights reserved.

---

Nombre de archivo: Main.java
Autor: László Ádám Vámosi
Ubicación: Córdoba, España
Fecha: 2025-11-15

© 2025 László Ádám Vámosi
Todos los derechos reservados.
*/

package com.example;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {

    public static void main(String[] args) throws Exception {

        File input = new File("Example.xlsx");
        String output = "Example.json";

        Map<String, Worker> workers = processExcel(input);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonOutput = gson.toJson(workers);

        try (FileOutputStream fos = new FileOutputStream(output);
             OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF-8")) {
            osw.write(jsonOutput);
        }

        System.out.println("\nJSON generated: " + output + "\n");
    }

    private static Map<String, Worker> processExcel(File file) throws Exception {

        Map<String, Worker> workers = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try (Workbook wb = new XSSFWorkbook(file)) {
            Sheet sheet = wb.getSheetAt(0);

            for (int r = 1; r <= sheet.getLastRowNum(); r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;

                String workerId = getCellString(row.getCell(0));
                String workerName = getCellString(row.getCell(1));
                String location = getCellString(row.getCell(2));
                String dateRange = getCellString(row.getCell(3));
                if (dateRange == null || dateRange.isEmpty()) continue;
                String reportNumber = getCellString(row.getCell(4));

                Date[] parsed = parseDateRange(dateRange);
                if (parsed == null) continue;

                Date start = parsed[0];
                Date end = parsed[1];

                long minutes = TimeUnit.MILLISECONDS.toMinutes(end.getTime() - start.getTime());

                Worker w = workers.get(workerId);
                if (w == null) {
                    w = new Worker();
                    w.workerName = workerName;
                    workers.put(workerId, w);
                }

                boolean consecutive = false;
                if (!w.interventions.isEmpty()) {
                    Intervention last = w.interventions.get(w.interventions.size() - 1);
                    Date lastEnd = sdf.parse(last.end);
                    if (lastEnd.getTime() == start.getTime()) consecutive = true;
                }

                long adjusted = DurationRules.adjustDuration(minutes, consecutive);

                Intervention inter = new Intervention();
                inter.location = location;
                inter.start = sdf.format(start);
                inter.end = sdf.format(end);
                inter.reportNumber = reportNumber;
                inter.originalDuration = DurationRules.formatMinutes(minutes);
                inter.adjustedDuration = DurationRules.formatMinutes(adjusted);

                w.interventions.add(inter);
            }
        }

        for (Worker w : workers.values()) {
            long total = 0;
            for (Intervention i : w.interventions) {
                String[] parts = i.adjustedDuration.split(":");
                total += Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
            }
            total = DurationRules.roundTotal(total);
            w.totalAdjustedHours = DurationRules.formatMinutes(total);
        }

        return workers;
    }

    private static String getCellString(Cell c) {
        if (c == null) return "";

        switch (c.getCellType()) {
            case STRING:
                return c.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(c)) {
                    return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(c.getDateCellValue());
                } else {
                    return String.valueOf((long) c.getNumericCellValue());
                }
            case FORMULA:
                return c.getRichStringCellValue().getString().trim();
            default:
                return "";
        }
    }

    private static Date[] parseDateRange(String s) {
        try {
            s = s.trim();
            s = s.replace(" de ", " ");
            s = s.replace(" a ", " ");
            String[] p = s.split(" ");
            if (p.length != 3) return null;

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            Date start = sdf.parse(p[0] + " " + p[1]);
            Date end = sdf.parse(p[0] + " " + p[2]);

            if (end.before(start)) {
                Calendar cal = Calendar.getInstance();
                cal.setTime(end);
                cal.add(Calendar.DAY_OF_MONTH, 1);
                end = cal.getTime();
            }

            return new Date[]{start, end};
        } catch (Exception e) {
            return null;
        }
    }
}
