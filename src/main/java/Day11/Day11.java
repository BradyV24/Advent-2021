package Day11;

import Day0.Input;

import java.util.ArrayList;
import java.util.List;

public class Day11 {
    static List<String> input = Input.parseDocString("resources/2021/day11");
    public static void day11() {
        List<List<Integer>> mapIm = input.stream()
            .map(x -> x.chars().mapToObj(y -> (char) y).map(z -> (int) z - '0').toList()).toList();
        int[][] map = new int[mapIm.size()][mapIm.get(0).size()];
        for (int i = 0; i < mapIm.size(); i++)
            for (int j = 0; j < mapIm.get(i).size(); j++)
                map[i][j] = mapIm.get(i).get(j);

        int flashes = 0;
        for (int day = 0; true; day++) {
            for (int[] row : map)
                for (int j = 0; j < row.length; j++)
                    row[j]++;
            int[][] next = new int[mapIm.size()][mapIm.get(0).size()];
            for (int i = 0; i < mapIm.size(); i++)
                for (int j = 0; j < mapIm.get(i).size(); j++)
                    next[i][j] = map[i][j];

            boolean changed = true;
            while (changed) {
                changed = false;
                for (int y = 0; y < map.length; y++) {
                    for (int x = 0; x < map[y].length; x++) {
                        if (map[y][x] > 9) {
                            flashes++;
                            if (y > 0) {
                                if (x > 0 && next[y - 1][x - 1] != 0)
                                    next[y - 1][x - 1]++;
                                if (next[y - 1][x] != 0)
                                    next[y - 1][x]++;
                                if (x < map[y].length-1 && next[y - 1][x + 1] != 0)
                                        next[y - 1][x + 1]++;
                            }
                            if (x > 0 && next[y][x - 1] != 0)
                                    next[y][x - 1]++;
                            next[y][x] = 0;
                            if (x < map[y].length-1 && next[y][x + 1] != 0)
                                    next[y][x + 1]++;
                            if (y < map.length - 1) {
                                if (x > 0 && next[y + 1][x - 1] != 0)
                                        next[y + 1][x - 1]++;
                                if (next[y + 1][x] != 0)
                                        next[y + 1][x]++;
                                if (x < map[y].length-1 && next[y + 1][x + 1] != 0)
                                        next[y + 1][x + 1]++;
                            }
                        }
                    }
                }
                for (int i = 0; i < mapIm.size(); i++)
                    for (int j = 0; j < mapIm.get(i).size(); j++) {
                        if (map[i][j] != next[i][j]) changed = true;
                        map[i][j] = next[i][j];
                    }
            }

            if (day == 99) System.out.println(flashes);

            boolean sync = true;
            for (int i = 0; i < mapIm.size(); i++)
                for (int j = 0; j < mapIm.get(i).size(); j++)
                    if (map[i][j] != 0) sync = false;
            if (sync) {
                System.out.println(day+1);
                break;
            }
        }
    }
}
