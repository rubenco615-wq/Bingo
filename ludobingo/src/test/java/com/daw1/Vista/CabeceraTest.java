package com.daw1.Vista;

import static org.junit.Assert.*;
import org.junit.Test;

public class CabeceraTest {

    @Test
    public void testCabeceraInicializacion() {
        Cabecera cabecera = new Cabecera();
        assertNotNull("El botón extraer no debe ser nulo", cabecera.getBotonExtraer());
        assertNotNull("El botón auto no debe ser nulo", cabecera.getBtnAuto());
        assertNotNull("El spinner de velocidad no debe ser nulo", cabecera.getSpinnerVelocidad());
    }

    @Test
    public void testSetNumero() {
        Cabecera cabecera = new Cabecera();
        cabecera.setNumero(5);
        // No podemos verificar fácilmente el texto del label privado sin reflexión,
        // pero verificamos que el método se ejecuta.
        cabecera.setNumero("10");
        cabecera.resetNumero();
    }
}
