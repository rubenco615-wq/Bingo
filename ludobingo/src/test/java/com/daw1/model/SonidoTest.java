package com.daw1.model;

import org.junit.Test;

public class SonidoTest {

    @Test
    public void testSetVolumenLimites() {
        // No podemos probar la reproducción real fácilmente en CI/Headless, 
        // pero podemos probar que el método acepta valores extremos sin error.
        Sonido.setVolumenBGM(0.0f);
        Sonido.setVolumenBGM(1.0f);
        Sonido.setVolumenBGM(-0.5f); // Debería limitarse internamente a 0
        Sonido.setVolumenBGM(1.5f);  // Debería limitarse internamente a 1
    }
}
