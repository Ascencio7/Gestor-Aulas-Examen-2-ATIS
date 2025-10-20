/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.main;

/**
 *
 * @author Vladimir Ascencio
 */

import modelos.*;
import servicios.*;
import manejoErrores.*;
import java.util.*;
import java.time.*;
import servicios.reportes;
import archivosUtiles.archivos;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final aulaGestor gestorAulas = new aulaGestor();
    private static final reserveGestor gestorReservas = new reserveGestor();


    public static void main(String[] args) {
        boolean salir = false;
        while (!salir) {
            System.out.println("\n==== RESERVAS DE AULAS EN ITCA-FEPADE ====\n");
            System.out.println("1. Gestionar Aulas");
            System.out.println("2. Registrar Reserva");
            System.out.println("3. Buscar reservas por responsable");
            System.out.println("4. Cancelar Reserva");
            System.out.println("5. Listar Reservas");
            System.out.println("6. Generar Reporte");
            System.out.println("0. Salir");
            
            System.out.print("\nSelecciona una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> menuAulas();
                case "2" -> menuReservas();
                case "3" -> buscarReservas();
                case "4" -> cancelarReserva();
                case "5" -> listarReservas();
                case "6" -> generarReporte();
                case "0" -> salir = true;
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private static void menuAulas() {
        System.out.println("\n=== GESTIÓN DE AULAS ===\n");
        System.out.println("1. Registrar el aula");
        System.out.println("2. Listar las aulas");
        System.out.println("3. Modificar aula");
        
        System.out.print("\nSelecciona una opción: ");
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1" -> registrarAula();
            case "2" -> listarAulas();
            case "3" -> modificarAula();
            default -> System.out.println("\nOpción inválida, ingresa una opcion mostrada en el menu.\n");
        }
    }

    private static void registrarAula() {
        System.out.print("Código: ");
        String codigo = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Capacidad: ");
        int capacidad = Integer.parseInt(scanner.nextLine());
        System.out.print("Tipo (TEORICA, LABORATORIO, AUDITORIO): ");
        aulaTipo tipo = aulaTipo.valueOf(scanner.nextLine().toUpperCase());

        if (gestorAulas.buscarPorCodigo(codigo) != null) {
            System.out.println("Error: Ya existe un aula con ese código.");
            return;
        }

        
        aula nueva = new aula(codigo, nombre, tipo, capacidad);
        gestorAulas.registrarAula(nueva);
        System.out.println("\nAula ingresada correctamente\n");
    }

    private static void listarAulas() {
        System.out.println("\n=== LISTA AULAS ===");
        gestorAulas.listarAulas().forEach(System.out::println);
    }

    private static void modificarAula() {
        System.out.print("\nCódigo del aula a modificar: ");
        String codigo = scanner.nextLine();

        aula existente = gestorAulas.buscarPorCodigo(codigo);
        if (existente == null) {
            System.out.println("Error: No existe un aula con ese código.");
            return;
        }

        System.out.print("Nuevo nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Nueva capacidad: ");
        int capacidad = Integer.parseInt(scanner.nextLine());

        gestorAulas.modificarAula(codigo, nombre, capacidad);
        System.out.println("\nAula actualizada correctamente.\n");
    }

    private static void menuReservas() {
        System.out.println("\n=== REGISTRO DE LAS RESERVAS ===\n");
        System.out.println("1. Clase");
        System.out.println("2. Evento");
        System.out.println("3. Práctica");

        System.out.print("\nSelecciona tipo de reserva: ");
        String tipo = scanner.nextLine();

        try {
            System.out.print("\nIdentificador de Reserva: ");
            String id = scanner.nextLine();

            System.out.print("Código de aula: ");
            String codigoAula = scanner.nextLine();
            aula aula = gestorAulas.buscarPorCodigo(codigoAula);
            if (aula == null) {
                System.out.println("Error: El aula ingresada no fue encontrada.");
                return;
            }

            System.out.print("\nResponsable: ");
            String responsable = scanner.nextLine();

            System.out.print("Fecha (YYYY-MM-DD): ");
            LocalDate fecha = LocalDate.parse(scanner.nextLine());

            // Validación de hora inicio
            System.out.print("Hora inicio (HH:MM): ");
            String horaInicioStr = scanner.nextLine();
            if (!horaInicioStr.matches("\\d{1,2}:\\d{2}")) {
                System.out.println("Error: La hora de inicio debe estar en formato HH:MM, por ejemplo 09:00");
                return;
            }
            LocalTime inicio = LocalTime.parse(horaInicioStr);

            // Validación de hora fin
            System.out.print("Hora fin (HH:MM): ");
            String horaFinStr = scanner.nextLine();
            if (!horaFinStr.matches("\\d{1,2}:\\d{2}")) {
                System.out.println("Error: La hora de fin debe estar en formato HH:MM, por ejemplo 11:30");
                return;
            }
            LocalTime fin = LocalTime.parse(horaFinStr);

            // Validación de rango horario
            if (!inicio.isBefore(fin)) {
                System.out.println("Error: La hora de inicio debe ser anterior a la hora de fin.");
                return;
            }

            reserva reserva = switch (tipo) {
                case "1" -> {
                    System.out.print("\nMateria: ");
                    String materia = scanner.nextLine();
                    yield new reservaClase(id, aula, responsable, materia, fecha, inicio, fin);
                }
                case "2" -> {
                    System.out.print("Tipo de evento (CONFERENCIA, TALLER, REUNION): ");
                    eventoTipo tipoEvento = eventoTipo.valueOf(scanner.nextLine().toUpperCase());
                    yield new reservaEventos(id, aula, responsable, tipoEvento, fecha, inicio, fin);
                }
                case "3" -> {
                    System.out.print("Grupo: ");
                    String grupo = scanner.nextLine();
                    yield new reservaPractica(id, aula, responsable, grupo, fecha, inicio, fin);
                }
                default -> null;
            };

            if (reserva != null) {
                gestorReservas.registrarReserva(reserva);
                System.out.println("Éxito: Reserva ingresada correctamente.\n");
            } else {
                System.out.println("Error: El tipo de reserva es inválido.\n");
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void buscarReservas() {
        System.out.print("\nResponsable (Vladimir, Mauricio, ect.) : ");
        String texto = scanner.nextLine();
        List<reserva> resultados = gestorReservas.buscarPorResponsable(texto);
        System.out.println("\n=== RESPONSABLES ===");
        resultados.forEach(System.out::println);
    }

    private static void cancelarReserva() {
        System.out.print("Identificador de la reserva a cancelar: ");
        String id = scanner.nextLine();
        gestorReservas.cancelarReserva(id);
        System.out.println("Exito: La reserva fue cancelada.\n");
    }

    private static void listarReservas() {
        System.out.println("\n=== RESERVAS ===");
        gestorReservas.listarReservas().forEach(System.out::println);
    }
    
    private static void generarReporte() {
        System.out.println("\n=== REPORTE: LAS 3 AULAS MÁS RESERVADAS ===");
        List<reserva> todas = gestorReservas.listarReservas();
        List<aula> topAulas = reportes.topAulasPorHoras(todas);

        StringBuilder contenido = new StringBuilder();
        contenido.append("TOP 3 AULAS CON MÁS HORAS RESERVADAS HASTA EL MOMENTO:\n\n");
        for (int i = 0; i < topAulas.size(); i++) {
            aula a = topAulas.get(i);
            contenido.append(String.format("%d. %s (%s)\n", i + 1, a.getNombre(), a.getCodigo()));
        }

        System.out.print("\nEscribe o pega la ruta para guardar el reporte (ej. reportes/topAulas.txt): ");
        String ruta = scanner.nextLine();

        // Validación de ruta y extensión
        if (!ruta.toLowerCase().endsWith(".txt")) {
            System.out.println("Error: La ruta debe terminar en .txt");
            return;
        }

        archivos.exportarReporte(contenido.toString(), ruta);
    }

}