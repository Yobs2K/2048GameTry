package org.example;

import java.io.Console;
import java.util.Collection;
import java.util.Scanner;

public class ConsoleIO {
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; ++i) {
            System.out.printf("%s ", array[i]);
        }
    }
    public static void printlnArray(int[] array) {
        printArray(array);
        System.out.println();
    }
    public static void printlnMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            printlnArray(matrix[i]);
        }
    }

    public static char getChar () {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        return s.charAt(0);
    }

    private static void printGameArray(int [] array) {
        System.out.print("|");
        for (int i = 0; i < array.length; ++i) {
            String s = switch (array[i]) {
                case 0 -> " ";
                default -> String.valueOf(array[i]);
            };
            System.out.printf("%2s|", s);
        }
    }

    public static void printGameMatrix (int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            printGameArray(matrix[i]);
            System.out.println();
        }
    }

    public static void printlnGameMatrix (int[][] matrix) {
        printGameMatrix(matrix);
        System.out.println();
    }
}
