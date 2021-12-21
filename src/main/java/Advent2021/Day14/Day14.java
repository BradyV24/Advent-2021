package Advent2021.Day14;

import Advent2021.Day0.Input;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//THIS CODE IS REALLY BAD OFF BY ONE ERROR FOR PART 2 :/
public class Day14 {
    static String chain = Input.parseDocString("resources/2021/day14").get(0);
    static List<List<String>> rules =
        Input.parseDocString("resources/2021/day14").subList(2, 102).stream()
            .map(x -> Arrays.stream(x.split(" -> ")).toList()).toList();
    static Map<String, String> ruleMap = new HashMap<>();

    public static void part1() {
        for (List<String> rule : rules)
            ruleMap.put(rule.get(0), rule.get(1));
        Map<Character, Long> charCount = new HashMap<>();
        for (int j = 0; j < chain.length() - 1; j++)
            build(chain.substring(j, j + 2), 0, charCount);
        if (!charCount.containsKey(chain.charAt(chain.length() - 1)))
            charCount.put(chain.charAt(chain.length() - 1), 1L);
        else charCount.put(chain.charAt(chain.length() - 1),
            charCount.get(chain.charAt(chain.length() - 1)) + 1);
        long max = 0, min = 1000000000000000000L;
        for (Character c : charCount.keySet()) {
            max = Math.max(charCount.get(c), max);
            min = Math.min(charCount.get(c), min);
        }
        System.out.println(max - min);
    }

    private static void build(String chain, int step, Map<Character, Long> charCount) {
        if (step == 10) {
            if (!charCount.containsKey(chain.charAt(0))) charCount.put(chain.charAt(0), 1L);
            else charCount.put(chain.charAt(0), charCount.get(chain.charAt(0)) + 1);
            return;
        }
        build(chain.charAt(0) + ruleMap.get(chain), step + 1, charCount);
        build(ruleMap.get(chain) + chain.charAt(1), step + 1, charCount);
    }

    public static void part2() {
        Map<String, Long> pairs = new HashMap<>();
        for (int j = 0; j < chain.length() - 1; j++) {
            if (!pairs.containsKey(chain.substring(j, j + 2)))
                pairs.put(chain.substring(j, j + 2), 1L);
            else pairs.put(chain.substring(j, j + 2), pairs.get(chain.substring(j, j + 2)));
        }

        for (int i = 0; i < 40; i++) {
            Map<String, Long> nextPairs = new HashMap<>();
            for (String pair : pairs.keySet()) {
                if (!nextPairs.containsKey(pair.charAt(0) + ruleMap.get(pair)))
                    nextPairs.put(pair.charAt(0) + ruleMap.get(pair), pairs.get(pair));
                else nextPairs.put(pair.charAt(0) + ruleMap.get(pair),
                    nextPairs.get(pair.charAt(0) + ruleMap.get(pair)) + pairs.get(pair));
                if (!nextPairs.containsKey(ruleMap.get(pair) + pair.charAt(1)))
                    nextPairs.put(ruleMap.get(pair) + pair.charAt(1), pairs.get(pair));
                else nextPairs.put(ruleMap.get(pair) + pair.charAt(1),
                    nextPairs.get(ruleMap.get(pair) + pair.charAt(1)) + pairs.get(pair));
            }
            pairs = nextPairs;
        }
        Map<Character, Long> charCount = new HashMap<>();
        for (String pair : pairs.keySet()) {
            if (!charCount.containsKey(pair.charAt(0)))
                charCount.put(pair.charAt(0), pairs.get(pair));
            else charCount.put(pair.charAt(0), charCount.get(pair.charAt(0)) + pairs.get(pair));
            if (!charCount.containsKey(pair.charAt(1)))
                charCount.put(pair.charAt(1), pairs.get(pair));
            else charCount.put(pair.charAt(1), charCount.get(pair.charAt(1)) + pairs.get(pair));
        }
        long max = 0, min = 1000000000000000000L;
        for (Character c : charCount.keySet()) {
            max = Math.max(charCount.get(c), max);
            min = Math.min(charCount.get(c), min);
        }
        System.out.println((max - min) / 2 + 1);
    }
}
