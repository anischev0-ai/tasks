import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите длину стороны a:");
        double a = scanner.nextDouble();

        System.out.println("Введите длину стороны b:");
        double b = scanner.nextDouble();

        System.out.println("Введите длину стороны c:");
        double c = scanner.nextDouble();

        double p = (a + b + c) / 2;

        double ha = (2 / a) * Math.sqrt(p * (p - a) * (p - b) * (p - c));
        double hb = (2 / b) * Math.sqrt(p * (p - a) * (p - b) * (p - c));
        double hc = (2 / c) * Math.sqrt(p * (p - a) * (p - b) * (p - c));

        System.out.println("Высота, опущенная на сторону a: " + ha);
        System.out.println("Высота, опущенная на сторону b: " + hb);
        System.out.println("Высота, опущенная на сторону c: " + hc);

    }
}


