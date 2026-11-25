/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicaveterinaria.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
/**
 *
 * @author damia
 */
public class Factura {
    public static final double TARIFA_BASE = 50000;
    public static final double RECARGO_EXOTICA = 0.25;
    public static final double COSTO_SEDACION = 30000;
    public static final double DESCUENTO_MULTIPLES_MASCOTAS = 0.10;

    public static class Linea {

        private final String concepto;
        private final double valor;

        public Linea(String concepto, double valor) {
            this.concepto = concepto;
            this.valor = valor;
        }

        public String getConcepto() {
            return concepto;
        }

        public double getValor() {
            return valor;
        }
    }

    private final Consulta consulta;
    private final List<Linea> lineas = new ArrayList<>();
    private double porcentajeDescuento = 0;

    public Factura(Consulta consulta) {
        this.consulta = consulta;
    }

    public static Factura generar(Consulta consulta) {
        Factura factura = new Factura(consulta);
        factura.agregarLinea("Tarifa base de consulta", TARIFA_BASE);
        consulta.getMascota().agregarCargos(factura, TARIFA_BASE, consulta);
        if (consulta.getMascota().getPropietario().tieneDescuento()) {
            factura.aplicarDescuento(DESCUENTO_MULTIPLES_MASCOTAS);
        }
        return factura;
    }

    public void agregarLinea(String concepto, double valor) {
        lineas.add(new Linea(concepto, valor));
    }

    public void aplicarDescuento(double porcentaje) {
        if (porcentaje < 0 || porcentaje > 1) {
            throw new IllegalArgumentException("El descuento debe estar entre 0 y 1");
        }
        this.porcentajeDescuento = porcentaje;
    }

    public List<Linea> getLineas() {
        return new ArrayList<>(lineas);
    }

    public double getSubtotal() {
        double suma = 0;
        for (Linea l : lineas) {
            suma += l.getValor();
        }
        return suma;
    }

    public double getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public double getValorDescuento() {
        return getSubtotal() * porcentajeDescuento;
    }

    public double getTotal() {
        return getSubtotal() - getValorDescuento();
    }

    public String getTotalFormato() {
        return dinero(getTotal());
    }

    private static String dinero(double valor) {
        return String.format(Locale.forLanguageTag("es-CO"), "$%,.0f", valor);
    }

    public String generarComprobante() {
        Mascota m = consulta.getMascota();
        Propietario p = m.getPropietario();
        String raya = "--------------------------------------------------\n";
        StringBuilder sb = new StringBuilder();
        sb.append("============ COMPROBANTE DE COBRO ============\n");
        sb.append("Fecha:        ").append(consulta.getFechaFormateada()).append('\n');
        sb.append("Propietario:  ").append(p.getNombre()).append(" (").append(p.getDocumento()).append(")\n");
        sb.append("Mascota:      ").append(m.getNombre()).append(" - ").append(m.getTipo()).append('\n');
        sb.append("Veterinario:  ").append(consulta.getVeterinario().getNombre()).append('\n');
        sb.append("Diagnóstico:  ").append(consulta.getDiagnostico()).append('\n');
        sb.append(raya);
        for (Linea l : lineas) {
            sb.append(String.format("%-36s %13s%n", l.getConcepto(), dinero(l.getValor())));
        }
        sb.append(raya);
        sb.append(String.format("%-36s %13s%n", "Subtotal", dinero(getSubtotal())));
        if (porcentajeDescuento > 0) {
            String texto = String.format("Descuento %.0f%% (más de %d mascotas)",
                    porcentajeDescuento * 100, Propietario.LIMITE_MASCOTAS_SIN_DESCUENTO);
            sb.append(String.format("%-36s %13s%n", texto, "-" + dinero(getValorDescuento())));
        }
        sb.append(String.format("%-36s %13s%n", "TOTAL A PAGAR", dinero(getTotal())));
        sb.append("==================================================\n");
        return sb.toString();
    }
}
