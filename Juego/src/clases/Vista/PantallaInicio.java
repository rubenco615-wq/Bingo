package clases.Vista;

import clases.model.Sonido;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

// Pantalla de inicio del juego
// Muestra la imagen de fondo, el boton de jugar y el boton de ajustes
// El boton de ajustes despliega un panel flotante con el volumen y el boton de salir
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

        // Boton de ajustes, va en la esquina superior derecha con un icono de engranaje
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

        // Panel flotante de ajustes, empieza oculto
        JPanel panelAjustes = new JPanel();
        panelAjustes.setLayout(new BoxLayout(panelAjustes, BoxLayout.Y_AXIS));
        panelAjustes.setBackground(new Color(20, 20, 50, 220));
        panelAjustes.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 180), 1),
                BorderFactory.createEmptyBorder(16, 20, 16, 20)));
        panelAjustes.setVisible(false);

        // Titulo del panel de ajustes
        JLabel lblAjustesTitulo = new JLabel("Ajustes");
        lblAjustesTitulo.setForeground(new Color(255, 215, 0));
        lblAjustesTitulo.setFont(new Font("Arial", Font.BOLD, 18));
        lblAjustesTitulo.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Etiqueta y slider del volumen
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
        sliderVolumen.addChangeListener(e -> {
            float v = sliderVolumen.getValue() / 100.0f;
            Sonido.setVolumenBGM(v);
        });

        // Boton para cerrar el panel de ajustes sin salir
        JButton btnCerrarPanel = crearBotonAjuste("Cerrar", new Color(70, 70, 130));
        btnCerrarPanel.addActionListener(e -> panelAjustes.setVisible(false));

        // Boton para salir del juego con confirmacion
        JButton btnSalir = crearBotonAjuste("Salir del Juego", new Color(180, 40, 40));
        btnSalir.addActionListener(e -> {
            int resp = JOptionPane.showConfirmDialog(
                    null,
                    "\u00bfSeguro que quieres salir?",
                    "Salir",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                Sonido.detenerBGM();
                System.exit(0);
            }
        });

        // Monto el panel de ajustes
        panelAjustes.add(lblAjustesTitulo);
        panelAjustes.add(Box.createVerticalStrut(10));
        panelAjustes.add(etiquetaVolumen);
        panelAjustes.add(Box.createVerticalStrut(4));
        panelAjustes.add(sliderVolumen);
        panelAjustes.add(Box.createVerticalStrut(14));
        panelAjustes.add(btnCerrarPanel);
        panelAjustes.add(Box.createVerticalStrut(6));
        panelAjustes.add(btnSalir);

        // Al pulsar el boton de ajustes, mostramos u ocultamos el panel
        botonAjustes.addActionListener(e -> panelAjustes.setVisible(!panelAjustes.isVisible()));

        panelFondo.add(botonJugar);
        panelFondo.add(botonAjustes);
        panelFondo.add(panelAjustes);

        // Posicionamiento estatico de todos los elementos
        panelFondo.addComponentListener(new java.awt.event.ComponentAdapter() {
            @Override
            public void componentResized(java.awt.event.ComponentEvent e) {
                int w = panelFondo.getWidth();
                int h = panelFondo.getHeight();
                // el boton de jugar centrado en la imagen
                botonJugar.setBounds(w / 2 - 100, h / 2 + 25, 200, 100);
                // el boton de ajustes en la esquina superior derecha
                botonAjustes.setBounds(w - 65, 10, 50, 50);
                // el panel de ajustes justo debajo del boton de ajustes
                panelAjustes.setBounds(w - 260, 65, 235, 220);
            }
        });

        add(panelFondo);
    }

    // metodo para crear los botones de ajustes con el mismo estilo
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