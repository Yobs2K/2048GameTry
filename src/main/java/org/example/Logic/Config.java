package org.example.Logic;

class Config {
    private int width;
    private int heigth;
    private int undoCounts;

    private static Config instance;

    private Config(int width, int heigth, int undoCounts) {
        this.width = width;
        this.heigth = heigth;
        this.undoCounts = undoCounts;
    }

    static Config getInstance(int width, int heigth, int undoCounts) {
        if (instance == null)
            instance = new Config(width, heigth, undoCounts);
        return instance;
    }

    static Config getInstance() {
        if (instance == null)
            instance = new Config(4, 4, 1);
        return instance;
    }

    void updateConfig(int heigth, int width, int undoCounts) {
        this.width = width;
        this.heigth = heigth;
        this.undoCounts = undoCounts;
    }

    int getWidth() {
        return width;
    }
    int getHeigth() {
        return heigth;
    }
    boolean isUndoAllowed() {
        return (undoCounts>0);
    }
    int getUndoCounts() {
        if (undoCounts<0)
            return 0;
        else return undoCounts;
    }
}
