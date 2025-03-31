package com.hdt8;

/*
 * Universidad del Valle de Guatemala
 * Algoritmos y Estructuras de Datos
 * Ing. Douglas Barrios
 * @author: Julián Divas, Marcelo Detlefsen
 * Creación: 31/03/2025
 * última modificación: 31/03/2025
 * File Name: PriorityQueue.java
 * Descripción: Interface para implementar colas con prioridad
 */

/**
 * Interface de una Implementación de un PriorityQueue.
 * Basado en:
 * Bailey, D. A. (2007). Java structures: Data structures in Java for the 
 * principled programmer (√7 ed., Software release 33). Williams College.
 */

 
public interface PriorityQueue<E extends Comparable<E>>
{
	public E getFirst();
	// pre: !isEmpty()
	// post: returns the minimum value in priority queue
	
	public E remove();
	// pre: !isEmpty()
	// post: returns and removes minimum value from queue
	
	public void add(E value);
	// pre: value is non-null comparable
	// post: value is added to priority queue
	
	public boolean isEmpty();
	// post: returns true iff no elements are in queue
	
	public int size();
	// post: returns number of elements within queue
	
	public void clear();
	// post: removes all elements from queue
}
