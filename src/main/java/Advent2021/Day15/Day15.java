package Advent2021.Day15;

import Advent2021.Day0.Input;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Day15 {
    static List<String> input = Input.parseDocString("resources/2021/day15");
    static int size = input.size();
    public static void Day15() {
        List<List<Integer>> mapIm = input.stream()
            .map(x -> x.chars().mapToObj(y -> (char) y).map(z -> (int) z - '0').toList()).toList();
        int[][] map = new int[size][size];
        int[][] map2 = new int[size*5][size*5];

        for (int i = 0; i < size; i++)
            for (int I = 0; I < 5; I++)
                for (int j = 0; j < size; j++) {
                    for (int J = 0; J < 5; J++)
                        map2[i + I * size][j + J * size] =
                            (mapIm.get(i).get(j) + J + I - 1) % 9 + 1;
                    map[i][j] = mapIm.get(i).get(j);
                }
        route(map);
        route(map2);
    }

    private static void route(int[][] map) {
        int[][] costs = new int[map.length][map.length];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(map.length*map.length-1);
        costs[map.length-1][map.length-1] = map[map.length-1][map.length-1];
        while (!queue.isEmpty()) {
            int x = queue.peek() % map.length;
            int y = queue.poll() / map.length;
            if (x > 0 && (costs[y][x] + map[y][x-1] < costs[y][x-1] || costs[y][x-1] == 0)) {
                costs[y][x - 1] = costs[y][x] + map[y][x - 1];
                queue.add(y * map.length + x - 1);
            }
            if (x < map.length-1 && (costs[y][x] + map[y][x+1] < costs[y][x+1] || costs[y][x+1] == 0)) {
                costs[y][x + 1] = costs[y][x] + map[y][x + 1];
                queue.add(y * map.length + x + 1);
            }
            if (y > 0 && (costs[y][x] + map[y-1][x] < costs[y-1][x] || costs[y-1][x] == 0)) {
                costs[y-1][x] = costs[y][x] + map[y-1][x];
                queue.add((y-1)*map.length+x);
            }
            if (y < map.length-1 && (costs[y][x] + map[y+1][x] < costs[y+1][x] || costs[y+1][x] == 0)) {
                costs[y+1][x] = costs[y][x] + map[y+1][x];
                queue.add((y+1)*map.length+x);
            }
        }
        System.out.println(costs[0][0] - map[0][0]);
    }
}
