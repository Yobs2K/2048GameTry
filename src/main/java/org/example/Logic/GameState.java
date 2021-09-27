package org.example.Logic;

public class GameState {
    private int[][] matrix;
    private int score;
    private boolean isWon;

    GameState (int[][] matrix, int score, boolean isWon) {
        this.matrix = matrix;
        this.score = score;
        this.isWon = isWon;
    }

    GameState(Config config) {
        this(new int[config.getHeigth()][config.getWidth()], 0, false);
    }

    boolean updateState(int[][] matrix, int addScore, boolean isWon) {
        int height = matrix.length;;
        int width = matrix[0].length;
        if (!(height == this.matrix.length && width == this.matrix[0].length))
            return false;
        if (addScore<0)
            return false;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                this.matrix[i][j] = matrix[i][j];
            }
        }
        score += addScore;
        this.isWon = isWon;
        return true;
    }

    int[][] getMatrixCopy() {
        int height = matrix.length;
        int width = matrix[0].length;
        int[][] matrix2 = new int[height][width];
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                matrix2[i][j] = matrix[i][j];
            }
        }
        return matrix2;
    }

    int getScore() {
        return score;
    }
    boolean getIsWon() {
        return isWon;
    }
}
