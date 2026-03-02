##  Diagrama UML

```mermaid
classDiagram

class Juego {
    - Jugador jugador
    - Maquina maquina
    - Bombo bombo
    - Temporizador temporizador
    + iniciarPartida()
    + comprobarVictoria()
    + finalizarPartida()
}

class Bombo {
    - List~Integer~ numerosDisponibles
    - List~Integer~ numerosExtraidos
    + extraerNumero() Integer
    + quedanNumeros() boolean
}

class Carton {
    - int[][] numeros
    - boolean[][] marcados
    + generarCarton()
    + marcarNumero(int numero)
    + tieneNumero(int numero) boolean
    + comprobarLinea() boolean
    + comprobarBingo() boolean
}

class Jugador {
    - String nombre
    - Carton carton
    + marcarNumero(int numero)
}

class Maquina {
    + marcarAutomatico(int numero)
}

class Temporizador {
    - int intervalo
    + iniciar()
    + detener()
}

Jugador <|-- Maquina
Jugador --> Carton
Maquina --> Carton
Juego --> Jugador
Juego --> Maquina
Juego --> Bombo
Juego --> Temporizador
Bombo --> "0..90" Integer
