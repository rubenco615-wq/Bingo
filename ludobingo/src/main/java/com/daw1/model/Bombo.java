package com.daw1.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Representa el bombo físico del bingo.
//Contiene bolas del 1 al 90 y se encarga de mezclarlas y entregarlas.

public class Bombo {
    // Lista de números que todavía están dentro del bombo
    private final List<Integer> numerosDisponibles;

    // Conjunto rápido para saber al instante si un número ya ha salido
    private final Set<Integer> numerosExtraidos;

    // Inicializa el bombo con 90 números y los mezcla.

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

    // sacar los numeros del bombo
    public int sacarNumero() {
        if (numerosDisponibles.isEmpty()) {
            return -1;
        }

        int num = numerosDisponibles.remove(0);
        numerosExtraidos.add(num);
        return num;
    }

    public boolean contieneExtraido(int numero) {
        return numerosExtraidos.contains(numero);
    }

    public boolean isEmpty() {
        return numerosDisponibles.isEmpty();
    }
}
