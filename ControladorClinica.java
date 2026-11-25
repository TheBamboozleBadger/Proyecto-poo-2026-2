/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.clinicaveterinaria.controlador;

import com.mycompany.clinicaveterinaria.modelo.Consulta;
import com.mycompany.clinicaveterinaria.modelo.GestorClinica;
import com.mycompany.clinicaveterinaria.modelo.Mascota;
import com.mycompany.clinicaveterinaria.modelo.Mascota.MascotaNoEncontradaException;
import com.mycompany.clinicaveterinaria.modelo.Propietario;
import com.mycompany.clinicaveterinaria.modelo.Veterinario;
import com.mycompany.clinicaveterinaria.vista.VistaClinicaVeterinaria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author damia
 */
public class ControladorClinica implements ActionListener{
    private final GestorClinica modelo;
    private final VistaClinicaVeterinaria vista;

    public ControladorClinica(GestorClinica modelo, VistaClinicaVeterinaria vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.setControlador(this);
        this.vista.configurarCamposTipo();
        this.vista.configurarCamposVacuna();
        refrescarPropietarios();
        refrescarMascotas();
        refrescarVeterinarios();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            switch (e.getActionCommand()) {
                case "GUARDAR_PROPIETARIO":
                    guardarPropietario();
                    break;
                case "GUARDAR_MASCOTA":
                    guardarMascota();
                    break;
                case "TIPO_MASCOTA":
                    vista.configurarCamposTipo();
                    break;
                case "VACUNA":
                    vista.configurarCamposVacuna();
                    break;
                case "REGISTRAR_CONSULTA":
                    registrarConsulta();
                    break;
                case "VER_HISTORIAL":
                    verHistorial();
                    break;
                case "GENERAR_COMPROBANTE":
                    generarComprobante();
                    break;
                default:
                    break;
            }
        } catch (IllegalArgumentException | MascotaNoEncontradaException ex) {
            vista.mostrarMensaje(ex.getMessage());
        }
    }

    private void guardarPropietario() {
        modelo.registrarPropietario(vista.getPropDocumento(), vista.getPropNombre(),
                vista.getPropTelefono(), vista.getPropEmail());
        refrescarPropietarios();
        vista.limpiarPropietario();
        vista.mostrarMensaje("Propietario registrado correctamente");
    }

    private void guardarMascota() {
        String idPropietario = extraerId(vista.getPropietarioMascota(), "un propietario");
        int edad = convertirEntero(vista.getMascEdad(), "La edad");
        double peso = convertirDecimal(vista.getMascPeso(), "El peso");

        Mascota m;
        if ("Convencional".equals(vista.getTipoMascota())) {
            m = modelo.registrarMascotaConvencional(idPropietario, vista.getMascNombre(),
                    vista.getMascEspecie(), edad, peso, vista.getRaza(), vista.getEsquemaVacunas());
        } else {
            m = modelo.registrarMascotaExotica(idPropietario, vista.getMascNombre(),
                    vista.getMascEspecie(), edad, peso, vista.getPermisoCITES(),
                    vista.isRequiereSedacion());
        }
        refrescarPropietarios(); 
        refrescarMascotas();
        vista.limpiarMascota();
        vista.mostrarMensaje("Mascota registrada con el código " + m.getId());
    }

    private void registrarConsulta() throws MascotaNoEncontradaException {
        String idMascota = extraerId(vista.getMascotaConsulta(), "una mascota");
        String idVeterinario = extraerId(vista.getVeterinarioConsulta(), "un veterinario");
        boolean vacuna = vista.isVacunaAplicada();
        double valorVacuna = vacuna ? convertirDecimal(vista.getValorVacuna(), "El valor de la vacuna") : 0;

        Consulta consulta = modelo.registrarConsulta(idMascota, idVeterinario,
                vista.getDiagnostico(), vacuna, vista.getNombreVacuna(), valorVacuna,
                vista.getMedicamento(), vista.getNotas());

        vista.limpiarConsulta();
        vista.mostrarMascotas(filasMascotas()); 
        vista.mostrarMensaje("Consulta registrada. Total a pagar: "+ consulta.getFactura().getTotalFormato());
    }

    private void verHistorial() throws MascotaNoEncontradaException {
        String idMascota = extraerId(vista.getMascotaHistorial(), "una mascota");
        vista.mostrarHistorial(filasHistorial(idMascota));
        vista.mostrarComprobante("");
    }

    private void generarComprobante() throws MascotaNoEncontradaException {
        String idMascota = extraerId(vista.getMascotaHistorial(), "una mascota");
        int fila = vista.getFilaHistorialSeleccionada();
        if (fila < 0) {
            vista.mostrarMensaje("Seleccione una consulta de la tabla");
            return;
        }
        Consulta consulta = modelo.historialDe(idMascota).get(fila);
        vista.mostrarComprobante(consulta.getFactura().generarComprobante());
    }


    private void refrescarPropietarios() {
        List<String> items = new ArrayList<>();
        List<Object[]> filas = new ArrayList<>();
        for (Propietario p : modelo.listarPropietarios()) {
            items.add(p.toString());
            filas.add(new Object[]{p.getDocumento(), p.getNombre(), p.getTelefono(), p.getEmail(),
                p.getCantidadMascotas()});
        }
        vista.mostrarPropietarios(filas);
        vista.cargarPropietarios(items);
    }

    private void refrescarMascotas() {
        List<String> items = new ArrayList<>();
        for (Mascota m : modelo.listarMascotas()) {
            items.add(m.toString());
        }
        vista.mostrarMascotas(filasMascotas());
        vista.cargarMascotas(items);
    }

    private void refrescarVeterinarios() {
        List<String> items = new ArrayList<>();
        for (Veterinario v : modelo.listarVeterinarios()) {
            items.add(v.toString());
        }
        vista.cargarVeterinarios(items);
    }

    private List<Object[]> filasMascotas() {
        List<Object[]> filas = new ArrayList<>();
        for (Mascota m : modelo.listarMascotas()) {
            filas.add(new Object[]{m.getId(), m.getNombre(), m.getTipo(), m.getEspecie(), m.getEdad(),
                m.getPeso(), m.getPropietario().getNombre(), m.getDetalleEspecifico()});
        }
        return filas;
    }

    private List<Object[]> filasHistorial(String idMascota) throws MascotaNoEncontradaException {
        List<Object[]> filas = new ArrayList<>();
        int numero = 1;
        for (Consulta c : modelo.historialDe(idMascota)) {
            filas.add(new Object[]{numero++, c.getFechaFormateada(), c.getVeterinario().getNombre(),
                c.getDiagnostico(), c.isVacunaAplicada() ? c.getNombreVacuna() : "-",
                c.getMedicamento().isEmpty() ? "-" : c.getMedicamento(),
                c.getFactura().getTotalFormato()});
        }
        return filas;
    }


    private String extraerId(String item, String queSeleccionar) {
        if (item == null || item.trim().isEmpty()) {
            throw new IllegalArgumentException("Seleccione " + queSeleccionar);
        }
        int pos = item.indexOf(" - ");
        return pos < 0 ? item.trim() : item.substring(0, pos).trim();
    }

    private int convertirEntero(String texto, String campo) {
        try {
            return Integer.parseInt(texto.trim());
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(campo + " debe ser un número entero");
        }
    }

    private double convertirDecimal(String texto, String campo) {
        try {
            return Double.parseDouble(texto.trim().replace(',', '.'));
        } catch (NumberFormatException ex) {
            throw new IllegalArgumentException(campo + " debe ser un número");
        }
    }
}
