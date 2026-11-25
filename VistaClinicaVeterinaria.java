/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.clinicaveterinaria.vista;

import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author damia
 */
public class VistaClinicaVeterinaria extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(VistaClinicaVeterinaria.class.getName());

    /**
     * Creates new form VistaClinica
     */
    public VistaClinicaVeterinaria() {
        initComponents();
    }
    public void setControlador(ActionListener controlador) {
        btnGuardarPropietario.setActionCommand("GUARDAR_PROPIETARIO");
        btnGuardarMascota.setActionCommand("GUARDAR_MASCOTA");
        cbTipoMascota.setActionCommand("TIPO_MASCOTA");
        chkVacuna.setActionCommand("VACUNA");
        btnRegistrarConsulta.setActionCommand("REGISTRAR_CONSULTA");
        btnVerHistorial.setActionCommand("VER_HISTORIAL");
        btnGenerarComprobante.setActionCommand("GENERAR_COMPROBANTE");

        btnGuardarPropietario.addActionListener(controlador);
        btnGuardarMascota.addActionListener(controlador);
        cbTipoMascota.addActionListener(controlador);
        chkVacuna.addActionListener(controlador);
        btnRegistrarConsulta.addActionListener(controlador);
        btnVerHistorial.addActionListener(controlador);
        btnGenerarComprobante.addActionListener(controlador);
    }
    
    public String getPropDocumento(){ 
        return txtPropDocumento.getText(); 
    }
    public String getPropNombre(){ 
        return txtPropNombre.getText(); 
    }
    public String getPropTelefono(){ 
        return txtPropTelefono.getText(); 
    }
    public String getPropEmail(){ 
        return txtPropEmail.getText(); 
    }

    public String getPropietarioMascota(){ 
        return itemSeleccionado(cbPropietarioMascota); 
    }
    public String getMascNombre(){ 
        return txtMascNombre.getText();
    }
    public String getMascEspecie(){ 
        return txtMascEspecie.getText(); }
    public String getMascEdad(){ 
        return txtMascEdad.getText(); 
    }
    public String getMascPeso(){ 
        return txtMascPeso.getText(); 
    }
    public String getTipoMascota(){ 
        return itemSeleccionado(cbTipoMascota);
    }
    public String getRaza(){ 
        return txtRaza.getText(); 
    }
    public String getEsquemaVacunas(){
        return txtEsquemaVacunas.getText(); 
    }
    public String getPermisoCITES(){ 
        return txtPermisoCITES.getText();
    }
    public boolean isRequiereSedacion(){ 
        return chkSedacion.isSelected(); 
    }

    public String getMascotaConsulta(){ 
        return itemSeleccionado(cbMascotaConsulta);
    }
    public String getVeterinarioConsulta(){ 
        return itemSeleccionado(cbVeterinarioConsulta);
    }
    public String getDiagnostico(){ 
        return txtDiagnostico.getText(); 
    }
    public String getMedicamento(){ 
        return txtMedicamento.getText(); 
    }
    public boolean isVacunaAplicada(){ 
        return chkVacuna.isSelected();
    }
    public String getNombreVacuna(){ 
        return txtNombreVacuna.getText(); 
    }
    public String getValorVacuna(){ 
        return txtValorVacuna.getText();
    }
    public String getNotas(){ 
        return txtNotas.getText(); 
    }

    public String getMascotaHistorial(){ 
        return itemSeleccionado(cbMascotaHistorial);
    }

    public int getFilaHistorialSeleccionada() {
        int fila = tblHistorial.getSelectedRow();
        return fila < 0 ? -1 : tblHistorial.convertRowIndexToModel(fila);
    }

    private String itemSeleccionado(JComboBox<String> combo) {
        Object item = combo.getSelectedItem();
        return item == null ? "" : item.toString();
    }
    
    public void mostrarPropietarios(List<Object[]> filas) {
        llenarTabla(tblPropietarios, filas);
    }

    public void mostrarMascotas(List<Object[]> filas) {
        llenarTabla(tblMascotas, filas);
    }

    public void mostrarHistorial(List<Object[]> filas) {
        llenarTabla(tblHistorial, filas);
    }

    private void llenarTabla(JTable tabla, List<Object[]> filas) {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();
        modelo.setRowCount(0);
        for (Object[] fila : filas) {
            modelo.addRow(fila);
        }
        tabla.setDefaultEditor(Object.class, null);
    }

    public void cargarPropietarios(List<String> items) {
        llenarCombo(cbPropietarioMascota, items);
    }

    public void cargarMascotas(List<String> items) {
        llenarCombo(cbMascotaConsulta, items);
        llenarCombo(cbMascotaHistorial, items);
    }

    public void cargarVeterinarios(List<String> items) {
        llenarCombo(cbVeterinarioConsulta, items);
    }

    private void llenarCombo(JComboBox<String> combo, List<String> items) {
        combo.removeAllItems();
        for (String item : items) {
            combo.addItem(item);
        }
    }

    public void seleccionarMascotaHistorial(String item) {
        cbMascotaHistorial.setSelectedItem(item);
    }

    public void seleccionarPestana(int indice) {
        tabPrincipal.setSelectedIndex(indice);
    }

    public void mostrarComprobante(String texto) {
        txtComprobante.setText(texto);
        txtComprobante.setCaretPosition(0);
    }

    public void mostrarMensaje(String mensaje) {
        JOptionPane.showMessageDialog(this, mensaje);
    }
    
    public void configurarCamposTipo() {
        boolean convencional = "Convencional".equals(getTipoMascota());
        txtRaza.setEnabled(convencional);
        txtEsquemaVacunas.setEnabled(convencional);
        txtPermisoCITES.setEnabled(!convencional);
        chkSedacion.setEnabled(!convencional);
    }

    public void configurarCamposVacuna() {
        boolean aplicada = chkVacuna.isSelected();
        txtNombreVacuna.setEnabled(aplicada);
        txtValorVacuna.setEnabled(aplicada);
    }


    public void limpiarPropietario() {
        txtPropDocumento.setText("");
        txtPropNombre.setText("");
        txtPropTelefono.setText("");
        txtPropEmail.setText("");
        txtPropDocumento.requestFocus();
    }

    public void limpiarMascota() {
        txtMascNombre.setText("");
        txtMascEspecie.setText("");
        txtMascEdad.setText("");
        txtMascPeso.setText("");
        txtRaza.setText("");
        txtEsquemaVacunas.setText("");
        txtPermisoCITES.setText("");
        chkSedacion.setSelected(false);
        txtMascNombre.requestFocus();
    }

    public void limpiarConsulta() {
        txtDiagnostico.setText("");
        txtMedicamento.setText("");
        chkVacuna.setSelected(false);
        txtNombreVacuna.setText("");
        txtValorVacuna.setText("");
        txtNotas.setText("");
        configurarCamposVacuna();
    }
    

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabPrincipal = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        txtPropTelefono = new javax.swing.JTextField();
        txtPropEmail = new javax.swing.JTextField();
        btnGuardarPropietario = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPropietarios = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtPropDocumento = new javax.swing.JTextField();
        txtPropNombre = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        cbPropietarioMascota = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtMascNombre = new javax.swing.JTextField();
        txtMascEspecie = new javax.swing.JTextField();
        txtMascEdad = new javax.swing.JTextField();
        txtMascPeso = new javax.swing.JTextField();
        cbTipoMascota = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtRaza = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtEsquemaVacunas = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPermisoCITES = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        chkSedacion = new javax.swing.JCheckBox();
        btnGuardarMascota = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblMascotas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        cbMascotaConsulta = new javax.swing.JComboBox<>();
        cbVeterinarioConsulta = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        txtDiagnostico = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtMedicamento = new javax.swing.JTextField();
        chkVacuna = new javax.swing.JCheckBox();
        jLabel21 = new javax.swing.JLabel();
        txtValorVacuna = new javax.swing.JTextField();
        txtNombreVacuna = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtNotas = new javax.swing.JTextArea();
        btnRegistrarConsulta = new javax.swing.JButton();
        jLabel20 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        cbMascotaHistorial = new javax.swing.JComboBox<>();
        btnVerHistorial = new javax.swing.JButton();
        btnGenerarComprobante = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        tblHistorial = new javax.swing.JTable();
        jScrollPane5 = new javax.swing.JScrollPane();
        txtComprobante = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnGuardarPropietario.setText("Registrar Propietario");

        tblPropietarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Documento", "Nombre", "Telefono", "Correo", "# Mascotas"
            }
        ));
        jScrollPane1.setViewportView(tblPropietarios);

        jLabel1.setText("Documento:");

        jLabel2.setText("Nombre:");

        jLabel3.setText("Telefono:");

        jLabel4.setText("Correo:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPropDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPropNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPropTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtPropEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnGuardarPropietario)))
                .addGap(46, 46, 46)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 714, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(43, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtPropDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtPropNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtPropTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtPropEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarPropietario))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(155, Short.MAX_VALUE))
        );

        tabPrincipal.addTab("Propietarios", jPanel1);

        jLabel5.setText("Propietario:");

        jLabel6.setText("Nombre:");

        jLabel7.setText("Especie:");

        jLabel8.setText("Edad:");

        jLabel9.setText("Peso:");

        cbTipoMascota.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Convencional", "Exótica" }));

        jLabel10.setText("Tipo:");

        jLabel11.setText("Raza(convencional):");

        jLabel12.setText("Vacunas(convencional):");

        jLabel13.setText("CITES(exotica):");

        jLabel14.setText("Sedación(exotica):");

        btnGuardarMascota.setText("Registrar Mascota");

        tblMascotas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Codigo", "Nombre", "Tipo", "Especie", "Edad", "Peso", "Propietario", "Detalle"
            }
        ));
        jScrollPane2.setViewportView(tblMascotas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 70, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cbPropietarioMascota, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtMascNombre)
                                    .addComponent(txtMascEspecie)
                                    .addComponent(txtMascEdad)
                                    .addComponent(cbTipoMascota, 0, 129, Short.MAX_VALUE)
                                    .addComponent(txtMascPeso)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel14)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(chkSedacion)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtEsquemaVacunas, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                    .addComponent(txtRaza)
                                    .addComponent(txtPermisoCITES))))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(btnGuardarMascota)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(cbPropietarioMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtMascNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtMascEspecie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtMascEdad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtMascPeso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(cbTipoMascota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtRaza, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtEsquemaVacunas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtPermisoCITES, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 356, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chkSedacion)
                    .addComponent(jLabel14))
                .addGap(18, 18, 18)
                .addComponent(btnGuardarMascota)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tabPrincipal.addTab("Mascotas", jPanel2);

        jLabel15.setText("Mascota:");

        jLabel16.setText("Veterinario:");

        jLabel17.setText("Diagnostico:");

        jLabel18.setText("Medicamento:");

        jLabel19.setText("Se aplicó vacuna:");

        jLabel21.setText("Valor:");

        jLabel22.setText("Vacuna:");

        txtNotas.setColumns(20);
        txtNotas.setRows(5);
        jScrollPane3.setViewportView(txtNotas);

        btnRegistrarConsulta.setText("Registrar Consulta");

        jLabel20.setText("Notas:");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel22)
                            .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel15)
                                        .addComponent(jLabel16)
                                        .addComponent(jLabel17))
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(cbVeterinarioConsulta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(txtDiagnostico)
                                        .addComponent(cbMascotaConsulta, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGroup(jPanel3Layout.createSequentialGroup()
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel19)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                                            .addGroup(jPanel3Layout.createSequentialGroup()
                                                .addComponent(jLabel18)
                                                .addGap(21, 21, 21)))
                                        .addGroup(jPanel3Layout.createSequentialGroup()
                                            .addComponent(jLabel21)
                                            .addGap(67, 67, 67)))
                                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtMedicamento)
                                        .addComponent(chkVacuna)
                                        .addComponent(txtNombreVacuna)
                                        .addComponent(txtValorVacuna, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(73, 73, 73)
                        .addComponent(btnRegistrarConsulta)))
                .addContainerGap(684, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(cbMascotaConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(cbVeterinarioConsulta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(txtDiagnostico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(txtMedicamento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(chkVacuna))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(txtNombreVacuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(txtValorVacuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(btnRegistrarConsulta)
                .addContainerGap(25, Short.MAX_VALUE))
        );

        tabPrincipal.addTab("Consultas", jPanel3);

        jLabel23.setText("Mascota:");

        btnVerHistorial.setText("Ver Historial");

        btnGenerarComprobante.setText("Generar Comprobante");

        tblHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "", "Fecha", "Veterinario", "Diagnostico", "Vacuna", "Medicamento", "Total"
            }
        ));
        jScrollPane4.setViewportView(tblHistorial);

        txtComprobante.setColumns(20);
        txtComprobante.setRows(5);
        jScrollPane5.setViewportView(txtComprobante);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnVerHistorial)
                        .addGap(71, 71, 71))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnGenerarComprobante)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel23)
                                .addGap(35, 35, 35)
                                .addComponent(cbMascotaHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 658, Short.MAX_VALUE)
                    .addComponent(jScrollPane5))
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel23)
                            .addComponent(cbMascotaHistorial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(63, 63, 63)
                        .addComponent(btnVerHistorial))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(27, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGenerarComprobante)
                        .addGap(105, 105, 105))))
        );

        tabPrincipal.addTab("Historial y cobro", jPanel4);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPrincipal)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(tabPrincipal)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGenerarComprobante;
    private javax.swing.JButton btnGuardarMascota;
    private javax.swing.JButton btnGuardarPropietario;
    private javax.swing.JButton btnRegistrarConsulta;
    private javax.swing.JButton btnVerHistorial;
    private javax.swing.JComboBox<String> cbMascotaConsulta;
    private javax.swing.JComboBox<String> cbMascotaHistorial;
    private javax.swing.JComboBox<String> cbPropietarioMascota;
    private javax.swing.JComboBox<String> cbTipoMascota;
    private javax.swing.JComboBox<String> cbVeterinarioConsulta;
    private javax.swing.JCheckBox chkSedacion;
    private javax.swing.JCheckBox chkVacuna;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTabbedPane tabPrincipal;
    private javax.swing.JTable tblHistorial;
    private javax.swing.JTable tblMascotas;
    private javax.swing.JTable tblPropietarios;
    private javax.swing.JTextArea txtComprobante;
    private javax.swing.JTextField txtDiagnostico;
    private javax.swing.JTextField txtEsquemaVacunas;
    private javax.swing.JTextField txtMascEdad;
    private javax.swing.JTextField txtMascEspecie;
    private javax.swing.JTextField txtMascNombre;
    private javax.swing.JTextField txtMascPeso;
    private javax.swing.JTextField txtMedicamento;
    private javax.swing.JTextField txtNombreVacuna;
    private javax.swing.JTextArea txtNotas;
    private javax.swing.JTextField txtPermisoCITES;
    private javax.swing.JTextField txtPropDocumento;
    private javax.swing.JTextField txtPropEmail;
    private javax.swing.JTextField txtPropNombre;
    private javax.swing.JTextField txtPropTelefono;
    private javax.swing.JTextField txtRaza;
    private javax.swing.JTextField txtValorVacuna;
    // End of variables declaration//GEN-END:variables
}
