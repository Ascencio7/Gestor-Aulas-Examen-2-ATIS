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
import manejoErrores.*;
import java.util.*;
import java.time.*;

public class reserveGestor {
    private List<reserva> reservas = new ArrayList<>();

    public void registrarReserva(reserva reserva) throws conflictos, validarExc {
        reserva.validar();
        validarConflictos(reserva);
        reservas.add(reserva);
    }

    private void validarConflictos(reserva nueva) throws conflictos {
        for (reserva r : reservas) {
            if (r.getAula().getCodigo().equals(nueva.getAula().getCodigo()) &&
                r.getFecha().equals(nueva.getFecha()) &&
                r.getEstado().equals("ACTIVA") &&
                !(nueva.getHoraFin().isBefore(r.getHoraInicio()) || nueva.getHoraInicio().isAfter(r.getHoraFin()))) {
                throw new conflictos("\nConflicto de horario con otra reserva.\n");
            }
        }
    }

    public List<reserva> buscarPorResponsable(String texto) {
        return reservas.stream()
            .filter(r -> r.getResponsable().toLowerCase().contains(texto.toLowerCase()))
            .toList();
    }

    public void cancelarReserva(String id) {
        reservas.stream()
            .filter(r -> r.getId().equals(id))
            .findFirst()
            .ifPresent(reserva::cancelar);
    }

    public List<reserva> listarReservas() {
        return reservas;
    }
}