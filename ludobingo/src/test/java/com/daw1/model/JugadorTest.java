package com.daw1.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class JugadorTest {
    private Jugador jugador;

    @Before
    public void setUp() {
        jugador = new Jugador("UserTest");
    }

    @Test
    public void testInicializacion() {
        assertEquals("El nombre debe coincidir", "UserTest", jugador.getNombre());
        assertNotNull("Debe tener un cartón asignado", jugador.getCarton());
    }

    @Test
    public void testMarcarNumero() {
        Carton c = jugador.getCarton();
        int num = 0;
        int f = 0, col = 0;
        // Encontrar un número en el cartón
        for (f = 0; f < Carton.FILAS; f++) {
            for (col = 0; col < Carton.COLUMNAS; col++) {
                if (c.getNumero(f, col) != 0) {
                    num = c.getNumero(f, col);
                    break;
                }
            }
            if (num != 0) break;
        }
        
        jugador.marcarNumero(num);
        assertTrue("El número debe estar marcado en el cartón del jugador", c.isMarcado(f, col));
    }
}
