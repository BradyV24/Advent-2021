package Advent2021.Day25;

import Advent2021.Day0.Input;

import java.util.List;

public class Day25 {
    static List<String> input = Input.parseDocString("resources/2021/day25");
    public static void part1() {
        char[][] map = new char[input.size()][input.get(0).length()];
        for (int i = 0; i < input.size(); i++) {
            for (int j = 0 ; j < input.get(i).length(); j++) {
                map[i][j] = input.get(i).charAt(j);
            }
        }
        int steps = 0;
        boolean moved = true;
        while (moved) {
            moved = false;
            char[][] next = new char[input.size()][input.get(0).length()];
            for (int i = 0; i < input.size(); i++) {
                for (int j = 0 ; j < input.get(i).length(); j++) {
                    if (map[i][j] == '>') {
                        if (map[i][(j+1)%map[i].length] == '.') {
                            next[i][(j+1)%map[i].length] = '>';
                            next[i][j] = '.';
                            moved = true;
                        } else {
                            next[i][j] = '>';
                        }
                    } else {
                        if (next[i][j] != '>')
                            next[i][j] = map[i][j];
                    }
                }
            }
                map = next;
                next = new char[input.size()][input.get(0).length()];
                for (int i = 0; i < input.size(); i++) {
                    for (int j = 0; j < input.get(i).length(); j++) {
                        if (map[i][j] == 'v') {
                            if (map[(i + 1) % map.length][j] == '.') {
                                next[(i + 1) % map.length][j] = 'v';
                                next[i][j] = '.';
                                moved = true;
                            } else {
                                next[i][j] = 'v';
                            }
                        } else {
                            if (next[i][j] != 'v')
                                next[i][j] = map[i][j];
                        }
                    }
                }
                steps++;
                map = next;
        }
        System.out.println(steps);
    }
}
