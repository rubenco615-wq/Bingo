package com.daw1.model;

import java.util.ArrayList;
import java.util.List;

// Gestiona la lógica principal y el estado de una partida de Bingo.
public class Juego {
    private final List<Participante> participantes;
    private Bombo bombo;
    private boolean partidaActiva;

    private static final int INDICE_JUGADOR = 0;
    private static final int INDICE_MAQUINA = 1;

    public Juego() {
        this.participantes = new ArrayList<>();
        this.partidaActiva = false;
    }

    // Prepara y arranca una partida nueva con un jugador humano y la máquina.
    public void iniciarPartida(String nombreJugador) {
        participantes.clear();
        participantes.add(new Jugador(nombreJugador)); // Jugador humano
        participantes.add(new Jugador("Máquina")); // Jugador automático

        bombo = new Bombo();
        partidaActiva = true;
    }

    public void finalizarPartida() {
        partidaActiva = false;
    }

    // Extrae el siguiente número del bombo si la partida está activa.
    public int extraerNumero() {
        if (!partidaActiva || bombo == null) {
            return -1;
        }
        return bombo.sacarNumero();
    }

    // Comprueba si un número ya ha salido del bombo.
    public boolean esNumeroExtraido(int numero) {
        return bombo != null && bombo.contieneExtraido(numero);
    }

    public Participante getJugador() {
        return getParticipante(INDICE_JUGADOR);
    }

    public Participante getMaquina() {
        return getParticipante(INDICE_MAQUINA);
    }

    private Participante getParticipante(int indice) {
        if (indice >= 0 && indice < participantes.size()) {
            return participantes.get(indice);
        }
        return null;
    }

    public boolean isPartidaActiva() {
        return partidaActiva;
    }
}
