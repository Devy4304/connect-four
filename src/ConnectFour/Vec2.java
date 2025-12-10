package ConnectFour;

public class Vec2 {
    public int row;
    public int column;

    public Vec2(int row, int column) {
        this.row = row;
        this.column = column;
    }

    public Vec2(Vec2 other) {
        row = other.row;
        column = other.column;
    }

    public Vec2() {
        row = 0;
        column = 0;
    }
}
