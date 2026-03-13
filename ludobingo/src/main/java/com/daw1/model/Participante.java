package com.daw1.model;

/**
 * Define el comportamiento básico de cualquier participante en el juego de Bingo.
 * Puede ser implementada tanto por jugadores humanos como por la máquina.
 */
public interface Participante {
    /**
     * Marca un número en el cartón del participante si este lo contiene.
     * 
     * @param numero El número a marcar.
     */
    void marcarNumero(int numero);

    /**
     * Obtiene el cartón asociado al participante.
     * 
     * @return El cartón del participante.
     */
    Carton getCarton();

    /**
     * Obtiene el nombre del participante.
     * 
     * @return El nombre del participante.
     */
    String getNombre();
}
