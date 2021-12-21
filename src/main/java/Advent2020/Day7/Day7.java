package Advent2020.Day7;

import Advent2021.Day0.Input;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {
    static List<String> input = Input.parseDocString("resources/2020/day7");
    public static void part1() {
        Map<String, Map<String, Integer>> bags = new HashMap<>();
        for (String bag : input) {
            if (!bag.split(" bags contain ")[1].equals("no other bags.")) {
                String out = bag.split(" bags contain ")[0];
                List<String> in = new ArrayList<>(
                    Arrays.stream(bag.split(" bags contain ")[1].split(" bags, | bag, "))
                        .toList());
                if (in.get(in.size() - 1).charAt(in.get(in.size() - 1).length()-2) == 's') {
                    in.set(in.size() - 1,
                        in.get(in.size() - 1).substring(0, in.get(in.size() - 1).length() - 6));
                } else {
                    in.set(in.size() - 1,
                        in.get(in.size() - 1).substring(0, in.get(in.size() - 1).length() - 5));
                }
                bags.put(out, new HashMap<>());
                for (String content : in) {
                    bags.get(out).put(content.substring(2), Integer.parseInt(content.substring(0,1)));
                }
            } else {
                bags.put(bag.split(" bags contain ")[0], new HashMap<>());
            }
        }

        int count = 0;
        for (String bag : bags.keySet()) {
            if (search("shiny gold", bag, bags)) count++;
        }

        System.out.println(count);
        System.out.println(contains("shiny gold", bags));
    }

    private static boolean search(String target, String start, Map<String, Map<String, Integer>> bags) {
        if (bags.get(start).size() == 0) return false;
        if (bags.get(start).containsKey(target)) return true;
        for (String next : bags.get(start).keySet()) {
            if (search(target, next, bags)) return true;
        }
        return false;
    }

    private static long contains(String bag, Map<String, Map<String, Integer>> bags) {
        long tot = 0;
        for (String next : bags.get(bag).keySet()) {
            tot += (contains(next, bags) + 1) * bags.get(bag).get(next);
        }
        return tot;
    }
}
