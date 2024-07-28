package org.example.model;

import java.util.*;

public class Graph {
    private final Map<String, Node> nodes;

    public Graph() {
        nodes = new HashMap<>();
    }

    /**
     * Example
     * AE7
     * @param fromId: A
     * @param toId: E
     * @param distance: 7
     */
    public void addNode(String fromId, String toId, int distance) {
        Node fromNode = nodes.get(fromId) == null ? new Node(fromId) : nodes.get(fromId);
        Node toNode = nodes.get(toId) == null ? new Node(toId) : nodes.get(toId);

        fromNode.addEdge(toNode.getId(), distance);

        nodes.put(fromId, fromNode);
        nodes.put(toId, toNode);
    }

    /**
     *  Find distance from route for every city count sum distance
     * @param route:
     * @return : total distance from route
     */
    public String getDistance(String route) {
        String[] cities = route.split("");
        Integer distance = 0;

        for(int i = 0; i < cities.length - 1; i++) {
            String start = cities[i];
            String end = cities[i + 1];

            if(!nodes.get(start).hasRoute(end)){
                return "NO SUCH ROUTE";
            }
            distance += nodes.get(start).getDistance(end);
        }

        return String.valueOf(distance);
    }

    public int countTripsMaxStop(String start, String end, int maxStop){
        return  getTripsMaxStops(start, end, 0 , maxStop);
    }

    private int getTripsMaxStops(String start, String end, int countTrips, int maxStops) {
        Node currentNode = nodes.get(start);

        if(countTrips > maxStops) {
            return 0;
        }

        if(start.equals(currentNode.getId()) && countTrips > 0) {
            return 1;
        }

        for (String current: currentNode.getCities()){
            countTrips += getTripsMaxStops(current, end, countTrips + 1, maxStops);
        }

        return countTrips;
    }

    public int countRoutesMaxDistance(String start, String end, int maxDistance){
        int countRoutes = 0;
        int distance = 0;
        Node startNode = nodes.get(start);

        while (distance < maxDistance) {
            for (String current: startNode.getCities()){
                Node currentNode = nodes.get(current);
                if(currentNode.hasRoute(end)){
                    distance += currentNode.getDistance(end);
                }
                countRoutes++;
            }
        }

        return countRoutes - 1 ;
    }

    public int getShortestRoute(String start, String end) {
        List<String> allRoutes = this.generateRoutesAsStrings(start,end);
        List<Integer> routeDistance = allRoutes.stream()
                .map(route -> Integer.parseInt(getDistance(route)))
                .sorted()
                .toList();
        return routeDistance.get(0);
    }

    private List<String> generateRoutesAsStrings(String start, String end) {

        List<String> allRoutes = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        StringBuilder currentPath = new StringBuilder();

        getRouterPath(start, end, visited, currentPath, allRoutes);

        return allRoutes;
    }

    private void getRouterPath(String current, String end, Set<String> visited, StringBuilder currentPath, List<String> allRoutes) {
        visited.add(current);
        currentPath.append(current);

        if (current.equals(end)) {
            allRoutes.add(currentPath.toString());
        } else {
            Node currentNode = nodes.get(current);
            for (String nextCity : currentNode.getCities()) {
                if (!visited.contains(nextCity)) {
                    getRouterPath(nextCity, end, visited, currentPath, allRoutes);
                }
            }
        }

        visited.remove(current);
        currentPath.setLength(currentPath.length() - 1);
    }



    @Override
    public String toString() {
        return nodes.toString();
    }
}
