package com.daw1.Vista;

import com.daw1.model.Carton;
import com.daw1.model.Participante;
import javax.swing.*;
import java.awt.*;

// Representa visualmente un cartón de bingo (3 filas x 9 columnas).
public class PanelCarton extends JPanel {

    private JLabel labelTitulo;
    private JButton[] celdas;
    private JPanel grid;
    private Icon iconoVacio;

    public PanelCarton(String titulo, Color colorBase, boolean esMaquina, String nombreImagen) {
        setLayout(new BorderLayout(5, 5));
        setBackground(new Color(235, 235, 250));

        this.iconoVacio = crearIcono(nombreImagen);

        labelTitulo = new JLabel(titulo, SwingConstants.LEFT);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        labelTitulo.setForeground(colorBase);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        add(labelTitulo, BorderLayout.NORTH);

        grid = new JPanel(new GridLayout(3, 9, 4, 4));
        grid.setBackground(colorBase);
        grid.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        celdas = new JButton[27];
        for (int i = 0; i < 27; i++) {
            celdas[i] = new JButton("");
            celdas[i].setFont(new Font("Arial", Font.BOLD, 18));
            celdas[i].setPreferredSize(new Dimension(55, 55));
            celdas[i].setFocusPainted(false);
            celdas[i].setBackground(Color.WHITE);
            celdas[i].setForeground(new Color(40, 40, 40));
            celdas[i].setHorizontalAlignment(SwingConstants.CENTER);
            celdas[i].setVerticalAlignment(SwingConstants.CENTER);

            if (esMaquina) {
                celdas[i].setEnabled(false); // La máquina no necesita clics
                celdas[i].setCursor(null);
            } else {
                celdas[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            grid.add(celdas[i]);
        }
        add(grid, BorderLayout.CENTER);
    }

    // metodo para crear la foto del pinguino de cada carton
    private Icon crearIcono(String nombreImagen) {
        try {
            final Image img = new ImageIcon(getClass().getResource("/imagen/" + nombreImagen)).getImage();
            return new Icon() {
                @Override
                public void paintIcon(Component c, Graphics g, int x, int y) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
                    g2.drawImage(img, x, y, getIconWidth(), getIconHeight(), null);
                    g2.dispose();
                }

                @Override
                public int getIconWidth() {
                    return 42;
                }

                @Override
                public int getIconHeight() {
                    return 42;
                }
            };
        } catch (Exception e) {
            return null;
        }
    }

    public void setTitulo(String txt) {
        labelTitulo.setText(txt);
    }

    public JButton[] getCeldas() {
        return celdas;
    }

    // Sincroniza el dibujo del cartón con los datos reales del Participante.
    public void actualizarCarton(Participante p, boolean partidaActiva) {
        if (p == null) {
            limpiarCarton();
            return;
        }

        Carton carton = p.getCarton();
        for (int i = 0; i < celdas.length; i++) {
            actualizarCelda(i, carton, partidaActiva);
        }
    }

    private void limpiarCarton() {
        for (JButton b : celdas) {
            b.setText("");
            b.setBackground(Color.WHITE);
            b.setIcon(null);
        }
    }

    private void actualizarCelda(int indice, Carton carton, boolean partidaActiva) {
        int fila = indice / Carton.COLUMNAS;
        int col = indice % Carton.COLUMNAS;
        int num = carton.getNumero(fila, col);
        JButton celda = celdas[indice];

        if (num == 0) {
            configurarCeldaVacia(celda);
        } else {
            configurarCeldaConNumero(celda, num, carton.isMarcado(fila, col), col, partidaActiva);
        }
    }

    private void configurarCeldaVacia(JButton celda) {
        celda.setText("");
        celda.setIcon(iconoVacio);
        celda.setBackground(new Color(210, 210, 225));
        celda.setEnabled(false);
    }

    private void configurarCeldaConNumero(JButton celda, int num, boolean marcado, int col, boolean partidaActiva) {
        celda.setText(String.valueOf(num));
        celda.setIcon(null);
        celda.setEnabled(partidaActiva);

        if (marcado) {
            celda.setBackground(new Color(255, 230, 100)); // Amarillo (Marcado)
        } else {
            // Fondo blanco con celdas alternas para mejor legibilidad
            celda.setBackground((col % 2 == 0) ? new Color(245, 250, 255) : Color.WHITE);
        }
    }
}
