package com.mycompany.farmacia;

import javax.swing.*;
import java.awt.*;

public class Pedido extends JFrame {
    // Campos UI
    JTextField txtNombre, txtCantidad;
    JComboBox<String> cmbTipo;
    JRadioButton rCofarma, rEmpsephar, rCemefar;
    JCheckBox chkPrincipal, chkSecundaria;
    JButton btnBorrar, btnConfirmar;

    public Pedido() {
        setTitle("Pedidos Farmacia");
        setSize(460, 360);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Nombre
        add(new JLabel("Nombre del medicamento:"));
        txtNombre = new JTextField();
        add(txtNombre);

        // Tipo (lista exacta del enunciado, en minúsculas)
        add(new JLabel("Tipo de medicamento:"));
        cmbTipo = new JComboBox<>(new String[]{
                "Seleccione...", "analgésico", "analéptico", "anestésico",
                "antiácido", "antidepresivo", "antibiótico"
        });
        add(cmbTipo);

        // Cantidad
        add(new JLabel("Cantidad (entero positivo):"));
        txtCantidad = new JTextField();
        add(txtCantidad);

        // Distribuidor
        add(new JLabel("Distribuidor:"));
        JPanel pDist = new JPanel(new FlowLayout(FlowLayout.LEFT));
        rCofarma = new JRadioButton("Cofarma");
        rEmpsephar = new JRadioButton("Empsephar");
        rCemefar = new JRadioButton("Cemefar");
        ButtonGroup gDist = new ButtonGroup();
        gDist.add(rCofarma); gDist.add(rEmpsephar); gDist.add(rCemefar);
        pDist.add(rCofarma); pDist.add(rEmpsephar); pDist.add(rCemefar);
        add(pDist);

        // Sucursales
        add(new JLabel("Sucursal de destino:"));
        JPanel pSuc = new JPanel(new FlowLayout(FlowLayout.LEFT));
        chkPrincipal = new JCheckBox("Principal");
        chkSecundaria = new JCheckBox("Secundaria");
        pSuc.add(chkPrincipal); pSuc.add(chkSecundaria);
        add(pSuc);

        // Botones
        JPanel pBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnBorrar = new JButton("Borrar");
        btnConfirmar = new JButton("Confirmar");
        pBtns.add(btnBorrar);
        pBtns.add(btnConfirmar);
        add(pBtns);

        // Acción: Borrar
        btnBorrar.addActionListener(e -> {
            txtNombre.setText("");
            cmbTipo.setSelectedIndex(0);
            txtCantidad.setText("");
            gDist.clearSelection();
            chkPrincipal.setSelected(false);
            chkSecundaria.setSelected(false);
            txtNombre.requestFocus();
        });

        // Acción: Confirmar (VALIDACIONES + abrir ResumenPedido)
        btnConfirmar.addActionListener(e -> {
            // Nombre (no vacío y alfanumérico)
            String nombre = txtNombre.getText().trim();
            if (nombre.isEmpty()) {
                JOptionPane.showMessageDialog(this, "El nombre no puede estar vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (!nombre.matches("[\\p{L}0-9 .\\-]+")) { // letras con tildes, dígitos, espacio, punto y guion
                JOptionPane.showMessageDialog(this, "El nombre debe ser alfanumérico.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Tipo seleccionado
            String tipo = (String) cmbTipo.getSelectedItem();
            if (tipo == null || "Seleccione...".equals(tipo)) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un tipo de medicamento.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Cantidad entero positivo
            String cantidadStr = txtCantidad.getText().trim();
            int cantidad;
            try {
                cantidad = Integer.parseInt(cantidadStr);
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(this, "La cantidad debe ser un entero positivo.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "La cantidad debe ser un número entero válido.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Distribuidor elegido
            String distribuidor = null;
            if (rCofarma.isSelected()) distribuidor = "Cofarma";
            else if (rEmpsephar.isSelected()) distribuidor = "Empsephar";
            else if (rCemefar.isSelected()) distribuidor = "Cemefar";

            if (distribuidor == null) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar un distribuidor.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Sucursal (al menos una)
            boolean sucP = chkPrincipal.isSelected();
            boolean sucS = chkSecundaria.isSelected();
            if (!sucP && !sucS) {
                JOptionPane.showMessageDialog(this, "Debe seleccionar al menos una sucursal (Principal y/o Secundaria).", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Todo OK → abrir ResumenPedido
            ResumenPedido rp = new ResumenPedido(nombre, tipo, cantidad, distribuidor, sucP, sucS);
            rp.setVisible(true);
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Pedido().setVisible(true));
    }
}
////