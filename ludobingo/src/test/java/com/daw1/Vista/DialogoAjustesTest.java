package com.daw1.Vista;

import static org.junit.Assert.*;
import org.junit.Test;
import javax.swing.JFrame;

public class DialogoAjustesTest {

    @Test
    public void testDialogoAjustes() {
        JFrame frame = new JFrame();
        DialogoAjustes dialogo = new DialogoAjustes(frame);
        assertNotNull(dialogo);
        assertEquals("⚙  Ajustes", dialogo.getTitle());
    }
}
