package clases.model;

import java.util.ArrayList;
import java.util.List;

// como va el juego
public class Juego {
    private List<Participante> participantes;
    private Bombo bombo;
    private boolean partidaActiva;

    public Juego() {
        participantes = new ArrayList<>();
        partidaActiva = false;
    }

    // Prepara y arranca una partida nueva
    public void iniciarPartida(String nombreJugador) {
        participantes.clear();
        participantes.add(new Jugador(nombreJugador));
        participantes.add(new Jugador("Máquina"));

        // Creamos un bombo nuevo y mezclado
        bombo = new Bombo();
        partidaActiva = true;
    }

    // Marca la partida como terminada
    public void finalizarPartida() {
        partidaActiva = false;
    }

    // Saca el siguiente número del bombo.
    public int extraerNumero() {
        if (!partidaActiva || bombo == null)
            return -1;
        return bombo.sacarNumero();
    }

    // devuelve true si un número específico ya salió del bombo en esta partida
    public boolean esNumeroExtraido(int numero) {
        return bombo != null && bombo.contieneExtraido(numero);
    }

    // la persona
    public Participante getJugador() {
        return participantes.isEmpty() ? null : participantes.get(0);
    }

    // la máquina
    public Participante getMaquina() {
        return participantes.size() > 1 ? participantes.get(1) : null;
    }

    public boolean isPartidaActiva() {
        return partidaActiva;
    }
}
