package com.daw1.Vista;

import static org.junit.Assert.*;
import org.junit.Test;
import javax.swing.JFrame;
import java.awt.Color;

public class DialogoPremioTest {

    @Test
    public void testDialogoPremio() {
        JFrame frame = new JFrame();
        DialogoPremio dialogo = new DialogoPremio(frame, "PREMIO", "Mensaje", Color.BLUE);
        assertNotNull(dialogo);
        assertEquals("PREMIO", dialogo.getTitle());
    }
}
