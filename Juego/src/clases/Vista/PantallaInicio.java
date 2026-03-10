package clases.Vista;

import clases.model.Sonido;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// Pantalla de inicio del juego
// Muestra una imagen de fondo y un botón para empezar
// Pon tu imagen en: resources/splash.png
public class PantallaInicio extends JFrame {

    public PantallaInicio() {
        setTitle("LUDOVINGO");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centrar en pantalla
        setResizable(false);

        // Música de la pantalla inicial en bucle
        Sonido.reproducirPantallaInicial();

        // Añadimos el panel con imagen de fondo
        PanelFondo panelFondo = new PanelFondo();
        panelFondo.setLayout(null); // Usar layout nulo para posiciones exactas y estables

        // Creamos el botón de jugar
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

        // Efecto hover: cambia de color al pasar el ratón
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

        // Al pulsar el botón: cambiamos el contenido de la ventana al juego
        botonJugar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                // Detenemos la música de la pantalla inicial
                Sonido.detenerBGM();

                // Removemos el panel de inicio
                getContentPane().removeAll();

                // Añadimos el juego (ahora un JPanel)
                VentanaJuego panelJuego = new VentanaJuego();
                add(panelJuego);

                // Ajustamos la ventana para el juego
                setTitle("LUDOVINGO - ¡A jugar!");
                setSize(1250, 620); // Tamaño adecuado para el panel de juego
                setLocationRelativeTo(null); // Recentramos

                // Refrescamos la interfaz
                revalidate();
                repaint();
            }
        });

        // --- Panel de Volumen ---
        JPanel panelVolumen = new JPanel();
        panelVolumen.setOpaque(false);
        panelVolumen.setLayout(new BoxLayout(panelVolumen, BoxLayout.Y_AXIS));

        JLabel etiquetaVolumen = new JLabel("Volumen Música");
        etiquetaVolumen.setForeground(Color.WHITE);
        etiquetaVolumen.setFont(new Font("Arial", Font.BOLD, 14));
        etiquetaVolumen.setAlignmentX(Component.CENTER_ALIGNMENT);

        JSlider sliderVolumen = new JSlider(0, 100, 25); // 0 a 100, empieza en 75
        sliderVolumen.setPreferredSize(new Dimension(200, 30));
        sliderVolumen.setOpaque(false);
        sliderVolumen.setCursor(new Cursor(Cursor.HAND_CURSOR));

        sliderVolumen.addChangeListener(e -> {
            float v = sliderVolumen.getValue() / 100.0f;
            Sonido.setVolumenBGM(v);
        });

        panelVolumen.add(etiquetaVolumen);
        panelVolumen.add(Box.createVerticalStrut(5));
        panelVolumen.add(sliderVolumen);

        panelFondo.add(botonJugar);
        panelFondo.add(panelVolumen);

        // Posicionamiento estático
        panelFondo.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                int w = panelFondo.getWidth();
                int h = panelFondo.getHeight();
                // para poner el boton centrado en la bola
                botonJugar.setBounds(w / 2 - 100, h / 2 + 25, 200, 100);
                // para poner la barra del slider de la música justo por debajo del botón
                panelVolumen.setBounds(w / 2 - 100, h / 2 + 135, 200, 60);
            }
        });

        add(panelFondo);
    }

    class PanelFondo extends JPanel {

        Image imagen; // la imagen de fondo

        PanelFondo() {
            // Intentamos cargar la imagen desde la carpeta resources real
            File archivoImagen = new File("Juego/src/resources/imagen/foto_inicio.png");
            if (archivoImagen.exists()) {
                imagen = new ImageIcon(archivoImagen.getAbsolutePath()).getImage();
                System.out.println("Imagen de inicio cargada correctamente.");
            } else {
                // Si no hay imagen, avisamos en consola
                System.out.println("Aviso: no se encontró la imagen en Juego/src/resources/imagen/");
                System.out.println("Se usará fondo por defecto.");
                imagen = null;
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (imagen != null) {
                // Dibujamos la imagen escalada para llenar toda la ventana
                g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

                // Capa semitransparente oscura para que el botón resalte mejor
                Graphics2D g2d = (Graphics2D) g;
                g2d.setColor(new Color(0, 0, 0, 80)); // negro con 80/255 de opacidad
                g2d.fillRect(0, 0, getWidth(), getHeight());
            } else {
                // Fondo degradado oscuro por defecto si no hay imagen
                Graphics2D g2d = (Graphics2D) g;
                GradientPaint degradado = new GradientPaint(
                        0, 0, new Color(10, 10, 50),
                        0, getHeight(), new Color(40, 10, 80));
                g2d.setPaint(degradado);
                g2d.fillRect(0, 0, getWidth(), getHeight());

                // Texto de ejemplo si no hay imagen
                g2d.setColor(new Color(255, 215, 0));
                g2d.setFont(new Font("Arial", Font.BOLD, 60));
                g2d.drawString("BINGO", getWidth() / 2 - 140, getHeight() / 2 - 60);
                g2d.setColor(new Color(200, 200, 200));
                g2d.setFont(new Font("Arial", Font.PLAIN, 16));
                g2d.drawString(
                        "Recurso esperado: Juego/src/resources/imagen/ChatGPT Image 2 mar 2026, 10_51_19.png",
                        getWidth() / 2 - 200, getHeight() / 2 - 20);
            }
        }
    }
}