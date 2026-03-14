package com.daw1.Vista;

import javax.swing.Timer;
import java.awt.event.ActionListener;

/**
 * El temporizador de cada cuanto sale el siguente numero/ siendo regulable
 * hasta el maximo que son 15 segundos
 * 
 */
public class Temporizador {

    // Intervalo de tiempo entre cada numero
    int intervaloSegundos;

    // El timer de Swing que ejecuta la acción
    Timer timer;

    /**
     * Crea un nuevo temporizador
     * 
     * @param intervaloSegundos Tiempo en segundos entre cada numero
     * @param accion            La acción a realizar en cada tick
     */
    public Temporizador(int intervaloSegundos, ActionListener accion) {
        this.intervaloSegundos = intervaloSegundos;
        // Convertimos los segundos a milisegundos para el Timer
        timer = new Timer(intervaloSegundos * 1000, accion);
    }

    /**
     * Inicia el temporizador
     */
    public void iniciar() {
        timer.start();
    }

    /**
     * Detiene el temporizador
     */
    public void detener() {
        timer.stop();
    }

    /**
     * Cambia el intervalo de ejecución sobre la marcha
     * 
     * @param segundos El nuevo intervalo en segundos
     */
    public void cambiarIntervalo(int segundos) {
        this.intervaloSegundos = segundos;
        timer.setDelay(segundos * 1000);
        timer.setInitialDelay(segundos * 1000);
    }
}