/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Vladimir Ascencio
 */

public class aula {
    private String codigo;
    private String nombre;
    private aulaTipo tipo;
    private int capacidad;

    public aula(String codigo, String nombre, aulaTipo tipo, int capacidad) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.tipo = tipo;
        this.capacidad = capacidad;
    }

    public String getCodigo() { 
        return codigo; 
    }
    
    public String getNombre() { 
        return nombre; 
    }
    
    public aulaTipo getTipo() { 
        return tipo; 
    }
    
    public int getCapacidad() { 
        return capacidad; 
    }

    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }
    
    public void setTipo(aulaTipo tipo) { 
        this.tipo = tipo; 
    }
    
    public void setCapacidad(int capacidad) { 
        this.capacidad = capacidad; 
    }

    @Override
    public String toString() {
        return String.format("\nAula [%s] - %s (%s, %d personas)", codigo, nombre, tipo, capacidad);
    }
}