package com.daw1.model;

/**
 * Representa a un jugador en el bingo (ya sea humano o máquina).
 * Implementa la interfaz Participante.
 */
public class Jugador implements Participante {
    private final String nombre;
    private final Carton carton;

    /**
     * Crea un nuevo jugador y le asigna un cartón generado automáticamente.
     * 
     * @param nombre El nombre del jugador.
     */
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.carton = new Carton();
    }

    /**
     * Busca el número en su cartón y lo marca si lo tiene.
     * 
     * @param numero El número a marcar.
     */
    @Override
    public void marcarNumero(int numero) {
        carton.marcarNumero(numero);
    }

    @Override
    public Carton getCarton() {
        return carton;
    }

    @Override
    public String getNombre() {
        return nombre;
    }
}
