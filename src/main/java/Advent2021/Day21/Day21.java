package Advent2021.Day21;

import Advent2021.Day0.Input;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day21 {
    static Map<String, Long[]> cache = new HashMap<>();
    public static void day21() {
        int p1 = 10, s1 = 0, p2 = 1, s2 = 0, d = 0, r = 0;
        Long[] wins = game(10,1, 0, -1, 0, 0);
        while (true) {
            p1 += (d++ % 100) + 1 + (d++ % 100) + 1 + (d++ % 100) + 1;
            s1 += ((p1-1) % 10) + 1;
            r+=3;
            if (s1 >= 1000) break;
            p2 += (d++ % 100) + 1 + (d++ % 100) + 1 + (d++ % 100) + 1;
            s2 += ((p2-1) % 10) + 1;
            r+=3;
            if (s2 >= 1000) break;
        }
        System.out.println(r*Math.min(s1,s2));
        System.out.println(Math.max(wins[0],wins[1]));
    }
    private static Long[] game(int p1, int p2, int s1, int s2, int d, int t) {
        Long[][] g = new Long[7][2];
        if (t == 1) {
            p1 = (p1 - 1 + d) % 10 + 1;
            s1 += p1;
            if (s1 >= 21) return new Long[]{1L,0L};
        } else {
            p2 = (p2 - 1 + d) % 10 + 1;
            s2 += p2;
            if (s2 >= 21) return new Long[]{0L,1L};
        }
        t = (t+1)%2;
        for (int i = 0; i < 7; i++) {
            String key = "" + p1 + p2 + s1 + s2 + (3 + i) + t;
            if (!cache.containsKey(key)) cache.put(key, game(p1, p2, s1, s2, 3 + i, t));
            g[i] = cache.get(key);
        }
        return new Long[]{g[0][0] + 3*g[1][0] + 6*g[2][0] + 7*g[3][0] + 6*g[4][0] + 3*g[5][0] + g[6][0],
            g[0][1] + 3*g[1][1] + 6*g[2][1] + 7*g[3][1] + 6*g[4][1] + 3*g[5][1] + g[6][1]};
    }
}
