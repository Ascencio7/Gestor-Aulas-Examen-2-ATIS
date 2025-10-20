/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivosUtiles;

/**
 *
 * @author Vladimir Ascencio
 */


import modelos.*;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class archivos {

    // Guardar aulas
    public static void guardarAulas(List<aula> aulas, String ruta) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ruta))) {
            for (aula a : aulas) {
                writer.printf("%s;%s;%s;%d%n",
                    a.getCodigo(),
                    a.getNombre(),
                    a.getTipo().name(),
                    a.getCapacidad()
                );
            }
            System.out.println("\nAulas guardadas en archivo: " + ruta);
        } catch (IOException e) {
            System.out.println("\nError al guardar aulas: " + e.getMessage());
        }
    }

    public static List<aula> cargarAulas(String ruta) {
        List<aula> aulas = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(";");
                if (partes.length == 4) {
                    String codigo = partes[0];
                    String nombre = partes[1];
                    aulaTipo tipo = aulaTipo.valueOf(partes[2]);
                    int capacidad = Integer.parseInt(partes[3]);
                    aulas.add(new aula(codigo, nombre, tipo, capacidad));
                }
            }
        } catch (IOException e) {
            System.out.println("Error al cargar aulas: " + e.getMessage());
        }
        return aulas;
    }

    public static void guardarReservas(List<reserva> reservas, String ruta) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ruta))) {
            for (reserva r : reservas) {
                writer.printf("%s;%s;%s;%s;%s;%s;%s;%s%n",
                    r.getId(),
                    r.getTipo(),
                    r.getAula().getCodigo(),
                    r.getResponsable(),
                    r.getFecha(),
                    r.getHoraInicio(),
                    r.getHoraFin(),
                    r.getEstado()
                );
            }
            System.out.println("\nReservas guardadas en archivo: " + ruta);
        } catch (IOException e) {
            System.out.println("\nError al guardar reservas: " + e.getMessage());
        }
    }

    // Exportara el reporte
    public static void exportarReporte(String contenido, String ruta) {
        try {
            File archivo = new File(ruta);
            File carpeta = archivo.getParentFile();
            if (carpeta != null && !carpeta.exists()) {
                carpeta.mkdirs(); // crea la carpeta si no existe
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(archivo))) {
                writer.println(contenido);
                System.out.println("Reporte exportado a archivo: " + ruta);
            }
        } catch (IOException e) {
            System.out.println("Error al exportar reporte: " + e.getMessage());
        }
    }
}