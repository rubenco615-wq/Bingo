package com.daw1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Representa un cartón de bingo de 3x9.
 * Se encarga de su generación siguiendo las reglas estándar y de validar premios.
 */
public class Carton {
    /** Número de filas del cartón (estándar: 3) */
    public static final int FILAS = 3;
    /** Número de columnas del cartón (estándar: 9) */
    public static final int COLUMNAS = 9;
    /** Cantidad de números por cada fila (estándar: 5) */
    private static final int NUMEROS_POR_FILA = 5;

    /** Matriz con los números del cartón (0 indica casilla vacía) */
    private final int[][] numeros;
    /** Matriz con el estado de marcado de cada casilla */
    private final boolean[][] marcados;

    /**
     * Crea un nuevo cartón de bingo y lo genera automáticamente.
     */
    public Carton() {
        this.numeros = new int[FILAS][COLUMNAS];
        this.marcados = new boolean[FILAS][COLUMNAS];
        generarCarton();
    }

    /**
     * Obtiene el número en una posición específica.
     * 
     * @param fila Fila (0-2)
     * @param col Columna (0-8)
     * @return El número en la casilla, o 0 si está vacía.
     */
    public int getNumero(int fila, int col) {
        return numeros[fila][col];
    }

    /**
     * Indica si una casilla ya ha sido marcada.
     * 
     * @param fila Fila (0-2)
     * @param col Columna (0-8)
     * @return true si está marcada, false en caso contrario.
     */
    public boolean isMarcado(int fila, int col) {
        return marcados[fila][col];
    }

    /**
     * Genera un cartón de bingo válido siguiendo las reglas estándar.
     */
    private void generarCarton() {
        boolean[][] estructura = determinarEstructura();
        rellenarNumeros(estructura);
        ordenarColumnas();
    }

    /**
     * Selecciona qué casillas tendrán número, asegurando 5 por fila y al menos 1 por columna.
     * 
     * @return Matriz booleana con la estructura de huecos y números.
     */
    private boolean[][] determinarEstructura() {
        boolean[][] tieneNumero;
        boolean generacionOk;

        do {
            tieneNumero = new boolean[FILAS][COLUMNAS];
            int[] conteoPorColumna = new int[COLUMNAS];
            generacionOk = true;

            for (int f = 0; f < FILAS; f++) {
                List<Integer> columnasDisponibles = new ArrayList<>();
                for (int c = 0; c < COLUMNAS; c++) {
                    // Máximo 2 números por columna para una mejor distribución
                    if (conteoPorColumna[c] < 2) {
                        columnasDisponibles.add(c);
                    }
                }

                if (columnasDisponibles.size() < NUMEROS_POR_FILA) {
                    generacionOk = false;
                    break;
                }

                Collections.shuffle(columnasDisponibles);
                for (int i = 0; i < NUMEROS_POR_FILA; i++) {
                    int col = columnasDisponibles.get(i);
                    tieneNumero[f][col] = true;
                    conteoPorColumna[col]++;
                }
            }

            // Validar que no haya columnas vacías
            if (generacionOk) {
                for (int conteo : conteoPorColumna) {
                    if (conteo == 0) {
                        generacionOk = false;
                        break;
                    }
                }
            }
        } while (!generacionOk);

        return tieneNumero;
    }

    /**
     * Asigna números aleatorios a las casillas seleccionadas según el rango de cada columna.
     * 
     * @param estructura La estructura de huecos generada previamente.
     */
    private void rellenarNumeros(boolean[][] estructura) {
        Random random = new Random();

        for (int c = 0; c < COLUMNAS; c++) {
            int min = (c == 0) ? 1 : c * 10;
            int max = (c == 8) ? 90 : (c * 10) + 9;

            List<Integer> usadosEnColumna = new ArrayList<>();
            for (int f = 0; f < FILAS; f++) {
                if (estructura[f][c]) {
                    int num;
                    do {
                        num = random.nextInt(max - min + 1) + min;
                    } while (usadosEnColumna.contains(num));

                    numeros[f][c] = num;
                    usadosEnColumna.add(num);
                }
            }
        }
    }

    /**
     * Ordena de menor a mayor los números presentes en cada columna del cartón.
     */
    private void ordenarColumnas() {
        for (int c = 0; c < COLUMNAS; c++) {
            List<Integer> valores = new ArrayList<>();
            List<Integer> indicesFilas = new ArrayList<>();

            for (int f = 0; f < FILAS; f++) {
                if (numeros[f][c] != 0) {
                    valores.add(numeros[f][c]);
                    indicesFilas.add(f);
                }
            }

            Collections.sort(valores);
            for (int i = 0; i < valores.size(); i++) {
                numeros[indicesFilas.get(i)][c] = valores.get(i);
            }
        }
    }

    /**
     * Comprueba si el cartón contiene un número específico.
     * 
     * @param numero El número a buscar.
     * @return true si el número está en el cartón, false en caso contrario.
     */
    public boolean contieneNumero(int numero) {
        for (int[] fila : numeros) {
            for (int n : fila) {
                if (n == numero)
                    return true;
            }
        }
        return false;
    }

    /**
     * Marca un número en el cartón si este está presente.
     * 
     * @param numero El número a marcar.
     */
    public void marcarNumero(int numero) {
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                if (numeros[f][c] == numero) {
                    marcados[f][c] = true;
                    return; // Un número solo aparece una vez en el cartón
                }
            }
        }
    }

    /**
     * Comprueba si el jugador ha completado alguna línea horizontal.
     * 
     * @return true si hay al menos una línea completa, false en caso contrario.
     */
    public boolean comprobarLinea() {
        for (int f = 0; f < FILAS; f++) {
            if (isFilaCompleta(f))
                return true;
        }
        return false;
    }

    /**
     * Comprueba si una fila específica está completamente marcada.
     * 
     * @param fila El índice de la fila a comprobar.
     * @return true si la fila está completa, false en caso contrario.
     */
    private boolean isFilaCompleta(int fila) {
        for (int c = 0; c < COLUMNAS; c++) {
            // Si hay un número y no está marcado, la fila no está completa
            if (numeros[fila][c] != 0 && !marcados[fila][c]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Comprueba si el jugador ha completado todo el cartón (Bingo).
     * 
     * @return true si todas las casillas numeradas están marcadas, false en caso contrario.
     */
    public boolean comprobarBingo() {
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                if (numeros[f][c] != 0 && !marcados[f][c]) {
                    return false;
                }
            }
        }
        return true;
    }
}
