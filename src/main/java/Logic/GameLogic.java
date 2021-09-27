package Logic;

import Logic.Assets.Move;

public class GameLogic {
    private static GameLogic instance;

    private GameLogic() {

    }

    public static GameLogic getInstance() {
        if (instance == null)
            instance = new GameLogic();
        return instance;
    }

    public static int getRandomInt(int to) {
        return (int)(Math.random()*to);
    }
    public static int getRandomInt(int min, int max) {
        return getRandomInt(max-min+1)+min;
    }

    public static boolean isArrayMoveRightPossible(int[] array) {
        for (int i = 0; i < array.length-1; ++i) {
            if (array[i] != 0 && (array[i] == array[i+1] || array[i+1] == 0))
                return true;
        }
        return false;
    }

    private static void reverseArray(int[] array) {
        int c;
        for (int i = 0; i < array.length/2; ++i) {
            c = array[i];
            array[i] = array[array.length-1-i];
            array[array.length-1-i] = c;
        }
    }

    private static int[] getReversedArray(int[] array) {
        int[] arrayReversed = new int[array.length];
        for (int i = 0; i < array.length; ++i) {
            arrayReversed[i] = array[array.length-1-i];
        }
        return arrayReversed;
    }

    private static void reverseMatrix(int[][] matrix) {
        int heightOrd = matrix.length;
        int widthOrd = matrix[0].length;
        int[][] matrixCopy = new int[widthOrd][heightOrd];
        for (int i = 0; i < heightOrd; ++i) {
            for (int j = 0; j < widthOrd; ++j) {
                matrixCopy[i][j] = matrix[i][j];
            }
        }
        for (int i = 0; i < widthOrd; ++i) {
            for (int j = 0; j < heightOrd; ++j) {
                matrix[i][j] = matrixCopy[j][i];
            }
        }
    }

    private static int[][] getReversedMatrix(int[][] matrix) {
        int heightOrd = matrix.length;
        int widthOrd = matrix[0].length;
        int[][] matrixReversed = new int[widthOrd][heightOrd];
        for (int i = 0; i < widthOrd; ++i) {
            for (int j = 0; j < heightOrd; ++j) {
                matrixReversed[i][j] = matrix[j][i];
            }
        }
        return matrixReversed;
    }

    public static boolean isArrayMoveLeftPossible(int[] array) {
        int[] arrayReversed = getReversedArray(array);
        return isArrayMoveRightPossible(arrayReversed);
    }
    private static boolean isMatrixMoveRightPossible(int[][] matrix) {
        int height = matrix.length;;
        int width = matrix[0].length;
        for (int i = 0; i < height; ++i) {
            if (isArrayMoveRightPossible(matrix[i]))
                return true;
        }
        return false;
    }
    private static boolean isMatrixMoveLeftPossible(int[][] matrix) {
        int height = matrix.length;;
        int width = matrix[0].length;
        for (int i = 0; i < height; ++i) {
            if (isArrayMoveLeftPossible(matrix[i]))
                return true;
        }
        return false;
    }
    private static boolean isMatrixMoveDownPossible(int[][] matrix) {
        return isMatrixMoveRightPossible(getReversedMatrix(matrix));
    }
    private static boolean isMatrixMoveUpPossible(int[][] matrix) {
        return isMatrixMoveLeftPossible(getReversedMatrix(matrix));
    }
    public static boolean isMovePossible(Move move, int[][] matrix){
        return switch (move) {
            case UP -> isMatrixMoveUpPossible(matrix);
            case DOWN -> isMatrixMoveDownPossible(matrix);
            case RIGHT -> isMatrixMoveRightPossible(matrix);
            case LEFT -> isMatrixMoveLeftPossible(matrix);
        };
    }
    public static boolean isAnyMovePossible(int[][] matrix) {
        for (int i = 0; i < 4; ++i) {
            if (isMovePossible(Move.valueOf(i), matrix))
                return true;
        }
        return false;
    }

    private static void shiftArrayRight(int[] array, int indexTo) {
        int length = array.length;
        if (indexTo>length)
            indexTo = length;
        for (int i = indexTo-1; i > 0; --i) {
            array[i] = array[i-1];
        }
        array[0] = 0;
    }
    private static void shiftArrayLeft(int[] array, int indexFrom) {
        int length = array.length;
        if (indexFrom<0)
            indexFrom = 0;
        for (int i = indexFrom; i < length - 1; ++i) {
            array[i] = array[i+1];
        }
    }

    public static int moveArrayLeft (int[] array) {
        int score = 0;
        int[] arrayReversed = getReversedArray(array);
        score = moveArrayRight(arrayReversed);
        reverseArray(arrayReversed);
        for (int i = 0; i < array.length; ++i) {
            array[i] = arrayReversed[i];
        }
        return score;
    }
    public static int moveArrayRight (int[] array) {
        int score = 0;
        int length = array.length;
        if (!isArrayMoveRightPossible(array))
            return 0;
        for (int i = 1; i < length; ++i) {
            if (array[i] == 0) {
                shiftArrayRight(array, i+1);
            }
        }
        for (int i = length - 1; i > 0; --i) {
            if (array[i] != 0 && (array[i] == array[i-1])) {
                score += array[i]*=2;
                shiftArrayRight(array, i);
            }
        }
        return score;
    }

    private static int moveMatrixRight (int[][] matrix) {
        int score = 0;
        for (int i = 0; i < matrix.length; ++i) {
            score += moveArrayRight(matrix[i]);
        }
        return score;
    }
    private static int moveMatrixLeft (int[][] matrix) {
        int score = 0;
        for (int i = 0; i < matrix.length; ++i) {
            score += moveArrayLeft(matrix[i]);
        }
        return score;
    }
    private static int moveMatrixDown (int[][] matrix) {
        int score;
        reverseMatrix(matrix);
        score = moveMatrixRight(matrix);
        reverseMatrix(matrix);
        return  score;
    }
    private static int moveMatrixUp(int[][] matrix) {
        int score;
        reverseMatrix(matrix);
        score = moveMatrixLeft(matrix);
        reverseMatrix(matrix);
        return  score;
    }

    public static int moveMatrix (int[][] matrix, Move move) {
        return switch (move) {
            case UP -> moveMatrixUp(matrix);
            case RIGHT -> moveMatrixRight(matrix);
            case DOWN -> moveMatrixDown(matrix);
            case LEFT -> moveMatrixLeft(matrix);
        };
    }

    public static boolean isMatrixEmpty (int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (matrix[i][j] != 0)
                    return false;
            }
        }
        return true;
    }

    public static  boolean generateNumInMatrix(int[][] matrix) {
        int height = matrix.length;
        int width = matrix[0].length;
        int emptyCount = 0;
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (matrix[i][j] == 0)
                    emptyCount++;
            }
        }
        if (emptyCount == 0)
            return false;
        int num = 2 * (getRandomInt(10)/10+1);
        int numIndex = getRandomInt(emptyCount);
        for (int i = 0; i < height; ++i) {
            for (int j = 0; j < width; ++j) {
                if (matrix[i][j] == 0) {
                    if (numIndex == 0) {
                        matrix[i][j] = num;
                        return true;
                    }
                    numIndex--;
                }
            }
        }
        return false;
    }

    public static boolean isMatrixWon (int[][] matrix) {
        for (int i = 0; i < matrix.length; ++i) {
            for (int j = 0; j < matrix[0].length; ++j) {
                if (matrix[i][j] >= 2048)
                    return true;
            }
        }
        return false;
    }
}
