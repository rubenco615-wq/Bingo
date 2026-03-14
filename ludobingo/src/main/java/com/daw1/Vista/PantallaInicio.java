package com.daw1.Vista;

import com.daw1.model.Sonido;
import java.awt.Image;
import javax.swing.*;

/**
 * Ventana principal (JFrame) de la aplicación Bingo
 */
public class PantallaInicio extends JFrame {

    /**
     * Inicializa la ventana con los ajustes básicos y carga el menú inicial
     */
    public PantallaInicio() {
        setTitle("LUDOBINGO");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // asignar el icono de la aplicación
        Image icono = new ImageIcon(
                getClass().getResource("/imagen/LogoBingo.png")).getImage();
        setIconImage(icono);

        // Música de la pantalla inicial
        Sonido.reproducirPantallaInicial();

        // Añadimos el nuevo panel de inicio
        add(new PanelInicio());
    }
}