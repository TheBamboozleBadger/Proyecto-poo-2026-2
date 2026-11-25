/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicaveterinaria.modelo;

import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author damia
 */
public class HistorialClinico {
    private final List<Consulta> consultas = new ArrayList<>();

    public void agregarConsulta(Consulta consulta) {
        consultas.add(consulta);
    }

    public List<Consulta> getConsultas() {
        return new ArrayList<>(consultas);
    }

    public int getCantidadConsultas() {
        return consultas.size();
    }
}
