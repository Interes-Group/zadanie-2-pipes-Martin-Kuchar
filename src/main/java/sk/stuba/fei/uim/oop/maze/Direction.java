package sk.stuba.fei.uim.oop.maze;

public enum Direction {
    UP,
    RIGHT,
    DOWN,
    LEFT;

    public Direction rotateDirection(Direction d) {
        switch (d) {
            case UP:
                d=RIGHT;
                return RIGHT;

            case RIGHT:
                d=DOWN;
                return DOWN;

            case DOWN:
                d=LEFT;
                return LEFT;

            case LEFT:
                d=UP;
                return UP;

            default:
                return null;
        }
    }
}
