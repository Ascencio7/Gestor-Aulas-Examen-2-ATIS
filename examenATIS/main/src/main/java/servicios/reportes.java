/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

/**
 *
 * @author Vladimir Ascencio
 */

import modelos.*;
import java.util.*;
import java.util.stream.*;
import java.time.Duration;


public class reportes {
    public static List<aula> topAulasPorHoras(List<reserva> reservas) {
        return reservas.stream()
            .filter(r -> r.getEstado().equals("ACTIVA"))
            .collect(Collectors.groupingBy(
                r -> r.getAula(),
                Collectors.summingLong(r -> Duration.between(r.getHoraInicio(), r.getHoraFin()).toMinutes())
            ))
            .entrySet().stream()
            .sorted(Map.Entry.<aula, Long>comparingByValue().reversed())
            .limit(3)
            .map(Map.Entry::getKey)
            .toList();
    }
}