# Diagrama de Casos de Uso – Juego Bingo

Este diagrama representa las interacciones entre los usuarios (Jugador y Máquina) y el sistema, reflejando las funcionalidades actuales como el ajuste de velocidad, el modo automático y los efectos de sonido.

```mermaid
graph LR
    subgraph Actores
        J((Jugador))
        M((Máquina))
    end

    subgraph "Sistema Bingo"
        UC1[Iniciar Partida]
        UC2[Introducir Nombre]
        UC3[Generar Cartones y Bombo]
        UC4[Ajustar Velocidad / Modo AUTO]
        UC5[Extraer Número]
        UC6[Reproducir Sonido/Efectos]
        UC7[Marcar Número Manualmente]
        UC8[Marcar Número Automáticamente]
        UC9[Comprobar Línea/Bingo]
        UC10[Mostrar Pantalla Victoria]
        UC11[Abrir Ajustes]
    end

    J --> UC1
    UC1 ..> UC2 : << include >>
    UC2 ..> UC3 : << include >>
    
    J --> UC4
    J --> UC7
    J --> UC11
    
    M --> UC8
    
    UC5 ..> UC6 : << include >>
    UC5 ..> UC9 : << include >>
    
    UC9 ..> UC10 : << extend >>
```

# Explicación del Diagrama

## Actores

### **Jugador (Humano)**
Es el usuario principal. Sus responsabilidades incluyen:
- **Iniciar la partida** (introduciendo su nombre).
- **Controlar la partida**: Activar/desactivar el modo **AUTO** y ajustar la **velocidad** de extracción.
- **Participar**: Marcar manualmente los números en su cartón.
- **Configurar**: Acceder al menú de **Ajustes**.

### **Máquina (IA)**
Representa al oponente. Su única acción es **marcar automáticamente** sus números tras un breve retardo, simulando el comportamiento de un jugador.

---

## Casos de Uso del Sistema

### **1. Gestión de la Partida**
- **Iniciar Partida**: Incluye pedir el nombre del jugador y generar tanto los cartones (únicos y aleatorios) como el bombo.
- **Finalizar Partida**: Se dispara automáticamente cuando alguien consigue Bingo, mostrando la pantalla de victoria.

### **2. Control y Ajustes**
- **Ajustar Velocidad / Modo AUTO**: El jugador puede decidir si la extracción es manual o automática, y a qué ritmo salen las bolas.
- **Abrir Ajustes**: Permite acceder a opciones adicionales de configuración de la aplicación.

### **3. Lógica del Juego (Automática)**
- **Extraer Número**: El sistema saca una bola del bombo (sin repetición).
- **Reproducir Sonido**: Cada extracción y cada premio activa efectos de sonido para mejorar la inmersión.
- **Comprobar Línea/Bingo**: Tras cada marcado, el sistema valida si se ha cumplido alguna condición de premio.

### **4. Marcado de Números**
- **Marcado Manual (Jugador)**: Requiere que el usuario haga clic. El sistema valida que el número sea correcto (haya salido en el bombo).
- **Marcado Automático (Máquina)**: El sistema detecta el número y lo marca en el cartón de la IA tras unos segundos.

---

> **Nota**: Los casos de uso ayudan a definir **qué** hace el sistema sin entrar en detalles de **cómo** lo hace. Es la base para entender los requerimientos antes de empezar a programar.
