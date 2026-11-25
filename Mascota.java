/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicaveterinaria.modelo;

/**
 *
 * @author damia
 */
public abstract class Mascota {
    public static class MascotaNoEncontradaException extends Exception {

        public MascotaNoEncontradaException(String id) {
            super("No se encontró ninguna mascota con código: " + id);
        }
    }

    private final String id;
    private String nombre;
    private String especie;
    private int edad;
    private double peso;
    private final Propietario propietario;
    private final HistorialClinico historial = new HistorialClinico();

    public Mascota(String id, String nombre, String especie, int edad, double peso,
            Propietario propietario) {
        if (propietario == null) {
            throw new IllegalArgumentException("Toda mascota debe tener un propietario");
        }
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("El código de la mascota no puede estar vacío");
        }
        this.id = id.trim();
        this.propietario = propietario;
        setNombre(nombre);
        setEspecie(especie);
        setEdad(edad);
        setPeso(peso);
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre de la mascota no puede estar vacío");
        }
        this.nombre = nombre.trim();
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        if (especie == null || especie.trim().isEmpty()) {
            throw new IllegalArgumentException("La especie no puede estar vacía");
        }
        this.especie = especie.trim();
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }
        this.edad = edad;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso debe ser mayor que 0");
        }
        this.peso = peso;
    }

    public Propietario getPropietario() {
        return propietario;
    }

    public HistorialClinico getHistorial() {
        return historial;
    }

    public abstract String getTipo();

    public abstract String getDetalleEspecifico();

    public abstract void agregarCargos(Factura factura, double tarifaBase, Consulta consulta);

    public void registrarVacuna(String nombreVacuna) {
    }

    @Override
    public String toString() {
        return id + " - " + nombre + " (" + especie + ")";
    }
}
