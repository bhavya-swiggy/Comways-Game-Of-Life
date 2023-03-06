import java.util.List;

public class Grid {
    private final byte[][] grid;
    private final int numOfRows;
    private final int numOfColumns;

    public Grid(int numOfRows, int numOfColumns) {
        if (numOfRows < 0 || numOfColumns < 0) {
            throw new IllegalArgumentException(
                    String.format("Invalid dimensions {numOfRows : %s, numOfColumns : %s}",
                                  numOfRows, numOfColumns));
        }
        this.numOfRows = numOfRows;
        this.numOfColumns = numOfColumns;
        this.grid = new byte[numOfRows][numOfColumns];
    }

    public void print() {
        for (byte[] row : grid) {
            for (byte cell : row) {
                System.out.print(cell + " ");
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
            throw new IllegalArgumentException("Invalid coordinates : " + coordinates);
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
        for (int row = 0; row < grid.length; row++) {
            for (int column = 0; column < grid.length; column++) {
                grid[row][column] = calculateNextState(row, column);
            }
        }
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
        if (row > 0) {
            if (column > 0) {
                numOfLiveNeighbours += grid[row - 1][column - 1];
            }
            numOfLiveNeighbours += grid[row - 1][column];
            if (column < numOfColumns - 1) {
                numOfLiveNeighbours += grid[row - 1][column + 1];
            }
        }
        if (column > 0) {
            numOfLiveNeighbours += grid[row][column - 1];
        }
        if (column < numOfColumns - 1) {
            numOfLiveNeighbours += grid[row][column + 1];
        }
        if (row < numOfRows - 1) {
            if (column > 0) {
                numOfLiveNeighbours += grid[row + 1][column - 1];
            }
            numOfLiveNeighbours += grid[row + 1][column];
            if (column < numOfColumns - 1) {
                numOfLiveNeighbours += grid[row + 1][column + 1];
            }
        }
        return numOfLiveNeighbours;
    }
}
