package com.daw1.Vista;

import static org.junit.Assert.*;
import org.junit.Test;

public class PantallaInicioTest {

    @Test
    public void testPantallaInicio() {
        PantallaInicio pantalla = new PantallaInicio();
        assertNotNull(pantalla);
        assertEquals("LUDOBINGO", pantalla.getTitle());
    }
}
