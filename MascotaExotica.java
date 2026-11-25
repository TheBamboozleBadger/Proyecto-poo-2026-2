/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicaveterinaria.modelo;

/**
 *
 * @author damia
 */
public class MascotaExotica extends Mascota{
    private String permisoCITES;
    private boolean requiereSedacion;

    public MascotaExotica(String id, String nombre, String especie, int edad, double peso,
            Propietario propietario, String permisoCITES, boolean requiereSedacion) {
        super(id, nombre, especie, edad, peso, propietario);
        setPermisoCITES(permisoCITES);
        this.requiereSedacion = requiereSedacion;
    }

    public String getPermisoCITES() {
        return permisoCITES;
    }

    public void setPermisoCITES(String permisoCITES) {
        if (permisoCITES == null || permisoCITES.trim().isEmpty()) {
            throw new IllegalArgumentException("El permiso ambiental (CITES) es obligatorio");
        }
        this.permisoCITES = permisoCITES.trim();
    }

    public boolean isRequiereSedacion() {
        return requiereSedacion;
    }

    public void setRequiereSedacion(boolean requiereSedacion) {
        this.requiereSedacion = requiereSedacion;
    }

    @Override
    public String getTipo() {
        return "Exótica";
    }

    @Override
    public String getDetalleEspecifico() {
        return "CITES: " + permisoCITES + " | Sedación: " + (requiereSedacion ? "sí" : "no");
    }

    @Override
    public void agregarCargos(Factura factura, double tarifaBase, Consulta consulta) {
        factura.agregarLinea(
                String.format("Recargo mascota exótica (%.0f%%)", Factura.RECARGO_EXOTICA * 100),
                tarifaBase * Factura.RECARGO_EXOTICA);
        if (requiereSedacion) {
            factura.agregarLinea("Insumos de sedación", Factura.COSTO_SEDACION);
        }
    }
}
