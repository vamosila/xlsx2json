/*
Filename: ExcelProcessor.java
Author: László Ádám Vámosi
Location: Córdoba, Spain
Date: 2025-11-15

Copyright (c) 2025 László Ádám Vámosi
All rights reserved.

---

Nombre de archivo: ExcelProcessor.java
Autor: László Ádám Vámosi
Ubicación: Córdoba, España
Fecha: 2025-11-15

© 2025 László Ádám Vámosi
Todos los derechos reservados.
*/

package com.example;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class ExcelProcessor {

    public static Map<String, Worker> process(File file) throws Exception {

        Map<String, Worker> workers = new LinkedHashMap<>();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

        try (Workbook wb = new XSSFWorkbook(file)) {

            Sheet sheet = wb.getSheetAt(0);

            for (int r = 1; r <= sheet.getLastRowNum(); r++) {

                Row row = sheet.getRow(r);
                if (row == null) continue;

                String workerId = getCell(row.getCell(0));
                String workerName = getCell(row.getCell(1));
                String location = getCell(row.getCell(2));
                String dateRange = getCell(row.getCell(3));
                if (dateRange == null || dateRange.trim().isEmpty()) continue;

                String reportNumber = getCell(row.getCell(4));

                Date[] parsed = DateParser.parseDateRange(dateRange);
                if (parsed == null) continue;

                Date start = parsed[0];
                Date end   = parsed[1];

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
                    if (lastEnd.getTime() == start.getTime()) {
                        consecutive = true;
                    }
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

        // Calculate total adjusted hours for each worker
        for (Worker w : workers.values()) {
            long total = 0;

            for (Intervention i : w.interventions) {
                String[] p = i.adjustedDuration.split(":");
                total += Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
            }

            total = DurationRules.roundTotal(total);
            w.totalAdjustedHours = DurationRules.formatMinutes(total);
        }

        return workers;
    }

    private static String getCell(Cell c) {
        if (c == null) return "";

        CellType type = c.getCellType();
        if (type == CellType.STRING) {
            return c.getStringCellValue().trim();
        } else if (type == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(c)) {
                return new SimpleDateFormat("dd/MM/yyyy HH:mm").format(c.getDateCellValue());
            } else {
                return String.valueOf((long) c.getNumericCellValue());
            }
        } else if (type == CellType.FORMULA) {
            return c.getRichStringCellValue().getString().trim();
        } else {
            return "";
        }
    }
}
