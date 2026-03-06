##  Diagrama UML – Versión Mejorada con Interfaz

```mermaid
classDiagram

%% INTERFAZ

class Participante {
    <<interface>>
    + marcarNumero(int numero)
    + getCarton() Carton
    + getNombre() String
}

%% CLASES CONCRETAS

class Jugador {
    - String nombre
    - Carton carton
    + marcarNumero(int numero)
    + getCarton()
    + getNombre()
}

class Maquina {
    - String nombre
    - Carton carton
    + marcarNumero(int numero)
    + getCarton()
    + getNombre()
}

Participante <|.. Jugador
Participante <|.. Maquina

%% CLASE PRINCIPAL

class Juego {
    - List~Participante~ participantes
    - Bombo bombo
    - Temporizador temporizador
    - boolean partidaActiva
    + iniciarPartida()
    + extraerNumero()
    + comprobarVictoria()
    + finalizarPartida()
    + notificarNumeroExtraido(int numero)
}

%% Bombo

class Bombo {
    - List~Integer~ numerosDisponibles
    - List~Integer~ numerosExtraidos
    + extraerNumero() Integer
    + quedanNumeros() boolean
}

%% Carton

class Carton {
    - int[][] numeros
    - boolean[][] marcados
    + generarCarton()
    + contieneNumero(int numero) boolean
    + marcarNumero(int numero)
    + comprobarLinea() boolean
    + comprobarBingo() boolean
}

%% Temporizador

class Temporizador {
    - int intervaloSegundos
    + iniciar()
    + detener()
}

%% INTERFAZ GRAFICA

class VentanaPrincipal {
    - Juego juego
    - PanelCarton panelJugador
    - PanelCarton panelMaquina
    - JLabel numeroActual
    - JTextArea historialNumeros
    - JButton botonIniciar
    + iniciarPartida()
    + mostrarNumero(int numero)
    + actualizarHistorial(int numero)
    + solicitarMarcarNumero(int numero)
    + mostrarMensajeLinea(String ganador)
    + mostrarMensajeBingo(String ganador)
    + mostrarError(String mensaje)
    + finalizarPartida()
}

class PanelCarton {
    - Carton carton
    - JButton[][] botonesNumeros
    - Color colorMarcado
    - Color colorNoMarcado
    + actualizarVista()
    + marcarBotonVisualmente(int numero)
    + resaltarLinea(int fila)
}

%% RELACIONES

Juego --> Participante
Juego --> Bombo
Juego --> Temporizador
Participante --> Carton
VentanaPrincipal --> Juego
VentanaPrincipal --> PanelCarton
