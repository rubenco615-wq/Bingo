package com.daw1.Vista;

import static org.junit.Assert.*;
import org.junit.Test;

public class HistorialTest {

    @Test
    public void testHistorial() {
        Historial historial = new Historial();
        assertNotNull(historial);
        
        // No hay forma directa de leer el AreaLog sin cambiar el acceso a protegido/publico
        // pero podemos verificar que los metodos no lanzan excepciones
        historial.escribirLog("Test Message");
        historial.limpiarLog();
    }
}
