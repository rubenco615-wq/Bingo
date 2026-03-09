package clases.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Carton {
    private static final int FILAS = 3;
    private static final int COLUMNAS = 9;
    private static final int NUMEROS_POR_FILA = 5;

    private final int[][] numeros; // El número en la casilla
    private final boolean[][] marcados; // Si el número ha sido tachado

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

    // genera el carton
    private void generarCarton() {
        boolean[][] estructura = determinarEstructura();
        rellenarNumeros(estructura);
        ordenarColumnas();
    }

    // Determina qué casillas tendrán número y cuáles no.
    private boolean[][] determinarEstructura() {
        boolean[][] tieneNumero;
        boolean generacionOk;

        do {
            tieneNumero = new boolean[FILAS][COLUMNAS];
            int[] usosColumna = new int[COLUMNAS];
            generacionOk = true;

            for (int f = 0; f < FILAS; f++) {
                List<Integer> disponibles = new ArrayList<>();
                for (int c = 0; c < COLUMNAS; c++) {
                    // No más de 2 números por columna
                    if (usosColumna[c] < 2) {
                        disponibles.add(c);
                    }
                }

                if (disponibles.size() < NUMEROS_POR_FILA) {
                    generacionOk = false;
                    break;
                }

                Collections.shuffle(disponibles);
                for (int i = 0; i < NUMEROS_POR_FILA; i++) {
                    int col = disponibles.get(i);
                    tieneNumero[f][col] = true;
                    usosColumna[col]++;
                }
            }

            // Validar que cada columna tenga al menos un número
            if (generacionOk) {
                for (int c = 0; c < COLUMNAS; c++) {
                    if (usosColumna[c] == 0) {
                        generacionOk = false;
                        break;
                    }
                }
            }

        } while (!generacionOk);

        return tieneNumero;
    }

    // Rellena las casillas seleccionadas con números aleatorios válidos.
    private void rellenarNumeros(boolean[][] estructura) {
        Random random = new Random();
        int[] minCol = { 1, 10, 20, 30, 40, 50, 60, 70, 80 };
        int[] maxCol = { 9, 19, 29, 39, 49, 59, 69, 79, 90 };

        List<List<Integer>> usadosCol = new ArrayList<>();
        for (int i = 0; i < COLUMNAS; i++) {
            usadosCol.add(new ArrayList<>());
        }

        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                if (estructura[f][c]) {
                    int num;
                    do {
                        num = random.nextInt(maxCol[c] - minCol[c] + 1) + minCol[c];
                    } while (usadosCol.get(c).contains(num));

                    numeros[f][c] = num;
                    usadosCol.get(c).add(num);
                }
            }
        }
    }

    // Ordena los números de cada columna de menor a mayor.
    private void ordenarColumnas() {
        for (int c = 0; c < COLUMNAS; c++) {
            List<Integer> valores = new ArrayList<>();
            List<Integer> filasConNumero = new ArrayList<>();

            for (int f = 0; f < FILAS; f++) {
                if (numeros[f][c] != 0) {
                    valores.add(numeros[f][c]);
                    filasConNumero.add(f);
                }
            }
            // Ordena los números de menor a mayor
            Collections.sort(valores);
            for (int i = 0; i < valores.size(); i++) {
                numeros[filasConNumero.get(i)][c] = valores.get(i);
            }
        }
    }

    // Comprueba si el cartón contiene un número específico.
    public boolean contieneNumero(int numero) {
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                if (numeros[f][c] == numero) {
                    return true;
                }
            }
        }
        return false;
    }

    // Marca el número indicado si existe en el cartón.
    public void marcarNumero(int numero) {
        for (int f = 0; f < FILAS; f++) {
            for (int c = 0; c < COLUMNAS; c++) {
                if (numeros[f][c] == numero) {
                    marcados[f][c] = true;
                }
            }
        }
    }

    // Comprueba si hay alguna línea completa en el cartón.
    public boolean comprobarLinea() {
        for (int f = 0; f < FILAS; f++) {
            if (isFilaCompleta(f)) {
                return true;
            }
        }
        return false;
    }

    private boolean isFilaCompleta(int fila) {
        boolean tieneNumeros = false;
        for (int c = 0; c < COLUMNAS; c++) {
            if (numeros[fila][c] != 0) {
                tieneNumeros = true;
                if (!marcados[fila][c]) {
                    return false;
                }
            }
        }
        return tieneNumeros;
    }

    // comprobar si tiene bingo
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
