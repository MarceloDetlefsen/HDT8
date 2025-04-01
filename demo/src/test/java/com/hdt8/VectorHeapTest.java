package com.hdt8;

import java.util.Vector;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class VectorHeapTest {

    @Test
    void testAddAndRemove() {
        VectorHeap<Integer> heap = new VectorHeap<>();
        
        heap.add(5);
        heap.add(3);
        heap.add(7);
        heap.add(1);
        
        assertEquals(1, heap.remove());
        assertEquals(3, heap.remove());
        assertEquals(5, heap.remove());
        assertEquals(7, heap.remove());
    }

    @Test
    void testIsEmpty() {
        VectorHeap<String> heap = new VectorHeap<>();
        assertTrue(heap.isEmpty());
        
        heap.add("Test");
        assertFalse(heap.isEmpty());
        
        heap.remove();
        assertTrue(heap.isEmpty());
    }

    @Test
    void testGetFirst() {
        VectorHeap<Paciente> heap = new VectorHeap<>();
        Paciente p1 = new Paciente("A", "S1", "B");
        Paciente p2 = new Paciente("B", "S2", "A");
        
        heap.add(p1);
        assertEquals(p1, heap.getFirst());
        
        heap.add(p2);
        assertEquals(p2, heap.getFirst()); // "A" tiene mayor prioridad que "B"
    }

    @Test
    void testSize() {
        VectorHeap<Double> heap = new VectorHeap<>();
        assertEquals(0, heap.size());
        
        heap.add(1.5);
        heap.add(2.3);
        assertEquals(2, heap.size());
        
        heap.remove();
        assertEquals(1, heap.size());
    }

    @Test
    void testClear() {
        VectorHeap<Character> heap = new VectorHeap<>();
        heap.add('a');
        heap.add('b');
        
        heap.clear();
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
    }

    @Test
    void testConstructorWithVector() {
        Vector<Integer> vec = new Vector<>();
        vec.add(5);
        vec.add(1);
        vec.add(3);
        
        VectorHeap<Integer> heap = new VectorHeap<>(vec);
        assertEquals(1, heap.remove());
        assertEquals(3, heap.remove());
        assertEquals(5, heap.remove());
    }
}