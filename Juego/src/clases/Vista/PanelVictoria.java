package clases.Vista;

import javax.swing.*;
import java.awt.*;
import java.io.File;

// Panel de victoria: la imagen cubre todo el fondo del panel escalada al 100%.
// El título y el botón flotan encima con fondo semitransparente.
public class PanelVictoria extends JPanel {

    private Image imagenVictoria;

    public PanelVictoria(String nombreGanador) {
        setLayout(new BorderLayout());

        // Cargamos la imagen de fondo
        File archivo = new File("Juego/src/resources/imagen/Monedas_Bingo.jpg");
        if (archivo.exists()) {
            imagenVictoria = new ImageIcon(archivo.getAbsolutePath()).getImage();
        }

        // ---- Título (NORTH) — texto dorado sobre fondo semitransparente ----
        JLabel lblTitulo = new JLabel("¡FELICIDADES " + nombreGanador.toUpperCase() + "!", SwingConstants.CENTER) {
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

        // ---- Botón (SOUTH) — centrado sobre fondo semitransparente ----
        JButton btnMenu = new JButton("VOLVER AL MENÚ PRINCIPAL");
        btnMenu.setFont(new Font("Arial", Font.BOLD, 20));
        btnMenu.setPreferredSize(new Dimension(380, 55));
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

    // La imagen se escala para cubrir siempre TODO el panel
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (imagenVictoria != null) {
            g.drawImage(imagenVictoria, 0, 0, getWidth(), getHeight(), this);
        }
    }
}
