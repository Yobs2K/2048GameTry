package Logic;

import Logic.Assets.Move;

public class GameController {
    private Config config;
    private GameState gameState;
    private StatesHistory history;
    private boolean isContinuing;

    private static GameController instance;

    private GameController(Config config) {
        this.config = config;  //Доделать синглтон
        gameState = new GameState(config);
        history = new StatesHistory(config);
        isContinuing = true;
    }

    public static GameController getInstance(Config config) {
        if (instance == null) {
            instance = new GameController(config);
        }
        return instance;
    }

    public static GameController getInstance() {
        if (instance == null) {
            Config config = Config.getInstance();
            instance = new GameController(config);
        }
        return instance;
    }

    public static GameController getInstance(int height, int width, int undoCounts) {
        if (height < 2)
            return null;
        if (width < 2)
            return null;
        if (instance == null) {
            Config config = Config.getInstance(height, width, undoCounts);
            instance = new GameController(config);
        }
        return instance;
    }

    public boolean makeNewGame(int height, int width, int undoCounts) {
        if (height < 2)
            return false;
        if (width < 2)
            return false;
        this.config.updateConfig(height, width, undoCounts);
        gameState = new GameState(config);
        history = new StatesHistory(config);
        isContinuing = true;
        return true;
    }
    public void loadGame(int height, int width, int undoCounts, GameState GS, StatesHistory SH) {
        this.config.updateConfig(height, width, undoCounts);
        gameState = GS;
        history = SH;
    }

    public boolean startGame() {
        int[][] matrix = gameState.getMatrixCopy();
        if (GameLogic.isMatrixEmpty(matrix)) {
            GameLogic.generateNumInMatrix(matrix);
            gameState.updateState(matrix, 0, false);
            return true;
        }
        return false;
    }

    public boolean makeMove(Move move) {
        int[][] matrix = gameState.getMatrixCopy();
        if (GameLogic.isMovePossible(move, matrix)){
            int score = gameState.getScore();
            boolean isWon;
            score += GameLogic.moveMatrix(matrix, move);
            GameLogic.generateNumInMatrix(matrix);
            if (gameState.getIsWon())
                isWon = true;
            else isWon = GameLogic.isMatrixWon(matrix);
            if (!GameLogic.isAnyMovePossible(matrix))
                isContinuing = false;
            if (config.isUndoAllowed()){
                history.addState(gameState);
            }
            gameState = new GameState(matrix, score, isWon);
            return true;
        }
        return false;
    }

    public boolean undoMove() {
        if (!config.isUndoAllowed())
            return false;
        if (history.isEmpty())
            return false;
        gameState = history.popState();
        isContinuing = true;
        return true;
    }

    public int[][] getMatrix() {
        return gameState.getMatrixCopy();
    }
    public int getScore() {
        return gameState.getScore();
    }
    public boolean getIsContinuing() {
        return isContinuing;
    }
    public boolean getIsWon() {
        return gameState.getIsWon();
    }
    public int getHeight() {
        return config.getHeigth();
    }
    public int getWidth() {
        return config.getWidth();
    }
}
