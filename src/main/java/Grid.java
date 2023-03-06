import java.util.Arrays;
import java.util.List;

public class Grid {
    private final int numOfRows;
    private final int numOfColumns;
    private final boolean printDeadCell;
    private byte[][] grid;

    public Grid(int numOfRows, int numOfColumns, boolean printDeadCell) {
        if (numOfRows < 0 || numOfColumns < 0) {
            throw new IllegalArgumentException(
                    String.format("Invalid dimensions {numOfRows : %s, numOfColumns : %s}",
                                  numOfRows, numOfColumns));
        }
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.printDeadCell = printDeadCell;
        this.grid = new byte[numOfRows][numOfColumns];
    }

    public void print() {
        for (byte[] row : grid) {
            for (byte cell : row) {
                if (printDeadCell || cell == 1) {
                    System.out.print(cell + " ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public void seed(List<int[]> coordinatesList) {
        for (int[] coordinates : coordinatesList) {
            setState(coordinates, (byte) 1);
        }
    }

    private void setState(int[] coordinates, byte state) {
        if (coordinates.length != 2) {
            throw new IllegalArgumentException("Invalid coordinates : " + Arrays.toString(coordinates));
        }
        int row = coordinates[0];
        int column = coordinates[1];
        if (row < 0 || row >= numOfRows || column < 0 || column >= numOfColumns) {
            throw new IllegalArgumentException(
                    String.format("Invalid coordinates {row : %s, column : %s}",
                                  row, column));
        }
        grid[row][column] = state;
    }

    public void step() {
        byte[][] nextGenerationGrid = new byte[numOfRows][numOfColumns];
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid.length; column++) {
                nextGenerationGrid[row][column] = calculateNextState(row, column);
            }
        }
        grid = nextGenerationGrid;
    }

    private byte calculateNextState(int row, int column) {
        int numOfLiveNeighbours = getNumOfLiveNeighbours(row, column);
        if (numOfLiveNeighbours < 2 || numOfLiveNeighbours > 3) {
            return 0;
        }
        if (numOfLiveNeighbours == 3) {
            return 1;
        }
        return grid[row][column];
    }

    private int getNumOfLiveNeighbours(int row, int column) {
        int numOfLiveNeighbours = 0;
        for (int i = row - 1;i <= row + 1; i++) {
            for (int j = column - 1; j <= column + 1; j++) {
                if (i >= 0 && i < numOfRows && j >= 0 && j < numOfColumns && !(i == row && j == column)) {
                    numOfLiveNeighbours += grid[i][j];
                }
            }
        }
        return numOfLiveNeighbours;
    }
}
