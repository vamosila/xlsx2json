/*
Filename: DateParser.java
Author: László Ádám Vámosi
Location: Córdoba, Spain
Date: 2025-11-15

Copyright (c) 2025 László Ádám Vámosi
All rights reserved.

---

Nombre de archivo: DateParser.java
Autor: László Ádám Vámosi
Ubicación: Córdoba, España
Fecha: 2025-11-15

© 2025 László Ádám Vámosi
Todos los derechos reservados.
*/

package com.example;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateParser {

    public static Date[] parseDateRange(String s) {
        try {
            s = s.trim();
            s = s.replace(" de ", " ");
            s = s.replace(" a ", " ");

            String[] p = s.split(" ");
            if (p.length != 3) return null;

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

            Date start = sdf.parse(p[0] + " " + p[1]);
            Date end   = sdf.parse(p[0] + " " + p[2]);

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
