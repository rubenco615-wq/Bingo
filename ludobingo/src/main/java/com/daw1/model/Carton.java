package com.daw1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Carton {
    // Constantes para las dimensiones del cartón
    public static final int FILAS = 3;
    public static final int COLUMNAS = 9;
    private static final int NUMEROS_POR_FILA = 5;

    private final int[][] numeros; // 0 indica casilla vacía
    private final boolean[][] marcados; // true si el número ha sido extraído

    public Carton() {
        this.numeros = new int[FILAS][COLUMNAS];
        this.marcados = new boolean[FILAS][COLUMNAS];
        generarCarton();
    }

    public int getNumero(int fila, int col) {
        return numeros[fila][col];
    }

    public boolean isMarcado(int fila, int col) {
        return marcados[fila][col];
    }

    // Genera un cartón de bingo válido siguiendo las reglas estándar.
    private void generarCarton() {
        boolean[][] estructura = determinarEstructura();
        rellenarNumeros(estructura);
        ordenarColumnas();
    }

    // Selecciona qué casillas tendrán número, asegurando 5 por fila y al menos 1 por columna.
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

    // Asigna números aleatorios a las casillas seleccionadas según el rango de cada columna.
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

    // Ordena de menor a mayor los números presentes en cada columna.
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

    public boolean contieneNumero(int numero) {
        for (int[] fila : numeros) {
            for (int n : fila) {
                if (n == numero)
                    return true;
            }
        }
        return false;
    }

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

    // Comprueba si el jugador ha completado alguna línea.
    public boolean comprobarLinea() {
        for (int f = 0; f < FILAS; f++) {
            if (isFilaCompleta(f))
                return true;
        }
        return false;
    }

    private boolean isFilaCompleta(int fila) {
        for (int c = 0; c < COLUMNAS; c++) {
            // Si hay un número y no está marcado, la fila no está completa
            if (numeros[fila][c] != 0 && !marcados[fila][c]) {
                return false;
            }
        }
        return true;
    }

    // Comprueba si el jugador ha tachado todos los números del cartón.
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
