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


public class reservaEventos extends reserva {
    private eventoTipo tipoEvento;

    public reservaEventos(String id, aula aula, String responsable, eventoTipo tipoEvento, LocalDate fecha, LocalTime inicio, LocalTime fin) {
        super(id, aula, responsable, fecha, inicio, fin);
        this.tipoEvento = tipoEvento;
    }

    @Override
    public String getTipo() {
        return "EVENTO";
    }

    @Override
    public void validar() throws validarExc {
        if (tipoEvento == null) {
            throw new validarExc("\nDebes especificar el tipo de evento.\n");
        }
    }
}