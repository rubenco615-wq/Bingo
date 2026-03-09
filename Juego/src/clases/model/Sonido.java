package clases.model;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

// poner la musica en cada sitio
public class Sonido {
    private static final String DIR = "resources/sounds/";
    private static Clip musicaFondo;

    // Reproduce un archivo de música en bucle.
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

    // Reproduce un efecto de sonido de corta duración una sola vez.
    private static void reproducirSFX(String file) {
        try {
            File f = new File(DIR + file);
            if (!f.exists()) {
                return;
            }
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(f));
            clip.start();
        } catch (Exception e) {
            System.err.println("Error al reproducir SFX: " + e.getMessage());
        }
    }

    public static void reproducirLinea() {
        reproducirSFX("linea.wav");
    }

    public static void reproducirBingo() {
        detenerBGM();
        reproducirSFX("bingo.wav");
    }
}
