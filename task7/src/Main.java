import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {

    public static int[] getIntArray() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите массив целых чисел через запятую: ");
        String input = scanner.nextLine();
        String[] numbers = input.split(",");
        int[] result = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            result[i] = Integer.parseInt(numbers[i]);
        }
        return result;

    }

    public static int solution(int[] array) {
        int min = Integer.MAX_VALUE;
        int secondMin = Integer.MAX_VALUE;
        int result = -1;

        for (int num : array) {
            if (num < min) {
                min = num;
            }
        }

        for (int i = 0; i < array.length; i++) {
            if (array[i] > min && array[i] <= secondMin) {
                secondMin = array[i];
                result = i;
            }
        }

        return result;
    }

    public static void print(int[] array){
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void print(int result){
        if (result != -1) {
            System.out.println("Позиция второго наименьшего элемента: " + result);
        }else {
            System.out.println("Нет второго наименьшего элемента.");
        }
    }

    public static void test(int i, int[] array){
        System.out.println("--------\nТест " + i);
        System.out.print("--------\nВходной массив: ");
        print(array);
        int result = solution(array);
        print(result);
    }

    public static void main(String[] args) {

        int[] test1 = {1, 2, 3, 4, 5};
        test(1, test1);
        int[] test2 = {1, 1, 1, 1, 1};
        test(2, test2);
        int[] test3 = {6, 2, 3, 4, 3};
        test(3, test3);
        int[] test4 = {-1, -5, -9, 4, 5};
        test(4, test4);
        int[] test5 = {11, 25, 3, 9, 5};
        test(5, test5);
        int[] test6 = {1, -2, 3, -4, 5};
        test(6, test6);
        int[] test7 = {23, 22, 21, 20, 24};
        test(7, test7);
        int[] test8 = {-1, -2, -3, -4, -5};
        test(8, test8);
        int[] test9 = {4, 5, 5, 5, 5};
        test(9, test9);
        int[] test10 = {-1, -1, -1, -1, -1};
        test(10, test10);

        System.out.println("--------");

        int[] array = getIntArray();

        int result = solution(array);
        print(result);

    }
}
