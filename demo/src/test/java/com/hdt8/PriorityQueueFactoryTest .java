package com.hdt8;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class PriorityQueueFactoryTest {

    @Test
    void testGetPriorityQueueVectorHeap() {
        PriorityQueue<Integer> pq = PriorityQueueFactory.getPriorityQueue("Vector Heap");
        
        assertNotNull(pq);
        assertTrue(pq instanceof VectorHeap);
        
        pq.add(5);
        pq.add(1);
        assertEquals(1, pq.remove());
    }

    @Test
    void testGetPriorityQueueJCF() {
        PriorityQueue<String> pq = PriorityQueueFactory.getPriorityQueue("PriorityQueue");
        
        assertNotNull(pq);
        assertTrue(pq instanceof PriorityQueuePropio);
        
        pq.add("Zebra");
        pq.add("Apple");
        assertEquals("Apple", pq.remove());
    }

    @Test
    void testGetPriorityQueueDefault() {
        // Prueba que el default sea PriorityQueuePropio
        PriorityQueue<Double> pq = PriorityQueueFactory.getPriorityQueue("Invalid");
        
        assertNotNull(pq);
        assertTrue(pq instanceof PriorityQueuePropio);
    }
}