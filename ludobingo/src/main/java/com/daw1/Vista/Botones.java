package com.daw1.Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Panel con los botones del juego.
 */
public class Botones extends JPanel {
    private final JButton botonIniciar;
    private final JButton botonFinalizar;
    private final JButton botonAjustes;

    /**
     * pone los botones
     */
    public Botones() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 15, 8));
        setBackground(new Color(25, 25, 55));

        botonIniciar = crearBoton(" Iniciar Partida", new Color(45, 170, 45));
        botonFinalizar = crearBoton("Finalizar Partida", new Color(190, 45, 45));
        botonAjustes = crearBoton("Ajustes", new Color(60, 60, 140));

        botonFinalizar.setEnabled(false);

        add(botonIniciar);
        add(botonFinalizar);
        add(botonAjustes);
    }

    /**
     * Obtiene el botón para iniciar una nueva partida
     * 
     * @return El botón de inicio
     */
    public JButton getBotonIniciar() {
        return botonIniciar;
    }

    /**
     * Obtiene el botón para finalizar la partida
     * 
     * @return El botón de finalizar
     */
    public JButton getBotonFinalizar() {
        return botonFinalizar;
    }

    /**
     * Obtiene el botón para abrir los ajustes
     * 
     * @return El botón de ajustes
     */
    public JButton getBotonAjustes() {
        return botonAjustes;
    }

    /**
     * Crea un botón con el estilo del panel
     * 
     * @param texto El texto del botón
     * @param color El color de fondo del botón
     * @return Un JButton configurado
     */
    private JButton crearBoton(String texto, Color color) {
        JButton b = new JButton(texto);
        b.setFont(new Font("Arial", Font.BOLD, 16));
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setPreferredSize(new Dimension(220, 50));
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setBorder(BorderFactory.createRaisedBevelBorder());
        return b;
    }
}