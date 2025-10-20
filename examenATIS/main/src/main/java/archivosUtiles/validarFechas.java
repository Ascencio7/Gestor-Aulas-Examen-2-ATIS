/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package archivosUtiles;

/**
 *
 * @author Vladimir Ascencio
 */

import java.time.LocalDate;
import java.time.LocalTime;

public class validarFechas {

    // Verifica que la fecha no esté en el pasado
    public static boolean fechaValida(LocalDate fecha) {
        return !fecha.isBefore(LocalDate.now());
    }

    // Verifica que la hora de inicio sea antes que la hora de fin
    public static boolean horarioValido(LocalTime inicio, LocalTime fin) {
        return inicio.isBefore(fin);
    }
}