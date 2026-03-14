package com.daw1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Representa el bombo físico del bingo.
 * Contiene bolas del 1 al 90 y se encarga de mezclarlas y sacarlas.
 */
public class Bombo {
    /** Lista de números que todavía están dentro del bombo */
    private final List<Integer> numerosDisponibles;

    /** saber si un número ya ha salido */
    private final Set<Integer> numerosExtraidos;

    /**
     * Inicializa el bombo con 90 números y los mezcla para que salgan al azar.
     */
    public Bombo() {
        this.numerosDisponibles = new ArrayList<>();
        this.numerosExtraidos = new HashSet<>();

        // Llenamos el bombo con las bolas del 1 al 90
        for (int i = 1; i <= 90; i++) {
            numerosDisponibles.add(i);
        }
        // Mezclamos las bolas para que salgan al azar
        Collections.shuffle(numerosDisponibles);
    }

    /**
     * Extrae el siguiente número del bombo.
     * 
     * @return El número extraído, o -1 si el bombo está vacío.
     */
    public int sacarNumero() {
        if (numerosDisponibles.isEmpty()) {
            return -1;
        }

        int num = numerosDisponibles.remove(0);
        numerosExtraidos.add(num);
        return num;
    }

    /**
     * Comprueba si un número ya ha salido del bombo.
     * 
     * @param numero El número a comprobar.
     * @return true si el número ya ha sido extraído, false en caso contrario.
     */
    public boolean contieneExtraido(int numero) {
        return numerosExtraidos.contains(numero);
    }

    /**
     * Indica si el bombo ya no tiene más números disponibles.
     * 
     * @return true si el bombo está vacío, false si quedan números.
     */
    public boolean isEmpty() {
        return numerosDisponibles.isEmpty();
    }
}
