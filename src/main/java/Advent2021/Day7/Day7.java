package Advent2021.Day7;

import Advent2021.Day0.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day7 {
    public static void day7() {
        List<Integer> in = Arrays.stream(Input.parseDocString("resources/2021/day7").get(0).split(",")).map(y -> Integer.parseInt(y)).toList();
        List<Integer> fuels1 = new ArrayList<>(), fuels2 = new ArrayList<>();
        for (int[] i = {0}; i[0] < in.stream().max(Integer::compareTo).get(); i[0]++) {
            fuels1.add(in.stream().map(x -> Math.abs(i[0] - x)).reduce(0, Integer::sum));
            fuels2.add(in.stream().map(x -> Math.abs(i[0] - x) * (Math.abs(i[0] - x) + 1) / 2).reduce(0, Integer::sum));
        }
        System.out.println(fuels1.stream().min(Integer::compareTo).get() + "\n" + fuels2.stream().min(Integer::compareTo).get());
    }
}
