package com.daw1.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class JuegoTest {
    private Juego juego;

    @Before
    public void setUp() {
        juego = new Juego();
    }

    @Test
    public void testInicioPartida() {
        assertFalse("La partida no debe estar activa al inicio", juego.isPartidaActiva());
        juego.iniciarPartida("TestUser");
        assertTrue("La partida debe estar activa tras iniciar", juego.isPartidaActiva());
        assertNotNull("Debe existir el jugador", juego.getJugador());
        assertNotNull("Debe existir la máquina", juego.getMaquina());
        assertEquals("El nombre del jugador debe coincidir", "TestUser", juego.getJugador().getNombre());
    }

    @Test
    public void testFlujoExtraccion() {
        juego.iniciarPartida("Test");
        int num = juego.extraerNumero();
        assertTrue("El número extraído debe ser válido", num >= 1 && num <= 90);
        assertTrue("El juego debe reconocer el número como extraído", juego.esNumeroExtraido(num));
    }

    @Test
    public void testFinalizarPartida() {
        juego.iniciarPartida("Test");
        juego.finalizarPartida();
        assertFalse("La partida no debe estar activa tras finalizar", juego.isPartidaActiva());
        assertEquals("No se debe poder extraer números", -1, juego.extraerNumero());
    }
}
