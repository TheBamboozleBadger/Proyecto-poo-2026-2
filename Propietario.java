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
public class Propietario {
    public static final int LIMITE_MASCOTAS_SIN_DESCUENTO = 2;

    private final String documento;
    private String nombre;
    private String telefono;
    private String email;
    private final List<Mascota> mascotas = new ArrayList<>();

    public Propietario(String documento, String nombre, String telefono, String email) {
        if (documento == null || documento.trim().isEmpty()) {
            throw new IllegalArgumentException("El documento no puede estar vacío");
        }
        this.documento = documento.trim();
        setNombre(nombre);
        setTelefono(telefono);
        setEmail(email);
    }

    public String getDocumento() {
        return documento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre del propietario no puede estar vacío");
        }
        this.nombre = nombre.trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new IllegalArgumentException("El teléfono no puede estar vacío");
        }
        this.telefono = telefono.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? "" : email.trim();
    }

    public void agregarMascota(Mascota mascota) {
        mascotas.add(mascota);
    }

    public List<Mascota> getMascotas() {
        return new ArrayList<>(mascotas);
    }

    public int getCantidadMascotas() {
        return mascotas.size();
    }

    public boolean tieneDescuento() {
        return mascotas.size() > LIMITE_MASCOTAS_SIN_DESCUENTO;
    }

    @Override
    public String toString() {
        return documento + " - " + nombre;
    }
}
