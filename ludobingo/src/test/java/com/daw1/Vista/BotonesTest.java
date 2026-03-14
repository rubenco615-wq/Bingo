package com.daw1.Vista;

import static org.junit.Assert.*;
import org.junit.Test;

public class BotonesTest {

    @Test
    public void testBotonesInicializacion() {
        Botones botones = new Botones();
        assertNotNull("El botón iniciar no debe ser nulo", botones.getBotonIniciar());
        assertNotNull("El botón finalizar no debe ser nulo", botones.getBotonFinalizar());
        assertNotNull("El botón ajustes no debe ser nulo", botones.getBotonAjustes());
        assertFalse("El botón finalizar debe estar deshabilitado al inicio", botones.getBotonFinalizar().isEnabled());
    }
}
