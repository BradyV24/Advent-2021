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
        for (List<Integer> row : mapIm) {
            map.add(new ArrayList<>(row));
        }

        List<Integer> basins = new ArrayList<>();
        int tot = 0;
        for (int i = 0; i < map.size(); i++) {
            for (int j = 0; j < map.get(i).size(); j++) {
                if (i == 0) {
                    if (map.get(i).get(j) < map.get(i+1).get(j) &&
                        (j+1 == map.get(i).size() || map.get(i).get(j) < map.get(i).get(j+1)) &&
                        (j == 0 || map.get(i).get(j) < map.get(i).get(j-1))) {
                        tot += 1 + map.get(i).get(j);
                        basins.add(basinSize(i,j,map));
                    }
                } else if (i == map.size()-1) {
                    if (map.get(i).get(j) < map.get(i-1).get(j) &&
                        (j+1 == map.get(i).size() || map.get(i).get(j) < map.get(i).get(j+1)) &&
                        (j == 0 || map.get(i).get(j) < map.get(i).get(j-1))) {
                        tot += 1 + map.get(i).get(j);
                        basins.add(basinSize(i,j,map));
                    }
                } else if (j == 0) {
                    if ((map.get(i).get(j) < map.get(i+1).get(j) || i+1 == map.size()) &&
                        (map.get(i).get(j) < map.get(i-1).get(j) || i == 0) &&
                        map.get(i).get(j) < map.get(i).get(j+1)) {
                        tot += 1 + map.get(i).get(j);
                        basins.add(basinSize(i,j,map));
                    }
                } else if (j == map.get(i).size()-1) {
                    if ((map.get(i).get(j) < map.get(i+1).get(j) || i+1 == map.size()) &&
                        (map.get(i).get(j) < map.get(i-1).get(j) || i == 0) &&
                        map.get(i).get(j) < map.get(i).get(j-1)) {
                        tot += 1 + map.get(i).get(j);
                        basins.add(basinSize(i,j,map));
                    }
                } else {
                    if (map.get(i).get(j) < map.get(i + 1).get(j) &&
                        map.get(i).get(j) < map.get(i - 1).get(j) &&
                        map.get(i).get(j) < map.get(i).get(j + 1) &&
                        map.get(i).get(j) < map.get(i).get(j - 1)) {
                        tot += 1 + map.get(i).get(j);
                        basins.add(basinSize(i,j,map));
                    }
                }
            }
        }
        int ans = 1;
        for (int num = 0; num < 3; num++) {
            int maxI = -1;
            int max = 0;
            for (int i = 0; i < basins.size(); i++) {
                if (basins.get(i) > max) {
                    max = basins.get(i);
                    maxI = i;
                }
            }
            ans *= basins.get(maxI);
            basins.remove(maxI);
        }

        System.out.println(tot);
        System.out.println(ans);
    }

    private static int basinSize(int x, int y, List<List<Integer>> map) {
        List<List<Integer>> safeMap = new ArrayList<>();
        for (List<Integer> row : map) {
            safeMap.add(new ArrayList<>(row));
        }
        safeMap.get(x).set(y, -2);
     return recurse(x,y,safeMap);
    }

    private static int recurse(int x, int y, List<List<Integer>> map) {
        int size = 1;
        if (x+1 < map.size() && map.get(x+1).get(y) > -1 && map.get(x+1).get(y) != 9) {
            map.get(x+1).set(y, -2);
            size += recurse(x+1, y, map);
        }
        if (x-1 >= 0 && map.get(x-1).get(y) > -1 && map.get(x-1).get(y) != 9) {
            map.get(x-1).set(y, -2);
            size += recurse(x-1, y, map);
        }
        if (y+1 < map.get(x).size() && map.get(x).get(y+1) > -1 && map.get(x).get(y+1) != 9) {
            map.get(x).set(y+1, -2);
            size += recurse(x, y+1, map);
        }
        if (y-1 >= 0 && map.get(x).get(y-1) > -1 && map.get(x).get(y-1) != 9) {
            map.get(x).set(y-1, -2);
            size += recurse(x, y-1, map);
        }
        return size;
    }
}
