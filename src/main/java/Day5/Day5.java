package Day5;

import Day0.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {
    static List<String> input = Input.parseDocString("resources/2021/day5");
    public static void day5() {
        int[][] map1 = new int[1000][1000];
        int[][] map2 = new int[1000][1000];
        List<List<Integer>> vents = new ArrayList<>();

        for (int i = 0; i < input.size(); i++)
            vents.add(Arrays.stream(input.get(i).split(",| -> ")).map(x -> Integer.parseInt(x)).toList());

       for (List<Integer> vent : vents) {
           if (vent.get(0).equals(vent.get(2))) {
               for (int i = Math.min(vent.get(1), vent.get(3)); i <= Math.max(vent.get(1), vent.get(3)); i++) {
                   map1[vent.get(0)][i]++;
                   map2[vent.get(0)][i]++;
               }
           } else if (vent.get(1).equals(vent.get(3))) {
               for (int i = Math.min(vent.get(0), vent.get(2)); i <= Math.max(vent.get(0), vent.get(2)); i++) {
                   map1[i][vent.get(1)]++;
                   map2[i][vent.get(1)]++;
               }
           } else {
               if (vent.get(1).compareTo(vent.get(3)) == vent.get(0).compareTo(vent.get(2))) {
                   for (int i = Math.min(vent.get(0), vent.get(2)), j =
                        Math.min(vent.get(1), vent.get(3));
                        i <= Math.max(vent.get(0), vent.get(2)); j++, i++)
                       map2[i][j]++;
               } else {
                   for (int i = Math.min(vent.get(0), vent.get(2)), j =
                        Math.max(vent.get(1), vent.get(3));
                        i <= Math.max(vent.get(0), vent.get(2)); j--, i++)
                       map2[i][j]++;
               }
           }
       }

       int p1 = 0, p2 = 0;
       for (int i = 0; i < 1000; i++) {
           for (int j = 0; j < 1000; j++) {
               if (map1[i][j] >= 2)
                   p1++;
               if (map2[i][j] >= 2)
                   p2++;
           }
       }

        System.out.println(p1 + "\n" + p2);
    }
}
