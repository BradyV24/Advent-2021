package Day4;
import Day0.Input;
import java.util.*;
public class Day4 {
    static List<String> input = Input.parseDocString("resources/2021/day4");
    static List<String> balls = new ArrayList<>(Arrays.stream(input.get(0).split(",")).toList());

    public static void day4_1() {
        List<String[][]> boards = parseBoards();
        for (String call : balls) {
            mark(call, boards);
            for (String[][] board : boards) {
                if (check(board)) {
                    System.out.println(count(board) * Integer.parseInt(call));
                    return;
                }
            }
        }
    }

    public static void day4_2() {
        List<String[][]> boards = parseBoards();
        for (String call : balls) {
            mark(call, boards);
            List<String[][]> next = new ArrayList<>(boards);
            for (String[][] board : boards) {
                if (check(board)) {
                    if (boards.size() == 1) {
                        System.out.println(count(board) * Integer.parseInt(call));
                        return;
                    } else next.remove(board);
                }
            }
            boards = new ArrayList<>(next);
        }
    }

    private static List<String[][]> parseBoards() {
        List<String[][]> boards = new ArrayList<>();
        String[][] board = new String[5][5];
        int boardSize = 0;
        for (String line : input.subList(1,input.size())) {
            if (!line.isBlank()) {
                for (int j = 0, k = 0; k < 5; j++, k++)
                    if (line.split("[ ]+")[j] != "") board[boardSize][k] = line.split("[ ]+")[j];
                    else k--;
                if (boardSize == 4) {
                    boardSize = 0;
                    boards.add(board);
                    board = new String[5][5];
                } else boardSize++;
            }
        }
        return boards;
    }

    private static void mark(String call, List<String[][]> boards) {
        for (String[][] board : boards) {
            for (int j = 0; j < 5; j++) {
                for (int k = 0; k < 5; k++) {
                    if (board[j][k].equals(call))
                        board[j][k] = "X";
                }
            }
        }
    }

    private static int count(String[][] board) {
        int tot = 0;
        for (String[] row : board) {
            for (String num : row) {
                if (!num.equals("X"))
                    tot += Integer.parseInt(num);
            }
        }
        return tot;
    }

    private static boolean check(String[][] board) {
        for (int j = 0; j < 5; j++)
            if (board[j][0].equals("X") && board[j][1].equals("X") && board[j][2].equals("X") && board[j][3].equals("X") && board[j][4].equals("X") ||
                board[0][j].equals("X") && board[1][j].equals("X") && board[2][j].equals("X") && board[3][j].equals("X") && board[4][j].equals("X"))
                return true;
        return false;
    }
}
