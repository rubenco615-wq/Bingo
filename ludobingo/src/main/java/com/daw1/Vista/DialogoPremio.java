package com.daw1.Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Diálogo personalizado para anunciar premios (Línea o Bingo).
 * Utiliza un diseño sin bordes y un mensaje en formato HTML para resaltar el ganador.
 */
public class DialogoPremio extends JDialog {

    /**
     * Crea un nuevo diálogo de premio.
     * 
     * @param p El frame padre.
     * @param title El título de la ventana.
     * @param msg El mensaje de felicitación.
     * @param bg El color de fondo del diálogo.
     */
    public DialogoPremio(Frame p, String title, String msg, Color bg) {
        super(p, title, true);
        setUndecorated(true);

        JPanel main = new JPanel(new BorderLayout(15, 15));
        main.setBackground(bg);
        main.setBorder(BorderFactory.createLineBorder(Color.WHITE, 4));

        // Creamos un panel para meter las cosas dentro
        String htmlMsg = String.format("<html><div style='text-align:center;'>%s</div></html>",
                msg.replace("\n", "<br>"));
        JLabel lbl = new JLabel(htmlMsg, SwingConstants.CENTER);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 28));
        lbl.setForeground(Color.WHITE);
        lbl.setBorder(BorderFactory.createEmptyBorder(30, 40, 10, 40));

        // crear el boton de genial
        JButton btn = new JButton("¡GENIAL!");
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setFocusPainted(false);
        btn.setBackground(Color.WHITE);
        btn.setForeground(bg);
        btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btn.addActionListener(e -> dispose());

        // crear el panel para el boton de genial
        JPanel pBtn = new JPanel();
        pBtn.setOpaque(false);
        pBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 25, 0));
        pBtn.add(btn);

        main.add(lbl, BorderLayout.CENTER);
        main.add(pBtn, BorderLayout.SOUTH);
        add(main);
        pack();
        setLocationRelativeTo(p);
    }
}
