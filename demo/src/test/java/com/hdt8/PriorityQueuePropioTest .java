package com.hdt8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class PriorityQueuePropioTest {

    @Test
    void testAddAndRemove() {
        PriorityQueuePropio<Integer> pq = new PriorityQueuePropio<>();
        
        pq.add(5);
        pq.add(1);
        pq.add(3);
        
        assertEquals(1, pq.remove());
        assertEquals(3, pq.remove());
        assertEquals(5, pq.remove());
    }

    @Test
    void testGetFirst() {
        PriorityQueuePropio<String> pq = new PriorityQueuePropio<>();
        assertNull(pq.getFirst());
        
        pq.add("Banana");
        pq.add("Apple");
        
        assertEquals("Apple", pq.getFirst());
    }

    @Test
    void testIsEmpty() {
        PriorityQueuePropio<Double> pq = new PriorityQueuePropio<>();
        assertTrue(pq.isEmpty());
        
        pq.add(3.14);
        assertFalse(pq.isEmpty());
        
        pq.remove();
        assertTrue(pq.isEmpty());
    }

    @Test
    void testSize() {
        PriorityQueuePropio<Character> pq = new PriorityQueuePropio<>();
        assertEquals(0, pq.size());
        
        pq.add('X');
        pq.add('Y');
        assertEquals(2, pq.size());
        
        pq.remove();
        assertEquals(1, pq.size());
    }

    @Test
    void testClear() {
        PriorityQueuePropio<Paciente> pq = new PriorityQueuePropio<>();
        pq.add(new Paciente("A", "S1", "C"));
        pq.add(new Paciente("B", "S2", "A"));
        
        pq.clear();
        assertTrue(pq.isEmpty());
        assertEquals(0, pq.size());
    }
}