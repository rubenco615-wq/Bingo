package clases.model;

import java.util.ArrayList;
import java.util.List;

// Gestiona la partida de bingo.
public class Juego {
    private final List<Participante> participantes;
    private Bombo bombo;
    private boolean partidaActiva;

    public Juego() {
        this.participantes = new ArrayList<>();
        this.partidaActiva = false;
    }

    // Prepara y arranca una partida nueva con un jugador humano y la máquina.
    public void iniciarPartida(String nombreJugador) {
        participantes.clear();
        participantes.add(new Jugador(nombreJugador));
        participantes.add(new Jugador("Máquina"));

        // Creamos un bombo nuevo y mezclado
        bombo = new Bombo();
        partidaActiva = true;
    }

    // Marca la partida como terminada.
    public void finalizarPartida() {
        partidaActiva = false;
    }

    // Saca el siguiente número del bombo.
    public int extraerNumero() {
        if (!partidaActiva || bombo == null) {
            return -1;
        }
        return bombo.sacarNumero();
    }

    // Comprueba si un número específico ya ha salido del bombo.
    public boolean esNumeroExtraido(int numero) {
        return bombo != null && bombo.contieneExtraido(numero);
    }

    // Obtiene el participante que representa al jugador humano.
    public Participante getJugador() {
        return participantes.isEmpty() ? null : participantes.get(0);
    }

    // Obtiene el participante que representa a la máquina.
    public Participante getMaquina() {
        return participantes.size() > 1 ? participantes.get(1) : null;
    }

    public boolean isPartidaActiva() {
        return partidaActiva;
    }
}
