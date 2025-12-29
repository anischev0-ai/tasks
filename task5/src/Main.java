import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static void print(int h, int w) {
        if (h <= 0 || w <= 0) {
            System.out.println("Размеры должны быть положительными");
            return;
        }
        for (int i = 0; i < h; i++) {
            int blockSize = h - i;
            for (int j = 0; j < w; j++) {
                int blockNumber = j / blockSize;
                if (blockNumber % 2 == 0) {
                    System.out.print("a");
                } else {
                    System.out.print("b");
                }
            }
            System.out.print("\n");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input h: ");
        int inputH = scanner.nextInt();
        System.out.print("Input w: ");
        int inputW = scanner.nextInt();

        print(inputH, inputW);

    }
}