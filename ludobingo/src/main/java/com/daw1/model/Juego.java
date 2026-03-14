package com.daw1.model;

import java.util.ArrayList;
import java.util.List;

/**
 * la logica y la partida
 */
public class Juego {
    private final List<Participante> participantes;

    private Bombo bombo;

    private boolean partidaActiva;

    private static final int INDICE_JUGADOR = 0;

    private static final int INDICE_MAQUINA = 1;

    /**
     * Crea una nueva instancia de Juego.
     */
    public Juego() {
        this.participantes = new ArrayList<>();
        this.partidaActiva = false;
    }

    /**
     * crea una nueva partida con el jugador y la maquina
     * 
     * @param nombreJugador El nombre del jugador
     */
    public void iniciarPartida(String nombreJugador) {
        participantes.clear();
        participantes.add(new Jugador(nombreJugador)); // Jugador
        participantes.add(new Jugador("Máquina")); // Maquina

        bombo = new Bombo();
        partidaActiva = true;
    }

    /**
     * Finaliza la partida
     */
    public void finalizarPartida() {
        partidaActiva = false;
    }

    /**
     * Extrae el siguiente número del bombo
     * 
     * @return El número extraído, o -1 si no se puede extraer
     */
    public int extraerNumero() {
        if (!partidaActiva || bombo == null) {
            return -1;
        }
        return bombo.sacarNumero();
    }

    /**
     * Comprueba si un número ya ha salido del bombo.
     * 
     * @param numero El número a comprobar.
     * @return true si el número ya ha salido, false si no ha salido
     */
    public boolean esNumeroExtraido(int numero) {
        return bombo != null && bombo.contieneExtraido(numero);
    }

    /**
     * Coje al jugador.
     * 
     * @return devuelve al jugador
     */
    public Participante getJugador() {
        return getParticipante(INDICE_JUGADOR);
    }

    /**
     * Coje a la maquina
     * 
     * @return devuelve a la maquina
     */
    public Participante getMaquina() {
        return getParticipante(INDICE_MAQUINA);
    }

    /**
     * Obtiene un participante por su índice.
     * 
     * @param indice El índice del participante.
     * @return El participante solicitado o null si el índice es inválido.
     */
    private Participante getParticipante(int indice) {
        if (indice >= 0 && indice < participantes.size()) {
            return participantes.get(indice);
        }
        return null;
    }

    /**
     * Dice si se esta jugando la partida
     * 
     * @return true si la partida esta activa, false si no lo esta
     */
    public boolean isPartidaActiva() {
        return partidaActiva;
    }
}
