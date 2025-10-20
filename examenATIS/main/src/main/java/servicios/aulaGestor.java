/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

/**
 *
 * @author Vladimir Ascencio
 */


import modelos.aula;
import java.util.ArrayList;
import java.util.List;

public class aulaGestor {
    private List<aula> aulas = new ArrayList<>();

    public void registrarAula(aula nueva) {
        aulas.add(nueva);
    }

    public List<aula> listarAulas() {
        return aulas;
    }

    public aula buscarPorCodigo(String codigo) {
        return aulas.stream()
            .filter(a -> a.getCodigo().equalsIgnoreCase(codigo))
            .findFirst()
            .orElse(null);
    }

    public void modificarAula(String codigo, String nuevoNombre, int nuevaCapacidad) {
        aula aula = buscarPorCodigo(codigo);
        if (aula != null) {
            aula.setNombre(nuevoNombre);
            aula.setCapacidad(nuevaCapacidad);
        }
    }
}