package com.daw1.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CartonTest {
    private Carton carton;

    @Before
    public void setUp() {
        carton = new Carton();
    }

    @Test
    public void testEstructuraCarton() {
        int countNumeros = 0;
        for (int f = 0; f < Carton.FILAS; f++) {
            int numerosFila = 0;
            for (int c = 0; c < Carton.COLUMNAS; c++) {
                int num = carton.getNumero(f, c);
                if (num != 0) {
                    numerosFila++;
                    countNumeros++;
                    // Verificar rango por columna
                    int min = (c == 0) ? 1 : c * 10;
                    int max = (c == 8) ? 90 : (c * 10) + 9;
                    assertTrue("Número fuera de rango para columna " + c, num >= min && num <= max);
                }
            }
            assertEquals("Cada fila debe tener 5 números", 5, numerosFila);
        }
        assertEquals("El cartón debe tener 15 números en total", 15, countNumeros);
    }

    @Test
    public void testMarcado() {
        int num = 0;
        int fila = -1, col = -1;
        
        // Buscar el primer número para marcar
        outer: for (int f = 0; f < Carton.FILAS; f++) {
            for (int c = 0; c < Carton.COLUMNAS; c++) {
                if (carton.getNumero(f, c) != 0) {
                    num = carton.getNumero(f, c);
                    fila = f;
                    col = c;
                    break outer;
                }
            }
        }
        
        assertFalse("No debe estar marcado inicialmente", carton.isMarcado(fila, col));
        carton.marcarNumero(num);
        assertTrue("Debe estar marcado tras la acción", carton.isMarcado(fila, col));
    }

    @Test
    public void testBingo() {
        // Marcar todos los números del cartón
        for (int f = 0; f < Carton.FILAS; f++) {
            for (int c = 0; c < Carton.COLUMNAS; c++) {
                int num = carton.getNumero(f, c);
                if (num != 0) {
                    carton.marcarNumero(num);
                }
            }
        }
        assertTrue("Debe haber bingo tras marcar todos los números", carton.comprobarBingo());
        assertTrue("Debe haber línea también", carton.comprobarLinea());
    }
}
