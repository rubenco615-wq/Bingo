package clases.Vista;

import javax.swing.*;
import java.awt.*;

// Panel superior que muestra el número extraído
public class Cabecera extends JPanel {
    private static final Color BG_COLOR = new Color(25, 25, 55);
    private static final Color GOLD = new Color(255, 215, 0);

    private final JLabel labelNumero;
    private final JToggleButton btnAuto;
    private final JSpinner spinnerVelocidad;

    // Crea la cabecera con el visor de números y los ajustes de velocidad.
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

        spinnerVelocidad = new JSpinner(new SpinnerNumberModel(3, 1, 15, 1));
        spinnerVelocidad.setFont(new Font("Arial", Font.BOLD, 13));
        spinnerVelocidad.setPreferredSize(new Dimension(55, 26));
        spinnerVelocidad.setEnabled(false);

        JLabel lblInfo = new JLabel("Velocidad:");
        lblInfo.setForeground(Color.LIGHT_GRAY);
        JLabel lblSeg = new JLabel("seg    |  Columnas    Marcado    Error");
        lblSeg.setForeground(Color.LIGHT_GRAY);

        pAuto.add(btnAuto);
        pAuto.add(lblInfo);
        pAuto.add(spinnerVelocidad);
        pAuto.add(lblSeg);
        add(pAuto, BorderLayout.SOUTH);
    }

    public void setNumero(int num) {
        labelNumero.setText(String.format("%02d", num));
    }

    public void setNumero(String txt) {
        labelNumero.setText(txt);
    }

    public void resetNumero() {
        labelNumero.setText("--");
    }

    public JToggleButton getBtnAuto() {
        return btnAuto;
    }

    public JSpinner getSpinnerVelocidad() {
        return spinnerVelocidad;
    }

    private void configurarBotonAuto() {
        btnAuto.setFont(new Font("Arial", Font.BOLD, 12));
        btnAuto.setPreferredSize(new Dimension(80, 28));
        btnAuto.setFocusPainted(false);
        btnAuto.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnAuto.setEnabled(false);

        // Estilo visual moderno
        btnAuto.setBackground(new Color(60, 60, 90));
        btnAuto.setForeground(Color.LIGHT_GRAY);
        btnAuto.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 120)));

        // Efecto visual al activarse
        btnAuto.addActionListener(e -> {
            if (btnAuto.isSelected()) {
                btnAuto.setBackground(new Color(45, 120, 200));
                btnAuto.setForeground(Color.WHITE);
                btnAuto.setText("AUTO ON");
            } else {
                btnAuto.setBackground(new Color(60, 60, 90));
                btnAuto.setForeground(Color.LIGHT_GRAY);
                btnAuto.setText("AUTO");
            }
        });
    }
}
