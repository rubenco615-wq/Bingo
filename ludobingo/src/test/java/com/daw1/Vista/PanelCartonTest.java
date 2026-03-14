package com.daw1.Vista;

import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Color;
import javax.swing.JButton;

public class PanelCartonTest {

    @Test
    public void testInicializacionPanelCarton() {
        PanelCarton panel = new PanelCarton("Test Title", Color.BLUE, false, "pingu.png");
        assertNotNull("El panel no debe ser nulo", panel);
        assertEquals("Debe tener 27 celdas", 27, panel.getCeldas().length);
    }

    @Test
    public void testGetCeldas() {
        PanelCarton panel = new PanelCarton("Test", Color.RED, true, "PinguBetis.png");
        JButton[] celdas = panel.getCeldas();
        assertNotNull(celdas);
        assertEquals(27, celdas.length);
        for (JButton b : celdas) {
            assertNotNull(b);
        }
    }
}
