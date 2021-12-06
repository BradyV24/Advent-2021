package Day6;

import Day0.Input;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
