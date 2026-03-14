package com.daw1.Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Cabecera que muestra el número y los botones para extraer de forma manual o
 * automatica
 */
public class Cabecera extends JPanel {
    private static final Color BG_COLOR = new Color(25, 25, 55);
    private static final Color GOLD = new Color(255, 215, 0);

    private final JLabel labelNumero;
    private final JToggleButton btnAuto;
    private final JButton botonExtraer;
    private final JSpinner spinnerVelocidad;

    /**
     * Crea la cabecera con el número y los botones de juego.
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
     * El numero que sale del bombo
     * 
     * @param num El número
     */
    public void setNumero(int num) {
        labelNumero.setText(String.format("%02d", num));
    }

    /**
     * Establece el texto
     * 
     * @param txt El texto
     */
    public void setNumero(String txt) {
        labelNumero.setText(txt);
    }

    /**
     * Reinicia el bombo( lo deja sin numeros, se queda en --)
     */
    public void resetNumero() {
        labelNumero.setText("--");
    }

    /**
     * El boton de auto
     * 
     * @return El botón de auto
     */
    public JToggleButton getBtnAuto() {
        return btnAuto;
    }

    /**
     * El boton de extraer
     * 
     * @return El botón de extraer
     */
    public JButton getBotonExtraer() {
        return botonExtraer;
    }

    /**
     * El jSlider de velocidad
     * 
     * @return El jSlider de velocidad
     */
    public JSpinner getSpinnerVelocidad() {
        return spinnerVelocidad;
    }

    /**
     * Configura el boton de auto
     */
    private void configurarBotonAuto() {
        btnAuto.setFont(new Font("Arial", Font.BOLD, 16));
        btnAuto.setPreferredSize(new Dimension(160, 45)); // Mismo tamaño que extraer
        btnAuto.setFocusPainted(false);
        btnAuto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAuto.setEnabled(false);

        // fondo carne pastel y texto negro
        btnAuto.setBackground(new Color(255, 230, 210));
        btnAuto.setForeground(Color.BLACK);
        btnAuto.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 160, 140), 2),
                BorderFactory.createEmptyBorder(4, 10, 4, 10)));

        btnAuto.addActionListener(e -> {
            if (btnAuto.isSelected()) {
                btnAuto.setBackground(new Color(180, 230, 180)); //
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
     * Configura el boton de extraer
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
                    botonExtraer.setBackground(new Color(255, 245, 235)); // Fondo más claro al pasar el ratón
                }
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                if (botonExtraer.isEnabled()) {
                    botonExtraer.setBackground(new Color(255, 230, 210)); // Vuelve a su color original
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