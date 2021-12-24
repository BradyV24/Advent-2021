package Advent2021.Day24;

import Advent2021.Day0.Input;

import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day24 {
    //Solved by hand :/
    static List<String> input = Input.parseDocString("resources/2021/day24");
    public static void part1() {
        int[] d = new int[14];
        d = new int[]{1,2,9,9,6,9,9,7,8,2,9,3,9,9};
        System.out.println(MONAD(d));
    }

    private static long MONAD(int[] in) {
        long[] vars = {0,0,0,0};

        for (int i = 0; i < 14; i++) {
            vars[0] = in[i];
            vars[1] = (vars[3] % 26 + Integer.parseInt(input.get(18*i + 5).substring(6)) != vars[0]) ? 1:0;
            vars[3] /= Integer.parseInt(input.get(18*i + 4).substring(6));
            vars[3] *= 25*vars[1] + 1;
            vars[3] += (vars[0] + Integer.parseInt(input.get(18*i + 15).substring(6)))*vars[1];
        }
        return vars[3];
    }
}
