package clases.Vista;

import clases.model.Carton;
import clases.model.Juego;
import clases.model.Sonido;
import javax.swing.*;
import java.awt.*;

// El panel principal donde ocurre toda la acción.
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

        juego = new Juego();

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

        // Música de la pantalla de cartones/juego en bucle
        Sonido.reproducirCartones();
    }

    // Registra los eventos de los botones
    private void registrarEventos() {
        botones.getBotonIniciar().addActionListener(e -> {
            String nombre = DialogosJuego.pedirNombre(this).trim();
            if (timerAuto != null)
                timerAuto.detener();
            cabecera.getBtnAuto().setSelected(false);

            juego.iniciarPartida(nombre);
            yaHayLineaEnPartida = false;

            panelCartonJugador.setTitulo("Tu Cartón  [" + nombre + "] — haz clic para marcar");
            actualizarCartones();

            botones.getBotonIniciar().setEnabled(false);
            cabecera.getBotonExtraer().setEnabled(true);
            botones.getBotonFinalizar().setEnabled(true);
            cabecera.getBtnAuto().setEnabled(true);
            cabecera.getSpinnerVelocidad().setEnabled(true);

            historial.limpiarLog();
            cabecera.resetNumero();
            historial.escribirLog("Partida iniciada!\nJugador: " + nombre + "\n¡Buena suerte!\n---------------");
        });

        cabecera.getBotonExtraer().addActionListener(e -> procesarExtraccion());

        botones.getBotonFinalizar().addActionListener(e -> {
            if (DialogosJuego.confirmarFinalizar(this))
                finalizarJuego("Partida finalizada manualmente.");
        });

        // Configurar qué pasa al hacer clic en el cuadradito "Auto"
        cabecera.getBtnAuto().addActionListener(e -> {
            if (cabecera.getBtnAuto().isSelected()) {
                int s = (Integer) cabecera.getSpinnerVelocidad().getValue();
                timerAuto = new Temporizador(s, ev -> procesarExtraccion());
                timerAuto.iniciar();
                cabecera.getBotonExtraer().setEnabled(false);
                historial.escribirLog("Auto ON [" + s + "s]");
            } else {
                if (timerAuto != null)
                    timerAuto.detener();
                cabecera.getBotonExtraer().setEnabled(true);
                historial.escribirLog("Auto OFF");
            }
        });

        cabecera.getSpinnerVelocidad().addChangeListener(e -> {
            if (cabecera.getBtnAuto().isSelected() && timerAuto != null) {
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

    private void marcarCeldaJugador(int indice) {
        if (!juego.isPartidaActiva())
            return;

        Carton c = juego.getJugador().getCarton();
        int fila = indice / 9, col = indice % 9;
        int num = c.getNumero(fila, col);

        if (num == 0 || c.isMarcado(fila, col))
            return; // Era un hueco vacío o ya lo habías marcado

        // Has pulsado un número que aún no ha salido del bombo
        if (!juego.esNumeroExtraido(num)) {
            panelCartonJugador.getCeldas()[indice].setBackground(new Color(255, 70, 70));
            new Timer(350, ev -> {
                panelCartonJugador.actualizarCarton(juego.getJugador(), juego.isPartidaActiva());
                ((Timer) ev.getSource()).stop();
            }).start();
            return;
        }

        // Si es correcto se marca
        c.marcarNumero(num);
        historial.escribirLog("Haz marcado el " + num);
        panelCartonJugador.actualizarCarton(juego.getJugador(), true);

        verificarPremios(c, juego.getJugador().getNombre());
    }

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
        Sonido.reproducirNumero(num); // Reproducir audio del número
        actualizarCartones();

        // Tras 2 a 5 segundos falsos de "pensar", la máquina revisa su propio cartón
        new Timer(2000 + (int) (Math.random() * 3000), e -> {
            ((Timer) e.getSource()).stop();
            if (juego.isPartidaActiva() && juego.getMaquina() != null) {
                Carton cm = juego.getMaquina().getCarton();
                if (cm.contieneNumero(num)) { // Si la maquina lo tiene lo marca y mira si ha ganado
                    juego.getMaquina().marcarNumero(num);
                    panelCartonMaquina.actualizarCarton(juego.getMaquina(), true);
                    verificarPremios(cm, "Máquina");
                }
            }
        }).start();
    }

    // comprueba si ha conseguido las lineas o el bingo
    private void verificarPremios(Carton c, String nombre) {
        if (!yaHayLineaEnPartida && c.comprobarLinea()) {
            yaHayLineaEnPartida = true;
            historial.escribirLog("¡LÍNEA! " + nombre);
            Sonido.reproducirLinea();
            DialogosJuego.mostrarLinea(this, nombre);
            Sonido.reproducirCartones(); // Reanudamos la música del juego tras cerrar el aviso
        }
        if (c.comprobarBingo()) {
            finalizarJuego("BINGO de " + nombre + "!");
            historial.escribirLog("¡BINGO! " + nombre);
            Sonido.reproducirBingo();

            // Reemplazamos el diálogo por la pantalla de victoria completa
            JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);
            if (frame != null) {
                frame.getContentPane().removeAll();
                frame.add(new PanelVictoria(nombre));
                frame.revalidate();
                frame.repaint();
            }
        }
    }

    // finaliza la partida
    private void finalizarJuego(String msg) {
        juego.finalizarPartida();
        if (timerAuto != null)
            timerAuto.detener();
        actualizarCartones();

        cabecera.getBtnAuto().setSelected(false);
        cabecera.getBtnAuto().setEnabled(false);
        cabecera.getSpinnerVelocidad().setEnabled(false);
        cabecera.resetNumero();

        cabecera.getBotonExtraer().setEnabled(false);
        botones.getBotonFinalizar().setEnabled(false);
        botones.getBotonIniciar().setEnabled(true);
        historial.escribirLog(msg);
    }

    // actualiza los cartones con cada partida
    private void actualizarCartones() {
        panelCartonJugador.actualizarCarton(juego.getJugador(), juego.isPartidaActiva());
        panelCartonMaquina.actualizarCarton(juego.getMaquina(), juego.isPartidaActiva());
    }
}