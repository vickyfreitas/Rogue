package pt.upskill.projeto1.rogue.utils;

public enum DiagonalDirection {
    UP_LEFT(-1, -1),
    UP_RIGHT(-1, 1),
    DOWN_LEFT(1, -1),
    DOWN_RIGHT(1, 1);

    private final int x;
    private final int y;

    DiagonalDirection(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector2D asVector() {
        return new Vector2D(x, y);
    }
}
