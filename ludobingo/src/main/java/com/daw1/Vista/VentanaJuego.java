package com.daw1.Vista;

import com.daw1.model.Carton;
import com.daw1.model.Juego;
import com.daw1.model.Participante;
import com.daw1.model.Sonido;
import javax.swing.*;
import java.awt.*;

/**
 * El panel principal del juego donde se conjuntan todos los elementos
 * Gestiona el flujo de la partida, los eventos de usuario y la actualización de
 * la interfaz.
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

    /**
     * Inicializa el tablero de juego
     */
    public VentanaJuego() {
        setLayout(new BorderLayout(8, 8));

        juego = new Juego();

        cabecera = new Cabecera();
        historial = new Historial();
        botones = new Botones();
        panelCartonJugador = new PanelCarton("Tu Cartón  [haz clic para marcar]", new Color(25, 25, 55), false,
                "pingu.png");
        panelCartonMaquina = new PanelCarton("Cartón Máquina  [automático]", new Color(140, 30, 30), true,
                "PinguBetis.png");

        // Juntamos los dos cartones en un panel central
        JPanel panelCartones = new JPanel(new GridLayout(2, 1, 0, 12));
        panelCartones.setBackground(new Color(235, 235, 250));
        panelCartones.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 8));
        panelCartones.add(panelCartonJugador);
        panelCartones.add(panelCartonMaquina);
        // los agregamos a la ventana en sus respectivos bordes
        add(cabecera, BorderLayout.NORTH);
        add(panelCartones, BorderLayout.CENTER);
        add(historial, BorderLayout.EAST);
        add(botones, BorderLayout.SOUTH);

        // Enganchamos botones con funciones
        registrarEventos();

        // Música de la pantalla de cartones/juego en bucle
        Sonido.reproducirCartones();
    }

    /**
     * Registra los listeners de eventos para interactuar con los componentes de la
     * interfaz.
     */
    private void registrarEventos() {
        botones.getBotonIniciar().addActionListener(e -> iniciarNuevaPartida());
        cabecera.getBotonExtraer().addActionListener(e -> procesarExtraccion());
        botones.getBotonFinalizar().addActionListener(e -> confirmarYFinalizar());
        botones.getBotonAjustes().addActionListener(e -> abrirAjustes());

        configurarModoAutomatico();
        configurarClicsEnCarton();
    }

    /**
     * Solicita el nombre al usuario e inicia una nueva partida.
     */
    private void iniciarNuevaPartida() {
        String nombre = DialogosJuego.pedirNombre(this).trim();
        if (nombre.isEmpty())
            return;

        detenerModoAuto();
        juego.iniciarPartida(nombre);
        yaHayLineaEnPartida = false;

        panelCartonJugador.setTitulo("Tu Cartón [" + nombre + "] — haz clic para marcar");
        actualizarCartones();

        botones.getBotonIniciar().setEnabled(false);
        habilitarControlesPartida(true);

        historial.limpiarLog();
        cabecera.resetNumero();
        historial.escribirLog("¡Partida iniciada!\nJugador: " + nombre + "\n¡Buena suerte!\n---------------");
    }

    /**
     * Habilita o deshabilita los controles del juego según el estado de la partida
     * 
     * @param habilitar true para habilitar, false para deshabilitar
     */
    private void habilitarControlesPartida(boolean habilitar) {
        cabecera.getBotonExtraer().setEnabled(habilitar && !cabecera.getBtnAuto().isSelected());
        botones.getBotonFinalizar().setEnabled(habilitar);
        cabecera.getBtnAuto().setEnabled(habilitar);
        cabecera.getSpinnerVelocidad().setEnabled(habilitar);
    }

    /**
     * Muestra una confirmación y finaliza la partida si el usuario acepta
     */
    private void confirmarYFinalizar() {
        if (DialogosJuego.confirmarFinalizar(this)) {
            finalizarJuego("Partida finalizada manualmente.");
        }
    }

    /**
     * Abre la ventana de ajustes
     */
    private void abrirAjustes() {
        DialogoAjustes dlg = new DialogoAjustes(SwingUtilities.getWindowAncestor(this));
        dlg.setVisible(true);
    }

    /**
     * Configura el comportamiento de la extracción automática de números
     */
    private void configurarModoAutomatico() {
        cabecera.getBtnAuto().addActionListener(e -> {
            if (cabecera.getBtnAuto().isSelected()) {
                activarModoAuto();
            } else {
                detenerModoAuto();
            }
        });

        cabecera.getSpinnerVelocidad().addChangeListener(e -> {
            if (cabecera.getBtnAuto().isSelected() && timerAuto != null) {
                int segundos = (Integer) cabecera.getSpinnerVelocidad().getValue();
                timerAuto.cambiarIntervalo(segundos);
                historial.escribirLog("Velocidad -> " + segundos + "s");
            }
        });
    }

    /**
     * Activa el temporizador de auto
     */
    private void activarModoAuto() {
        int segundos = (Integer) cabecera.getSpinnerVelocidad().getValue();
        timerAuto = new Temporizador(segundos, ev -> procesarExtraccion());
        timerAuto.iniciar();
        cabecera.getBotonExtraer().setEnabled(false);
        historial.escribirLog("Auto ON [" + segundos + "s]");
    }

    /**
     * Detiene el temporizador de auto
     */
    private void detenerModoAuto() {
        if (timerAuto != null) {
            timerAuto.detener();
        }
        cabecera.getBtnAuto().setSelected(false);
        cabecera.getBotonExtraer().setEnabled(juego.isPartidaActiva());
        historial.escribirLog("Auto OFF");
    }

    /**
     * Registra los eventos de clic en cada celda del cartón del jugador
     */
    private void configurarClicsEnCarton() {
        JButton[] celdas = panelCartonJugador.getCeldas();
        for (int i = 0; i < celdas.length; i++) {
            final int indice = i;
            celdas[i].addActionListener(e -> marcarCeldaJugador(indice));
        }
    }

    /**
     * Procesa el intento de marcado de una celda por parte del jugador
     * 
     * @param indice El índice de la celda pulsada
     */
    private void marcarCeldaJugador(int indice) {
        if (!juego.isPartidaActiva())
            return;

        Participante jugador = juego.getJugador();
        Carton carton = jugador.getCarton();
        int fila = indice / Carton.COLUMNAS;
        int col = indice % Carton.COLUMNAS;
        int num = carton.getNumero(fila, col);

        if (num == 0 || carton.isMarcado(fila, col))
            return;

        if (!juego.esNumeroExtraido(num)) {
            reproducirEfectoError(indice);
            return;
        }

        carton.marcarNumero(num);
        historial.escribirLog("Has marcado el " + num);
        panelCartonJugador.actualizarCarton(jugador, true);

        verificarPremios(carton, jugador.getNombre());
    }

    /**
     * Muestra un efecto visual de error cuando se pulsa una celda incorrecta
     * 
     * @param indice El índice de la celda que causó el error
     */
    private void reproducirEfectoError(int indice) {
        panelCartonJugador.getCeldas()[indice].setBackground(new Color(255, 70, 70));
        new Timer(350, ev -> {
            panelCartonJugador.actualizarCarton(juego.getJugador(), juego.isPartidaActiva());
            ((Timer) ev.getSource()).stop();
        }).start();
    }

    /**
     * Realiza la extracción de un número
     */
    private void procesarExtraccion() {
        if (!juego.isPartidaActiva())
            return;

        int num = juego.extraerNumero();
        if (num == -1) {
            DialogosJuego.mostrarBomboVacio(this);
            finalizarJuego("Ya no quedan más números. Fin.");
            return;
        }

        cabecera.setNumero(String.valueOf(num));
        historial.escribirLog("Nº " + num);
        Sonido.reproducirNumero(num);
        actualizarCartones();

        planificarMarcadoMaquina(num);
    }

    /**
     * lo que tarda la maquina en marcar el numero
     * 
     * @param num El número marcado
     */
    private void planificarMarcadoMaquina(int num) {
        // La máquina "piensa" entre 2 y 5 segundos antes de marcar
        int retardo = 2000 + (int) (Math.random() * 3000);
        new Timer(retardo, e -> {
            ((Timer) e.getSource()).stop();
            if (juego.isPartidaActiva()) {
                procesarMarcadoMaquina(num);
            }
        }).start();
    }

    /**
     * Realiza el marcado del numero en el carton de la maquina
     * 
     * @param num El número marcado
     */
    private void procesarMarcadoMaquina(int num) {
        Participante maquina = juego.getMaquina();
        if (maquina != null && maquina.getCarton().contieneNumero(num)) {
            maquina.getCarton().marcarNumero(num);
            panelCartonMaquina.actualizarCarton(maquina, true);
            verificarPremios(maquina.getCarton(), "Máquina");
        }
    }

    /**
     * Verifica si se han conseguido premios de línea o bingo tras un marcado
     * 
     * @param carton El cartón a comprobar
     * @param nombre El nombre del participante
     */
    private void verificarPremios(Carton carton, String nombre) {
        if (!yaHayLineaEnPartida && carton.comprobarLinea()) {
            gestionarLinea(nombre);
        }
        if (carton.comprobarBingo()) {
            gestionarBingo(nombre);
        }
    }

    /**
     * Gestiona los eventos y notificaciones tras conseguirse una línea
     * 
     * @param nombre Nombre de quien hizo línea
     */
    private void gestionarLinea(String nombre) {
        yaHayLineaEnPartida = true;
        historial.escribirLog("¡LÍNEA! " + nombre);
        Sonido.reproducirLinea();

        boolean autoEstaba = cabecera.getBtnAuto().isSelected();
        if (autoEstaba && timerAuto != null)
            timerAuto.detener();

        DialogosJuego.mostrarLinea(this, nombre);

        Sonido.detenerLinea();
        Sonido.reproducirCartones();

        if (autoEstaba && timerAuto != null && juego.isPartidaActiva()) {
            timerAuto.iniciar();
        }
    }

    /**
     * Gestiona el fin de la partida tras cantarse Bingo
     * 
     * @param nombre El nombre del ganador
     */
    private void gestionarBingo(String nombre) {
        finalizarJuego("¡BINGO de " + nombre + "!");
        historial.escribirLog("¡BINGO! " + nombre);
        Sonido.reproducirBingo();

        mostrarPantallaVictoria(nombre);
    }

    /**
     * Muestra la pantalla de victoria final ocupando toda la ventana
     * 
     * @param nombre El nombre del ganador
     */
    private void mostrarPantallaVictoria(String nombre) {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
        if (frame != null) {
            frame.getContentPane().removeAll();
            boolean esMaquina = nombre.equals("Máquina");
            frame.add(new PanelVictoria(nombre, esMaquina));
            frame.revalidate();
            frame.repaint();
        }
    }

    /**
     * Finaliza la estructura lógica y visual de la partida actual
     * 
     * @param mensaje El mensaje de finalización a mostrar en el log
     */
    private void finalizarJuego(String mensaje) {
        juego.finalizarPartida();
        detenerModoAuto();
        actualizarCartones();

        habilitarControlesPartida(false);
        botones.getBotonIniciar().setEnabled(true);
        cabecera.resetNumero();
        historial.escribirLog(mensaje);
    }

    /**
     * Actualiza los paneles visuales de ambos cartones
     */
    private void actualizarCartones() {
        panelCartonJugador.actualizarCarton(juego.getJugador(), juego.isPartidaActiva());
        panelCartonMaquina.actualizarCarton(juego.getMaquina(), juego.isPartidaActiva());
    }
}