package clases.Vista;

import javax.swing.*;
import java.awt.*;

// Panel inferior que contiene los controles principales del juego.
public class Botones extends JPanel {
    private final JButton botonIniciar;
    private final JButton botonExtraer;
    private final JButton botonFinalizar;

    public Botones() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 8));
        setBackground(new Color(25, 25, 55));

        botonIniciar = crearBoton(" Iniciar Partida", new Color(45, 170, 45));
        botonExtraer = crearBoton("Extraer Número", new Color(45, 95, 195));
        botonFinalizar = crearBoton("Finalizar", new Color(190, 45, 45));

        botonExtraer.setEnabled(false);
        botonFinalizar.setEnabled(false);

        add(botonIniciar);
        add(botonExtraer);
        add(botonFinalizar);
    }

    public JButton getBotonIniciar() {
        return botonIniciar;
    }

    public JButton getBotonExtraer() {
        return botonExtraer;
    }

    public JButton getBotonFinalizar() {
        return botonFinalizar;
    }

    private JButton crearBoton(String texto, Color color) {
        JButton b = new JButton(texto);
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(180, 40));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        return b;
    }
}
