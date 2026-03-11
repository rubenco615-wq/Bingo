package clases.Vista;

import clases.model.Sonido;
import javax.swing.*;

// Esta es la ventana principal que aguanta todo el juego
// Primero muestra el menu, luego el juego y al final la victoria
public class PantallaInicio extends JFrame {

    public PantallaInicio() {
        setTitle("LUDOVINGO");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Música de la pantalla inicial
        Sonido.reproducirPantallaInicial();

        // Añadimos el nuevo panel de inicio
        add(new PanelInicio());
    }
}