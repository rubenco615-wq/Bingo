package com.daw1.Vista;

import javax.swing.Timer;
import java.awt.event.ActionListener;

/**
 * Clase envolvente para el Timer de Swing que simplifica su uso en el juego.
 * Facilita la ejecución de acciones periódicas como la extracción automática.
 */
public class Temporizador {

    // Intervalo de tiempo entre cada acción (en segundos)
    int intervaloSegundos;

    // El timer de Swing que ejecuta la acción
    Timer timer;

    /**
     * Crea un nuevo temporizador.
     * 
     * @param intervaloSegundos Tiempo en segundos entre ejecuciones.
     * @param accion La acción a realizar en cada tick.
     */
    public Temporizador(int intervaloSegundos, ActionListener accion) {
        this.intervaloSegundos = intervaloSegundos;
        // Convertimos los segundos a milisegundos para el Timer
        timer = new Timer(intervaloSegundos * 1000, accion);
    }

    /**
     * Inicia la ejecución del temporizador.
     */
    public void iniciar() {
        timer.start();
    }

    /**
     * Detiene la ejecución del temporizador.
     */
    public void detener() {
        timer.stop();
    }

    /**
     * Cambia el intervalo de ejecución sobre la marcha.
     * 
     * @param segundos El nuevo intervalo en segundos.
     */
    public void cambiarIntervalo(int segundos) {
        this.intervaloSegundos = segundos;
        timer.setDelay(segundos * 1000);
        timer.setInitialDelay(segundos * 1000);
    }
}