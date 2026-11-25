/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicaveterinaria.modelo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
/**
 *
 * @author damia
 */
public class Consulta {
    private final LocalDate fecha;
    private final Mascota mascota;
    private final Veterinario veterinario;
    private final String diagnostico;
    private final boolean vacunaAplicada;
    private final String nombreVacuna;
    private final double valorVacuna;
    private final String medicamento;
    private final String notas;
    private Factura factura;

    public Consulta(Mascota mascota, Veterinario veterinario, String diagnostico,
            boolean vacunaAplicada, String nombreVacuna, double valorVacuna,
            String medicamento, String notas) {
        if (mascota == null) {
            throw new IllegalArgumentException("Una consulta debe estar asociada a una mascota");
        }
        if (veterinario == null) {
            throw new IllegalArgumentException("Una consulta debe tener un veterinario");
        }
        if (diagnostico == null || diagnostico.trim().isEmpty()) {
            throw new IllegalArgumentException("El diagnóstico no puede estar vacío");
        }
        if (vacunaAplicada) {
            if (nombreVacuna == null || nombreVacuna.trim().isEmpty()) {
                throw new IllegalArgumentException("Indique el nombre de la vacuna aplicada");
            }
            if (valorVacuna < 0) {
                throw new IllegalArgumentException("El valor de la vacuna no puede ser negativo");
            }
        }
        this.mascota = mascota;
        this.veterinario = veterinario;
        this.fecha = LocalDate.now();
        this.diagnostico = diagnostico.trim();
        this.vacunaAplicada = vacunaAplicada;
        this.nombreVacuna = vacunaAplicada ? nombreVacuna.trim() : "";
        this.valorVacuna = vacunaAplicada ? valorVacuna : 0;
        this.medicamento = medicamento == null ? "" : medicamento.trim();
        this.notas = notas == null ? "" : notas.trim();
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public String getFechaFormateada() {
        return fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public Mascota getMascota() {
        return mascota;
    }

    public Veterinario getVeterinario() {
        return veterinario;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public boolean isVacunaAplicada() {
        return vacunaAplicada;
    }

    public String getNombreVacuna() {
        return nombreVacuna;
    }

    public double getValorVacuna() {
        return valorVacuna;
    }

    public String getMedicamento() {
        return medicamento;
    }

    public String getNotas() {
        return notas;
    }

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }
}
