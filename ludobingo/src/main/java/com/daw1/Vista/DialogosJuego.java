package com.daw1.Vista;

import javax.swing.*;
import java.awt.*;

/**
 * Clase de utilidad para centralizar la visualización de diálogos estándar (JOptionPane).
 * Proporciona métodos estáticos para interactuar con el usuario.
 */
public class DialogosJuego {

    private DialogosJuego() {
    }

    /**
     * Solicita al usuario que introduzca su nombre.
     * 
     * @param p El componente padre para posicionar el diálogo.
     * @return El nombre introducido o "Jugador" si se cancela o está vacío.
     */
    public static String pedirNombre(Component p) {
        String n = JOptionPane.showInputDialog(p, "¿Cómo te llamas?", "Nombre", JOptionPane.QUESTION_MESSAGE);
        return (n == null || n.trim().isEmpty()) ? "Jugador" : n.trim();
    }

    /**
     * Muestra una confirmación para finalizar la partida.
     * 
     * @param p El componente padre.
     * @return true si el usuario confirma la finalización, false en caso contrario.
     */
    public static boolean confirmarFinalizar(Component p) {
        return JOptionPane.showConfirmDialog(p, "¿Seguro que quieres finalizar?", "Finalizar",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION;
    }

    /**
     * Muestra un mensaje anunciando que alguien ha hecho línea.
     * 
     * @param p El componente padre.
     * @param n El nombre del jugador que hizo línea.
     */
    public static void mostrarLinea(Component p, String n) {
        JOptionPane.showMessageDialog(p, "¡¡LÍNEA!! \n→ " + n, "¡Línea!", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Muestra un mensaje anunciando que alguien ha ganado (Bingo).
     * 
     * @param p El componente padre.
     * @param n El nombre del ganador.
     */
    public static void mostrarBingo(Component p, String n) {
        JOptionPane.showMessageDialog(p, "¡¡BINGO!! \nGanador: " + n, "¡BINGO!", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Informa al usuario de que no quedan más números en el bombo.
     * 
     * @param p El componente padre.
     */
    public static void mostrarBomboVacio(Component p) {
        JOptionPane.showMessageDialog(p, "¡Bombo agotado!\nNadie ganó.", "Vacío", JOptionPane.INFORMATION_MESSAGE);
    }
}
