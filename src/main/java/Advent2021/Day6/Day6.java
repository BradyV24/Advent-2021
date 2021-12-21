package Advent2021.Day6;

import Advent2021.Day0.Input;

import java.util.Arrays;
import java.util.List;

public class Day6 {
    static List<String> input = Input.parseDocString("resources/2021/day6");
    public static void day6() {
        List<Integer> fishes = Arrays.stream(input.get(0).split(",")).map(x->Integer.parseInt(x)).toList();
        long[] fishGroup = new long[9];
        for (Integer fish : fishes) fishGroup[fish]++;
        for (int i = 0; i < 256; i++) {
            fishGroup[(i+7)%9] += fishGroup[i%9];
            if (i == 79) System.out.println(Arrays.stream(fishGroup).sum());
        }
        System.out.println(Arrays.stream(fishGroup).sum());
    }
}
