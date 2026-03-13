package com.daw1.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona la lógica principal y el estado de una partida de Bingo.
 * Se encarga de coordinar a los participantes y el bombo.
 */
public class Juego {
    /** Lista de participantes en la partida (humano y máquina) */
    private final List<Participante> participantes;
    
    /** El bombo utilizado en la partida actual */
    private Bombo bombo;
    
    /** Indica si una partida está actualmente en curso */
    private boolean partidaActiva;

    /** Índice del jugador humano en la lista de participantes */
    private static final int INDICE_JUGADOR = 0;
    
    /** Índice de la máquina en la lista de participantes */
    private static final int INDICE_MAQUINA = 1;

    /**
     * Crea una nueva instancia de Juego.
     */
    public Juego() {
        this.participantes = new ArrayList<>();
        this.partidaActiva = false;
    }

    /**
     * Prepara y arranca una partida nueva con un jugador humano y la máquina.
     * 
     * @param nombreJugador El nombre que usará el jugador humano.
     */
    public void iniciarPartida(String nombreJugador) {
        participantes.clear();
        participantes.add(new Jugador(nombreJugador)); // Jugador humano
        participantes.add(new Jugador("Máquina")); // Jugador automático

        bombo = new Bombo();
        partidaActiva = true;
    }

    /**
     * Finaliza la partida actual.
     */
    public void finalizarPartida() {
        partidaActiva = false;
    }

    /**
     * Extrae el siguiente número del bombo si la partida está activa.
     * 
     * @return El número extraído, o -1 si no se puede extraer (partida inactiva o bombo vacío).
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
     * @return true si el número ya fue extraído, false en caso contrario.
     */
    public boolean esNumeroExtraido(int numero) {
        return bombo != null && bombo.contieneExtraido(numero);
    }

    /**
     * Obtiene el participante que representa al jugador humano.
     * 
     * @return El participante humano.
     */
    public Participante getJugador() {
        return getParticipante(INDICE_JUGADOR);
    }

    /**
     * Obtiene el participante que representa a la máquina.
     * 
     * @return El participante máquina.
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
     * Indica si hay una partida activa.
     * 
     * @return true si la partida está activa, false en caso contrario.
     */
    public boolean isPartidaActiva() {
        return partidaActiva;
    }
}
