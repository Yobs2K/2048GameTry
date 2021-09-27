package org.example.Logic.Assets;

public enum Move {
    UP ,
    RIGHT ,
    DOWN ,
    LEFT ;

    public static Move valueOf(int i) {
        return switch (i) {
            case 0 -> UP;
            case 1 -> RIGHT;
            case 2 -> DOWN;
            case 3 -> LEFT;
            default -> null;
        };
    }
}
