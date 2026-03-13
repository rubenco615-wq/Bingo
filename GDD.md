#  POO BINGO - Documento de Diseño (GDD)

Este documento describe la estructura y el funcionamiento del juego de Bingo, diseñado como material educativo para estudiantes de 1º de DAW.

- [1. Concepto de Alto Nivel (The Pitch)](#1-concepto-de-alto-nivel-the-pitch)
- [2. Mecánicas de Juego (Gameplay)](#2-mecánicas-de-juego-gameplay)
- [3. Aspectos Técnicos (Arquitectura)](#3-aspectos-técnicos-arquitectura)
- [4. Bucle de Juego (Game Loop)](#4-bucle-de-juego-game-loop)
- [5. Interfaz (UI/UX)](#5-interfaz-uiux)

---

# 1. Concepto de Alto Nivel (The Pitch)

## Concepto:
Videojuego desarrollado en Java (Maven) basado en el Bingo clásico. El jugador compite contra una "Máquina" en una partida frenética donde la atención es clave.

## MVP (Mínimo Producto Viable):
- Sistema de un jugador contra la IA.
- Generación de cartones únicos siguiendo reglas reales.
- Extracción automática (Bombo) con velocidad ajustable.
- Marcado manual para el jugador y automático para la máquina.

---

# 2. Mecánicas de Juego (Gameplay)

## Objetivo:
Ser el primero en completar todos los números de su cartón (**Bingo**). También se premia la **Línea** (completar una fila entera).

## Estructura de la Partida:
1. El jugador introduce su nombre.
2. Se generan dos cartones (Jugador y Máquina).
3. El bombo comienza a extraer números.
4. **Jugador**: Debe hacer clic en los números de su cartón que hayan salido.
5. **Máquina**: Marca automáticamente sus números tras un pequeño retardo.
6. El primer participante en cantar Bingo gana la partida.

## El Cartón de Bingo:
- Estructura de **3 filas x 9 columnas**.
- Cada fila contiene exactamente **5 números**.
- Los números están organizados por columnas (Col 1: 1-9, Col 2: 10-19... Col 9: 80-90).
- Las casillas vacías contienen una imagen decorativa (Pingu).

---

# 3. Aspectos Técnicos (Arquitectura)

El proyecto sigue una estructura limpia, separando la lógica del negocio de la interfaz visual.

## Paquetes:
- `com.daw1.model`: Contiene la lógica pura (cartón, bombo, juego).
- `com.daw1.Vista`: Contiene todas las clases de la interfaz Swing.

## Clases Principales (Modelo):
- **`Juego`**: El director de orquesta. Gestiona la partida, el bombo y los participantes.
- **`Participante` (Interfaz)**: Define qué puede hacer un jugador (marcar, obtener nombre, etc.).
- **`Jugador`**: Implementación de la interfaz para humanos y máquinas.
- **`Carton`**: Contiene la matriz de números y la lógica de validación de premios.
- **`Bombo`**: Gestiona las 90 bolas y asegura que no se repitan.

## Clases Principales (Vista):
- **`VentanaJuego`**: Panel principal que conecta el modelo con la interfaz.
- **`PanelCarton`**: Representación gráfica de un cartón.
- **`Cabecera`**: Muestra el número actual y controles de velocidad/automático.
- **`Historial`**: Registro visual de todos los números que han salido.
- **`Botones`**: Controles de inicio, fin y ajustes.

---

# 4. Bucle de Juego (Game Loop)

1. **Inicio**: `PantallaInicio` -> `DialogosJuego` (pedir nombre).
2. **Setup**: `Juego.iniciarPartida()` -> Crea Cartones y Bombo.
3. **Loop**:
   - `Juego.extraerNumero()` -> Se obtiene la bola.
   - `Sonido.reproducirNumero()` -> Locución de la bola.
   - `PanelCarton.actualizarCarton()` -> Refresco visual.
   - `VerificarPremios` -> ¿Hay Línea o Bingo?
4. **Fin**: `PanelVictoria` si alguien consigue el Bingo.

---

# 5. Interfaz (UI/UX)

- **Feedback Visual**: Las celdas cambian de color (amarillo al marcar, rojo si hay error).
- **Modo Automático**: Permite que el juego extraiga bolas solo según la velocidad del spinner.
- **Sonido**: Música ambiental y efectos para números y premios.
- **Adaptabilidad**: Interfaz basada en `BorderLayout` y `GridLayout` para mantener el orden.

---

> **Nota para estudiantes**: Esta estructura está diseñada para ser escalable. Si quisieras añadir un tercer jugador, solo tendrías que añadir un nuevo `Participante` al objeto `Juego`.
