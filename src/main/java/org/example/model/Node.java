package org.example.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Node {
    /**
     * Name of the city
     */
    private String id;

    /**
     * Routes to other cities with distance
     */
    private Map<String,Integer> edges;

    public Node(String id) {
        this.setId(id);
        this.edges = new HashMap<>();
    }

    public void addEdge(String id, Integer value){
        edges.put(id, value);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean hasRoute(String to) {
        return edges.containsKey(to);
    }

    public Integer getDistance(String to){
        return  edges.get(to);
    }

    public Set<String> getCities() {
        return edges.keySet();
    }

    @Override
    public String toString() {
        return edges.toString();
    }
}
