package org.example;

import org.example.model.Graph;

public class Main {
    public static void main(String[] args) {
        String graphString = "AB5, BC4, CD8, DC8, DE6, AD5, CE2, EB3, AE7";
        String[] routes = graphString.split(", ");

        Graph graph = new Graph();

        for (String route : routes) {
            String[] values = route.split("");
            graph.addNode(
                    values[0],
                    values[1],
                    Integer.parseInt(values[2])
            );
        }

        System.out.println(graph);
        System.out.println("Output #1: " + graph.getDistance("ABC"));
        System.out.println("Output #2: " + graph.getDistance("AD"));
        System.out.println("Output #3: " + graph.getDistance("ADC"));
        System.out.println("Output #4: " + graph.getDistance("AEBCD"));
        System.out.println("Output #5: " + graph.getDistance("AED"));
        System.out.println("Output #6: " + graph.countTripsMaxStop("C", "C", 3));
        System.out.println("Output #7: " + graph.countTripsMaxStop("A", "C", 4));
        System.out.println("Output #8: " + graph.getShortestRoute("A", "C"));
        System.out.println("Output #9: " + graph.getShortestRoute("B", "B"));
        System.out.println("Output #10: " + graph.countRoutesMaxDistance("C", "C", 30));

    }
}