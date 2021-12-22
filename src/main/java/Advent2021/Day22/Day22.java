package Advent2021.Day22;

import Advent2021.Day0.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day22 {
    static List<String> input = Input.parseDocString("resources/2021/day22");
    public static void part1() {
        List<Boolean> com = input.stream().map(x-> (x.split(" ")[0].equals("on"))).toList();
        List<List<Integer>> coords = input.stream().map(x-> Arrays.stream(x.split(" ")[1].substring(2).split("\\.\\.|,y=|,z=")).map(y->Integer.parseInt(y)).toList()).toList();
        boolean[][][] map = new boolean[101][101][101];
        for (int s = 0; s < 20; s++) {
            for (int i = coords.get(s).get(0); i <= coords.get(s).get(1); i++) {
                for (int j = coords.get(s).get(2); j <= coords.get(s).get(3); j++) {
                    for (int k = coords.get(s).get(4); k <= coords.get(s).get(5); k++) {
                        map[i+50][j+50][k+50] = com.get(s);
                    }
                }
            }
        }
        int count = 0;
        for (int i = 0; i < 101; i++) {
            for (int j = 0; j < 101; j++) {
                for (int k = 0; k < 101; k++) {
                    if (map[i][j][k]) count++;
                }
            }
        }
        System.out.println(count);
    }
    public static void part2() {
        List<Boolean> com = input.stream().map(x-> (x.split(" ")[0].equals("on"))).toList();
        List<List<Integer>> coords = input.stream().map(x-> Arrays.stream(x.split(" ")[1].substring(2).split("\\.\\.|,y=|,z=")).map(y->Integer.parseInt(y)).toList()).toList();

        List<Cuboid> cuboids = new ArrayList<>();
        for (int i = 0; i < com.size(); i++) {
            Cuboid nextCuboid = new Cuboid(coords.get(i).get(0),coords.get(i).get(1),coords.get(i).get(2),coords.get(i).get(3),coords.get(i).get(4),coords.get(i).get(5));
            List<Cuboid> newCuboids = new ArrayList<>();
            for (Cuboid vol : cuboids) {
                newCuboids.addAll(vol.cut(nextCuboid));
            }
            cuboids = newCuboids;
            if (com.get(i)) {
                cuboids.add(nextCuboid);
            }
        }
        long tot = 0;
        for (Cuboid cuboid : cuboids) {
            tot += cuboid.area;
        }
        System.out.println(tot);
    }
}
