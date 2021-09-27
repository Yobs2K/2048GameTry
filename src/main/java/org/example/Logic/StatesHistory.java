package org.example.Logic;

public class StatesHistory {
    private int length;
    private GameState[] array;
    private int  height;
    private int width;
    private int index;

    StatesHistory (Config config) {
        this.length = config.getUndoCounts();
        height = config.getHeigth();
        width = config.getWidth();
        if (length != 0)
            array = new GameState[length];
        index = -1;
    }

    void addState (GameState GS) {
        index++;
        if (index >= length) {
            index--;
            for(int i = 0; i < length - 1; ++i) {
                array[i] = array[i+1];
            }
            array[index] = GS;
        }
    }
    GameState popState() {
        GameState GS;
        if (!isEmpty()) {
            GS = array[index];
            array[index] = null;
            index--;
            return GS;
        }
        else return null;
    }

    public boolean isEmpty() {
        return (index == -1);
    }


}
