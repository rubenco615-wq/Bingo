package com.daw1.Vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Diálogo modal para solicitar el nombre al jugador antes de empezar una partida.
 */
public class DialogoNombre extends JDialog {
    private String nombre = "Jugador";
    private final JTextField txtNombre;

    /**
     * Crea un nuevo diálogo para solicitar el nombre.
     * 
     * @param p El frame padre.
     */
    public DialogoNombre(Frame p) {
        super(p, "Nuevo Jugador", true);
        setResizable(false);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(40, 45, 60));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));
        GridBagConstraints gbc = new GridBagConstraints();
        // darle espacio entre los elementos
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // crear el bloque para preguntar el nombre del jugador
        JLabel title = new JLabel("¿Cómo te llamas?", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        panel.add(title, gbc);

        // crear el bloque para escribir el nombre del jugador
        txtNombre = new JTextField(15);
        txtNombre.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        txtNombre.setHorizontalAlignment(JTextField.CENTER);
        gbc.gridy = 1;
        panel.add(txtNombre, gbc);

        // crear el boton de entrar
        JButton btnEntrar = new JButton("Entrar");
        btnEntrar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnEntrar.setBackground(new Color(80, 200, 120));
        btnEntrar.setForeground(Color.WHITE);
        btnEntrar.addActionListener(e -> aceptar());

        // crear el bloque para que se pueda aceptar con la tecla enter
        txtNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    aceptar();
                }
            }
        });

        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        panel.add(btnEntrar, gbc);

        add(panel);
        pack();
        setLocationRelativeTo(p);
    }

    /**
     * Valida la entrada y cierra el diálogo.
     */
    private void aceptar() {
        String input = txtNombre.getText().trim();
        if (!input.isEmpty()) {
            nombre = input;
        }
        dispose();
    }

    /**
     * Obtiene el nombre introducido por el usuario.
     * 
     * @return El nombre del jugador.
     */
    public String getNombre() {
        return nombre;
    }
}
