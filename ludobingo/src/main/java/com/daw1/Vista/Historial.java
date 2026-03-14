package com.daw1.Vista;

import javax.swing.*;
import java.awt.*;

/**
 * muestra los numeros que han salido y una barra para desplazar y ver los
 * numeros que son
 */
public class Historial extends JPanel {
    private JTextArea areaLog;

    /**
     * Inicializa el panel del historial
     */
    public Historial() {
        setLayout(new BorderLayout(0, 5));
        setBorder(BorderFactory.createEmptyBorder(8, 0, 8, 12));
        setPreferredSize(new Dimension(175, 0));
        setBackground(new Color(235, 235, 250));

        JLabel titulo = new JLabel("HISTORIAL", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 13));
        titulo.setForeground(new Color(25, 25, 55));
        add(titulo, BorderLayout.NORTH);

        areaLog = new JTextArea();
        areaLog.setEditable(false);
        areaLog.setFont(new Font("Monospaced", Font.PLAIN, 11));
        areaLog.setBackground(new Color(18, 18, 40));
        areaLog.setForeground(new Color(160, 255, 160));
        add(new JScrollPane(areaLog), BorderLayout.CENTER);
    }

    /**
     * Añade un mensaje al registro
     * 
     * @param msj El texto
     */
    public void escribirLog(String msj) {
        areaLog.append(msj + "\n");
        areaLog.setCaretPosition(areaLog.getDocument().getLength());
    }

    /**
     * Borra todo el contenido del historial
     */
    public void limpiarLog() {
        areaLog.setText("");
    }
}