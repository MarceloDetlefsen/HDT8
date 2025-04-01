package com.hdt8;
import java.util.PriorityQueue;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 31/03/2025
 * última modificación: 31/03/2025
 * File Name: PriorityQueuePropio.java
 * Descripción: Wrapper de la clase PriorityQueue de Java para adaptarse a la interface PriorityQueue como es deseado en la HT8
 */

public class PriorityQueuePropio<E extends Comparable<E>> implements com.hdt8.PriorityQueue<E>{
    private final PriorityQueue<E> cola;

    /**
     * Constructor correspondiente
     */
    public PriorityQueuePropio(){
        this.cola = new PriorityQueue<>();
    }

    //Remove alterado para su uso en la simulación.
    @Override
    public E remove(){
        return cola.poll();
    }

    //Métodos necesarios para cumplir con el contrato de la interface
    @Override
    public E getFirst(){
        return cola.peek();
    }

    @Override
    public void add(E value) {
        cola.add(value);
    }

    @Override
    public boolean isEmpty() {
        return cola.isEmpty();
    }

    @Override
    public int size() {
        return cola.size();
    }

    @Override
    public void clear() {
        cola.clear();
    }
}
