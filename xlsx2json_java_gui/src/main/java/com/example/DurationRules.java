/*
Filename: DurationRules.java
Author: László Ádám Vámosi
Location: Córdoba, Spain
Date: 2025-11-15

Copyright (c) 2025 László Ádám Vámosi
All rights reserved.

---

Nombre de archivo: DurationRules.java
Autor: László Ádám Vámosi
Ubicación: Córdoba, España
Fecha: 2025-11-15

© 2025 László Ádám Vámosi
Todos los derechos reservados.
*/

package com.example;

public class DurationRules {

    public static long adjustDuration(long minutes, boolean consecutive) {
        if (!consecutive && minutes < 60) return 60;

        if (minutes > 60) {
            long mod = minutes % 10;
            if (mod >= 5) return minutes + (10 - mod);
            else return minutes - mod;
        }

        return minutes;
    }

    public static long roundTotal(long mins) {
        return ((mins + 29) / 30) * 30;
    }

    public static String formatMinutes(long m) {
        long h = m / 60;
        long min = m % 60;
        return String.format("%d:%02d", h, min);
    }
}
