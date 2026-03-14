package com.daw1.Vista;

import org.junit.Test;
import static org.junit.Assert.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class TemporizadorTest {

    @Test
    public void testTemporizador() throws InterruptedException {
        AtomicBoolean aserto = new AtomicBoolean(false);
        // Intervalo de 1 segundo
        Temporizador t = new Temporizador(1, e -> aserto.set(true));
        
        t.iniciar();
        // Esperar un poco más del intervalo para que se ejecute (2s)
        Thread.sleep(1200); 
        t.detener();
        
        assertTrue("La acción del temporizador debería haberse ejecutado", aserto.get());
    }

    @Test
    public void testCambiarIntervalo() {
        Temporizador t = new Temporizador(5, e -> {});
        assertEquals(5, t.intervaloSegundos);
        t.cambiarIntervalo(2);
        assertEquals(2, t.intervaloSegundos);
    }
}
