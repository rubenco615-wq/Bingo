package clases.model;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Sonido {
    // Ruta base donde están los archivos de audio
    private static final String DIR = "Juego/src/resources/";
    private static Clip musicaFondo;

    // Reproduce un archivo de música en bucle (BGM = Background Music).
    public static void reproducirBGM(String file) {
        detenerBGM();
        try {
            File f = new File(DIR + file);
            if (!f.exists()) {
                System.err.println("Archivo de audio no encontrado: " + f.getAbsolutePath());
                return;
            }
            musicaFondo = AudioSystem.getClip();
            musicaFondo.open(AudioSystem.getAudioInputStream(f));
            musicaFondo.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            System.err.println("Error al reproducir BGM: " + e.getMessage());
        }
    }

    public static void detenerBGM() {
        if (musicaFondo != null && musicaFondo.isRunning()) {
            musicaFondo.stop();
            musicaFondo.close();
        }
    }

    // Reproduce un efecto de sonido corto una sola vez
    private static void reproducirSFX(String file) {
        try {
            File f = new File(DIR + file);
            if (!f.exists()) {
                System.err.println("Archivo de audio no encontrado: " + f.getAbsolutePath());
                return;
            }
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(f));
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

    // Efecto de sonido cuando el jugador o la máquina hace línea.
    public static void reproducirLinea() {
        detenerBGM(); // Paramos la música de fondo para que se oiga la línea
        reproducirSFX("Audio_Bingo_1.wav");
    }

    // Detiene la BGM y reproduce el sonido de victoria al hacer bingo.
    public static void reproducirBingo() {
        detenerBGM();
        reproducirSFX("Audio_Victoria_1.wav");
    }
}
