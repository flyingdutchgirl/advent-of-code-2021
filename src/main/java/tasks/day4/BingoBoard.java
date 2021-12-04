package tasks.day4;

import java.util.Arrays;
import java.util.stream.Stream;

public class BingoBoard {

    private BingoCell[][] board;
    private int lastNumber = -1;

    private BingoBoard(BingoCell[][] board) {
        this.board = board;
    }

    static BingoBoard parse(String fields) {
        var board = Arrays.stream(fields.split("\n"))
                .map(row -> Arrays.stream(row.split("\\s+"))
                        .filter(str -> !str.isBlank())
                        .map(Integer::parseInt)
                        .map(BingoCell::new)
                        .toArray(BingoCell[]::new)
                ).toArray(BingoCell[][]::new);

        return new BingoBoard(board);
    }

    private Stream<BingoCell> cellStream() {
        return Arrays.stream(this.board)
                .flatMap(Arrays::stream);
    }

    void drawNumber(int number) {
        this.lastNumber = number;

        for (int i = 0; i < board.length; i++) {
            var row = board[i];
            for (int k = 0; k < row.length; k++) {
                if (board[i][k].getVal() == number) {
                    board[i][k].setMarked(true);
                    break;
                }
            }
        }
    }

    private boolean checkRows() {
        for (BingoCell[] cells : board) {
            boolean b = Arrays.stream(cells)
                    .allMatch(BingoCell::isMarked);
            if (b) {
                return true;
            }
        }
        return false;
    }

    private boolean checkColumns() {
        int len = board[0].length;

        for (int k = 0; k < len; k++) {
            final int finK = k;
            boolean b = Arrays.stream(board)
                    .map(arr -> arr[finK])
                    .allMatch(BingoCell::isMarked);

            if (b) {
                return true;
            }
        }
        return false;
    }

    boolean check() {
        if (checkRows()) {
            return true;
        }

        return checkColumns();
    }

    long score() {
        int sum = cellStream()
                .filter(BingoCell::isNotMarked)
                .mapToInt(BingoCell::getVal)
                .sum();

        return (long) sum * this.lastNumber;
    }
}

class BingoCell {
    final int val;
    private boolean marked = false;

    public BingoCell(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public boolean isMarked() {
        return marked;
    }

    public boolean isNotMarked() {
        return !marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }
}