package Day18;

import Day0.Input;

import java.util.List;

public class Day18 {
    static List<String> input = Input.parseDocString("resources/2021/day18");
    public static void part1() {
        SPair num = new SPair(input.get(0));
        for (int i = 1; i < input.size(); i++) {
            num = new SPair(num, new SPair(input.get(i)));
            num.left.setParent(num);
            num.right.setParent(num);
            while( num.explodeCheck());
            while(num.splitCheck()) {
                while( num.explodeCheck());
            }

        }
        System.out.println(num.mag());
    }
    public static void part2() {
        long maxM = 0;
        for (String num1 : input) {
            for (String num2 : input) {
                SPair snum1 = new SPair(num1);
                SPair snum2 = new SPair(num2);
                SPair snumr = new SPair(snum1, snum2);
                snumr.left.setParent(snumr);
                snumr.right.setParent(snumr);
                while( snumr.explodeCheck());
                while(snumr.splitCheck()) {
                    while( snumr.explodeCheck());
                }
                maxM = Math.max(snumr.mag(), maxM);
            }
        }
        System.out.println(maxM);
    }
}
