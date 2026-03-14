package com.daw1.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class ParticipanteTest {
    private Participante participante;
    private final String NOMBRE_TEST = "Test Player";

    @Before
    public void setUp() {
        participante = new Jugador(NOMBRE_TEST);
    }

    @Test
    public void testGetNombre() {
        assertEquals("El nombre debe coincidir con el proporcionado", NOMBRE_TEST, participante.getNombre());
    }

    @Test
    public void testGetCarton() {
        assertNotNull("El participante debe tener un cartón", participante.getCarton());
    }

    @Test
    public void testMarcarNumero() {
        Carton carton = participante.getCarton();
        // Buscamos un número que esté en el cartón para probar marcarlo
        int numeroEnCarton = -1;
        int filaEncontrada = -1;
        int colEncontrada = -1;
        
        for (int f = 0; f < Carton.FILAS; f++) {
            for (int c = 0; c < Carton.COLUMNAS; c++) {
                int num = carton.getNumero(f, c);
                if (num != 0) {
                    numeroEnCarton = num;
                    filaEncontrada = f;
                    colEncontrada = c;
                    break;
                }
            }
            if (numeroEnCarton != -1) break;
        }
        
        if (numeroEnCarton != -1) {
            participante.marcarNumero(numeroEnCarton);
            assertTrue("El número debe estar marcado en el cartón", carton.isMarcado(filaEncontrada, colEncontrada));
        } else {
            fail("No se encontró ningún número en el cartón generado");
        }
    }
}
