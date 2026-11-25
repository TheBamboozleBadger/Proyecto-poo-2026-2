/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.clinicaveterinaria.main;

import com.mycompany.clinicaveterinaria.controlador.ControladorClinica;
import com.mycompany.clinicaveterinaria.modelo.GestorClinica;
import com.mycompany.clinicaveterinaria.modelo.Veterinario;
import com.mycompany.clinicaveterinaria.vista.VistaClinicaVeterinaria;

/**
 *
 * @author damia
 */
public class ClinicaVeterinariaMain {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            GestorClinica modelo = new GestorClinica();

            modelo.registrarVeterinario(new Veterinario("V01", "Laura Gómez", "Medicina general", "TP-1001"));
            modelo.registrarVeterinario(new Veterinario("V02", "Carlos Ruiz", "Fauna exótica", "TP-2002"));

            VistaClinicaVeterinaria vista = new VistaClinicaVeterinaria();
            new ControladorClinica(modelo, vista);

            vista.setLocationRelativeTo(null);
            vista.setVisible(true);
        });
    }
}
