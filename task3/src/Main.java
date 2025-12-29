import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static final Rectangle R1 = new Rectangle(-6, 3, -2, -2);
    public static final Rectangle R2 = new Rectangle(-2, 6, 1, 3);
    public static final Rectangle R3 = new Rectangle(1, 3, 2, -2);
    public static final Rectangle R4 = new Rectangle(-2, -2, 1, -4);
    public static final HorizontalParabola P1 = new HorizontalParabola(-4, -3, -0.25);
    public static SimpleColor getColor(double x, double y) {

        if (!P1.isPointRightOfParabola(x, y) && R1.isPointInRectangle(x, y)) {
            return SimpleColor.BLUE;
        }
        if (R1.isPointInRectangle(x, y) || R3.isPointInRectangle(x, y)) {
            return SimpleColor.GRAY;
        }
        if (R2.isPointInRectangle(x, y)) {
            return SimpleColor.BLUE;
        }
        if (R4.isPointInRectangle(x, y)){
            return SimpleColor.GREEN;
        }
        return SimpleColor.ORANGE;
    }

    public static void printColorForPoint(double x, double y) {
        System.out.print("(");
        System.out.print(x);
        System.out.print(", ");
        System.out.print(y);
        System.out.print(")");
        System.out.print(" -> ");
        System.out.println(getColor(x,y));
    }

    public static void main(String[] args) {

        printColorForPoint(-3, 1.0);
        printColorForPoint(-1, 4.0);
        printColorForPoint(1.5, 1);
        printColorForPoint(0.5, -3);
        printColorForPoint(-5.0, -1.5);
        printColorForPoint(-1.0, -1);

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите X: ");
        double inputX = scanner.nextDouble();
        System.out.print("Введите Y: ");
        double inputY = scanner.nextDouble();

        printColorForPoint(inputX, inputY);

    }
}