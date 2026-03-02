#  POO BINGO

- [1. Concepto de Alto Nivel (The Pitch):](#1-concepto-de-alto-nivel-the-pitch)
  - [Concepto](#concepto)
  - [Navarrativa](#narrativa)
  - [MVP (Minimum Viable Product)](#mvp)
- [2. Mecánicas de Juego (Gameplay):](#2-mecánicas-de-juego-gameplay)
  - [Objetivo del Juego](#objetivo-del-juego)
  - [Estructura de la Partida](#estructura-de-la-partida)
  - [Core Loop](#core-loop)
- [3. Aspectos Técnicos (Stack Tecnológico):](#3-aspectos-técnicos-stack-tecnológico)
- [4. Bucle de Juego (Game Loop):](#4-bucle-de-juego-game-loop)
- [5. Contenido:](#5-contenido)
- [6. Interfaz (UI/HUD):](#6-interfaz-uihud)
- [7. Planificación del Proyecto:](#7-planificación-del-proyecto-28-horas)
- [8. Riesgos Técnicos:](#8-riesgos-técnicos)
- [9. Conclusión:](#9-conclusión)

# 1. Concepto de Alto Nivel (The Pitch)

## Concepto:

Videojuego desarrollado en Java (Maven) basado en el juego clásico del Bingo, en el que un jugador compite contra una máquina controlada por el sistema.

La extracción de números se realiza automáticamente cada 4 segundos, y el jugador debe marcar manualmente los números en su cartón si han sido extraídos.

El objetivo es conseguir Bingo antes que la máquina.

##  Narrativa:

En esta versión digital del clásico Bingo, el jugador se enfrenta a una Inteligencia Artificial que compite bajo las mismas reglas.

Ambos reciben un cartón generado aleatoriamente.  
El bombo comienza a girar y los números se extraen automáticamente.

El jugador deberá estar atento y marcar manualmente los números correctos antes de que la máquina complete su cartón.

##  MVP (Minimum Viable Product):

Esta versión inicial describe los elementos básicos necesarios para que el juego sea completamente funcional.

### Incluye:

-  1 Jugador vs Máquina  
-  1 cartón por participante  
-  Extracción automática cada X segundos  
-  Marcado manual por parte del jugador  
-  Marcado automático por parte de la máquina  
-  Comprobación de Línea (informativa)  
-  Comprobación de Bingo (condición de victoria)  

Las mejoras futuras estarán indicadas con el tag:

> **[OMSI] (Out of MVP Scope Improvement)**

# 2. Mecánicas de Juego (Gameplay)

##  Objetivo del Juego

Completar todos los números del cartón antes que la máquina.

Gana quien consiga Bingo primero.

##  Estructura de la Partida

1. El jugador inicia la partida.
2. Se generan automáticamente:
   - Cartón del jugador
   - Cartón de la máquina
3. Comienza la extracción automática de números.

Cada vez que se extrae un número:

- Se añade al historial.
- La máquina lo marca automáticamente si lo tiene.
- El jugador debe marcarlo manualmente si lo tiene.

Tras cada extracción:

- Se comprueba si hay Línea.
- Se comprueba si hay Bingo.
- Si alguien consigue Bingo -> Finaliza la partida.


##  Core Loop
- Esperar X segundos
- Extraer número no repetido
- Mostrar número en pantalla
- Máquina marca automáticamente
- Jugador puede marcar manualmente
- Comprobar condiciones de victoria
- Repetir hasta que haya ganador
