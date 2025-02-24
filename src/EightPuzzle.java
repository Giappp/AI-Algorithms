import util.State;

import java.util.*;

public class EightPuzzle {
    private final int[][] initialState;
    private final int[][] goalState;

    static final int[][] DIRECTIONS = {
            {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public EightPuzzle(int[][] initialState, int[][] goalState) {
        this.initialState = initialState;
        this.goalState = goalState;
    }

    // Method to solve the problem using Depth-First Search
    public String dfs() {
        Stack<State> stackNodes = new Stack<>();
        Set<String> explored = new HashSet<>();

        int zeroRow = -1, zeroCol = -1;
        // Finds the zero's position
        for (int i = 0; i < initialState.length; i++) {
            for (int j = 0; j < initialState[i].length; j++) {
                if (initialState[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                }
            }
        }
        // Push the initial state into the stack
        stackNodes.push(new State(initialState, zeroRow, zeroCol, ""));

        while (!stackNodes.isEmpty()) {
            State current = stackNodes.pop();

            // Mark the current node as explored based on the current step taken
            explored.add(current.toString());

            // If the current state is the goal state then return the path
            if (current.toString().equals(new State(goalState, 0, 0, "").toString())) {
                printBoard(current.board);
                return current.path;
            }

            // Else expand the search
            for (int[] dir : DIRECTIONS) {
                int newRow = current.zeroRow + dir[0];
                int newCol = current.zeroCol + dir[1];

                if (newRow >= 0 && newRow < 3 && newCol >= 0 && newCol < 3) {
                    int[][] newBoard = State.copyBoard(current.board);
                    newBoard[current.zeroRow][current.zeroCol] = newBoard[newRow][newCol];
                    newBoard[newRow][newCol] = 0;

                    State newState = new State(newBoard, newRow, newCol, current.path + getMove(dir));
                    if (!explored.contains(newState.toString())) {
                        stackNodes.push(newState);
                        explored.add(newState.toString());
                    }
                }
            }
        }
        // If it reaches here then the goal state is unachievable
        return "No Solution";
    }

    // Method to solve the problem using Breadth-First Search
    public String bfs() {
        Queue<State> stateQueue = new LinkedList<>();
        Set<String> explored = new HashSet<>();

        // Finds the zero's position
        int zeroRow = -1, zeroCol = -1;
        for (int i = 0; i < initialState.length; i++) {
            for (int j = 0; j < initialState[i].length; j++) {
                if (initialState[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                }
            }
        }

        State goal = new State(goalState, 0, 0, "");
        // Push the initial state into the queue
        stateQueue.add(new State(initialState, zeroRow, zeroCol, ""));

        while (!stateQueue.isEmpty()) {
            State currentState = stateQueue.poll();
            explored.add(currentState.toString());
            if (currentState.toString().equals(goal.toString())) {
                printBoard(currentState.board);
                return currentState.path;
            }
            for (int[] dir : DIRECTIONS) {
                int newZeroRow = currentState.zeroRow + dir[0];
                int newZeroCol = currentState.zeroCol + dir[1];

                if (newZeroRow >= 0 && newZeroRow < 3 && newZeroCol >= 0 && newZeroCol < 3) {
                    State newState = moveState(currentState, dir, newZeroRow, newZeroCol);
                    if (!explored.contains(newState.toString())) {
                        stateQueue.add(newState);
                    }
                }
            }
        }
        // If it reaches here then the goal state is unachievable -> return No Solution
        return "No Solution";
    }

    public String bfsSearchWithExperience() {
        State.goalBoard = goalState;
        Queue<State> stateQueue = new PriorityQueue<>();
        Set<String> explored = new HashSet<>();

        // Finds the zero's position
        int zeroRow = -1, zeroCol = -1;
        for (int i = 0; i < initialState.length; i++) {
            for (int j = 0; j < initialState[i].length; j++) {
                if (initialState[i][j] == 0) {
                    zeroRow = i;
                    zeroCol = j;
                }
            }
        }

        State goal = new State(goalState, 0, 0, "");
        // Push the initial state into the queue
        stateQueue.add(new State(initialState, zeroRow, zeroCol, ""));

        while (!stateQueue.isEmpty()) {
            State currentState = stateQueue.poll();
            explored.add(currentState.toString());
            if (currentState.toString().equals(goal.toString())) {
                printBoard(currentState.board);
                return currentState.path;
            }
            for (int[] dir : DIRECTIONS) {
                int newZeroRow = currentState.zeroRow + dir[0];
                int newZeroCol = currentState.zeroCol + dir[1];

                if (newZeroRow >= 0 && newZeroRow < 3 && newZeroCol >= 0 && newZeroCol < 3) {
                    State newState = moveState(currentState, dir, newZeroRow, newZeroCol);
                    newState.calculatePriority();
                    if (!explored.contains(newState.toString())) {
                        stateQueue.add(newState);
                    }
                }
            }
        }
        // If it reaches here then the goal state is unachievable -> return No Solution
        return "No Solution";
    }

    private State moveState(State currentState, int[] dir, int newZeroRow, int newZeroCol) {
        State newState = currentState.clone();
        newState.board[currentState.zeroRow][currentState.zeroCol] = currentState.board[newZeroRow][newZeroCol];
        newState.board[newZeroRow][newZeroCol] = 0;
        newState.zeroRow = newZeroRow;
        newState.zeroCol = newZeroCol;
        newState.path = currentState.path + getMove(dir);
        return newState;
    }

    // Helper method to convert direction array to a move letter
    private static String getMove(int[] dir) {
        if (Arrays.equals(dir, new int[]{1, 0})) return "D";
        if (Arrays.equals(dir, new int[]{-1, 0})) return "U";
        if (Arrays.equals(dir, new int[]{0, 1})) return "R";
        if (Arrays.equals(dir, new int[]{0, -1})) return "L";
        return "";
    }

    public static void main(String[] args) {
        int[][] startState = {
                {2, 8, 3},
                {1, 6, 4},
                {7, 0, 5}

        };

        int[][] goalState = new int[][]{
                {1, 2, 3},
                {8, 0, 4},
                {7, 6, 5}
        };
        EightPuzzle eightPuzzle = new EightPuzzle(startState, goalState);
        System.out.println("BFS Solution: " + eightPuzzle.bfsSearchWithExperience());
    }

    public void printBoard(int[][] board) {
        for (int[] ints : board) {
            for (int anInt : ints) {
                System.out.printf("%d ", anInt);
            }
            System.out.println();
        }
    }
}
