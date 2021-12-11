package Day10;

import Day0.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Day10 {
    static List<String> input = Input.parseDocString("resources/2021/day10");
    public static void day10() {
        int tot = 0;
        List<Long> scores = new ArrayList<>();
        for (String line : input) {
            Stack<Character> stack = new Stack<>();
            boolean valid = true;
            for (Character c : line.toCharArray()) {
                if (c == '<' || c == '{' || c == '(' || c == '[') {
                    stack.push(c);
                } else {
                    if (c == '>') {
                        if (stack.peek() == '<') {
                            stack.pop();
                        } else {
                            tot += 25137;
                            valid = false;
                            break;
                        }
                    } else if (c == '}') {
                        if (stack.peek() == '{') {
                            stack.pop();
                        } else {
                            tot += 1197;
                            valid = false;
                            break;
                        }
                    } else if (c == ']') {
                        if (stack.peek() == '[') {
                            stack.pop();
                        } else {
                            tot += 57;
                            valid = false;
                            break;
                        }
                    } else if (c == ')') {
                        if (stack.peek() == '(') {
                            stack.pop();
                        } else {
                            tot += 3;
                            valid = false;
                            break;
                        }
                    }
                }
            }
            long score = 0;
            if (!stack.isEmpty() && valid == true) {
                for (int i = stack.size(); i > 0; i--) {
                    score *= 5;
                    char c = stack.pop();
                    if (c == '(') score+=1;
                    else if (c == '[') score+=2;
                    else if (c == '{') score+=3;
                    else if (c == '<') score+=4;
                }
                scores.add(score);
            }
        }
        scores.sort(Long::compareTo);
        System.out.println(tot + "\n" + scores.get(scores.size()/2));
    }
}
