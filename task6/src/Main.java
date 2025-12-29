import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите x (-1 < x < 1): ");
        double x = scanner.nextDouble();
        System.out.print("Введите n (целое число): ");
        int n = scanner.nextInt();
        System.out.print("Введите e: ");
        double e = scanner.nextDouble();

        double sumN = 0, sumE = 0, sumE10 = 0;
        int countE = 0, countE10 = 0;

        double current = 1.0;
        int i = 0;

        while (i < n || Math.abs(current) > e / 10.0) {

            if (i < n) {
                sumN += current;
            }

            if (Math.abs(current) > e) {
                sumE += current;
                countE++;
            }

            if (Math.abs(current) > e / 10.0) {
                sumE10 += current;
                countE10++;
            }

            i++;
            current *= (-(2.0 * i - 1.0) / (2.0 * i) * x);
        }

        double fMath = 1.0 / Math.sqrt(1 + x);

        System.out.printf("1) Сумма %d слагаемых: %.12f%n", n, sumN);
        System.out.printf("2) Сумма слагаемых > e: %.12f, количество: %d%n", sumE, countE);
        System.out.printf("3) Сумма слагаемых > e/10: %.12f, количество: %d%n", sumE10, countE10);
        System.out.printf("4) Значение функции через Math: %.12f%n", fMath);
    }
}