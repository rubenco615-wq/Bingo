package clases.Vista;

import javax.swing.Timer;
import java.awt.event.ActionListener;

// Clase que representa el temporizador del juego
public class Temporizador {

    int intervaloSegundos;
    Timer timer;

    public Temporizador(int intervaloSegundos, ActionListener accion) {
        this.intervaloSegundos = intervaloSegundos;
        timer = new Timer(intervaloSegundos * 1000, accion);
    }

    // Inicia el temporizador
    public void iniciar() {
        timer.start();
    }

    // Detiene el temporizador
    public void detener() {
        timer.stop();
    }

    // Cambia el intervalo en segundos sobre la marcha
    public void cambiarIntervalo(int segundos) {
        this.intervaloSegundos = segundos;
        timer.setDelay(segundos * 1000);
        timer.setInitialDelay(segundos * 1000);
    }
}