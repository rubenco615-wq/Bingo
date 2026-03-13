package com.daw1.Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Panel de transición que se muestra cuando un jugador canta Bingo.
 * Muestra el nombre del ganador sobre un fondo festivo y permite volver al menú.
 */
public class PanelVictoria extends JPanel {

    private Image imagenVictoria;

    /**
     * Crea el panel de victoria configurando el mensaje según quién haya ganado.
     * 
     * @param nombreGanador El nombre del ganador.
     * @param esMaquina true si el ganador ha sido la máquina.
     */
    public PanelVictoria(String nombreGanador, boolean esMaquina) {
        setLayout(new BorderLayout());

        // la imagen de fondo
        try {
            imagenVictoria = new ImageIcon(getClass().getResource("/imagen/Monedas_Bingo.jpg")).getImage();
        } catch (Exception e) {
            System.err.println("No se pudo cargar la imagen de victoria: " + e.getMessage());
        }

        // Etiqueta con el mensaje de victoria diferenciado
        String mensajeVictoria;
        if (esMaquina) {
            mensajeVictoria = "¡LA MÁQUINA HA GANADO!";
        } else {
            mensajeVictoria = "¡FELICIDADES " + nombreGanador.toUpperCase() + ", HAS GANADO!";
        }

        JLabel lblTitulo = new JLabel(mensajeVictoria, SwingConstants.CENTER) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(0, 0, 0, 140));
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 0, 0);
                g2.dispose();
                super.paintComponent(g);
            }
        };
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 46));
        lblTitulo.setForeground(new Color(255, 215, 0));
        lblTitulo.setOpaque(false);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(18, 10, 18, 10));
        add(lblTitulo, BorderLayout.NORTH);

        // Boton para volver al menu principal
        JButton btnMenu = new JButton("VOLVER AL MENÚ PRINCIPAL");
        btnMenu.setFont(new Font("Arial", Font.BOLD, 20));
        btnMenu.setPreferredSize(new Dimension(380, 55));
        btnMenu.setBackground(new Color(45, 170, 45));
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFocusPainted(false);
        btnMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Al pulsar este boton, limpiamos todo y volvemos al menu de inicio
        btnMenu.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (frame != null) {
                frame.getContentPane().removeAll();
                frame.add(new PanelInicio()); // Cargamos el menu otra vez
                frame.setTitle("LUDOVINGO");
                frame.setSize(800, 500); // Tamaño pequeño para el menu
                frame.setLocationRelativeTo(null);
                frame.revalidate();
                frame.repaint();
            }
        });

        JPanel pSur = new JPanel(new FlowLayout(FlowLayout.CENTER)) {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setColor(new Color(0, 0, 0, 130));
                g2.fillRect(0, 0, getWidth(), getHeight());
                g2.dispose();
                super.paintComponent(g);
            }
        };
        pSur.setOpaque(false);
        pSur.setBorder(BorderFactory.createEmptyBorder(12, 10, 18, 10));
        pSur.add(btnMenu);
        add(pSur, BorderLayout.SOUTH);
    }

    /**
     * Dibuja la imagen de victoria ocupando todo el fondo del panel.
     * 
     * @param g El contexto gráfico.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenVictoria != null) {
            g.drawImage(imagenVictoria, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
