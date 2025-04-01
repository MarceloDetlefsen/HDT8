package com.hdt8;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas
 * Creación: 31/03/2025
 * última modificación: 31/03/2025
 * File Name: PriorityQueueFactory.java
 * Descripción: Aplicación del patrón de Diseño Factory para elegir la implementación de una PriorityQueue deseada
 */

public class PriorityQueueFactory<E> {
    /**
     * @param <E> El tipo de valor a almacenar en la priority queue, debe ser comparable
     * @param priority_queue el tipo de implementación que se quiere utilizar
     * @return la implementación escogida en base al parametro priority_queue
     */
    public static <E extends Comparable<E>> PriorityQueue<E> getPriorityQueue(String priority_queue) {
        if (priority_queue.equalsIgnoreCase("Vector Heap")){
            return new VectorHeap<E>();
        }
        else {
            return new PriorityQueuePropio<E>();
        }
    }
}