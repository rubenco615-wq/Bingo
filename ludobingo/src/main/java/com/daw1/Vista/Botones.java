package com.daw1.Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Panel que contiene los botones de control principales del juego.
 * Se ubica usualmente en la parte inferior de la ventana principal.
 */
public class Botones extends JPanel {
    private final JButton botonIniciar;
    private final JButton botonFinalizar;
    private final JButton botonAjustes;

    /**
     * Inicializa el panel y crea los botones con sus estilos visuales.
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
     * Obtiene el botón para iniciar una nueva partida.
     * 
     * @return El botón de inicio.
     */
    public JButton getBotonIniciar() {
        return botonIniciar;
    }

    /**
     * Obtiene el botón para finalizar la partida actual.
     * 
     * @return El botón de finalizar.
     */
    public JButton getBotonFinalizar() {
        return botonFinalizar;
    }

    /**
     * Obtiene el botón para abrir el diálogo de ajustes.
     * 
     * @return El botón de ajustes.
     */
    public JButton getBotonAjustes() {
        return botonAjustes;
    }

    /**
     * Crea un botón configurado con el estilo común del panel.
     * 
     * @param texto El texto a mostrar en el botón.
     * @param color El color de fondo del botón.
     * @return Un objeto JButton configurado.
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