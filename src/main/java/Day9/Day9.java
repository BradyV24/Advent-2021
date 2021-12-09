package Day9;

import Day0.Input;

import java.util.ArrayList;
import java.util.List;

public class Day9 {
    static List<String> input = Input.parseDocString("resources/2021/day9");

    public static void day9_1() {
        List<List<Integer>> mapIm = input.stream()
            .map(x -> x.chars().mapToObj(y -> (char) y).map(z -> (int) z - '0').toList()).toList();
        List<List<Integer>> map = new ArrayList<>();
        for (List<Integer> row : mapIm)
            map.add(new ArrayList<>(row));
        List<Integer> basins = new ArrayList<>();
        int tot = 0;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if ((i + 1 == map.size() || map.get(i).get(j) < map.get(i + 1).get(j)) &&
                    (i == 0 || map.get(i).get(j) < map.get(i - 1).get(j)) &&
                    (j + 1 == map.get(i).size() || map.get(i).get(j) < map.get(i).get(j + 1)) &&
                    (j == 0 || map.get(i).get(j) < map.get(i).get(j - 1))) {
                    tot += 1 + map.get(i).get(j);
                    map.get(i).set(j, -1);
                    basins.add(basinSize(i, j, map));
                }
            }
        }
        int ans = 1;
        for (int num = 0, max = 0, maxI = -1; num < 3; num++, maxI = -1, max = 0) {
            for (int i = 0; i < basins.size(); i++) {
                if (basins.get(i) > max) {
                    max = basins.get(i);
                    maxI = i;
                }
            }
            ans *= basins.get(maxI);
            basins.remove(maxI);
        }
        System.out.println(tot + "\n" + ans);
    }

    private static int basinSize(int x, int y, List<List<Integer>> map) {
        int size = 1;
        if (x+1 < map.size() && map.get(x+1).get(y) > -1 && map.get(x+1).get(y) != 9) {
            map.get(x+1).set(y, -1);
            size += basinSize(x+1, y, map);
        }
        if (x-1 >= 0 && map.get(x-1).get(y) > -1 && map.get(x-1).get(y) != 9) {
            map.get(x-1).set(y, -1);
            size += basinSize(x-1, y, map);
        }
        if (y+1 < map.get(x).size() && map.get(x).get(y+1) > -1 && map.get(x).get(y+1) != 9) {
            map.get(x).set(y+1, -1);
            size += basinSize(x, y+1, map);
        }
        if (y-1 >= 0 && map.get(x).get(y-1) > -1 && map.get(x).get(y-1) != 9) {
            map.get(x).set(y-1, -1);
            size += basinSize(x, y-1, map);
        }
        return size;
    }
}
