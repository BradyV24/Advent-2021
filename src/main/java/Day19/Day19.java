package Day19;

import Day0.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day19 {
    static List<String> input = Input.parseDocString("resources/2021/day19");
    public static void part1() {
        List<List<List<Integer>>> maps = new ArrayList<>();
        for (int i = 0; i < 40; i++) {
            maps.add(new ArrayList<>());
            for (int j = 1 + i*26; j < (i+1)*26; j++) {
                maps.get(i).add(Arrays.stream(input.get(j).split(",")).map(x->Integer.parseInt(x)).toList());
            }
        }
        System.out.println();
    }
    public static void part2() {

    }
}
