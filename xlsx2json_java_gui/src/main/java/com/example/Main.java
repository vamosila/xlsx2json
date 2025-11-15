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

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.util.Map;

public class Main {

    private JFrame frame;
    private JTextArea textArea;
    private JButton btnOpen, btnExport;
    private File selectedFile;
    private Map<String, Worker> workers;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createGUI());
    }

    private void createGUI() {
        frame = new JFrame("Convertidor de Excel a JSON");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);

        textArea = new JTextArea();
        textArea.setEditable(false);
        JScrollPane scroll = new JScrollPane(textArea);

        btnOpen = new JButton("Abrir archivo Excel");
        btnOpen.addActionListener(e -> openExcel());

        btnExport = new JButton("Exportar JSON");
        btnExport.setEnabled(false);
        btnExport.addActionListener(e -> exportJSON());

        JPanel panel = new JPanel();
        panel.add(btnOpen);
        panel.add(btnExport);

        frame.getContentPane().add(scroll, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);

        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    private void openExcel() {
        File rootDir = new File(System.getProperty("user.dir"));
        JFileChooser chooser = new JFileChooser(rootDir);
        chooser.setFileFilter(new FileNameExtensionFilter("Archivos Excel", "xlsx"));
        int result = chooser.showOpenDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            try {
                workers = ExcelProcessor.process(selectedFile);
                displayWorkers();
                btnExport.setEnabled(true);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error al leer el Excel: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void displayWorkers() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Worker> entry : workers.entrySet()) {
            sb.append("ID del trabajador: ").append(entry.getKey())
                    .append(" - Nombre: ").append(entry.getValue().workerName)
                    .append(" - Total: ").append(entry.getValue().totalAdjustedHours)
                    .append("\n");

            for (Intervention i : entry.getValue().interventions) {
                sb.append("  ").append(i.start).append(" → ").append(i.end)
                        .append(" | Lugar: ").append(i.location)
                        .append(" | Informe: ").append(i.reportNumber)
                        .append(" | Original: ").append(i.originalDuration)
                        .append(" | Ajustado: ").append(i.adjustedDuration)
                        .append("\n");
            }
            sb.append("\n");
        }
        textArea.setText(sb.toString());
    }

    private void exportJSON() {
        File rootDir = new File(System.getProperty("user.dir"));
        JFileChooser chooser = new JFileChooser(rootDir);
        chooser.setFileFilter(new FileNameExtensionFilter("Archivos JSON", "json"));

        if (selectedFile != null) {
            String name = selectedFile.getName();
            if (name.contains(".")) name = name.substring(0, name.lastIndexOf('.'));
            chooser.setSelectedFile(new File(rootDir, name + ".json"));
        }

        int result = chooser.showSaveDialog(frame);
        if (result == JFileChooser.APPROVE_OPTION) {
            File outputFile = chooser.getSelectedFile();
            try {
                com.google.gson.Gson gson = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
                String jsonOutput = gson.toJson(workers);
                java.nio.file.Files.write(outputFile.toPath(), jsonOutput.getBytes("UTF-8"));
                JOptionPane.showMessageDialog(frame, "¡JSON guardado correctamente!");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error al escribir el JSON: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}
