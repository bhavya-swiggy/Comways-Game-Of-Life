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
        return new Grid(numOfRows, numOfColumns);
    }

    private static void seedGrid(BufferedReader reader, Grid grid) throws IOException {
        System.out.print("Enter num of live cells : ");
        int numOfCoordinates = Integer.parseInt(reader.readLine().trim());
        List<int[]> coordinatesList = new ArrayList<>(numOfCoordinates);
        System.out.print("Enter coordinates for live cells :");
        for (int i = 0; i < numOfCoordinates; i++) {
            int row = Integer.parseInt(reader.readLine().trim());
            int column = Integer.parseInt(reader.readLine().trim());
            int[] coordinates = new int[] { row, column };
            coordinatesList.add(coordinates);
        }
        grid.seed(coordinatesList);
    }

    private static void step(BufferedReader reader, Grid grid) throws IOException {
    }
}
