package org.example.model;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    Graph graph;

    @BeforeEach
    public void setUp(){
        String graphString = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        String[] routes = graphString.split(", ");
        graph = new Graph();

        for (String route : routes) {
            String[] values = route.split("");
            graph.addNode(
                    values[0],
                    values[1],
                    Integer.parseInt(values[2])
            );
        }

    }

    @Test
    public void testLongerValidRoute() {
        assertEquals("23", graph.getDistance("ABCDE"));
    }

    @Test
    public void testRouteWithNoSuchRoute() {
        assertEquals("NO SUCH ROUTE", graph.getDistance("AED"));
    }

    @Test
    public void testCountTripsMaxStop() {
        assertEquals(2, graph.countTripsMaxStop("C","C", 3));
    }

    @Test
    public void testShortestRoute() {
        assertEquals(9, graph.getShortestRoute("A","C"));
    }

    @Test
    public void testCountRoutesMaxDistance() {
        assertEquals(7, graph.countRoutesMaxDistance("C","C",30));
    }


}