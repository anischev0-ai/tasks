import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите координаты точки A (x y):");
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();

        System.out.println("Введите координаты точки B (x y):");
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();

        System.out.println("Введите координаты точки C (x y):");
        double x3 = scanner.nextDouble();
        double y3 = scanner.nextDouble();

        double EPS = 1e-9;

        double sideAB = distance(x1, y1, x2, y2);
        double sideBC = distance(x2, y2, x3, y3);
        double sideCA = distance(x3, y3, x1, y1);

        if (sideAB + sideBC <= sideCA + EPS || sideBC + sideCA <= sideAB + EPS || sideCA + sideAB <= sideBC + EPS) {
            System.out.println("Точки не образуют треугольник.");
        } else {
            if (Math.abs(sideAB - sideBC) < EPS && Math.abs(sideBC - sideCA) < EPS) {
                System.out.println("Треугольник равносторонний.");
            } else if (Math.abs(sideAB - sideBC) < EPS || Math.abs(sideBC - sideCA) < EPS || Math.abs(sideCA - sideAB) < EPS) {
                System.out.println("Треугольник равнобедренный.");
            } else {
                System.out.println("Треугольник разносторонний.");
            }

            double a2 = sideAB * sideAB;
            double b2 = sideBC * sideBC;
            double c2 = sideCA * sideCA;

            double maxSide = Math.max(sideAB, Math.max(sideBC, sideCA));
            double maxSide2 = maxSide * maxSide;

            double sumOthers = a2 + b2 + c2 - maxSide2;

            if (Math.abs(maxSide2 - sumOthers) < EPS) {
                System.out.println("Треугольник прямоугольный.");
            } else if (maxSide2 > sumOthers + EPS) {
                System.out.println("Треугольник тупоугольный.");
            } else {
                System.out.println("Треугольник остроугольный.");
            }
        }

        scanner.close();
    }

    public static double distance(double x1, double y1, double x2, double y2) {
        return Math.sqrt( (x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1) );
    }
}
