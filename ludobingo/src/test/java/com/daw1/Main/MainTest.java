package com.daw1.Main;

import org.junit.Test;
import static org.junit.Assert.*;

public class MainTest {
    @Test
    public void testMainSanity() {
        // No podemos probar el main real porque lanza la GUI y se queda bloqueado/necesita display
        // pero verificamos que la clase existe y es cargable.
        assertNotNull(new Main());
    }
}
