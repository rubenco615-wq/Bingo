package com.daw1.Vista;

import static org.junit.Assert.*;
import org.junit.Test;

public class PanelVictoriaTest {

    @Test
    public void testPanelVictoriaJugador() {
        PanelVictoria panel = new PanelVictoria("Jugador1", false);
        assertNotNull(panel);
    }

    @Test
    public void testPanelVictoriaMaquina() {
        PanelVictoria panel = new PanelVictoria("Máquina", true);
        assertNotNull(panel);
    }
}
