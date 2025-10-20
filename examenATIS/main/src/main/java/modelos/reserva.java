/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Vladimir Ascencio
 */


import java.time.LocalDate;
import java.time.LocalTime;
import archivosUtiles.validaciones;

public abstract class reserva implements validaciones {
    protected String id;
    protected aula aula;
    protected String responsable;
    protected LocalDate fecha;
    protected LocalTime horaInicio;
    protected LocalTime horaFin;
    protected String estado = "ACTIVA";

    public reserva(String id, aula aula, String responsable, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin) {
        this.id = id;
        this.aula = aula;
        this.responsable = responsable;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    public abstract String getTipo();

    public String getId() { 
        return id; 
    }
    
    public aula getAula() { 
        return aula; 
    }
    
    public String getResponsable() { 
        return responsable; 
    }
    
    public LocalDate getFecha() { 
        return fecha; 
    }
    
    public LocalTime getHoraInicio() { 
        return horaInicio; 
    }
    
    public LocalTime getHoraFin() { 
        return horaFin; 
    }
    
    public String getEstado() { 
        return estado; 
    }

    public void cancelar() { 
        this.estado = "CANCELADA"; 
    }

    @Override
    public String toString() {
        return String.format("\n[%s] %s - %s (%s a %s) Aula: %s (%s)", id, getTipo(), responsable, horaInicio, horaFin, aula.getNombre(), estado);
    }
}