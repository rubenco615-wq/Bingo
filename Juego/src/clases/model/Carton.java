package clases.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Carton {

    private int[][] numeros; // Guarda el número en sí
    private boolean[][] marcados; // Guarda True si ese número ya está tachado

    public Carton() {
        numeros = new int[3][9];
        marcados = new boolean[3][9];
        generarCarton();
    }

    public int getNumero(int fila, int col) {
        return numeros[fila][col];
    }

    public boolean isMarcado(int fila, int col) {
        return marcados[fila][col];
    }

    // Rellena las casillas vacías
    private void generarCarton() {
        Random random = new Random();

        // 1ra (1-9), 2da (10-19), 3ra (20-29)...
        int[] minCol = { 1, 10, 20, 30, 40, 50, 60, 70, 80 };
        int[] maxCol = { 9, 19, 29, 39, 49, 59, 69, 79, 90 };
        boolean[][] tieneNumero;
        boolean generacionOk;

        // donde van a ir los números
        do {
            tieneNumero = new boolean[3][9];
            int[] usosColumna = new int[9];
            generacionOk = true;
            for (int fila = 0; fila < 3; fila++) {
                List<Integer> disponibles = new ArrayList<>();
                // llenamos las columnas, no puede haber más de 2 números en una
                for (int col = 0; col < 9; col++) {
                    if (usosColumna[col] < 2)
                        disponibles.add(col);
                }
                if (disponibles.size() < 5) {
                    generacionOk = false;
                    break;
                }
                // Elegimos 5 columnas al azar para esta fila
                Collections.shuffle(disponibles);
                for (int i = 0; i < 5; i++) {
                    int c = disponibles.get(i);
                    tieneNumero[fila][c] = true;
                    usosColumna[c]++;
                }
            }
        } while (!generacionOk);

        List<List<Integer>> usadosPorColumna = new ArrayList<>();
        for (int col = 0; col < 9; col++)
            usadosPorColumna.add(new ArrayList<>());

        // Rellenar los huecos elegidos con números
        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 9; col++) {
                if (tieneNumero[fila][col]) {
                    int numero;
                    // Genera un número hasta que tengamos uno que no haya salido ya en la columna
                    do {
                        numero = random.nextInt(maxCol[col] - minCol[col] + 1) + minCol[col];
                    } while (usadosPorColumna.get(col).contains(numero));
                    numeros[fila][col] = numero;
                    usadosPorColumna.get(col).add(numero);
                }
            }
        }

        // Ordenar los números de cada columna de menor a mayor
        for (int col = 0; col < 9; col++) {
            List<Integer> valores = new ArrayList<>();
            List<Integer> filas = new ArrayList<>();
            for (int fila = 0; fila < 3; fila++) {
                if (numeros[fila][col] != 0) {
                    valores.add(numeros[fila][col]);
                    filas.add(fila);
                }
            }
            Collections.sort(valores);
            for (int i = 0; i < valores.size(); i++)
                numeros[filas.get(i)][col] = valores.get(i);
        }
    }

    // Escanea todo el cartón para ver si el número que pasas está dentro
    public boolean contieneNumero(int numero) {
        for (int[] filaObj : numeros) {
            for (int n : filaObj) {
                if (n == numero)
                    return true;
            }
        }
        return false;
    }

    // Busca el número y, si lo encuentra, lo tacha
    public void marcarNumero(int numero) {
        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 9; col++) {
                if (numeros[fila][col] == numero)
                    marcados[fila][col] = true;
            }
        }
    }

    // Recorre fila a fila. Si encuentra una fila que no está vacía
    // y TODOS sus números están tachados, devuelve True
    public boolean comprobarLinea() {
        for (int fila = 0; fila < 3; fila++) {
            boolean completa = true, vacia = true;
            for (int col = 0; col < 9; col++) {
                if (numeros[fila][col] != 0) {
                    vacia = false;
                    if (!marcados[fila][col]) {
                        completa = false;
                        break;
                    }
                }
            }
            if (!vacia && completa)
                return true;
        }
        return false;
    }

    // Recorre todo el cartón. Si encuentra un número sin marcar, devuelve False.
    // Si pasa todo el test, devuelve True.
    public boolean comprobarBingo() {
        for (int fila = 0; fila < 3; fila++) {
            for (int col = 0; col < 9; col++) {
                if (numeros[fila][col] != 0 && !marcados[fila][col])
                    return false;
            }
        }
        return true;
    }
}