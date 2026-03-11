package clases.Vista;

import clases.model.Sonido;
import javax.swing.*;
import java.awt.*;

// Ventana de ajustes del juego
// Aqui se puede cambiar el volumen de la musica y salir del juego
public class DialogoAjustes extends JDialog {

    public DialogoAjustes(Window propietario) {
        super(propietario, "⚙  Ajustes", ModalityType.APPLICATION_MODAL);
        setResizable(false);

        // Creo el panel principal con fondo oscuro
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(30, 30, 60));
        panel.setBorder(BorderFactory.createEmptyBorder(24, 32, 24, 32));

        // Titulo del dialogo
        JLabel lblTitulo = new JLabel("Ajustes");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 22));
        lblTitulo.setForeground(new Color(255, 215, 0));
        lblTitulo.setAlignmentX(CENTER_ALIGNMENT);

        // Linea separadora para que quede mas ordenado
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(80, 80, 120));
        sep.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));

        // Etiqueta encima del slider
        JLabel lblVolumen = new JLabel("Volumen Música");
        lblVolumen.setForeground(Color.LIGHT_GRAY);
        lblVolumen.setFont(new Font("Arial", Font.BOLD, 14));
        lblVolumen.setAlignmentX(CENTER_ALIGNMENT);

        // El slider va de 0 a 100, empieza en 25
        JSlider sliderVolumen = new JSlider(0, 100, 25);
        sliderVolumen.setOpaque(false);
        sliderVolumen.setMaximumSize(new Dimension(260, 36));
        sliderVolumen.setAlignmentX(CENTER_ALIGNMENT);
        sliderVolumen.setCursor(new Cursor(Cursor.HAND_CURSOR));
        sliderVolumen.setPaintTicks(true);
        sliderVolumen.setMajorTickSpacing(25);
        sliderVolumen.setPaintLabels(true);
        sliderVolumen.setForeground(Color.LIGHT_GRAY); // para que las marcas se vean sobre el fondo oscuro

        // Cada vez que muevo el slider actualizo el volumen de la musica
        sliderVolumen.addChangeListener(e -> {
            float v = sliderVolumen.getValue() / 100.0f;
            Sonido.setVolumenBGM(v);
        });

        // Otra linea separadora antes de los botones
        JSeparator sep2 = new JSeparator();
        sep2.setForeground(new Color(80, 80, 120));
        sep2.setMaximumSize(new Dimension(Integer.MAX_VALUE, 2));

        // Boton para cerrar este dialogo y volver al juego
        JButton btnCerrar = crearBoton("Cerrar", new Color(70, 70, 130));
        btnCerrar.addActionListener(e -> dispose());

        // Boton para salir del juego, pide confirmacion antes por si acaso
        JButton btnSalir = crearBoton("Salir del Juego", new Color(180, 40, 40));
        btnSalir.addActionListener(e -> {
            int resp = JOptionPane.showConfirmDialog(
                    this,
                    "¿Seguro que quieres salir del juego?",
                    "Salir",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (resp == JOptionPane.YES_OPTION) {
                Sonido.detenerBGM();
                System.exit(0);
            }
        });

        // Agrego todo al panel en orden
        panel.add(lblTitulo);
        panel.add(Box.createVerticalStrut(10));
        panel.add(sep);
        panel.add(Box.createVerticalStrut(16));
        panel.add(lblVolumen);
        panel.add(Box.createVerticalStrut(6));
        panel.add(sliderVolumen);
        panel.add(Box.createVerticalStrut(20));
        panel.add(sep2);
        panel.add(Box.createVerticalStrut(18));
        panel.add(btnCerrar);
        panel.add(Box.createVerticalStrut(8));
        panel.add(btnSalir);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(propietario);
    }

    // Metodo para crear botones con el mismo estilo, asi no repito codigo
    private JButton crearBoton(String texto, Color color) {
        JButton b = new JButton(texto);
        b.setFont(new Font("Arial", Font.BOLD, 14));
        b.setBackground(color);
        b.setForeground(Color.WHITE);
        b.setFocusPainted(false);
        b.setCursor(new Cursor(Cursor.HAND_CURSOR));
        b.setAlignmentX(CENTER_ALIGNMENT);
        b.setMaximumSize(new Dimension(220, 42));
        b.setPreferredSize(new Dimension(220, 42));
        return b;
    }
}
