package Advent2021.Day12;

import Advent2021.Day0.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day12 {
    static List<List<String>> dirs = Input.parseDocString("resources/2021/day12").stream().map(x-> Arrays.stream(x.split("-")).toList()).toList();
    static Map<String, List<String>> map = new HashMap<>();
    static int routes = 0, routes2 = 0;

    public static void day12_1() {
        for (List<String> dir : dirs) {
            if (!map.containsKey(dir.get(0))) map.put(dir.get(0), new ArrayList<>());
            if (!map.containsKey(dir.get(1))) map.put(dir.get(1), new ArrayList<>());
            map.get(dir.get(0)).add(dir.get(1));
            map.get(dir.get(1)).add(dir.get(0));
        }
        route("start", "end", new HashSet<>(), false);
        System.out.println(routes + "\n" + (routes + routes2));
    }

    private static void route(String start, String end, Set<String> visited, boolean doubled) {
        Set<String> nextVisited = new HashSet<>(visited);
        if (start.charAt(0) > 'Z') nextVisited.add(start);
        if (start.equals(end)) {
            if (!doubled) routes++;
            else routes2++;
        }
        for (String next : map.get(start)) {
            if (!nextVisited.contains(next)) route(next, end, nextVisited, doubled);
            else if (!doubled && !next.equals("start") && !next.equals("end")) route(next, end, nextVisited, true);
        }
    }
}
