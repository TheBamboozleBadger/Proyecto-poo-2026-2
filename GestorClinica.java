/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicaveterinaria.modelo;

import com.mycompany.clinicaveterinaria.modelo.Mascota.MascotaNoEncontradaException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author damia
 */
public class GestorClinica {
    private final Map<String, Propietario> propietarios = new LinkedHashMap<>();
    private final Map<String, Mascota> mascotas = new LinkedHashMap<>();
    private final Map<String, Veterinario> veterinarios = new LinkedHashMap<>();
    private int contadorMascotas = 0;


    public void registrarVeterinario(Veterinario veterinario) {
        if (veterinarios.containsKey(veterinario.getId())) {
            throw new IllegalArgumentException("Ya existe un veterinario con código " + veterinario.getId());
        }
        veterinarios.put(veterinario.getId(), veterinario);
    }

    public Veterinario buscarVeterinario(String id) {
        Veterinario v = veterinarios.get(id);
        if (v == null) {
            throw new IllegalArgumentException("No existe un veterinario con código " + id);
        }
        return v;
    }

    public List<Veterinario> listarVeterinarios() {
        return new ArrayList<>(veterinarios.values());
    }

    public Propietario registrarPropietario(String documento, String nombre, String telefono,
            String email) {
        Propietario p = new Propietario(documento, nombre, telefono, email);
        if (propietarios.containsKey(p.getDocumento())) {
            throw new IllegalArgumentException("Ya existe un propietario con documento " + p.getDocumento());
        }
        propietarios.put(p.getDocumento(), p);
        return p;
    }

    public Propietario buscarPropietario(String documento) {
        Propietario p = propietarios.get(documento);
        if (p == null) {
            throw new IllegalArgumentException("No existe un propietario con documento " + documento);
        }
        return p;
    }

    public List<Propietario> listarPropietarios() {
        return new ArrayList<>(propietarios.values());
    }


    public Mascota registrarMascotaConvencional(String idPropietario, String nombre, String especie,
            int edad, double peso, String raza, String vacunasTexto) {
        Propietario dueno = buscarPropietario(idPropietario);
        MascotaConvencional m = new MascotaConvencional(siguienteId(), nombre, especie, edad, peso,
                dueno, raza, separarVacunas(vacunasTexto));
        guardarMascota(m, dueno);
        return m;
    }

    public Mascota registrarMascotaExotica(String idPropietario, String nombre, String especie,
            int edad, double peso, String permisoCITES, boolean requiereSedacion) {
        Propietario dueno = buscarPropietario(idPropietario);
        MascotaExotica m = new MascotaExotica(siguienteId(), nombre, especie, edad, peso, dueno,
                permisoCITES, requiereSedacion);
        guardarMascota(m, dueno);
        return m;
    }

    public Mascota buscarMascota(String id) throws MascotaNoEncontradaException {
        Mascota m = mascotas.get(id);
        if (m == null) {
            throw new MascotaNoEncontradaException(id);
        }
        return m;
    }

    public List<Mascota> listarMascotas() {
        return new ArrayList<>(mascotas.values());
    }

    private String siguienteId() {
        return String.format("M%03d", contadorMascotas + 1);
    }

    private void guardarMascota(Mascota m, Propietario dueno) {
        contadorMascotas++;
        mascotas.put(m.getId(), m);
        dueno.agregarMascota(m);
    }

    private List<String> separarVacunas(String texto) {
        List<String> lista = new ArrayList<>();
        if (texto != null) {
            for (String parte : texto.split(",")) {
                if (!parte.trim().isEmpty()) {
                    lista.add(parte.trim());
                }
            }
        }
        return lista;
    }

    public Consulta registrarConsulta(String idMascota, String idVeterinario, String diagnostico,
            boolean vacunaAplicada, String nombreVacuna, double valorVacuna, String medicamento,
            String notas) throws MascotaNoEncontradaException {
        Mascota mascota = buscarMascota(idMascota);
        Veterinario veterinario = buscarVeterinario(idVeterinario);
        Consulta consulta = new Consulta(mascota, veterinario, diagnostico, vacunaAplicada,
                nombreVacuna, valorVacuna, medicamento, notas);
        consulta.setFactura(Factura.generar(consulta));
        mascota.getHistorial().agregarConsulta(consulta);
        if (vacunaAplicada) {
            mascota.registrarVacuna(nombreVacuna);
        }
        return consulta;
    }

    public List<Consulta> historialDe(String idMascota) throws MascotaNoEncontradaException {
        return buscarMascota(idMascota).getHistorial().getConsultas();
    }
}
