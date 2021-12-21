package Advent2021.Day20;

import Advent2021.Day0.Advent;
import Advent2021.Day0.Input;

import java.util.List;

public class Day20 {
    public static void part1() {
        List<String> input = Input.parseDocString("resources/2021/day20");
        String key = input.get(0);
        int size = input.size()-2;
        boolean[][] map = new boolean[size+201][size+201];
        for (int i = map.length / 2 - size / 2 - size % 2 + 2, si = 2; i < map.length / 2 + size / 2 + 2; i++, si++)
            for (int j = map.length / 2 - size / 2 - size % 2, sj = 0; j < map.length / 2 + size / 2; j++, sj++)
                map[i][j] = (input.get(si).charAt(sj) == '#');

        for (int step = 0; step < 50; step++) {
            boolean[][] nextMap = new boolean[size+201][size+201];
            for (int i = 1 + step; i < map.length-1-step; i++) {
                for (int j = 1 + step; j < map.length-1-step; j++) {
                    boolean[] bin = new boolean[9];
                    for (int di = -1, bi = 0; di <= 1; di++)
                        for (int dj = -1; dj <= 1; dj++, bi++)
                            bin[bi] = map[i+di][j+dj];
                    nextMap[i][j] = key.charAt(Advent.binary(bin)) == '#';
                }
            }
            map = nextMap;
            if (step == 1) System.out.println(count(map));
        }
        System.out.println(count(map));
    }
    private static int count(boolean[][] map) {
        int count = 0;
        for (int i = 51; i < map.length - 51; i++)
            for (int j = 51; j < map.length - 51; j++)
                if (map[i][j]) count++;
        return count;
    }
}
