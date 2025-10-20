/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Vladimir Ascencio
 */

import manejoErrores.validarExc;
import java.time.LocalDate;
import java.time.LocalTime;
import manejoErrores.validarExc;


public class reservaPractica extends reserva {
    private String grupo;

    public reservaPractica(String id, aula aula, String responsable, String grupo, LocalDate fecha, LocalTime inicio, LocalTime fin) {
        super(id, aula, responsable, fecha, inicio, fin);
        this.grupo = grupo;
    }

    @Override
    public String getTipo() {
        return "PRACTICA";
    }

    @Override
    public void validar() throws validarExc {
        if (grupo == null || grupo.isBlank()) {
            throw new validarExc("\nEl grupo es obligatorio de ingresar.\n");
        }
    }
}