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
public class MascotaConvencional extends Mascota{
    private String raza;
    private final List<String> esquemaVacunas = new ArrayList<>();
    
    public MascotaConvencional(String id, String nombre, String especie, int edad, double peso, Propietario propietario, String raza, List<String> vacunasPrevias){
        super(id, nombre, especie, edad, peso, propietario);
        setRaza(raza);
        if (vacunasPrevias != null) {
            esquemaVacunas.addAll(vacunasPrevias);
        }
    }

    

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        if (raza == null || raza.trim().isEmpty()) {
            throw new IllegalArgumentException("La raza no puede estar vacía");
        }
        this.raza = raza.trim();
    }

    public List<String> getEsquemaVacunas() {
        return new ArrayList<>(esquemaVacunas);
    }

    @Override
    public void registrarVacuna(String nombreVacuna) {
        if (nombreVacuna != null && !nombreVacuna.trim().isEmpty()
                && !esquemaVacunas.contains(nombreVacuna.trim())) {
            esquemaVacunas.add(nombreVacuna.trim());
        }
    }

    @Override
    public String getTipo() {
        return "Convencional";
    }

    @Override
    public String getDetalleEspecifico() {
        String vacunas = esquemaVacunas.isEmpty() ? "ninguna" : String.join(", ", esquemaVacunas);
        return "Raza: " + raza + " | Vacunas: " + vacunas;
    }

    @Override
    public void agregarCargos(Factura factura, double tarifaBase, Consulta consulta) {
        if (consulta.isVacunaAplicada()) {
            factura.agregarLinea("Vacuna: " + consulta.getNombreVacuna(), consulta.getValorVacuna());
        }
    }
}
