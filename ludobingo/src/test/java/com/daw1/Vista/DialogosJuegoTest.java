package com.daw1.Vista;

import static org.junit.Assert.*;
import org.junit.Test;
import javax.swing.*;
import java.awt.*;

public class DialogosJuegoTest {

    @Test
    public void testDialogoAjustes() {
        JFrame frame = new JFrame();
        DialogoAjustes dialogo = new DialogoAjustes(frame);
        assertNotNull("El diálogo de ajustes no debe ser nulo", dialogo);
        assertEquals("⚙  Ajustes", dialogo.getTitle());
    }

    @Test
    public void testDialogoNombre() {
        JFrame frame = new JFrame();
        DialogoNombre dialogo = new DialogoNombre(frame);
        assertNotNull("El diálogo de nombre no debe ser nulo", dialogo);
        assertEquals("Nuevo Jugador", dialogo.getTitle());
        assertEquals("Jugador", dialogo.getNombre());
    }

    @Test
    public void testDialogoPremio() {
        JFrame frame = new JFrame();
        DialogoPremio dialogo = new DialogoPremio(frame, "BINGO", "¡Has ganado!", Color.GREEN);
        assertNotNull("El diálogo de premio no debe ser nulo", dialogo);
        assertEquals("BINGO", dialogo.getTitle());
    }
}
