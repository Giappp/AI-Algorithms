package util;

public class State implements Cloneable, Comparable<State> {
    public static int[][] goalBoard;
    public final int[][] board;
    public int zeroRow, zeroCol;
    public String path; // Stores the path taken (U,D,L,R);

    public int priority;

    public State(int[][] board, int zeroRow, int zeroCol, String path) {
        this.board = board;
        this.zeroRow = zeroRow;
        this.zeroCol = zeroCol;
        this.path = path;
        // Calculate the priority
        priority = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == goalBoard[i][j]) {
                    priority++;
                }
            }
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int[] row : board) {
            for (int num : row) {
                sb.append(num);
            }
        }
        return sb.toString();
    }

    public static int[][] copyBoard(int[][] board) {
        int[][] newBoard = new int[3][3];
        for (int i = 0; i < 3; i++) {
            newBoard[i] = board[i].clone();
        }
        return newBoard;
    }

    public void calculatePriority() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == goalBoard[i][j]) {
                    priority++;
                }
            }
        }
    }

    @Override
    public State clone() {
        return new State(copyBoard(this.board), this.zeroRow, this.zeroCol, this.path);
    }

    @Override
    public int compareTo(State o) {
        return Integer.compare(this.priority, o.priority);
    }
}
