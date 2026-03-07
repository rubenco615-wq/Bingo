package clases.Vista;

import clases.model.Carton;
import clases.model.Juego;
import clases.model.Sonido;
import clases.model.Sonido;
import javax.swing.*;
import java.awt.*;

/**
 * El panel principal donde ocurre toda la acción.
 * Es la que "une" la lógica de Juego.java con los dibujos de los Paneles.
 */
public class VentanaJuego extends JPanel {

    private Juego juego;
    // Paneles visuales que componen la ventana
    private Cabecera cabecera;
    private PanelCarton panelCartonJugador;
    private PanelCarton panelCartonMaquina;
    private Historial historial;
    private Botones botones;

    private boolean yaHayLineaEnPartida;
    private Temporizador timerAuto;

    public VentanaJuego() {
        setLayout(new BorderLayout(8, 8));

        juego = new Juego(); // Creamos la lógica en la sombra

        // Preparamos todas las piezas del puzzle visual
        cabecera = new Cabecera();
        historial = new Historial();
        botones = new Botones();
        panelCartonJugador = new PanelCarton("Tu Cartón  [haz clic para marcar]", new Color(25, 25, 55), false);
        panelCartonMaquina = new PanelCarton("Cartón Máquina  [automático]", new Color(140, 30, 30), true);

        // Juntamos los dos cartones en un panel central
        JPanel panelCartones = new JPanel(new GridLayout(2, 1, 0, 12));
        panelCartones.setBackground(new Color(235, 235, 250));
        panelCartones.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 8));
        panelCartones.add(panelCartonJugador);
        panelCartones.add(panelCartonMaquina);

        // Agregamos las piezas a la ventana en sus respectivos bordes cardinales
        add(cabecera, BorderLayout.NORTH);
        add(panelCartones, BorderLayout.CENTER);
        add(historial, BorderLayout.EAST);
        add(botones, BorderLayout.SOUTH);

        // Enganchamos botones con funciones
        registrarEventos();

        // Cambiamos a la música de espera/juego nada más cargar la ventana
        Sonido.reproducirBGM("juego.wav");
    }

    /**
     * Asocia qué ocurre cuando interactúas con la interfaz visual.
     * (Ej: qué pasa si pulsas "Extraer" o activas el "Auto")
     */
    private void registrarEventos() {
        botones.getBotonIniciar().addActionListener(e -> {
            String nombre = DialogosJuego.pedirNombre(this).trim();
            if (timerAuto != null)
                timerAuto.detener();
            cabecera.getChkAutoExtraccion().setSelected(false);

            juego.iniciarPartida(nombre);
            yaHayLineaEnPartida = false;

            panelCartonJugador.setTitulo("Tu Cartón  [" + nombre + "] — haz clic para marcar");
            actualizarCartones();

            botones.getBotonIniciar().setEnabled(false);
            botones.getBotonExtraer().setEnabled(true);
            botones.getBotonFinalizar().setEnabled(true);
            cabecera.getChkAutoExtraccion().setEnabled(true);
            cabecera.getSpinnerVelocidad().setEnabled(true);

            historial.limpiarLog();
            cabecera.resetNumero();
            historial.escribirLog("Partida iniciada!\nJugador: " + nombre + "\n¡Buena suerte!\n---------------");
        });

        botones.getBotonExtraer().addActionListener(e -> procesarExtraccion());

        botones.getBotonFinalizar().addActionListener(e -> {
            if (DialogosJuego.confirmarFinalizar(this))
                finalizarJuego("Partida finalizada manualmente.");
        });

        // Configurar qué pasa al hacer clic en el cuadradito "Auto"
        cabecera.getChkAutoExtraccion().addActionListener(e -> {
            if (cabecera.getChkAutoExtraccion().isSelected()) {
                int s = (Integer) cabecera.getSpinnerVelocidad().getValue();
                timerAuto = new Temporizador(s, ev -> procesarExtraccion());
                timerAuto.iniciar();
                botones.getBotonExtraer().setEnabled(false);
                historial.escribirLog("Auto ON [" + s + "s]");
            } else {
                if (timerAuto != null)
                    timerAuto.detener();
                botones.getBotonExtraer().setEnabled(true);
                historial.escribirLog("Auto OFF");
            }
        });

        // Modificador de velocidad del bombo automático en tiempo real
        cabecera.getSpinnerVelocidad().addChangeListener(e -> {
            if (cabecera.getChkAutoExtraccion().isSelected() && timerAuto != null) {
                int s = (Integer) cabecera.getSpinnerVelocidad().getValue();
                timerAuto.cambiarIntervalo(s);
                historial.escribirLog("Velocidad -> " + s + "s");
            }
        });

        // Enganchar listeners de Clic a todos los huecos numéricos de TU cartón
        JButton[] celdas = panelCartonJugador.getCeldas();
        for (int i = 0; i < celdas.length; i++) {
            int indice = i;
            celdas[i].addActionListener(e -> marcarCeldaJugador(indice));
        }
    }

    /**
     * Se dispara cuando haces clic con el ratón sobre un número de tu cartón.
     */
    private void marcarCeldaJugador(int indice) {
        if (!juego.isPartidaActiva())
            return;

        Carton c = juego.getJugador().getCarton();
        int fila = indice / 9, col = indice % 9;
        int num = c.getNumero(fila, col);

        if (num == 0 || c.isMarcado(fila, col))
            return; // Era un hueco vacío o ya lo habías marcado

        // Has pulsado un número que aún no ha salido del bombo (marcado erróneo,
        // parpadeo rojo)
        if (!juego.esNumeroExtraido(num)) {
            panelCartonJugador.getCeldas()[indice].setBackground(new Color(255, 70, 70));
            new Timer(350, ev -> {
                panelCartonJugador.actualizarCarton(juego.getJugador(), juego.isPartidaActiva());
                ((Timer) ev.getSource()).stop();
            }).start();
            return;
        }

        // Si es legítimo, se marca de verdad
        c.marcarNumero(num);
        historial.escribirLog("Marcas el " + num);
        panelCartonJugador.actualizarCarton(juego.getJugador(), true);

        // Nos fijamos en si has conseguido ganar algo
        verificarPremios(c, juego.getJugador().getNombre());
    }

    /**
     * Tira del bombo real en la sombra y canta el número por pantalla.
     */
    private void procesarExtraccion() {
        if (!juego.isPartidaActiva())
            return;

        int num = juego.extraerNumero();
        if (num == -1) {
            DialogosJuego.mostrarBomboVacio(this);
            finalizarJuego("Bombo vacío. Fin.");
            return;
        }

        cabecera.setNumero(String.valueOf(num));
        historial.escribirLog("Nº " + num + " ← ¡márcalo!");
        actualizarCartones();

        // Tras 2 a 5 segundos falsos de "pensar", la máquina revisa su propio cartón
        new Timer(2000 + (int) (Math.random() * 3000), e -> {
            ((Timer) e.getSource()).stop();
            if (juego.isPartidaActiva() && juego.getMaquina() != null) {
                Carton cm = juego.getMaquina().getCarton();
                if (cm.contieneNumero(num)) { // Si la CPU lo tiene, lo tacha y mira si ha ganado
                    juego.getMaquina().marcarNumero(num);
                    panelCartonMaquina.actualizarCarton(juego.getMaquina(), true);
                    verificarPremios(cm, "Máquina");
                }
            }
        }).start();
    }

    /**
     * Mira un cartón en concreto y lanza pompas si has hecho línea o terminado.
     */
    private void verificarPremios(Carton c, String nombre) {
        if (!yaHayLineaEnPartida && c.comprobarLinea()) {
            yaHayLineaEnPartida = true;
            historial.escribirLog("¡LÍNEA! " + nombre);
            Sonido.reproducirLinea(); // FX
            DialogosJuego.mostrarLinea(this, nombre);
        }
        if (c.comprobarBingo()) {
            historial.escribirLog("¡BINGO! " + nombre);
            Sonido.reproducirBingo(); // FX Final
            DialogosJuego.mostrarBingo(this, nombre);
            finalizarJuego("BINGO de " + nombre + "!");
        }
    }

    private void finalizarJuego(String msg) {
        juego.finalizarPartida();
        if (timerAuto != null)
            timerAuto.detener();
        actualizarCartones();

        cabecera.getChkAutoExtraccion().setSelected(false);
        cabecera.getChkAutoExtraccion().setEnabled(false);
        cabecera.getSpinnerVelocidad().setEnabled(false);
        cabecera.resetNumero();

        botones.getBotonExtraer().setEnabled(false);
        botones.getBotonFinalizar().setEnabled(false);
        botones.getBotonIniciar().setEnabled(true);
        historial.escribirLog(msg);
    }

    private void actualizarCartones() {
        panelCartonJugador.actualizarCarton(juego.getJugador(), juego.isPartidaActiva());
        panelCartonMaquina.actualizarCarton(juego.getMaquina(), juego.isPartidaActiva());
    }
}
