package Advent2021.Day23;

import Advent2021.Day0.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day23 {
    static List<String> input = Input.parseDocString("resources/2021/day23");
    static Map<String, Integer> cache = new HashMap<>();
    static int MAXFUEL = 20000;
    public static void part1() {
        List<List<Character>> mapL = new ArrayList<>(input.stream().map(x->x.chars().mapToObj(y->(char)y).toList()).toList());

        char[][] map1 = new char[5][13];
        for (int y = 0; y < mapL.size(); y++ ) {
            for (int x = 0; x < mapL.get(y).size(); x++ ) {
                map1[y][x] = mapL.get(y).get(x);
            }
        }
        System.out.println(game1(map1, 0));

        cache = new HashMap<>();
        MAXFUEL = 100000;
        mapL.add(3, "  #D#C#B#A#".chars().mapToObj(x->(char)x).toList());
        mapL.add(4, "  #D#B#A#C#".chars().mapToObj(x->(char)x).toList());
        char[][] map2 = new char[7][13];
        for (int y = 0; y < mapL.size(); y++ ) {
            for (int x = 0; x < mapL.get(y).size(); x++ ) {
                map2[y][x] = mapL.get(y).get(x);
            }
        }
        System.out.println(game2(map2, 0));
    }

    private static int game1(char[][] map, int currentFuel) {
        List<Integer> possibleCosts = new ArrayList<>();
        int[] holes = {3,5,7,9};
        char[] letters = {'A', 'B', 'C', 'D'};
        int[] validStops = {1,2,4,6,8,10,11};
        Map<Character, Integer> costs = new HashMap<>();
        costs.put('A', 1);
        costs.put('B', 10);
        costs.put('C', 100);
        costs.put('D', 1000);


        if (cache.containsKey(arrString(map))) {
            return cache.get(arrString(map));
        }

        if (currentFuel > MAXFUEL) {
            return Integer.MIN_VALUE;
        }

        boolean win = true;
        for (int i = 0; i < 4; i++) {
            if (!(map[2][holes[i]] == letters[i] && map[3][holes[i]] == letters[i])) win = false;
        }
        if (win) {
            MAXFUEL = Math.min(MAXFUEL, currentFuel);
            return 0;
        }

        //Try moving in.
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[1][validStops[i]] == letters[j]) {
                    if (isClear(map, validStops[i], holes[j])) {
                        if (map[2][holes[j]] == '.') {
                            char[][] next = copy(map);
                            next[1][validStops[i]] = '.';
                            if (map[3][holes[j]] == '.') {
                                next[3][holes[j]] = letters[j];
                                int fuel = (Math.abs(holes[j] - validStops[i]) + 2) * costs.get(letters[j]);
                                fuel += game1(next, currentFuel + fuel);
                                if (fuel > 0) possibleCosts.add(fuel);
                            } else if (map[3][holes[j]] == letters[j]) {
                                next[2][holes[j]] = letters[j];
                                int fuel = (Math.abs(holes[j] - validStops[i]) + 1) * costs.get(letters[j]);
                                fuel += game1(next, currentFuel + fuel);
                                if (fuel > 0) possibleCosts.add(fuel);
                            }
                        }
                    }
                }
            }
        }


        //Try moving out.
        for (int i = 0; i < 4; i++) {
            if (map[2][holes[i]] != '.') {
                if (map[2][holes[i]] != letters[i] || map[3][holes[i]] != letters[i]) {
                    for (int j = 0; j < 7; j++) {
                        if (isClear(map, holes[i], validStops[j])) {
                            char[][] next = copy(map);
                            next[2][holes[i]] = '.';
                            next[1][validStops[j]] = map[2][holes[i]];
                            int fuel = (1 + Math.abs(validStops[j] - holes[i]))*costs.get(map[2][holes[i]]);
                            fuel += game1(next, currentFuel+fuel);
                            if (fuel > 0) possibleCosts.add(fuel);
                        }
                    }
                }
            } else if (map[3][holes[i]] != '.') {
                if (map[3][holes[i]] != letters[i]) {
                    for (int j = 0; j < 7; j++) {
                        if (isClear(map, holes[i], validStops[j])) {
                            char[][] next = copy(map);
                            next[3][holes[i]] = '.';
                            next[1][validStops[j]] = map[3][holes[i]];
                            int fuel = (2 + Math.abs(validStops[j] - holes[i]))*costs.get(map[3][holes[i]]);
                            fuel += game1(next, currentFuel+fuel);
                            if (fuel > 0) possibleCosts.add(fuel);
                        }
                    }
                }
            }
        }

        if (possibleCosts.isEmpty()) cache.put(arrString(map),Integer.MIN_VALUE);
        else cache.put(arrString(map),possibleCosts.stream().min(Integer::compareTo).get());
        return cache.get(arrString(map));
    }

    private static int game2(char[][] map, int currentFuel) {
        List<Integer> possibleCosts = new ArrayList<>();
        int[] holes = {3,5,7,9};
        char[] letters = {'A', 'B', 'C', 'D'};
        int[] validStops = {1,2,4,6,8,10,11};
        Map<Character, Integer> costs = new HashMap<>();
        costs.put('A', 1);
        costs.put('B', 10);
        costs.put('C', 100);
        costs.put('D', 1000);


        if (cache.containsKey(arrString(map))) {
            return cache.get(arrString(map));
        }

        if (currentFuel > MAXFUEL) {
            return Integer.MIN_VALUE;
        }

        boolean win = true;
        for (int i = 0; i < 4; i++) {
            if (!(map[2][holes[i]] == letters[i] && map[3][holes[i]] == letters[i]
            && map[4][holes[i]] == letters[i] && map[5][holes[i]] == letters[i])) win = false;
        }
        if (win) {
            MAXFUEL = Math.min(MAXFUEL, currentFuel);
            return 0;
        }

        //Try moving in.
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 4; j++) {
                if (map[1][validStops[i]] == letters[j]) {
                    if (isClear(map, validStops[i], holes[j])) {
                        char[][] next = copy(map);
                        next[1][validStops[i]] = '.';
                        if (map[2][holes[j]] == '.') {
                            if (map[3][holes[j]] == '.') {
                                if (map[4][holes[j]] == '.') {
                                    if (map[5][holes[j]] == '.') {
                                        next[5][holes[j]] = letters[j];
                                        int fuel = (Math.abs(holes[j] - validStops[i]) + 4) *
                                            costs.get(letters[j]);
                                        fuel += game2(next, currentFuel + fuel);
                                        if (fuel > 0) possibleCosts.add(fuel);
                                    } else if (map[5][holes[j]] == letters[j]) {
                                        next[4][holes[j]] = letters[j];
                                        int fuel = (Math.abs(holes[j] - validStops[i]) + 3) *
                                            costs.get(letters[j]);
                                        fuel += game2(next, currentFuel + fuel);
                                        if (fuel > 0) possibleCosts.add(fuel);
                                    }
                                } else if (map[4][holes[j]] == letters[j] && map[5][holes[j]] == letters[j]) {
                                    next[3][holes[j]] = letters[j];
                                    int fuel = (Math.abs(holes[j] - validStops[i]) + 2) *
                                        costs.get(letters[j]);
                                    fuel += game2(next, currentFuel + fuel);
                                    if (fuel > 0) possibleCosts.add(fuel);
                                }
                            } else if (map[3][holes[j]] == letters[j] && map[4][holes[j]] == letters[j] && map[5][holes[j]] == letters[j]) {
                                next[2][holes[j]] = letters[j];
                                int fuel = (Math.abs(holes[j] - validStops[i]) + 1) * costs.get(letters[j]);
                                fuel += game2(next, currentFuel + fuel);
                                if (fuel > 0) possibleCosts.add(fuel);
                            }
                        }
                    }
                }
            }
        }


        //Try moving out.
        for (int i = 0; i < 4; i++) {
            if (map[2][holes[i]] != '.') {
                if (map[2][holes[i]] != letters[i] || map[3][holes[i]] != letters[i] || map[4][holes[i]] != letters[i] || map[5][holes[i]] != letters[i]) {
                    for (int j = 0; j < 7; j++) {
                        if (isClear(map, holes[i], validStops[j])) {
                            char[][] next = copy(map);
                            next[2][holes[i]] = '.';
                            next[1][validStops[j]] = map[2][holes[i]];
                            int fuel = (1 + Math.abs(validStops[j] - holes[i]))*costs.get(map[2][holes[i]]);
                            fuel += game2(next, currentFuel+fuel);
                            if (fuel > 0) possibleCosts.add(fuel);
                        }
                    }
                }
            } else if (map[3][holes[i]] != '.') {
                if (map[3][holes[i]] != letters[i] || map[4][holes[i]] != letters[i] || map[5][holes[i]] != letters[i]) {
                    for (int j = 0; j < 7; j++) {
                        if (isClear(map, holes[i], validStops[j])) {
                            char[][] next = copy(map);
                            next[3][holes[i]] = '.';
                            next[1][validStops[j]] = map[3][holes[i]];
                            int fuel = (2 + Math.abs(validStops[j] - holes[i]))*costs.get(map[3][holes[i]]);
                            fuel += game2(next, currentFuel+fuel);
                            if (fuel > 0) possibleCosts.add(fuel);
                        }
                    }
                }
            } else if (map[4][holes[i]] != '.') {
                if (map[4][holes[i]] != letters[i] || map[5][holes[i]] != letters[i]) {
                    for (int j = 0; j < 7; j++) {
                        if (isClear(map, holes[i], validStops[j])) {
                            char[][] next = copy(map);
                            next[4][holes[i]] = '.';
                            next[1][validStops[j]] = map[4][holes[i]];
                            int fuel = (3 + Math.abs(validStops[j] - holes[i]))*costs.get(map[4][holes[i]]);
                            fuel += game2(next, currentFuel+fuel);
                            if (fuel > 0) possibleCosts.add(fuel);
                        }
                    }
                }
            } else if (map[5][holes[i]] != '.') {
                if (map[5][holes[i]] != letters[i]) {
                    for (int j = 0; j < 7; j++) {
                        if (isClear(map, holes[i], validStops[j])) {
                            char[][] next = copy(map);
                            next[5][holes[i]] = '.';
                            next[1][validStops[j]] = map[5][holes[i]];
                            int fuel = (4 + Math.abs(validStops[j] - holes[i]))*costs.get(map[5][holes[i]]);
                            fuel += game2(next, currentFuel+fuel);
                            if (fuel > 0) possibleCosts.add(fuel);
                        }
                    }
                }
            }
        }

        if (possibleCosts.isEmpty())
            cache.put(arrString(map),Integer.MIN_VALUE);
        else
            cache.put(arrString(map),possibleCosts.stream().min(Integer::compareTo).get());
        return cache.get(arrString(map));
    }

    private static boolean isClear(char[][] map, int x1, int x2) {
        boolean clear = true;
        if (x1 < x2) {
            for (int x = x1+1; x <= x2; x++) {
                if (map[1][x] != '.') {
                    clear = false;
                    break;
                }
            }
        } else {
            for (int x = x1-1; x >= x2; x--) {
                if (map[1][x] != '.') {
                    clear = false;
                    break;
                }
            }
        }
        return clear;
    }

    private static char[][] copy(char[][] map) {
        char[][] copy = new char[map.length][map[0].length];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    private static String arrString(char[][] arr) {
        StringBuilder string = new StringBuilder();
        for (char[] row : arr) {
            for (char c : row) {
                string.append(c);
            }
            string.append("\n");
        }
        return string.toString();
    }
}
