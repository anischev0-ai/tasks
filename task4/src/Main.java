import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static double func(double x) {
        return (x*x+1)*Math.cos(x);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Input a: ");
        double inputA = scanner.nextDouble();
        System.out.print("Input h: ");
        double inputH = scanner.nextDouble();
        System.out.print("Input n: ");
        int inputN = scanner.nextInt();

        double result = func(inputA);
        for (int i = 1; i <= inputN; i++) {
            result += func(inputA + i * inputH);
        }

        System.out.println(result);

    }
}