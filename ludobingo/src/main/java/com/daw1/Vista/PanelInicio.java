package clases.Vista;

import clases.model.Sonido;
import javax.swing.*;
import java.awt.*;
import java.io.File;

// Este panel es el menu principal que ves al abrir el juego
// Aqui puedes darle a jugar o abrir los ajustes del volumen
public class PanelInicio extends JPanel {

    public PanelInicio() {
        setLayout(null); // Layout nulo para control absoluto de posiciones (según diseño original)

        // Botón de jugar
        JButton botonJugar = new JButton("");
        botonJugar.setFont(new Font("Arial", Font.BOLD, 30));
        botonJugar.setOpaque(false);
        botonJugar.setContentAreaFilled(false);
        botonJugar.setBorderPainted(false);
        botonJugar.setFocusPainted(false);
        botonJugar.setPreferredSize(new Dimension(200, 100));
        botonJugar.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(180, 0, 0), 3),
                BorderFactory.createEmptyBorder(5, 15, 5, 15)));
        botonJugar.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Efecto hover
        botonJugar.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent e) {
                botonJugar.setBackground(new Color(255, 230, 50));
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent e) {
                botonJugar.setBackground(new Color(255, 200, 0));
            }
        });

        // Cuando pulsas jugar, paramos la musica de inicio y cambiamos al panel del juego
        botonJugar.addActionListener(e -> {
            Sonido.detenerBGM();
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (frame != null) {
                frame.getContentPane().removeAll(); // Quitamos el menu
                VentanaJuego panelJuego = new VentanaJuego();
                frame.add(panelJuego); // Ponemos el bingo
                frame.setTitle("LUDOVINGO - ¡A jugar!");
                frame.setSize(1250, 620); // Hacemos la ventana mas grande para el carton
                frame.setLocationRelativeTo(null);
                frame.revalidate();
                frame.repaint();
            }
        });

        // Botón de ajustes
        JButton botonAjustes = new JButton();
        File fGear = new File("Juego/src/resources/imagen/gear_icon.png");
        if (fGear.exists()) {
            ImageIcon icon = new ImageIcon(fGear.getAbsolutePath());
            Image scaled = icon.getImage().getScaledInstance(45, 45, Image.SCALE_SMOOTH);
            botonAjustes.setIcon(new ImageIcon(scaled));
        } else {
            botonAjustes.setText("⚙");
            botonAjustes.setFont(new Font("Arial", Font.PLAIN, 26));
        }
        botonAjustes.setForeground(Color.WHITE);
        botonAjustes.setOpaque(false);
        botonAjustes.setContentAreaFilled(false);
        botonAjustes.setBorderPainted(false);
        botonAjustes.setFocusPainted(false);
        botonAjustes.setCursor(new Cursor(Cursor.HAND_CURSOR));

        // Panel de ajustes flotante
        JPanel panelAjustes = new JPanel();
        panelAjustes.setLayout(new BoxLayout(panelAjustes, BoxLayout.Y_AXIS));
        panelAjustes.setBackground(new Color(20, 20, 50, 220));
        panelAjustes.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 180), 1),
                BorderFactory.createEmptyBorder(16, 20, 16, 20)));
        panelAjustes.setVisible(false);

        JLabel lblAjustesTitulo = new JLabel("Ajustes");
        lblAjustesTitulo.setForeground(new Color(255, 215, 0));
        lblAjustesTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblAjustesTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel etiquetaVolumen = new JLabel("Volumen Musica");
        etiquetaVolumen.setForeground(Color.LIGHT_GRAY);
        etiquetaVolumen.setFont(new Font("Arial", Font.BOLD, 13));
        etiquetaVolumen.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSlider sliderVolumen = new JSlider(0, 100, 25);
        sliderVolumen.setOpaque(false);
        sliderVolumen.setMaximumSize(new Dimension(200, 36));
        sliderVolumen.setAlignmentX(Component.CENTER_ALIGNMENT);
        sliderVolumen.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sliderVolumen.setPaintTicks(true);
        sliderVolumen.setMajorTickSpacing(25);
        sliderVolumen.setPaintLabels(true);
        sliderVolumen.setForeground(Color.LIGHT_GRAY);
        sliderVolumen.addChangeListener(ev -> {
            float v = sliderVolumen.getValue() / 100.0f;
            Sonido.setVolumenBGM(v);
        });

        JButton btnCerrarPanel = crearBotonAjuste("Cerrar", new Color(70, 70, 130));
        btnCerrarPanel.addActionListener(ev -> panelAjustes.setVisible(false));

        JButton btnSalir = crearBotonAjuste("Salir del Juego", new Color(180, 40, 40));
        btnSalir.addActionListener(ev -> {
            int resp = JOptionPane.showConfirmDialog(
                    null,
                    "¿Seguro que quieres salir?",
                    "Salir",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                Sonido.detenerBGM();
                System.exit(0);
            }
        });

        panelAjustes.add(lblAjustesTitulo);
        panelAjustes.add(Box.createVerticalStrut(10));
        panelAjustes.add(etiquetaVolumen);
        panelAjustes.add(Box.createVerticalStrut(4));
        panelAjustes.add(sliderVolumen);
        panelAjustes.add(Box.createVerticalStrut(14));
        panelAjustes.add(btnCerrarPanel);
        panelAjustes.add(Box.createVerticalStrut(6));
        panelAjustes.add(btnSalir);

        botonAjustes.addActionListener(ev -> panelAjustes.setVisible(!panelAjustes.isVisible()));

        add(botonJugar);
        add(botonAjustes);
        add(panelAjustes);

        // Posicionamiento
        addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                int w = getWidth();
                int h = getHeight();
                botonJugar.setBounds(w / 2 - 100, h / 2 + 25, 200, 100);
                botonAjustes.setBounds(w - 65, 10, 50, 50);
                panelAjustes.setBounds(w - 260, 65, 235, 220);
            }
        });
    }

    private JButton crearBotonAjuste(String texto, Color color) {
        JButton b = new JButton(texto);
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(Component.CENTER_ALIGNMENT);
        b.setMaximumSize(new Dimension(200, 40));
        return b;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        File archivoImagen = new File("Juego/src/resources/imagen/foto_inicio.png");
        if (archivoImagen.exists()) {
            Image imagen = new ImageIcon(archivoImagen.getAbsolutePath()).getImage();
            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
            Graphics2D g2d = (Graphics2D) g;
            g2d.setColor(new Color(0, 0, 0, 80));
            g2d.fillRect(0, 0, getWidth(), getHeight());
        } else {
            Graphics2D g2d = (Graphics2D) g;
            GradientPaint degradado = new GradientPaint(
                    0, 0, new Color(10, 10, 50),
                    0, getHeight(), new Color(40, 10, 80));
            g2d.setPaint(degradado);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.setColor(new Color(255, 215, 0));
            g2d.setFont(new Font("Arial", Font.BOLD, 60));
            g2d.drawString("BINGO", getWidth() / 2 - 140, getHeight() / 2 - 60);
        }
    }
}
