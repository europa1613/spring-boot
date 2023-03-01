package com.async.asyncdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class NodeElectionDemo {
    public static void main(String[] args) {
        Map<Integer, List<String>> map = new HashMap<>();
        for (int nodeCount = 1; nodeCount < 9; nodeCount++) {
            List<String> elects = new ArrayList<>();
            for (int hourOfTheDay = 0; hourOfTheDay < 24; hourOfTheDay++) {
                String pad = hourOfTheDay < 10 ? "" : " ";
                elects.add(pad + hourOfTheDay % nodeCount);
            }
            map.put(nodeCount, elects);
        }

        System.out.println("Hour  :::: " + IntStream.range(0, 24).boxed().collect(Collectors.toList()));

        map.forEach((k, v) -> {
            System.out.println("Nodes " + k + "::: " + v);
        });
    }
}
