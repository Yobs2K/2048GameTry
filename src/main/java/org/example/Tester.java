package org.example;

import org.example.Logic.Assets.Move;
import org.example.Logic.GameController;

public class Tester {
    public static void main(String[] args) {
        GameController Game = GameController.getInstance();
        int height = Game.getHeight();
        int width = Game.getWidth();
        Game.startGame();
        while (true) {
            int score = Game.getScore();
            int[][] matrix = Game.getMatrix();
            System.out.printf("score = %s\n", score);
            ConsoleIO.printGameMatrix(matrix);
            char c = ConsoleIO.getChar();
            switch (c) {
                case 'a':
                    Game.makeMove(Move.LEFT);
                    break;
                case 'w':
                    Game.makeMove(Move.UP);
                    break;
                case 'd':
                    Game.makeMove(Move.RIGHT);
                    break;
                case 's':
                    Game.makeMove(Move.DOWN);
                    break;
                case 'u':
                    Game.undoMove();
                    break;
                default:
                    System.out.println("Неправильный ввод");
            }
        }
    }
}
