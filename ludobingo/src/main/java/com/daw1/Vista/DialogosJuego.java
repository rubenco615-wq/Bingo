package com.daw1.Vista;

import javax.swing.*;
import java.awt.*;

// Utilidades para mostrar cuadros de diálogo estándar de Swing.
public class DialogosJuego {

    private DialogosJuego() {
    }

    // pedir el nombre del jugador
    public static String pedirNombre(Component p) {
        String n = JOptionPane.showInputDialog(p, "¿Cómo te llamas?", "Nombre", JOptionPane.QUESTION_MESSAGE);
        return (n == null || n.trim().isEmpty()) ? "Jugador" : n.trim();
    }

    // confirmar finalizar
    public static boolean confirmarFinalizar(Component p) {
        return JOptionPane.showConfirmDialog(p, "¿Seguro que quieres finalizar?", "Finalizar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    // mostrar linea
    public static void mostrarLinea(Component p, String n) {
        JOptionPane.showMessageDialog(p, "¡¡LÍNEA!! \n→ " + n, "¡Línea!", JOptionPane.INFORMATION_MESSAGE);
    }

    // mostrar bingo
    public static void mostrarBingo(Component p, String n) {
        JOptionPane.showMessageDialog(p, "¡¡BINGO!! \nGanador: " + n, "¡BINGO!", JOptionPane.INFORMATION_MESSAGE);
    }

    // mostrar bombo vacio
    public static void mostrarBomboVacio(Component p) {
        JOptionPane.showMessageDialog(p, "¡Bombo agotado!\nNadie ganó.", "Vacío", JOptionPane.INFORMATION_MESSAGE);
    }
}
