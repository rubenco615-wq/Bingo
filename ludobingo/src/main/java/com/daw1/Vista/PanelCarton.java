package com.daw1.Vista;

import com.daw1.model.Carton;
import com.daw1.model.Participante;
import javax.swing.*;
import java.awt.*;

/**
 * Representa el carton del jugador/ maquina
 */
public class PanelCarton extends JPanel {

    private JLabel labelTitulo;
    private JButton[] celdas;
    private JPanel grid;
    private Icon iconoVacio;

    /**
     * Crea un nuevo panel para un cartón de bingo.
     * 
     * @param titulo       El título a mostrar sobre el cartón.
     * @param colorBase    El color temático de este cartón.
     * @param esMaquina    Indica si el cartón pertenece a la máquina (deshabilita
     *                     clics).
     * @param nombreImagen El nombre del archivo de imagen para las casillas vacías.
     */
    public PanelCarton(String titulo, Color colorBase, boolean esMaquina, String nombreImagen) {
        setLayout(new BorderLayout(5, 5));
        setBackground(new Color(235, 235, 250));

        this.iconoVacio = crearIcono(nombreImagen);

        labelTitulo = new JLabel(titulo, SwingConstants.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        labelTitulo.setForeground(colorBase);
        labelTitulo.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        add(labelTitulo, BorderLayout.NORTH);

        grid = new JPanel(new GridLayout(3, 9, 4, 4));
        grid.setBackground(colorBase);
        grid.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));

        celdas = new JButton[27];
        for (int i = 0; i < 27; i++) {
            celdas[i] = new JButton("");
            celdas[i].setFont(new Font("Arial", Font.BOLD, 22));
            celdas[i].setPreferredSize(new Dimension(65, 65));
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

    /**
     * Crea un icono personalizado a partir de una imagen para las celdas sin
     * número.
     * 
     * @param nombreImagen Nombre de la imagen
     * @return Un objeto Icon con la imagen escalada
     */
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

    /**
     * Cambia el título que se muestra sobre el cartón
     * 
     * @param txt El nuevo título
     */
    public void setTitulo(String txt) {
        labelTitulo.setText(txt);
    }

    /**
     * Obtiene el array de botones que forman las celdas del cartón
     * 
     * @return Array de JButtons (27 elementos)
     */
    public JButton[] getCeldas() {
        return celdas;
    }

    /**
     * Sincroniza el dibujo del cartón con los datos del jugador
     * 
     * @param p             El jugador asociado al panel
     * @param partidaActiva Estado actual de la partida
     */
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

    /**
     * Restablece todas las celdas del cartón a su estado inicial vacío
     */
    private void limpiarCarton() {
        for (JButton b : celdas) {
            b.setText("");
            b.setBackground(Color.WHITE);
            b.setIcon(null);
        }
    }

    /**
     * Actualiza el estado de una celda
     * 
     * 
     * @param indice        El índice lineal de la celda
     * @param carton        El modelo del cartón del que obtener los datos
     * @param partidaActiva Si la partida está activa
     */
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

    /**
     * Configura la celda como una casilla vacía (sin número) mostrando el icono
     * 
     * @param celda El botón de la celda
     */
    private void configurarCeldaVacia(JButton celda) {
        celda.setText("");
        celda.setIcon(iconoVacio);
        celda.setBackground(new Color(210, 210, 225));
        celda.setEnabled(false);
    }

    /**
     * Configura la celda para mostrar un número y su estado de marcado
     * 
     * @param celda         El botón de la celda
     * @param num           El número a mostrar
     * @param marcado       true si el número está marcado
     * @param col           Índice de la columna para aplicar estilo alterno
     * @param partidaActiva Indica si la celda debe estar habilitada para
     *                      interacción.
     */
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
