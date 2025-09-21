package com.mycompany.farmacia;

import javax.swing.*;
import java.awt.*;

public class ResumenPedido extends JFrame {

    public ResumenPedido(String nombre, String tipo, int cantidad,
                         String distribuidor, boolean sucPrincipal, boolean sucSecundaria) {

        setTitle("Pedido al distribuidor " + distribuidor);
        setSize(520, 220);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Texto: "X unidades del T M"
        String linea1 = cantidad + " unidades del " + tipo + " " + nombre;

        // Direcciones por sucursal
        String dirPrincipal = "Calle de la Rosa n. 28";
        String dirSecundaria = "Calle Alcazabilla n. 3";

        String linea2;
        if (sucPrincipal && sucSecundaria) {
            linea2 = "Para la farmacia situada en " + dirPrincipal +
                     " y para la situada en " + dirSecundaria + ".";
        } else if (sucPrincipal) {
            linea2 = "Para la farmacia situada en " + dirPrincipal + ".";
        } else { // solo secundaria
            linea2 = "Para la farmacia situada en " + dirSecundaria + ".";
        }

        // Panel central con labels
        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro, BoxLayout.Y_AXIS));
        centro.add(new JLabel(linea1));
        centro.add(Box.createVerticalStrut(8));
        centro.add(new JLabel(linea2));

        // Botones
        JButton btnCancelar = new JButton("Cancelar");
        JButton btnEnviar = new JButton("Enviar pedido");

        JPanel abajo = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        abajo.add(btnCancelar);
        abajo.add(btnEnviar);

        add(centro, BorderLayout.CENTER);
        add(abajo, BorderLayout.SOUTH);

        // Acciones
        btnCancelar.addActionListener(e -> dispose());

        btnEnviar.addActionListener(e -> {
            System.out.println("Pedido enviado");
            JOptionPane.showMessageDialog(this, "Pedido enviado ✅", "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });
    }
}
