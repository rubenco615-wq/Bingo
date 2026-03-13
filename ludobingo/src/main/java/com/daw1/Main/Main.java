package com.daw1.Main;

import com.daw1.Vista.PantallaInicio;

/**
 * Punto de entrada principal de la aplicación LUDOVINGO.
 * Se encarga de lanzar la interfaz gráfica inicial.
 */
public class Main {
    /**
     * Método main que inicia la ejecución del programa.
     * 
     * @param args Argumentos de la línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        PantallaInicio inicio = new PantallaInicio();
        inicio.setVisible(true);
    }
}
