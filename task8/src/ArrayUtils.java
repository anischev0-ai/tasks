import java.io.*;
import java.util.*;

public class ArrayUtils {

    public static int[][] readIntArrayFromFile(String fileName) throws FileNotFoundException {
        List<int[]> list = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;
                String[] items = line.split("\\s+");
                int[] row = new int[items.length];
                for (int i = 0; i < items.length; i++) {
                    row[i] = Integer.parseInt(items[i]);
                }
                list.add(row);
            }
        }
        return list.toArray(new int[0][]);
    }

    public static void writeIntArrayToFile(String fileName, int[][] array) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (int[] row : array) {
                for (int i = 0; i < row.length; i++) {
                    pw.print(row[i] + (i < row.length - 1 ? " " : ""));
                }
                pw.println();
            }
        }
    }

    public static boolean isRectangular(int[][] array) {
        if (array == null || array.length == 0) return true;
        int width = array[0].length;
        for (int[] row : array) {
            if (row.length != width) return false;
        }
        return true;
    }

    public static void swapColumns(int[][] array) {
        if (array == null || array.length == 0 || array[0].length == 0) return;
        if (!isRectangular(array)) throw new IllegalArgumentException("Массив должен быть прямоугольным");

        int rows = array.length;
        int cols = array[0].length;

        int maxVal = Integer.MIN_VALUE;
        int minVal = Integer.MAX_VALUE;
        int firstMaxCol = -1;
        int lastMinCol = -1;

        for (int j = 0; j < cols; j++) {
            for (int i = 0; i < rows; i++) {
                int val = array[i][j];
                if (val > maxVal) {
                    maxVal = val;
                    firstMaxCol = j;
                }
                if (val <= minVal) {
                    minVal = val;
                    lastMinCol = j;
                }
            }
        }

        if (firstMaxCol != lastMinCol && firstMaxCol != -1 && lastMinCol != -1) {
            for (int i = 0; i < rows; i++) {
                int temp = array[i][firstMaxCol];
                array[i][firstMaxCol] = array[i][lastMinCol];
                array[i][lastMinCol] = temp;
            }
        }
    }
}
