package com.daw1.model;

/**
 * Define como es el jugador y la maquina
 */
public interface Participante {
    /**
     * Busca el número en su cartón y lo marca si lo tiene
     * 
     * @param numero el numero marcado
     */
    void marcarNumero(int numero);

    /**
     * el carton del jugador
     * 
     * @return el cartón del participante
     */
    Carton getCarton();

    /**
     * Obtiene el nombre del participante
     * 
     * @return el nombre del participante
     */
    String getNombre();
}
