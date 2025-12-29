import java.io.*;
import java.util.*;

public class ListUtils {

    public static void process(List<Integer> list) {
        if (list == null || list.size() <= 1) {
            return;
        }
        int n = list.size();
        for (int i = 0; i < n / 2; i++) {
            swap(list, i, n - 1 - i);
        }
    }

    private static void swap(List<Integer> list, int i, int j) {
        int temp = getElement(list, i);
        setElement(list, i, getElement(list, j));
        setElement(list, j, temp);
    }

    public static int getElement(List<Integer> list, int index) {
        int curr = 0;
        for (Integer val : list) {
            if (curr == index) return val;
            curr++;
        }
        throw new IndexOutOfBoundsException();
    }

    public static void setElement(List<Integer> list, int index, int value) {
        if (index < 0 || index >= list.size()) {
            throw new IndexOutOfBoundsException();
        }
        ListIterator<Integer> it = list.listIterator();
        int curr = 0;
        while (it.hasNext()) {
            it.next();
            if (curr == index) {
                it.set(value);
                return;
            }
            curr++;
        }
    }

    public static List<List<Integer>> readListsFromFile(String fileName) throws FileNotFoundException {
        List<List<Integer>> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                List<Integer> row = new ArrayList<>();
                Scanner lineScanner = new Scanner(line);
                while (lineScanner.hasNextInt()) {
                    row.add(lineScanner.nextInt());
                }

                if (!row.isEmpty()) {
                    result.add(row);
                }
            }
        }
        return result;
    }

    public static void writeListsToFile(String fileName, List<List<Integer>> lists) throws IOException {
        try (PrintWriter pw = new PrintWriter(new FileWriter(fileName))) {
            for (List<Integer> list : lists) {
                for (int i = 0; i < list.size(); i++) {
                    pw.print(getElement(list, i) + (i < list.size() - 1 ? " " : ""));
                }
                pw.println();
            }
        }
    }
}
