/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicaveterinaria.modelo;

/**
 *
 * @author damia
 */
public abstract class Empleado {
    private final String id;
    private String nombre;

    public Empleado(String id, String nombre) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El código del empleado no puede estar vacío");
        }
        this.id = id.trim();
        setNombre(nombre);
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del empleado no puede estar vacío");
        }
        this.nombre = nombre.trim();
    }

    public abstract String getCargo();

    @Override
    public String toString() {
        return id + " - " + nombre;
    }
}
