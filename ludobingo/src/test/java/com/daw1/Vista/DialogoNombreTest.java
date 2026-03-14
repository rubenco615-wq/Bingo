package com.daw1.Vista;

import static org.junit.Assert.*;
import org.junit.Test;
import javax.swing.JFrame;

public class DialogoNombreTest {

    @Test
    public void testDialogoNombre() {
        JFrame frame = new JFrame();
        DialogoNombre dialogo = new DialogoNombre(frame);
        assertNotNull(dialogo);
        assertEquals("Nuevo Jugador", dialogo.getTitle());
        assertEquals("Jugador", dialogo.getNombre());
    }
}
