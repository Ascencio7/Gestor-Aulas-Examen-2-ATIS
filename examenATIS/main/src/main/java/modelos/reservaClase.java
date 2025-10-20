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


public class reservaClase extends reserva {
    private String materia;

    public reservaClase(String id, aula aula, String responsable, String materia, LocalDate fecha, LocalTime inicio, LocalTime fin) {
        super(id, aula, responsable, fecha, inicio, fin);
        this.materia = materia;
    }

    @Override
    public String getTipo() {
        return "CLASE";
    }

    @Override
    public void validar() throws validarExc {
        if (materia == null || materia.isBlank()) {
            throw new validarExc("\nLa materia es obligatoria de ingresar.\n");
        }
    }
}