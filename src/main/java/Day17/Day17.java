package Day17;

import Day0.Input;

import java.util.List;

public class Day17 {
    public static void part1() {
        int[] targetX = {34,67}, targetY = {-215,-186};
        int maxY = 0, count = 0;
        for (int vy = targetY[0]; vy < 1000; vy++) {
            for (int vx = 0; vx <= targetX[1]; vx++) {
                int x = 0, y = 0, dx = vx, dy = vy, cMaxY = 0;
                while (x <= targetX[1] && y >= targetY[0]) {
                    x+=dx;
                    y+=dy;
                    cMaxY = Math.max(y, cMaxY);
                    if (dx > 0) dx--;
                    dy--;
                    if (x >= targetX[0] && x <= targetX[1] && y >= targetY[0] && y <= targetY[1]) {
                        maxY = Math.max(cMaxY, maxY);
                        count++;
                        break;
                    }
                }
            }
        }
        System.out.println(maxY + "\n" + count);
    }
}
