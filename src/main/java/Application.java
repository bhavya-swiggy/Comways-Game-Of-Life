import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            Grid grid = initialiseGrid(reader);
            seedGrid(reader, grid);
            step(reader, grid);
        } catch (Exception ex) {
            System.err.println("An error occurred : " + ex.getMessage());
        }
    }

    private static Grid initialiseGrid(BufferedReader reader) throws IOException {
        System.out.print("Enter num of rows : ");
        int numOfRows = Integer.parseInt(reader.readLine().trim());
        System.out.print("Enter num of columns : ");
        int numOfColumns = Integer.parseInt(reader.readLine().trim());
        System.out.print("Print dead cell ('Y' for Yes) : ");
        boolean printDeadCell = reader.readLine().trim().toUpperCase().charAt(0) == 'Y';
        return new Grid(numOfRows, numOfColumns, printDeadCell);
    }

    private static void seedGrid(BufferedReader reader, Grid grid) throws IOException {
        System.out.print("Enter num of live cells : ");
        int numOfCoordinates = Integer.parseInt(reader.readLine().trim());
        List<int[]> coordinatesList = new ArrayList<>(numOfCoordinates);
        System.out.println("Enter coordinates for live cells :");
        for (int i = 1; i <= numOfCoordinates; i++) {
            System.out.println("Enter coordinate " + i + " :");
            System.out.print("Enter row : ");
            int row = Integer.parseInt(reader.readLine().trim());
            System.out.print("Enter column : ");
            int column = Integer.parseInt(reader.readLine().trim());
            int[] coordinates = new int[] { row, column };
            coordinatesList.add(coordinates);
        }
        grid.seed(coordinatesList);
    }

    private static void step(BufferedReader reader, Grid grid) throws IOException {
        System.out.print("Enter num of steps : ");
        int numOfSteps = Integer.parseInt(reader.readLine().trim());
        System.out.println("T = 0");
        grid.print();
        for (int i = 1; i <= numOfSteps; i++) {
            System.out.println("T = " + i);
            grid.step();
            grid.print();
        }
    }
}
