package com.daw1.model;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.io.InputStream;

public class Sonido {
    private static Clip musicaFondo;
    private static Clip musicaLinea; // Clip separado para el sonido de línea
    private static float volumenActual = 0.75f; // Volumen por defecto (0.0 a 1.0)

    // Carga un audio desde el classpath
    private static Clip cargarClip(String rutaClasspath) throws Exception {
        InputStream is = Sonido.class.getResourceAsStream(rutaClasspath);
        if (is == null) {
            System.err.println("Archivo de audio no encontrado en classpath: " + rutaClasspath);
            return null;
        }
        Clip clip = AudioSystem.getClip();
        clip.open(AudioSystem.getAudioInputStream(is));
        return clip;
    }

    // Reproduce un archivo de música en bucle (BGM = Background Music).
    public static void reproducirBGM(String file) {
        detenerBGM();
        try {
            musicaFondo = cargarClip("/" + file);
            if (musicaFondo == null) return;
            actualizarVolumenInterno();
            musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println("Error al reproducir BGM: " + e.getMessage());
        }
    }

    // Ajusta el volumen de la música de fondo (0.0f a 1.0f)
    public static void setVolumenBGM(float valor) {
        volumenActual = Math.max(0.0f, Math.min(1.0f, valor));
        actualizarVolumenInterno();
    }

    private static void actualizarVolumenInterno() {
        if (musicaFondo != null && musicaFondo.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) musicaFondo.getControl(FloatControl.Type.MASTER_GAIN);
            // Convertir lineal (0.0-1.0) a decibelios (logarítmico)
            float dB = (float) (Math.log(volumenActual != 0 ? volumenActual : 0.0001f) / Math.log(10.0) * 20.0);
            gainControl.setValue(dB);
        }
    }

    public static void detenerBGM() {
        if (musicaFondo != null && musicaFondo.isRunning()) {
            musicaFondo.stop();
            musicaFondo.close();
        }
    }

    // Reproduce un efecto de sonido corto una sola vez
    private static void reproducirSFX(String rutaClasspath) {
        try {
            Clip clip = cargarClip(rutaClasspath);
            if (clip == null) return;
            clip.start();
        } catch (Exception e) {
            System.err.println("Error al reproducir SFX: " + e.getMessage());
        }
    }

    // Música en bucle para la pantalla inicial.
    public static void reproducirPantallaInicial() {
        reproducirBGM("Audio_PantallaInicial_1.wav");
    }

    // Música en bucle para la pantalla de cartones / juego.
    public static void reproducirCartones() {
        reproducirBGM("Audio_Cartones_1.wav");
    }

    // Detiene el sonido de línea si sigue reproduciéndose.
    public static void detenerLinea() {
        if (musicaLinea != null && musicaLinea.isRunning()) {
            musicaLinea.stop();
            musicaLinea.close();
        }
    }

    // Efecto de sonido cuando el jugador o la máquina hace línea.
    public static void reproducirLinea() {
        detenerBGM(); // Paramos la música de fondo para que se oiga la línea
        try {
            if (musicaLinea != null) musicaLinea.close();
            musicaLinea = cargarClip("/Audio_Bingo_1.wav");
            if (musicaLinea == null) return;
            musicaLinea.start();
        } catch (Exception e) {
            System.err.println("Error al reproducir sonido de línea: " + e.getMessage());
        }
    }

    // Detiene la BGM y reproduce el sonido de victoria al hacer bingo.
    public static void reproducirBingo() {
        detenerBGM();
        reproducirSFX("/Audio_Victoria_1.wav");
    }

    // Reproduce el audio correspondiente al número extraído
    public static void reproducirNumero(int numero) {
        reproducirSFX("/audios/Numeros/Numeros/" + numero + ".wav");
    }
}
