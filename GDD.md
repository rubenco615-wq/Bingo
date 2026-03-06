#  POO BINGO

- [1. Concepto de Alto Nivel (The Pitch):](#1-concepto-de-alto-nivel-the-pitch)
  - [Concepto](#concepto)
  - [Narrativa](#narrativa)
  - [MVP (Minimum Viable Product)](#mvp-minimum-viable-product)
- [2. Mecánicas de Juego (Gameplay):](#2-mecánicas-de-juego-gameplay)
  - [Objetivo del Juego](#objetivo-del-juego)
  - [Estructura de la Partida](#estructura-de-la-partida)
  - [Core Loop](#core-loop)
- [3. Aspectos Técnicos (Stack Tecnológico):](#3-aspectos-técnicos-stack-tecnológico)
- [4. Bucle de Juego (Game Loop):](#4-bucle-de-juego-game-loop)
- [5. Contenido:](#5-contenido)
- [6. Interfaz (UI/HUD):](#6-interfaz-uihud)

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

&gt; **[OMSI] (Out of MVP Scope Improvement)**

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
- La máquina marca automáticamente si lo tiene.
- El jugador debe marcarlo manualmente si lo tiene.

Tras cada extracción:

- Se comprueba si hay Línea.
- Se comprueba si hay Bingo.
- Si alguien consigue Bingo -&gt; Finaliza la partida.


##  Core Loop

- Esperar X segundos
- Extraer número no repetido
- Mostrar número en pantalla
- Notificar a participantes del nuevo número
- Máquina marca automáticamente
- Jugador puede marcar manualmente (haciendo clic)
- Comprobar condiciones de victoria
- Repetir hasta que haya ganador

##  Sistema de Extracción

- Rango: 1 a 90
- Sin números repetidos
- Lista de números disponibles
- Extracción automática mediante temporizador interno

##  Sistema de Cartones

Cada cartón tiene:
- 15 números aleatorios
- Sin repetidos
- Distribuidos en formato tradicional (3 filas x 9 columnas)

## Marcado de Números

### Flujo de Marcado:

1. El sistema notifica a ambos participantes que un número ha sido extraído
2. La máquina marca automáticamente en su cartón
3. El jugador debe hacer clic en el número de su cartón para marcarlo
4. La ventana solicita al objeto Jugador que marque el número
5. El Jugador valida y marca su propio cartón

### Jugador

Debe marcar manualmente el número si ha salido.

El sistema validará:

- Que el número haya sido extraído
- Que pertenezca al cartón
- Que no esté ya marcado

Si no cumple condiciones -&gt; Mensaje de error

### Máquina

- Marca automáticamente cuando el número extraído está en su cartón
- No puede equivocarse

## Condiciones de Victoria

### Línea

Se consigue cuando se completa una fila.

&gt; No finaliza la partida (solo mensaje informativo).

### Bingo

Se consigue cuando todos los números del cartón están marcados.

&gt; Finaliza la partida inmediatamente.

##  Fin de la Partida

- Se muestra mensaje de victoria.
- Se detiene la extracción automática.
- Opción de volver al menú principal.


# 3. Aspectos Técnicos (Stack Tecnológico)

## Tecnología

- **Java**
- **Maven**
- [OMSI] JavaFX para interfaz gráfica

##  Arquitectura

Se aplicará Programación Orientada a Objetos (POO).

### Clases principales:

- Juego
- Jugador
- Maquina
- Carton
- Bombo
- Numero (opcional)
- Temporizador
- VentanaPrincipal
- PanelCarton

##  Separación por paquetes

- com.bingo.engine
- com.bingo.model
- com.bingo.ui

## Responsabilidades

- Juego: Controla el flujo general, extrae números y notifica a participantes.
- Bombo: Gestiona números disponibles y extracción.
- Carton: Contiene números y lógica de marcado.
- Jugador: Gestiona interacción manual, valida y marca su propio cartón.
- Maquina: Hereda de Jugador pero marca automáticamente.
- VentanaPrincipal: Interfaz gráfica, muestra información y captura eventos.
- PanelCarton: Representación visual del cartón, sin lógica de negocio.

## Flujo de Marcado (Arquitectura)

VentanaPrincipal -> Jugador -> Carton
VentanaPrincipal -> Maquina -> Carton

La ventana nunca accede directamente al Cartón. Siempre a través del Participante.

# 4. Bucle de Juego (Game Loop)

## Pantalla de Inicio

- Título del juego
- Botón "Nueva Partida"
- Botón "Salir"

## Pantalla de Partida

Elementos:
- Número actual extraído
- Historial de números
- Cartón del jugador interactivo
- Cartón de la máquina (visualización básica)
- Mensajes del sistema

## Flujo

Inicio -> Generación cartones -> Extracción automática -> Notificación a participantes -> Marcado automático (máquina) -> Marcado manual (jugador) -> Comprobaciones -> Victoria -> Reinicio.

# 5. Contenido

## Elementos MVP

- Sistema de generación aleatoria
- Temporizador
- Validaciones
- Mensajes de error
- Comprobación de línea y bingo
- Arquitectura con separación de responsabilidades

# [OMSI] Mejoras Futuras

- Opción de elegir 1, 2 o 3 cartones.
- Dificultad (velocidad del temporizador).
- Ranking de partidas.
- Guardado de estadísticas.
- Sonidos de bombo.
- Modo multijugador local.

# 6. Interfaz (UI/HUD)
   
## Pantalla de Inicio

- Fondo simple.
- Título centrado.
- Botones principales.

## Pantalla de Juego

### Elementos visibles:

- Número actual grande en pantalla.
- Historial lateral.
- Cartón del jugador interactivo.
- Cartón de la máquina (visualización básica).
- Mensajes de validación.
- Indicador de línea.
- Indicador de bingo.

## Interacciones

### Al hacer clic en un número del cartón:

- La ventana solicita al objeto Jugador que marque el número
- El Jugador verifica si el número ha salido y pertenece a su cartón
- Si es válido, el Jugador marca su Cartón interno
- El Cartón notifica el cambio visual a través de la ventana

### Validaciones del sistema:

- Que el número haya sido extraído previamente
- Que el número pertenezca al cartón del jugador
- Que el número no esté ya marcado
- Si no cumple condiciones -> Mensaje de error
