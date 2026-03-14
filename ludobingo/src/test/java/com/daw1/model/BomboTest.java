package com.daw1.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.HashSet;
import java.util.Set;

public class BomboTest {
    private Bombo bombo;

    @Before
    public void setUp() {
        bombo = new Bombo();
    }

    @Test
    public void testInicializacion() {
        assertFalse("El bombo no debe estar vacío al inicio", bombo.isEmpty());
    }

    @Test
    public void testExtraccionCompleta() {
        Set<Integer> extraidos = new HashSet<>();
        for (int i = 0; i < 90; i++) {
            int num = bombo.sacarNumero();
            assertTrue("El número debe estar entre 1 y 90", num >= 1 && num <= 90);
            assertFalse("El número no debe estar repetido", extraidos.contains(num));
            extraidos.add(num);
        }
        assertTrue("El bombo debe estar vacío tras extraer 90 números", bombo.isEmpty());
        assertEquals("Deben haberse extraído 90 números únicos", 90, extraidos.size());
    }

    @Test
    public void testSacarDeBomboVacio() {
        for (int i = 0; i < 90; i++) {
            bombo.sacarNumero();
        }
        assertEquals("Debe devolver -1 al intentar sacar de un bombo vacío", -1, bombo.sacarNumero());
    }

    @Test
    public void testContieneExtraido() {
        int num = bombo.sacarNumero();
        assertTrue("Debe marcar el número como extraído", bombo.contieneExtraido(num));
        assertFalse("No debe marcar un número no extraído", bombo.contieneExtraido(0));
    }
}
