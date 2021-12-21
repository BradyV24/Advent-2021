package Advent2021.Day13;

import Advent2021.Day0.Input;

import java.util.Arrays;
import java.util.List;

public class Day13 {
    public static void day13() {
        List<String> input = Input.parseDocString("resources/2021/day13");
        List<List<Integer>> points = input.subList(0, 949).stream()
            .map(x -> Arrays.stream(x.split(",")).map(y -> Integer.parseInt(y)).toList()).toList();
        List<String> directions =
            input.subList(951, 963).stream().map(x -> x.substring(11)).toList();
        int[][] map = new int[895][1311];
        for (List<Integer> point : points) {
            map[point.get(1)][point.get(0)] = 1;
        }

        boolean part1 = false;
        for (String dir : directions) {
            int[][] newMap;
            int fold = Integer.parseInt(dir.split("=")[1]);
            if (dir.split("=")[0].equals("y")) {
                newMap = new int[fold][map[0].length];
            } else {
                newMap = new int[map.length][fold];
            }
            if (dir.split("=")[0].equals("y")) {
                for (int y = 0; y < fold; y++) {
                    for (int x = 0; x < map[y].length; x++) {
                        newMap[y][x] = map[y][x] + map[map.length - 1 - y][x];
                    }
                }
            } else {
                for (int y = 0; y < map.length; y++) {
                    for (int x = 0; x < fold; x++) {
                        newMap[y][x] = map[y][x] + map[y][map[y].length - 1 - x];
                    }
                }
            }
            map = newMap;

            if (!part1) {
                int count = 0;
                for (int y = 0; y < map.length; y++) {
                    for (int x = 0; x < map[y].length; x++) {
                        if (map[y][x] != 0) {
                            count++;
                        }
                    }
                }
                System.out.println(count);
                part1 = true;
            }
        }
        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (map[y][x] != 0) {
                    System.out.print("#");
                } else {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
