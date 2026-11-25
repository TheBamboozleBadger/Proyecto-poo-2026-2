/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicaveterinaria.modelo;

/**
 *
 * @author damia
 */
public class Veterinario extends Empleado{
    private String especialidad;
    private String tarjetaProfesional;

    public Veterinario(String id, String nombre, String especialidad, String tarjetaProfesional) {
        super(id, nombre);
        setEspecialidad(especialidad);
        setTarjetaProfesional(tarjetaProfesional);
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new IllegalArgumentException("La especialidad no puede estar vacía");
        }
        this.especialidad = especialidad.trim();
    }

    public String getTarjetaProfesional() {
        return tarjetaProfesional;
    }

    public void setTarjetaProfesional(String tarjetaProfesional) {
        if (tarjetaProfesional == null || tarjetaProfesional.trim().isEmpty()) {
            throw new IllegalArgumentException("La tarjeta profesional no puede estar vacía");
        }
        this.tarjetaProfesional = tarjetaProfesional.trim();
    }

    @Override
    public String getCargo() {
        return "Médico Veterinario";
    }

    @Override
    public String toString() {
        return super.toString() + " (" + especialidad + ")";
    }
}
