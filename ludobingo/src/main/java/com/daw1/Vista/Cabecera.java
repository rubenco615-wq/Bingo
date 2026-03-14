package com.daw1.Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Panel superior de la interfaz que muestra el número extraído del bombo.
 * También contiene los controles del modo automático y extracción manual.
 */
public class Cabecera extends JPanel {
    private static final Color BG_COLOR = new Color(25, 25, 55);
    private static final Color GOLD = new Color(255, 215, 0);

    private final JLabel labelNumero;
    private final JToggleButton btnAuto;
    private final JButton botonExtraer;
    private final JSpinner spinnerVelocidad;

    /**
     * Crea la cabecera con el visor de números y los controles de juego.
     */
    public Cabecera() {
        setLayout(new BorderLayout());
        setBackground(BG_COLOR);
        setBorder(BorderFactory.createEmptyBorder(8, 15, 4, 15));

        labelNumero = new JLabel("--", SwingConstants.CENTER);
        labelNumero.setFont(new Font("Arial", Font.BOLD, 72));
        labelNumero.setForeground(GOLD);
        add(labelNumero, BorderLayout.CENTER);

        JPanel pAuto = new JPanel(new FlowLayout(FlowLayout.CENTER, 14, 2));
        pAuto.setBackground(BG_COLOR);

        btnAuto = new JToggleButton("AUTO");
        configurarBotonAuto();

        botonExtraer = new JButton("Extraer");
        configurarBotonExtraer();

        spinnerVelocidad = new JSpinner(new SpinnerNumberModel(3, 1, 15, 1));
        spinnerVelocidad.setFont(new Font("Arial", Font.BOLD, 13));
        spinnerVelocidad.setPreferredSize(new Dimension(55, 26));
        spinnerVelocidad.setEnabled(false);

        JLabel lblInfo = new JLabel("Velocidad:");
        lblInfo.setForeground(Color.LIGHT_GRAY);
        JLabel lblSeg = new JLabel("seg    |  Columnas    Marcado    Error");
        lblSeg.setForeground(Color.LIGHT_GRAY);

        pAuto.add(btnAuto);
        pAuto.add(botonExtraer);
        pAuto.add(lblInfo);
        pAuto.add(spinnerVelocidad);
        pAuto.add(lblSeg);
        add(pAuto, BorderLayout.SOUTH);
    }

    /**
     * Establece el número a mostrar en el visor central.
     * 
     * @param num El número entero a mostrar.
     */
    public void setNumero(int num) {
        labelNumero.setText(String.format("%02d", num));
    }

    /**
     * Establece el texto a mostrar en el visor central.
     * 
     * @param txt El texto a mostrar.
     */
    public void setNumero(String txt) {
        labelNumero.setText(txt);
    }

    /**
     * Reinicia el visor a su estado inicial (--).
     */
    public void resetNumero() {
        labelNumero.setText("--");
    }

    /**
     * Obtiene el botón de alternancia del modo automático.
     * 
     * @return El botón toggle "AUTO".
     */
    public JToggleButton getBtnAuto() {
        return btnAuto;
    }

    /**
     * Obtiene el botón para extraer un número manualmente.
     * 
     * @return El botón "Extraer".
     */
    public JButton getBotonExtraer() {
        return botonExtraer;
    }

    /**
     * Obtiene el selector de velocidad para el modo automático.
     * 
     * @return El componente JSpinner.
     */
    public JSpinner getSpinnerVelocidad() {
        return spinnerVelocidad;
    }

    /**
     * Configura el estilo visual y el comportamiento básico del botón AUTO.
     */
    private void configurarBotonAuto() {
        btnAuto.setFont(new Font("Arial", Font.BOLD, 16));
        btnAuto.setPreferredSize(new Dimension(160, 45)); // Mismo tamaño que extraer
        btnAuto.setFocusPainted(false);
        btnAuto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAuto.setEnabled(false);

        // Estilo visual fondo carne pastel y texto negro
        btnAuto.setBackground(new Color(255, 230, 210));
        btnAuto.setForeground(Color.BLACK);
        btnAuto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 160, 140), 2),
                BorderFactory.createEmptyBorder(4, 10, 4, 10)));

        // Efecto visual al activarse
        btnAuto.addActionListener(e -> {
            if (btnAuto.isSelected()) {
                btnAuto.setBackground(new Color(180, 230, 180)); // Verde pálido (pastel)
                btnAuto.setForeground(Color.BLACK);
                btnAuto.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(130, 180, 130), 2),
                        BorderFactory.createEmptyBorder(4, 10, 4, 10)));
                btnAuto.setText("AUTO ON");
            } else {
                btnAuto.setBackground(new Color(255, 230, 210));
                btnAuto.setForeground(Color.BLACK);
                btnAuto.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 160, 140), 2),
                        BorderFactory.createEmptyBorder(4, 10, 4, 10)));
                btnAuto.setText("AUTO");
            }
        });
    }

    /**
     * Configura el estilo visual y los efectos del botón Extraer.
     */
    private void configurarBotonExtraer() {
        botonExtraer.setFont(new Font("Arial", Font.BOLD, 16));
        botonExtraer.setPreferredSize(new Dimension(160, 45)); // Mismo tamaño
        botonExtraer.setFocusPainted(false);
        botonExtraer.setCursor(new Cursor(Cursor.HAND_CURSOR));
        botonExtraer.setEnabled(false);

        botonExtraer.setBackground(new Color(255, 230, 210));
        botonExtraer.setForeground(Color.BLACK);
        botonExtraer.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 160, 140), 2),
                BorderFactory.createEmptyBorder(4, 10, 4, 10)));

        botonExtraer.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                if (botonExtraer.isEnabled()) {
                    botonExtraer.setBackground(new Color(255, 245, 235)); // Fondo más claro al pasar el ratón (más
                                                                          // pastel)
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (botonExtraer.isEnabled()) {
                    botonExtraer.setBackground(new Color(255, 230, 210)); // Vuelve a su carne pastel normal
                }
            }
        });

        // Listener para que cuando se deshabilite/habilite su color se actualice bien
        botonExtraer.addPropertyChangeListener("enabled", evt -> {
            if (botonExtraer.isEnabled()) {
                botonExtraer.setBackground(new Color(255, 230, 210));
                botonExtraer.setForeground(Color.BLACK);
                botonExtraer.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(200, 160, 140), 2),
                        BorderFactory.createEmptyBorder(4, 10, 4, 10)));
            } else {
                botonExtraer.setBackground(new Color(255, 230, 210));
                botonExtraer.setForeground(Color.GRAY);
                botonExtraer.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.GRAY, 2),
                        BorderFactory.createEmptyBorder(4, 10, 4, 10)));
            }
        });
    }
}