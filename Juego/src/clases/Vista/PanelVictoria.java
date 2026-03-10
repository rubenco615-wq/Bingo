package clases.Vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;

// Panel de victoria que muestra una imagen estática y permite volver al inicio.
public class PanelVictoria extends JPanel {

    private Image imagenVictoria;

    public PanelVictoria(String nombreGanador) {
        setLayout(new BorderLayout());
        setBackground(new Color(20, 20, 40));

        // Intentamos cargar la imagen Monedas_Bingo.jpg
        File archivo = new File("Juego/src/resources/imagen/Monedas_Bingo.jpg");
        if (archivo.exists()) {
            imagenVictoria = new ImageIcon(archivo.getAbsolutePath()).getImage();
        }

        // Título superior
        JLabel lblTitulo = new JLabel("¡FELICIDADES " + nombreGanador.toUpperCase() + "!", SwingConstants.CENTER);
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 50));
        lblTitulo.setForeground(new Color(255, 215, 0));
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(20, 10, 20, 10));
        add(lblTitulo, BorderLayout.NORTH);

        // Panel central que dibujará la imagen
        JPanel panelImagen = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (imagenVictoria != null) {
                    // Dibujamos la imagen centrada y escalada proporcionalmente
                    int iw = imagenVictoria.getWidth(this);
                    int ih = imagenVictoria.getHeight(this);
                    double ratio = Math.min((double) getWidth() / iw, (double) getHeight() / ih);
                    int nw = (int) (iw * ratio);
                    int nh = (int) (ih * ratio);
                    int x = (getWidth() - nw) / 2;
                    int y = (getHeight() - nh) / 2;
                    g.drawImage(imagenVictoria, x, y, nw, nh, this);
                }
            }
        };
        panelImagen.setOpaque(false);
        add(panelImagen, BorderLayout.CENTER);

        // Botón inferior para salir
        JButton btnMenu = new JButton("VOLVER AL MENÚ PRINCIPAL");
        btnMenu.setFont(new Font("Arial", Font.BOLD, 22));
        btnMenu.setPreferredSize(new Dimension(400, 60));
        btnMenu.setBackground(new Color(45, 170, 45));
        btnMenu.setForeground(Color.WHITE);
        btnMenu.setFocusPainted(false);
        btnMenu.setCursor(new Cursor(Cursor.HAND_CURSOR));

        btnMenu.addActionListener(e -> {
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (frame != null) {
                frame.getContentPane().removeAll();
                frame.add(new PantallaInicio().getContentPane());
                frame.setTitle("BingoSwingJunior - Inicio");
                frame.setSize(800, 500);
                frame.setLocationRelativeTo(null);
                frame.revalidate();
                frame.repaint();
            }
        });

        JPanel pInferior = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pInferior.setOpaque(false);
        pInferior.setBorder(BorderFactory.createEmptyBorder(20, 10, 40, 10));
        pInferior.add(btnMenu);
        add(pInferior, BorderLayout.SOUTH);
    }
}
